package org.brassbrewery.fragaliciousLib.time;

import org.brassbrewery.fragaliciousLib.structure.FragaliciousAPI;
import org.brassbrewery.fragaliciousLib.time.enums.DayState;
import org.brassbrewery.fragaliciousLib.time.enums.MoonState;

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
