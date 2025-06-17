package org.brassbrewery.fragaliciousCore.anomaly.event;

import org.brassbrewery.fragaliciousCore.anomaly.structure.ActiveAnomaly;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AnomalyEndEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final ActiveAnomaly anomaly;

    public AnomalyEndEvent(ActiveAnomaly anomaly) {
        this.anomaly = anomaly;
    }

    public ActiveAnomaly getAnomaly() {
        return anomaly;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}

