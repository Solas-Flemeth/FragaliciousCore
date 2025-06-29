package org.brassbrewery.fragaliciousCombat.towny;

import org.brassbrewery.fragaliciousCore.structure.FragaliciousModule;
import org.brassbrewery.fragaliciousCore.structure.FragaliciousPlugin;

public class TownyModule extends FragaliciousModule<FilterTownyAPI> {
    protected TownyPlayerUtility townyPlayerUtility;
    public TownyModule(FragaliciousPlugin plugin) {
        super(plugin);
    }

    @Override
    protected FilterTownyAPI createAPI(boolean isEnabled) {
        return new FilterTownyAPI(this);
    }

    @Override
    public String moduleName() {
        return "Integration:Towny";
    }

    @Override
    public boolean canLaunchModule() {
        boolean isTownyEnabled = this.isPluginEnabled("Towny");
        if(!isTownyEnabled){
            warn("This module requires the Towny plugin to be enabled.");
        }
        return isTownyEnabled;
    }

    @Override
    public void preInit() {

    }

    @Override
    public void registerListeners() {
        registerListener(townyPlayerUtility);
    }

    @Override
    public void registerServices() {
        townyPlayerUtility = new TownyPlayerUtility(this);
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
