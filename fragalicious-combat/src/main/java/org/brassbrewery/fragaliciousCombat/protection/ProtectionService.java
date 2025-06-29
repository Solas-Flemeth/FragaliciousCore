package org.brassbrewery.fragaliciousCombat.protection;

import org.brassbrewery.fragaliciousCombat.FragaliciousCombat;
import org.brassbrewery.fragaliciousCombat.database.DaoRegistry;
import org.brassbrewery.fragaliciousCombat.protection.data.ProtectedPlayer;
import org.brassbrewery.fragaliciousCombat.protection.data.ProtectedPlayerMap;
import org.brassbrewery.fragaliciousCombat.protection.data.ProtectionType;
import org.brassbrewery.fragaliciousCombat.protection.utility.ProtectionStringUtil;
import org.brassbrewery.fragaliciousCore.FragaliciousCore;
import org.brassbrewery.fragaliciousCore.exceptions.ModuleNotLoadedException;
import org.brassbrewery.fragaliciousCore.exceptions.UnknownPlayerException;
import org.brassbrewery.fragaliciousCore.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.brassbrewery.fragaliciousCore.combat.events.PvpHitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.UUID;

public class ProtectionService extends ProtectionStringUtil implements Listener {
    private ProtectedPlayerMap protectedPlayerMap;
    private ProtectionModule protectionModule;
    private static final int TICK_RATE_IN_SECONDS = 5;
    public ProtectionService(ProtectionModule protectionModule)
    {
        super(protectionModule.getConfig());
        this.protectionModule = protectionModule;
        this.protectedPlayerMap = new ProtectedPlayerMap();
        new BukkitRunnable() {
            @Override
            public void run() {
               tick();
            }
        }.runTaskTimerAsynchronously(FragaliciousCore.getInstance(), 60*20, TICK_RATE_IN_SECONDS * 20);
    }

    public void toggleProtection(Player player, boolean forced) throws ModuleNotLoadedException {
        ProtectedPlayer protectedPlayer = protectedPlayerMap.getProtectedPlayer(player.getUniqueId());
        boolean canAfford = FragaliciousCore.economyAPI().canAfford(getConfig().getCombatProtectionCost(), player);
        switch (protectedPlayer.getProtectionType()) {
            case NEW_PLAYER:
            case RESPAWN:
                if(forced){
                    FragaliciousCore.economyAPI().withdraw(getConfig().getCombatProtectionCost(), player);
                    protectedPlayer.setProtectionType(ProtectionType.NONE);
                    protectedPlayer.setDuration(0);
                    MessageUtil.sendMessage(player, getTogglePvpMessage(protectedPlayer, false));
                }else{
                    MessageUtil.sendMessage(player, getAlreadyOwnsProtectionMessage(protectedPlayer));
                }
                MessageUtil.sendMessage(player, getTogglePvpMessage(protectedPlayer, false));
                break;
            case PAID:
                MessageUtil.sendMessage(player, getTogglePvpMessage(protectedPlayer, false));
                protectedPlayer.setProtectionType(ProtectionType.NONE);
            break;
            case NONE:
                if(canAfford){
                    FragaliciousCore.economyAPI().withdraw(getConfig().getCombatProtectionCost(), player);
                    protectedPlayer.setProtectionType(ProtectionType.PAID);
                    MessageUtil.sendMessage(player, getTogglePvpMessage(protectedPlayer, true));
                }else{
                    MessageUtil.sendMessage(player, getCannotAffordProtectionMessage(protectedPlayer));
                }
            break;
        }
    }

