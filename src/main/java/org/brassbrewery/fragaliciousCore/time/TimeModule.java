package org.brassbrewery.fragaliciousCore.time;

import org.brassbrewery.fragaliciousCore.FragaliciousCore;
import org.brassbrewery.fragaliciousCore.structure.FragaliciouModule;
import org.brassbrewery.fragaliciousCore.time.service.TimeService;


public class TimeModule extends FragaliciouModule<TimeAPI> {
    private TimeService timeService;
    public TimeModule() {
        super(FragaliciousCore.getInstance());

    }

    @Override
    protected TimeAPI createAPI(boolean isEnabled) {
        return new TimeAPI(this);
    }


    @Override
    public void postInit() {

    }

    @Override
    public boolean canLaunchModule() {
        return true;
    }

    @Override
    public void preInit() {

    }


    public void onReload(){

    }

    @Override
    public String moduleName() {
        return "Time";
    }

    @Override
    public void registerListeners() {

    }

    @Override
    public void registerServices() {
        timeService = new TimeService(this);
    }

    @Override
    public void registerCommands() {

    }
    protected TimeService getTimeService(){
        return this.timeService;
    }
}
