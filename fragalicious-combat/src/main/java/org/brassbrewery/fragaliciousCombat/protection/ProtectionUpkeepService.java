package org.brassbrewery.fragaliciousCombat.protection;

import org.brassbrewery.fragaliciousCombat.FragaliciousCombat;
import org.brassbrewery.fragaliciousCombat.database.DaoRegistry;
import org.brassbrewery.fragaliciousCombat.protection.data.ProtectedPlayer;
import org.brassbrewery.fragaliciousCombat.protection.data.ProtectionType;
import org.brassbrewery.fragaliciousCombat.protection.data.ProtectionUpkeepType;
import org.brassbrewery.fragaliciousCombat.protection.utility.ProtectionStringUtil;
import org.brassbrewery.fragaliciousCore.FragaliciousCore;
import org.brassbrewery.fragaliciousCore.exceptions.ModuleNotLoadedException;
import org.brassbrewery.fragaliciousCore.time.events.NewDayEvent;
import org.brassbrewery.fragaliciousCore.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.LinkedList;
import java.util.List;

public class ProtectionUpkeepService extends ProtectionStringUtil implements Listener {
    private final ProtectionModule protectionModule;

    public ProtectionUpkeepService(ProtectionModule protectionModule) {
        super(protectionModule.getConfig());
        this.protectionModule = protectionModule;
    }


    public void validateCanRunUpkeep(ProtectionUpkeepType protectionUpkeepType) {
        Bukkit.getAsyncScheduler().runNow(FragaliciousCombat.getInstance(), scheduledTask -> {
            ProtectionUpkeepType configUpkeepType = ProtectionUpkeepType.getProtectionUpkeepType(getConfig().getCombatProtectionUpkeepType());
            switch (protectionUpkeepType) {
                case NO_UPKEEP:
                    break;
                case DAILY:
                case TOWNY:
                    if (configUpkeepType.equals(protectionUpkeepType)) {
                        applyUpkeep(false);
                    }
                    break;
                case INGAME_DAY:
                    if (configUpkeepType.equals(protectionUpkeepType)) {
                        applyUpkeep(true);
                    }
                    break;
                case MANAUL:
                    applyUpkeep(false);
                    break;
            }
        });
    }


    private void applyUpkeep(boolean onlineOnly) {
        protectionModule.fine("Running Upkeep for all players with active protection");
        List<ProtectedPlayer> protectedPlayerList;
        List<ProtectedPlayer> updatedProtectedPlayerList = new LinkedList<>();
        //get playerlist;
        if(onlineOnly) {
            protectedPlayerList = protectionModule.getProtectionService().getPaidProtectedPlayers();
        }else{
            protectedPlayerList = DaoRegistry.get().protectedPlayerDao().getProtectedPlayersWithUpkeep();
        }
        //charge players
        for (ProtectedPlayer protectedPlayer : protectedPlayerList) {
            try {
                 ProtectedPlayer updateProtectedPlayer = applyUpkeepToPlayer(protectedPlayer);
                 if(updateProtectedPlayer.getProtectionType().equals(ProtectionType.NONE)){
                     updatedProtectedPlayerList.add(updateProtectedPlayer);
                 }
            } catch (ModuleNotLoadedException ignored) {}
        }
        //update online and offline players
        protectionModule.fine("A total of " + protectedPlayerList.size() + " players were processed. Of those, " + updatedProtectedPlayerList.size() + " had their protection revoked. Saving to database");
        DaoRegistry.get().protectedPlayerDao().addOrUpdateProtectedPlayer(updatedProtectedPlayerList);
        protectionModule.fine("Finished Protected Player Upkeep");
    }

    private ProtectedPlayer applyUpkeepToPlayer(ProtectedPlayer protectedPlayer) throws ModuleNotLoadedException {
        boolean canAfford = FragaliciousCore.economyAPI().canAfford(getConfig().getCombatProtectionUpkeep(), protectedPlayer.getPlayer()); //can afford purchase
        Player player = Bukkit.getPlayer(protectedPlayer.getPlayer());
        //determine if player can afford and drain if possible
        if (player != null && player.isOnline()) { //if online
            if(canAfford){
                FragaliciousCore.economyAPI().withdraw(getConfig().getCombatProtectionUpkeep(), protectedPlayer.getPlayer());
                MessageUtil.sendMessage(player, getUpkeepAppliedMessage(protectedPlayer));
            }else{
                protectedPlayer.setProtectionType(ProtectionType.NONE);
                MessageUtil.sendMessage(player,getUpkeepExpiredMessage(protectedPlayer));
            }
            protectionModule.getProtectionService().updatePlayer(protectedPlayer);
        } else {
            if(canAfford){
                FragaliciousCore.economyAPI().withdraw(getConfig().getCombatProtectionUpkeep(), protectedPlayer.getPlayer());
            }else{
                protectedPlayer.setProtectionType(ProtectionType.NONE);
                protectedPlayer.setStatusNotificationNeedsSent(true);
            }
        }
        return protectedPlayer;
    }

    // EVENTS ///
    @EventHandler
    public void onNewDay(NewDayEvent newDayEvent) {
        validateCanRunUpkeep(ProtectionUpkeepType.INGAME_DAY);
    }

}
