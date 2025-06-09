package org.brassbrewery.fragaliciousLib.time;

import org.brassbrewery.fragaliciousLib.FragaliciousLib;
import org.brassbrewery.fragaliciousLib.structure.FragaliciouModule;
import org.brassbrewery.fragaliciousLib.time.service.TimeService;


public class TimeModule extends FragaliciouModule<TimeAPI> {
    private TimeService timeService;
    public TimeModule() {
        super(FragaliciousLib.getInstance());

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
