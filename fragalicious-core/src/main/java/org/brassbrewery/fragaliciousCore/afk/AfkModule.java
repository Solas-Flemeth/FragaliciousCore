package org.brassbrewery.fragaliciousCore.afk;

import org.brassbrewery.fragaliciousCore.FragaliciousCore;
import org.brassbrewery.fragaliciousCore.structure.FragaliciousModule;
import org.brassbrewery.fragaliciousCore.structure.FragaliciousPlugin;

public class AfkModule extends FragaliciousModule<AfkApi> {
    private  AfkConfig afkConfig;
    public AfkModule(FragaliciousPlugin plugin) {
        super(plugin);
    }

    @Override
    protected AfkApi createAPI(boolean isEnabled) {
        return new AfkApi(this);
    }

    @Override
    public String moduleName() {
        return "AFK";
    }

    @Override
    public boolean canLaunchModule() {
        return true;
    }

    @Override
    public void preInit() {
        afkConfig = new AfkConfig(FragaliciousCore.getInstance());
    }

    @Override
    public void registerListeners() {

    }

    @Override
    public void registerServices() {

    }

    @Override
    public void registerCommands() {

    }

    @Override
    public void postInit() {

    }

    @Override
    public void onReload() {

    }
    public AfkConfig getAfkConfig(){
        return this.afkConfig;
    }
}
