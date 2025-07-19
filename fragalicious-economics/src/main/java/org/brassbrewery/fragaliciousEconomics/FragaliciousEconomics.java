package org.brassbrewery.fragaliciousEconomics;

import org.brassbrewery.fragaliciousCore.exceptions.ModuleNotLoadedException;
import org.brassbrewery.fragaliciousCore.structure.FragaliciousPlugin;
import org.brassbrewery.fragaliciousEconomics.ecoflow.EcoFlowApi;
import org.brassbrewery.fragaliciousEconomics.ecoflow.EcoFlowModule;
import org.brassbrewery.fragaliciousEconomics.towny.FilterTownyAPI;
import org.brassbrewery.fragaliciousEconomics.towny.TownyModule;

public class FragaliciousEconomics extends FragaliciousPlugin {
    private EcoFlowModule ecoFlowModule;
    private TownyModule townyModule;
    private static FragaliciousEconomics instance;
    public FragaliciousEconomics(){
        instance = this;
    }
    public static FragaliciousEconomics getInstance(){
        return instance;
    }

    @Override
    public void onPreEnable() {

    }

    @Override
    public void onPostEnable() {

    }

    @Override
    public FragaliciousPlugin getGenericInstance() {
        return instance;
    }

    @Override
    public void reload() {
        ecoFlowModule.onReload();
        townyModule.onReload();
    }

    @Override
    public void registerModules() {
        ecoFlowModule = new EcoFlowModule(this);
        townyModule = new TownyModule(this);
    }

    @Override
    public void registerCommands() {

    }
    public EcoFlowApi getEcoFlowApi() throws ModuleNotLoadedException {
        return ecoFlowModule.getAPI();
    }
    public FilterTownyAPI getTownyApi() throws ModuleNotLoadedException{
        return townyModule.getAPI();
    }
}
