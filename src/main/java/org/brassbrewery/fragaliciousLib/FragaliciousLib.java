package org.brassbrewery.fragaliciousLib;

import org.brassbrewery.fragaliciousLib.combat.CombatAPI;
import org.brassbrewery.fragaliciousLib.combat.CombatModule;
import org.brassbrewery.fragaliciousLib.commands.ReloadPluginCommand;
import org.brassbrewery.fragaliciousLib.exceptions.ModuleNotLoadedException;
import org.brassbrewery.fragaliciousLib.time.TimeAPI;
import org.brassbrewery.fragaliciousLib.time.TimeModule;
import org.brassbrewery.fragaliciousLib.structure.FragaliciousLogger;
import org.brassbrewery.fragaliciousLib.structure.FragaliciousPlugin;


public class FragaliciousLib extends FragaliciousPlugin {
    private static FragaliciousLib INSTANCE;
    private static TimeModule timeModule;
    //private static AnomalyModule anomalyModule;
    private static CombatModule combatModule;
    private static FragaliciousLogger logger;
    @Override
    public void onEnable() {
        INSTANCE = this;
        logger = new FragaliciousLogger(INSTANCE, "Core");
        logger.fine("Starting Initialization of Plugin");
        // Plugin startup logic
        registerModules();
        registerCommands();
        logger.fine("Plugin Initialized");
    }

    @Override
    public void onPreEnable() {

    }

    @Override
    public void onPostEnable() {

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static FragaliciousLib getInstance(){
        return INSTANCE;
    }

    @Override
    public FragaliciousPlugin getGenericInstance() {
        return INSTANCE;
    }

    @Override
    public void reload() {
        onReload();
    }

    @Override
    public void registerModules() {
        timeModule = new TimeModule();
        //anomalyModule = new AnomalyModule(getInstance());
        combatModule = new CombatModule();
    }
    public static void onReload(){
        logger.fine("Reloading Plugin");
        timeModule.onReload();
        combatModule.onReload();
        //anomalyModule.onreload();
        logger.fine("Plugin Reloaded");
    }

    /**
     * Grants access to day API consumption
     * @return
     */
    public static TimeAPI timeAPI() throws ModuleNotLoadedException {
        return timeModule.getAPI();
    }
    public static CombatAPI combatAPI() throws ModuleNotLoadedException{
        return combatModule.getAPI();
    }
    public void registerCommands(){
        this.getCommand("reloadfraglib").setExecutor(new ReloadPluginCommand());
    }


}
