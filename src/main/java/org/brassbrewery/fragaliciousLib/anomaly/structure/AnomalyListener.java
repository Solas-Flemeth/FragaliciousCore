package org.brassbrewery.fragaliciousLib.anomaly.structure;

import org.brassbrewery.fragaliciousLib.anomaly.event.AnomalyEndEvent;
import org.brassbrewery.fragaliciousLib.anomaly.event.AnomalyJoinEvent;
import org.brassbrewery.fragaliciousLib.time.events.NewDayEvent;
import org.bukkit.event.Listener;

public interface AnomalyListener extends Listener {

    public void onJoin(AnomalyJoinEvent anomalyJoinEvent);
    public void onLeave(AnomalyJoinEvent anomalyJoinEvent);
    public void onEnd(AnomalyEndEvent anomalyEndEvent);
    public void onNewDay(NewDayEvent newDayEvent);
}
