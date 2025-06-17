package org.brassbrewery.fragaliciousCombat.protection;

import org.brassbrewery.fragaliciousCombat.FragaliciousCombat;
import org.brassbrewery.fragaliciousCombat.commands.CombatReloadCommand;
import org.brassbrewery.fragaliciousCombat.commands.PvpStatusCommand;
import org.brassbrewery.fragaliciousCombat.commands.TogglePvpCommand;
import org.brassbrewery.fragaliciousCombat.protection.config.ProtectionConfig;
import org.brassbrewery.fragaliciousCore.structure.FragaliciousModule;

public class ProtectionModule extends FragaliciousModule<ProtectionAPI> {
    private ProtectionConfig protectionConfig;
    private ProtectionService protectionService;
    private ProtectionUpkeepService protectionUpkeepService;
    public ProtectionModule(FragaliciousCombat plugin) {
        super(plugin);

    }

    @Override
    protected ProtectionAPI createAPI(boolean isEnabled) {
        return new ProtectionAPI(this);
    }

    @Override
    public String moduleName() {
        return "Protection";
    }

    @Override
    public boolean canLaunchModule() {
        return true;
    }

    @Override
    public void preInit() {
        protectionConfig = new ProtectionConfig();
    }

    @Override
    public void registerListeners() {
        registerListener(protectionService);
        registerListener(protectionUpkeepService);
    }

    @Override
    public void registerServices() {
        protectionService = new ProtectionService(this);
        protectionUpkeepService = new ProtectionUpkeepService(this);
    }

    @Override
    public void registerCommands() {
        registerCommand("togglepvp", new TogglePvpCommand());
        registerCommand("combatreload", new CombatReloadCommand());
        registerCommand("pvpstatus", new PvpStatusCommand());
    }

    @Override
    public void postInit() {

    }

    @Override
    public void onReload() {
        protectionConfig.reloadConfig();
    }
    public ProtectionConfig getConfig(){
        return protectionConfig;
    }
    protected ProtectionService getProtectionService(){return protectionService;}
    protected ProtectionUpkeepService getProtectionUpkeepService(){
        return protectionUpkeepService;
    }
}
