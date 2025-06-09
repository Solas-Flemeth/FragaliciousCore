package org.brassbrewery.fragaliciousLib.combat.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerStartPvpEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    public PlayerStartPvpEvent(Player player){
        super(true);
        this.player = player;
    }
    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
    public Player getPlayer(){
        return player;
    }
}
