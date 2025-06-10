package org.brassbrewery.fragaliciousCore.combat;

import org.brassbrewery.fragaliciousCore.FragaliciousCore;
import org.brassbrewery.fragaliciousCore.combat.config.CombatMainConfig;
import org.brassbrewery.fragaliciousCore.combat.service.PvpCheckerService;
import org.brassbrewery.fragaliciousCore.structure.FragaliciouModule;

public class CombatModule extends FragaliciouModule<CombatAPI> {
    private  CombatMainConfig config;
    private PvpCheckerService pvpCheckerService;
    public CombatModule() {
        super(FragaliciousCore.getInstance());
    }

    @Override
    public void preInit() {
        config = new CombatMainConfig(getPlugin());
    }

    @Override
    public void postInit() {

    }

    @Override
    public void onReload() {
        config.reloadConfig();
    }

    @Override
    public boolean canLaunchModule() {
        return true;
    }

    @Override
    protected CombatAPI createAPI(boolean isEnabled) {
        return new CombatAPI(this);
    }

    @Override
    public String moduleName() {
        return "Combat";
    }

    @Override
    public void registerListeners() {
        registerListener(pvpCheckerService);
    }

    @Override
    public void registerServices() {
        pvpCheckerService = new PvpCheckerService(this);
    }

    @Override
    public void registerCommands() {

    }
    public CombatMainConfig getConfig(){
        return this.config;
    }

    protected PvpCheckerService getPvpCheckerService() {
        return pvpCheckerService;
    }
}