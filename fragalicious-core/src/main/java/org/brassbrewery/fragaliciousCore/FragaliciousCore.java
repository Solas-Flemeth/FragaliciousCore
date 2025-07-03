package org.brassbrewery.fragaliciousCore;

import org.brassbrewery.fragaliciousCore.afk.AfkApi;
import org.brassbrewery.fragaliciousCore.afk.AfkModule;
import org.brassbrewery.fragaliciousCore.combat.CombatAPI;
import org.brassbrewery.fragaliciousCore.combat.CombatModule;
import org.brassbrewery.fragaliciousCore.commands.ReloadPluginCommand;
import org.brassbrewery.fragaliciousCore.economy.BasicEconomyAPI;
import org.brassbrewery.fragaliciousCore.economy.EconomyModule;
import org.brassbrewery.fragaliciousCore.exceptions.ModuleNotLoadedException;
import org.brassbrewery.fragaliciousCore.time.TimeAPI;
import org.brassbrewery.fragaliciousCore.time.TimeModule;
import org.brassbrewery.fragaliciousCore.structure.FragaliciousLogger;
import org.brassbrewery.fragaliciousCore.structure.FragaliciousPlugin;


public class FragaliciousCore extends FragaliciousPlugin {
    private static FragaliciousCore INSTANCE;
    private static TimeModule timeModule;
    //private static AnomalyModule anomalyModule;
    private static CombatModule combatModule;
    private static FragaliciousLogger logger;
    private static EconomyModule economyModule;
    private  static AfkModule afkModule;
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
    public static FragaliciousCore getInstance(){
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
        afkModule = new AfkModule(this);
        economyModule = new EconomyModule();
        //anomalyModule = new AnomalyModule(getInstance());
        combatModule = new CombatModule();

    }
    public static void onReload(){
        logger.fine("Reloading Plugin");
        timeModule.onReload();
        combatModule.onReload();
        economyModule.onReload();
        //anomalyModule.onreload();
        afkModule.onReload();
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
    public static BasicEconomyAPI economyAPI() throws ModuleNotLoadedException{
        return economyModule.getAPI();
    }
    public void registerCommands(){
        this.getCommand("reloadfraglib").setExecutor(new ReloadPluginCommand());
    }


    public AfkApi getAfkApi() throws ModuleNotLoadedException {
        return afkModule.getAPI();
    }
}
