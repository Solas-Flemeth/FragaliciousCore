package org.brassbrewery.fragaliciousEconomics.ecoflow;

import org.brassbrewery.fragaliciousCore.structure.FragaliciousModule;
import org.brassbrewery.fragaliciousEconomics.FragaliciousEconomics;
import org.brassbrewery.fragaliciousEconomics.ecoflow.config.EcoFlowConfig;
import org.brassbrewery.fragaliciousEconomics.ecoflow.service.DeathTaxService;
import org.brassbrewery.fragaliciousEconomics.ecoflow.service.UbiService;
import org.brassbrewery.fragaliciousEconomics.ecoflow.service.WealthTaxService;

public class EcoFlowModule extends FragaliciousModule<EcoFlowApi>{
    private UbiService ubiService;
    private WealthTaxService wealthTaxService;
    private DeathTaxService deathTaxService;
    private EcoFlowConfig ecoFlowConfig;
    public EcoFlowModule(FragaliciousEconomics plugin) {
        super(plugin);
    }

    @Override
    protected EcoFlowApi createAPI(boolean isEnabled) {
        return new EcoFlowApi(this);
    }

    @Override
    public String moduleName() {
        return "EcoFlow";
    }

    @Override
    public boolean canLaunchModule() {
        return true;
    }

    @Override
    public void preInit() {
        ecoFlowConfig = new EcoFlowConfig();
    }

    @Override
    public void registerListeners() {
        registerListener(ubiService);
        registerListener(deathTaxService);
        registerListener(deathTaxService);
    }

    @Override
    public void registerServices() {
        ubiService = new UbiService(this);
        wealthTaxService = new WealthTaxService(this);
        deathTaxService = new DeathTaxService(this);
    }

    @Override
    public void registerCommands() {

    }

    @Override
    public void postInit() {

    }

    @Override
    public void onReload() {
        ecoFlowConfig.reloadConfig();
    }
    public EcoFlowConfig getConfig(){
        return ecoFlowConfig;
    }

    public UbiService getUbiService() {
        return ubiService;
    }

    public WealthTaxService getWealthTaxService() {
        return wealthTaxService;
    }

    public DeathTaxService getDeathTaxService() {
        return deathTaxService;
    }
}
