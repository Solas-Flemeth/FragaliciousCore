package org.brassbrewery.fragaliciousCore.time.enums;

import org.bukkit.World;

public enum DayState {
    SUNRISE(23000),
    DAY(0),
    SUNSET(12000),
    NIGHT(13000);
    private final int time;

    DayState(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }
    public static DayState getCurrentTime(World world){
        long currentTick = world.getTime() % 24000;
        if(currentTick >= 23000){
            return DayState.SUNRISE;
        }else if( currentTick >= 13000){
            return DayState.NIGHT;
        }else if(currentTick >= 12000){
            return DayState.SUNSET;
        }else{
            return DayState.DAY;
        }
    }
    public static long TimeTillNewDay(World world){
        return 24000 -  (world.getTime() % 24000);
    }
}
