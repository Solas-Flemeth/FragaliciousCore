package org.brassbrewery.fragaliciousCore.combat.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PvpHitEvent extends Event implements Cancellable {
    private final Player attacker;
    private final Player target;
    private boolean isCancelled = false;
    private double damageDealt;
    private static final HandlerList handlers = new HandlerList();
    public PvpHitEvent(Player attacker, Player target, double damageDealt) {
        this.attacker = attacker;
        this.target = target;
        this.damageDealt = damageDealt;
    }
    public Player getAttacker(){return attacker;}
    public Player getTarget(){return target;}
    public double getDamageDealt(){return damageDealt;}

    public void setDamageDealt(float damage){
        if(damage < 0.0){
            damageDealt = damage;
        }
    }
    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        isCancelled = cancel;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}
