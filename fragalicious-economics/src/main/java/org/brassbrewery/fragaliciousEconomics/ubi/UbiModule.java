package org.brassbrewery.fragaliciousEconomics.ubi;

import org.brassbrewery.fragaliciousCore.structure.FragaliciousModule;
import org.brassbrewery.fragaliciousEconomics.FragaliciousEconomics;

public class UbiModule extends FragaliciousModule<UbiAPI> {
    public UbiModule(FragaliciousEconomics plugin) {
        super(plugin);
    }

    @Override
    protected UbiAPI createAPI(boolean isEnabled) {
        return new UbiAPI(this);
    }

    @Override
    public String moduleName() {
        return "UBI";
    }

    @Override
    public boolean canLaunchModule() {
        return true;
    }

    @Override
    public void preInit() {

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
}
