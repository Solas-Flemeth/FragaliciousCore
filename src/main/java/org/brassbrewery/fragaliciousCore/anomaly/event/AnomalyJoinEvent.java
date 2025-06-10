package org.brassbrewery.fragaliciousCore.anomaly.event;

import org.brassbrewery.fragaliciousCore.anomaly.structure.ActiveAnomaly;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class AnomalyJoinEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final ActiveAnomaly anomaly;
    private final Player player;
    private boolean isCanceled;

    public AnomalyJoinEvent(ActiveAnomaly anomaly, Player player) {
        this.anomaly = anomaly;
        this.player = player;
    }

    public ActiveAnomaly getAnomaly() {
        return anomaly;
    }

    public Player getPlayer() {
        return player;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return isCanceled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        isCanceled = cancel;
    }
}

