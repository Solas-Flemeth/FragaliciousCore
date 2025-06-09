package org.brassbrewery.fragaliciousLib.combat.service;

import org.brassbrewery.fragaliciousLib.combat.CombatModule;
import org.brassbrewery.fragaliciousLib.combat.events.PlayerEndPvpEvent;
import org.brassbrewery.fragaliciousLib.combat.events.PlayerStartPvpEvent;
import org.brassbrewery.fragaliciousLib.combat.events.PvpHitEvent;
import org.brassbrewery.fragaliciousLib.combat.events.PvpHitResultEvent;
import org.brassbrewery.fragaliciousLib.combat.events.PvpEndReason;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PvpCheckerService implements Listener {
    private ConcurrentHashMap<UUID, Integer> inCombatPlayers;
    private CombatModule module;
    public PvpCheckerService(CombatModule module) {
        this.module = module;
        this.inCombatPlayers = new ConcurrentHashMap<>();
        startCombatTick();
    }
    private void startCombatTick() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(module.getPlugin(), () -> {
            Iterator<Entry<UUID, Integer>> iterator = inCombatPlayers.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<UUID, Integer> entry = iterator.next();
                int timeLeft = entry.getValue() - 1;
                if (timeLeft <= 0) {
                    UUID uuid = entry.getKey();
                    iterator.remove(); // Safe removal
                    Player player = getPlayer(uuid);
                    if (player != null && player.isOnline()) {
                        Bukkit.getPluginManager().callEvent(new PlayerEndPvpEvent(player, PvpEndReason.TIME));
                    }
                } else {
                    entry.setValue(timeLeft);
                }
            }
        }, 20L, 20L); // 1 second tick
    }

    private void addPlayer(Player victim, Player attacker, double damage) {
        UUID uuid = victim.getUniqueId();
        boolean isNew = inCombatPlayers.put(uuid, module.getConfig().getCombatTimer()) == null;

        Bukkit.getScheduler().runTaskAsynchronously(module.getPlugin(), () -> {
            if (isNew) {
                Bukkit.getPluginManager().callEvent(new PlayerStartPvpEvent(victim));
            }
            Bukkit.getPluginManager().callEvent(new PvpHitResultEvent(victim, attacker, damage));
        });
    }

    private void removePlayer(Player player, PvpEndReason reason){
        inCombatPlayers.remove(player.getUniqueId());
        Bukkit.getScheduler().runTaskAsynchronously(module.getPlugin(), () -> {
            Bukkit.getPluginManager().callEvent(new PlayerEndPvpEvent(player, reason));
        });
    }

    private void removePlayer(UUID uuid, PvpEndReason reason){
        removePlayer(getPlayer(uuid), reason);
    }


    //API calls
    public boolean isInCombat(UUID uuid){
        return inCombatPlayers.containsKey(uuid);
    }
    public boolean isInCombat(Player player){
        return isInCombat(player.getUniqueId());
    }

    public Integer getTimeTillOutOfCombat(Player player){
        return getTimeTillOutOfCombat(player.getUniqueId());
    }

    public Integer getTimeTillOutOfCombat(UUID uuid){
        Integer timeRemaining = inCombatPlayers.get(uuid);
        if(timeRemaining == null){
            timeRemaining = 0;
        }
        return timeRemaining;
    }
    //Event handlers

    @EventHandler(ignoreCancelled = true, priority= EventPriority.NORMAL)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player victim)) return;
        Player attacker = getPlayerAttacker(event);
        if (attacker != null) {
            PvpHitEvent pvpHitEvent = new PvpHitEvent(attacker, victim, event.getDamage());
            pvpHitEvent.callEvent();
            //event logic changes to easily modify on pvp events
            if(pvpHitEvent.isCancelled()){
                event.setCancelled(true);
            }else if(event.getDamage() != pvpHitEvent.getDamageDealt()){
                event.setDamage(pvpHitEvent.getDamageDealt());
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority= EventPriority.MONITOR)
    protected void onMonitorEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player victim)) return;
        Player attacker = getPlayerAttacker(event);
        if (attacker != null) {
            addPlayer(victim, attacker, event.getDamage());
        }
    }
    @EventHandler(ignoreCancelled = true, priority= EventPriority.MONITOR)
    protected void onPlayerDeath(PlayerDeathEvent event){
        if(isInCombat(event.getPlayer())){
            removePlayer(event.getPlayer(), PvpEndReason.DEATH);
        }
    }
    @EventHandler (priority= EventPriority.MONITOR)
    protected void onDisconnect(PlayerQuitEvent event){
        if(isInCombat(event.getPlayer())){
            removePlayer(event.getPlayer(), PvpEndReason.DISCONNECT);
        }
    }
    // UTILITY

    /**
     * Returns the attacker from an entity damage event.
     *
     * @param event The entity damage event.
     * @return The attacker player, or null if no valid attacker was found.
     */
    private @Nullable Player getPlayerAttacker(EntityDamageByEntityEvent event){
        Player attacker = null;
        Entity damager = event.getDamager();
        if (damager instanceof Player) {
            attacker = (Player) damager;
        } else if (damager instanceof Projectile projectile) {
            if (projectile.getShooter() instanceof Player shooter) {
                attacker = shooter;
            }
        }
        return attacker;
    }

    /**
     * Retrieves a player by their UUID. This method returns null if the player does not exist.
     *
     * @param uuid The UUID of the player to retrieve.
     * @return The player with the specified UUID, or null if the player does not exist.
     */
    private Player getPlayer(UUID uuid){
        return Bukkit.getServer().getPlayer(uuid);
    }
}

