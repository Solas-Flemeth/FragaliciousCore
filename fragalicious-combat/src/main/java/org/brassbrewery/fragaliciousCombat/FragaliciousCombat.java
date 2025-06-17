package org.brassbrewery.fragaliciousCombat;

import org.brassbrewery.fragaliciousCombat.database.CombatDatabaseManager;
import org.brassbrewery.fragaliciousCombat.protection.ProtectionAPI;
import org.brassbrewery.fragaliciousCombat.protection.ProtectionModule;
import org.brassbrewery.fragaliciousCombat.protection.data.ProtectedPlayer;
import org.brassbrewery.fragaliciousCombat.towny.FilterTownyAPI;
import org.brassbrewery.fragaliciousCombat.towny.TownyModule;
import org.brassbrewery.fragaliciousCore.exceptions.ModuleNotLoadedException;
import org.brassbrewery.fragaliciousCore.structure.FragaliciousPlugin;

import java.util.UUID;

public class FragaliciousCombat extends FragaliciousPlugin {
    private static FragaliciousCombat instance;
    private ProtectionModule protectionModule;
    private TownyModule townyModule;
    private CombatDatabaseManager combatDatabaseManager;
    public FragaliciousCombat(){
        instance = this;
    }

    public static FragaliciousCombat getInstance() {
        return instance;
    }

    @Override
    public void onPreEnable() {
        combatDatabaseManager = new CombatDatabaseManager(this);
    }

    @Override
    public void onPostEnable() {

    }

    @Override
    public FragaliciousPlugin getGenericInstance() {
        return this;
    }

    @Override
    public void reload() {
        protectionModule.onReload();
    }

    @Override
    public void registerModules() {
        protectionModule = new ProtectionModule(this);
        townyModule = new TownyModule(this);
    }

    @Override
    public void registerCommands() {

    }
    public ProtectionAPI getProtectionAPI() throws ModuleNotLoadedException {
        return protectionModule.getAPI();
    }

    public FilterTownyAPI getTownyAPI() throws ModuleNotLoadedException{
        return townyModule.getAPI();
    }
}
