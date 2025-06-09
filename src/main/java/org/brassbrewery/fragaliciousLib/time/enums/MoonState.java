package org.brassbrewery.fragaliciousLib.time.enums;

import org.bukkit.World;

public enum MoonState {
    FULL_MOON,
    WANING_GIBBOUS,
    LAST_QUARTER,
    WANING_CRESCENT,
    NEW_MOON,
    WAXING_CRESCENT,
    FIRST_QUARTER,
    WAXING_GIBBOUS;

    public static MoonState getMoonCycle(World world){
        int currentPhase = (int)  ((world.getFullTime() / 24000.0) / 8.0);
        return switch (currentPhase) {
            case 0 -> MoonState.FULL_MOON;
            case 2 -> MoonState.WANING_GIBBOUS;
            case 3 -> MoonState.LAST_QUARTER;
            case 4 -> MoonState.WANING_CRESCENT;
            case 5 -> MoonState.NEW_MOON;
            case 6 -> MoonState.WAXING_CRESCENT;
            case 7 -> MoonState.FIRST_QUARTER;
            default -> MoonState.WAXING_GIBBOUS;
        };
    }
    public static double getPercentageTillFull(MoonState moonCycle) {
        return switch (moonCycle) {
            case MoonState.FULL_MOON -> 1.0;
            case MoonState.WANING_GIBBOUS, MoonState.WAXING_GIBBOUS -> 0.75;
            case MoonState.LAST_QUARTER, MoonState.FIRST_QUARTER -> 0.5;
            case MoonState.WANING_CRESCENT, MoonState.WAXING_CRESCENT -> 0.25;
            case MoonState.NEW_MOON -> 0.0;
        };
    }
}
