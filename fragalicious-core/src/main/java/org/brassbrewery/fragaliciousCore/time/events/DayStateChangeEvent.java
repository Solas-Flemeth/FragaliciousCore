package org.brassbrewery.fragaliciousCore.time.events;

import org.brassbrewery.fragaliciousCore.time.enums.DayState;
import org.brassbrewery.fragaliciousCore.time.enums.MoonState;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class DayStateChangeEvent extends Event {
    private final DayState currentState;
    private final DayState previousState;
    private final MoonState moonState;
    private static final HandlerList HANDLER_LIST = new HandlerList();

    /**
     * Used for when time changes.
     * @param currentState
     * @param previousState
     */
    public DayStateChangeEvent(DayState currentState, DayState previousState, MoonState moonState) {
        super(true);
        this.currentState = currentState;
        this.previousState = previousState;
        this.moonState = moonState;

    }

    /**
     * Used for when a server just rebooted
     * @param serverStartState
     */
    public DayStateChangeEvent(DayState serverStartState, MoonState moonState){
        super(true);
        this.currentState = serverStartState;
        this.previousState = serverStartState;
        this.moonState = moonState;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    /**
     * The current time state
     * @return the current time state
     */
    public DayState getCurrentState() {
        return currentState;
    }

    /**
     * The previous time state for the server
     * @return the previous state of time
     */
    public DayState getPreviousState() {
        return previousState;
    }

    /**
     * Checks to see if cause by server reboot by comparing current and previous state
     * @return true if caused by reboot, returns false if time change
     */
    public boolean causedByServerReboot(){
        return currentState == previousState;
    }

    /**
     * Get the current state of the moon cycle
     * @return the current Moon Cycle State
     */
    public MoonState getMoonState() {
        return moonState;
    }
}