    /**
     * Registers a new player with temporary protection if it is enabled.
     * @param player
     */
    public void registerNewPlayer(Player player){
        protectionModule.fine("Registering new player: " + player.getName());
        ProtectedPlayer newProtectedPlayer;
        if(getConfig().getEnableNewPlayerProtection()){ //if new player protection enabled
            newProtectedPlayer = new ProtectedPlayer(player.getUniqueId(), ProtectionType.NEW_PLAYER, getConfig().getNewPlayerProtectionDuration());
            protectedPlayerMap.addProtectedPlayer(newProtectedPlayer);
            MessageUtil.sendMessage(player, newPlayerTempProtection(newProtectedPlayer));
        }else{
            newProtectedPlayer = new ProtectedPlayer(player.getUniqueId(), ProtectionType.NONE);
            MessageUtil.sendMessage(player, getPvpPurchaseReminder(newProtectedPlayer));
        }
        newProtectedPlayer.updateLastLogin();
        protectedPlayerMap.addProtectedPlayer(newProtectedPlayer);
    }
    /**
     * Updates the player death protection when they die and makes them keep their inventory if proper criteria are met
     * @param event
     */
    public void updateOnDeath(PlayerDeathEvent event){
        Player player =  event.getPlayer();
        UUID uuid = player.getUniqueId();
        ProtectedPlayer protectedPlayer = protectedPlayerMap.getProtectedPlayer(uuid);
        //Check to see if in a protected town for protecting their inventory
        if(isInSafeTown(player) && getConfig().getKeepInventoryInPeacefulTowns()){
            event.setKeepInventory(true);
            event.setKeepLevel(true);
            event.setDroppedExp(0);
            event.getDrops().clear();
        }
        //Validate their protection exist
        if(protectedPlayer == null){
            return; //Do nothing as we don't want to override the existing protection type.
        }
        //if protected and in wilderness with keep inv option, keep their inventory.
        else if(isInWilderness(player) && getConfig().getKeepInventoryOnDeath() && !(protectedPlayer.getProtectionType() == ProtectionType.NONE)){
            event.setKeepInventory(true);
            event.setKeepLevel(true);
            event.setDroppedExp(0);
            event.getDrops().clear();
        //if no protection but has respawn protection, add respawn protection.
        } else if(protectedPlayer.getProtectionType() == ProtectionType.NONE && getConfig().getEnableRespawnProtection()){
            protectedPlayer.setProtectionType(ProtectionType.RESPAWN);
            protectedPlayer.setDuration(getConfig().getRespawnProtectionDuration());
            protectedPlayerMap.addProtectedPlayer(protectedPlayer);
            MessageUtil.sendMessage(player, respawnTempProtection(protectedPlayer));
        }
    }

    /**
     * When a player logs in, provide the correct information to the player
     * @param protectedPlayer
     */
    public void loginReturningPlayer(ProtectedPlayer protectedPlayer){
        try{
            Player player = Bukkit.getPlayer(protectedPlayer.getPlayer());
            switch (protectedPlayer.getProtectionType()) {
                case PAID:
                    MessageUtil.sendMessage(player,pvpProtectionEnableOnLogin(protectedPlayer));
                    try {
                        if (!FragaliciousCore.economyAPI().canAfford(getConfig().getCombatProtectionUpkeep(), protectedPlayer.getPlayer())) {
                            MessageUtil.sendMessage(player, cannotAffordUpkeepReminder(protectedPlayer));
                        }
                    }
                    catch(ModuleNotLoadedException ignored){} // No need to send status if eco isnt loaded
                    break;
                case NEW_PLAYER:
                    MessageUtil.sendMessage(player, newPlayerTempProtection(protectedPlayer));
                    break;
                case RESPAWN:
                    MessageUtil.sendMessage(player, respawnTempProtection(protectedPlayer));
                    break;
                default:
                    if(protectedPlayer.getStatusNotificationNeedsSent()) {
                        MessageUtil.sendMessage(player, getUpkeepExpiredLoginMessage(protectedPlayer));
                        protectedPlayer.setStatusNotificationNeedsSent(false);
                    }
                    if(getConfig().getCombatProtectionUpkeepWarning()){
                        MessageUtil.sendMessage(player, getPvpPurchaseReminder(protectedPlayer));
                    }
                    break;
            }
        }catch(NullPointerException ignored){}
    }

