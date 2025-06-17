package org.brassbrewery.fragaliciousCore.time;

import org.brassbrewery.fragaliciousCore.structure.FragaliciousAPI;
import org.brassbrewery.fragaliciousCore.time.enums.DayState;
import org.brassbrewery.fragaliciousCore.time.enums.MoonState;

public class TimeAPI extends FragaliciousAPI<TimeModule> {
    public TimeAPI(TimeModule module) {
        super(module);

    }
    public long timeTillNextDay(){
        return this.getModule().getTimeService().timeTillNextDay();
    }
    public DayState getDayState() {
        return this.getModule().getTimeService().getDayState();
    }
    public MoonState getMoonState(){
        return this.getModule().getTimeService().getMoonState();
    }
}
