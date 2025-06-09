package org.brassbrewery.fragaliciousLib.time.service;

import org.brassbrewery.fragaliciousLib.FragaliciousLib;
import org.brassbrewery.fragaliciousLib.time.TimeModule;
import org.brassbrewery.fragaliciousLib.time.enums.DayState;
import org.brassbrewery.fragaliciousLib.time.enums.MoonState;
import org.brassbrewery.fragaliciousLib.time.events.DayStateChangeEvent;
import org.brassbrewery.fragaliciousLib.time.events.NewDayEvent;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class TimeService {
    private World world;
    private DayState dayState;
    private MoonState moonState;
    private TimeModule timeModule;

    public TimeService(TimeModule timeModule) {
        this.timeModule = timeModule;
        world = FragaliciousLib.getInstance().getServer().getWorlds().getFirst();
        dayState = DayState.getCurrentTime(world);
        moonState = MoonState.getMoonCycle(world);

        new BukkitRunnable() {
            @Override
            public void run() {
                checkForNewDay();
            }
        }.runTaskTimer(FragaliciousLib.getInstance(), 0, 20); // Check every second (20 ticks)
    }

    private void checkForNewDay() {
        DayState currentState = DayState.getCurrentTime(world);
        if(dayState != currentState){
            moonState = MoonState.getMoonCycle(world);
            new DayStateChangeEvent(currentState, dayState, moonState).callEvent();
            timeModule.debug("New Time State:" + dayState.name());
            dayState = currentState;
            if(dayState.equals(DayState.DAY)){
                timeModule.debug("New Day");
                new NewDayEvent().callEvent();
            }

        }
    }
    public long timeTillNextDay(){
        return DayState.TimeTillNewDay(world);
    }

    public DayState getDayState() {
        return dayState;
    }

    public MoonState getMoonState(){
        return moonState;
    }
}