    /////////////////////
    // INTERNAL ONLY  //
    ////////////////////
    private void tick() {
        List<ProtectedPlayer> protectedPlayerList = protectedPlayerMap.getList();
        for(ProtectedPlayer player : protectedPlayerList){ //remove players that are no longer needed
            ProtectionType oldProtectionType = player.tick(TICK_RATE_IN_SECONDS);
            try{
                if(oldProtectionType == null){
                }else if(oldProtectionType.equals(ProtectionType.RESPAWN)) {
                    MessageUtil.sendMessage(player.getPlayer(), respawnProtectionExpire(player));
                }else if(oldProtectionType.equals(ProtectionType.NEW_PLAYER)){
                    MessageUtil.sendMessage(player.getPlayer(), newPlayerProtectionExpire(player));
                }
            } catch (UnknownPlayerException ignored) {}
        }
    }

    ////////////////////
    //      EVENTS   //
    ///////////////////

    @EventHandler
    public void onPlayerHit(PvpHitEvent event){
        ProtectedPlayer attacker = protectedPlayerMap.getProtectedPlayer(event.getAttacker().getUniqueId());
        ProtectedPlayer target = protectedPlayerMap.getProtectedPlayer(event.getTarget().getUniqueId());
        boolean attackerHasProtection = attacker.getProtectionType() != ProtectionType.NONE;
        boolean targetHasProtection = target.getProtectionType() != ProtectionType.NONE;
        boolean isWilderness = isInWilderness(event.getTarget()) || isInWilderness(event.getAttacker());
        if(isWilderness){
            if(attackerHasProtection){
                event.setCancelled(true);
                MessageUtil.sendActionBarMessage(event.getAttacker(), cannotAttackWhileProtected(attacker));
            }else if(targetHasProtection){
                event.setCancelled(true);
                MessageUtil.sendActionBarMessage(event.getAttacker(), cannotAttackProtectedPlayer(attacker));

            }
        }
    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event){
        UUID playerId = event.getPlayer().getUniqueId();
        Bukkit.getAsyncScheduler().runNow(FragaliciousCombat.getInstance(), scheduledTask -> {
            ProtectedPlayer protectedPlayer = protectedPlayerMap.removeProtectedPlayer(playerId);
            protectedPlayer.updateLastLogin();
            DaoRegistry.get().protectedPlayerDao().addOrUpdateProtectedPlayer(protectedPlayer);
            protectedPlayerMap.saveProtectedPlayersToDatabase(protectedPlayer);
        });
    }
    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        updateOnDeath(event);
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        boolean hasPlayedBefore = event.getPlayer().hasPlayedBefore();
        Bukkit.getAsyncScheduler().runNow(FragaliciousCombat.getInstance(), scheduledTask -> {
            if(!hasPlayedBefore) { //if they have never played before
                    registerNewPlayer(player);
            } else {
                ProtectedPlayer protectedPlayer = protectedPlayerMap.loadPlayerFromDatabase(player);
                loginReturningPlayer(protectedPlayer);
            }
        });
    }

    /////////////////////
    //    utility     //
    ///////////////////
    //generics

    private boolean isInWilderness(Player player){
        try {
           return FragaliciousCombat.getInstance().getTownyAPI().isLocationInWilderness(player.getLocation());
        } catch (ModuleNotLoadedException ignored) { //town isn't loaded so everything is wilderness
            return true;
        }
    }
    private boolean isInSafeTown(Player player){
        try {
            return FragaliciousCombat.getInstance().getTownyAPI().isInSafeTown(player.getLocation());
        } catch (ModuleNotLoadedException ignored) { //town isn't loaded so everything is warzone
            return false;
        }
    }
    public void updatePlayer(ProtectedPlayer protectedPlayer){
        protectedPlayerMap.updateProtectedPlayer(protectedPlayer);
    }

    public List<ProtectedPlayer> getPaidProtectedPlayers() {
        return protectedPlayerMap.getAllPaidProtectedPlayers();
    }

    public ProtectedPlayer getPlayer(UUID uuid) {
        return protectedPlayerMap.getProtectedPlayer(uuid);
    }
    public boolean playerInProtectionList(UUID uuid){
        return protectedPlayerMap.containsPlayer(uuid);
    }
}


