package org.brassbrewery.fragaliciousCore.combat.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerStartPvpEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private Player player;
    public PlayerStartPvpEvent(Player player){
        super(true);
        this.player = player;
    }
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public Player getPlayer(){
        return player;
    }
}
