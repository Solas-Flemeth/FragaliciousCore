package org.brassbrewery.fragaliciousCore.combat.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PvpHitResultEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Player attacker;
    private final Player target;
    private final double damageDealt;

    public PvpHitResultEvent(Player attacker, Player target, double damageDealt) {
        super(true);
        this.attacker = attacker;
        this.target = target;
        this.damageDealt = damageDealt;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public Player getAttacker(){return attacker;}
    public Player getTarget(){return target;}
    public double getDamageDealt(){return damageDealt;}

}
