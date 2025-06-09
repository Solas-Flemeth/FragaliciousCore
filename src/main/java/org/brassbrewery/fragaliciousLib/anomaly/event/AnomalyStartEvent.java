package org.brassbrewery.fragaliciousLib.anomaly.event;

import org.brassbrewery.fragaliciousLib.anomaly.structure.ActiveAnomaly;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AnomalyStartEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final ActiveAnomaly anomaly;

    public AnomalyStartEvent(ActiveAnomaly anomaly) {
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


