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
import org.brassbrewery.fragaliciousCore.structure.FragaliciousPlugin;

public class FragaliciousCore extends FragaliciousPlugin {
    private static FragaliciousCore INSTANCE;
    private static TimeModule timeModule;
    private static CombatModule combatModule;
    private static EconomyModule economyModule;
    private  static AfkModule afkModule;

    @Override
    public void onPreEnable() {
        INSTANCE = this;
        // Plugin startup logic
        registerModules();
        registerCommands();
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
        combatModule = new CombatModule();

    }
    public void onReload(){
        log("Reloading Plugin");
        timeModule.onReload();
        combatModule.onReload();
        economyModule.onReload();
        afkModule.onReload();
        log("Plugin Reloaded");
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
