package org.brassbrewery.fragaliciousCore.afk;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerAfkStateChangeEvent extends PlayerEvent {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final boolean isAfk;
    public PlayerAfkStateChangeEvent(@NotNull Player player, boolean isAfk) {
        super(player, true);
        this.isAfk = isAfk;
    }

    /**
     * Returns whether the player is currently AFK.
     * If true, the player just became afk
     * If false, the player just stopped being afk.
     * @return Whether the players new state is afk or not
     */
    public boolean isAfk(){
        return isAfk;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
