package org.brassbrewery.fragaliciousEconomics.mcmmo;

import org.brassbrewery.fragaliciousCore.structure.FragaliciousModule;
import org.brassbrewery.fragaliciousEconomics.FragaliciousEconomics;
import org.brassbrewery.fragaliciousEconomics.mcmmo.service.LevelingIncomeService;
import org.brassbrewery.fragaliciousEconomics.mcmmo.service.SkillingIncomeService;

public class McMMOEcoModule extends FragaliciousModule<McMMOEcoAPI> {
    private McMMOEcoConfig mcMMOEcoConfig;
    private LevelingIncomeService levelingIncomeService;
    private SkillingIncomeService skillingIncomeService;
    public McMMOEcoModule(FragaliciousEconomics plugin) {
        super(plugin);
    }

    @Override
    protected McMMOEcoAPI createAPI(boolean isEnabled) {
        return new McMMOEcoAPI(this);
    }

    @Override
    public String moduleName() {
        return "McmmoEco";
    }

    @Override
    public boolean canLaunchModule() {
        return isPluginEnabled("mcMMO");
    }

    @Override
    public void preInit() {

    }

    @Override
    public void registerListeners() {
        registerListener(skillingIncomeService);
        registerListener(levelingIncomeService);
    }

    @Override
    public void registerServices() {
        skillingIncomeService = new SkillingIncomeService(this);
        levelingIncomeService = new LevelingIncomeService(this);
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

    public McMMOEcoConfig getConfig() {
        return mcMMOEcoConfig;
    }
}
