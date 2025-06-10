package org.brassbrewery.fragaliciousCore.combat.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a player ends PvP with another player.
 *
 * This event will only be called if the player was previously in combat and is now no longer in combat.
 *
 *
 *
 * @author BrassBrewery
 */
public class PlayerEndPvpEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final PvpEndReason endReason;
    public PlayerEndPvpEvent(Player player, PvpEndReason endReason){
        super(true);
        this.player = player;
        this.endReason = endReason;
    }
    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
    public Player getPlayer(){
        return player;
    }
    public PvpEndReason getPvpEndReason(){
        return endReason;
    }

}
