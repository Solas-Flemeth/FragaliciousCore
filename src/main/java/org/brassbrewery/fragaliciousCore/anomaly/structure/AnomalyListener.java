package org.brassbrewery.fragaliciousCore.anomaly.structure;

import org.brassbrewery.fragaliciousCore.anomaly.event.AnomalyEndEvent;
import org.brassbrewery.fragaliciousCore.anomaly.event.AnomalyJoinEvent;
import org.brassbrewery.fragaliciousCore.time.events.NewDayEvent;
import org.bukkit.event.Listener;

public interface AnomalyListener extends Listener {

    public void onJoin(AnomalyJoinEvent anomalyJoinEvent);
    public void onLeave(AnomalyJoinEvent anomalyJoinEvent);
    public void onEnd(AnomalyEndEvent anomalyEndEvent);
    public void onNewDay(NewDayEvent newDayEvent);
}
