package org.brassbrewery.fragaliciousLib.structure;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class FragaliciousPlugin extends JavaPlugin {
    private FragaliciousPlugin INSTANCE;
    private FragaliciousLogger logger;
    public void onEnable() {
        INSTANCE = this;
        logger = new FragaliciousLogger(INSTANCE, "Core");
        logger.fine("Starting Initialization of Plugin");
        // Plugin startup logic
        onPreEnable();
        logger.fine("Registering Modules");
        registerModules();
        logger.fine("Registering Commands");
        registerCommands();
        onPostEnable();
        logger.fine("Plugin Initialized");
    }


    public abstract void onPreEnable();

    public abstract void onPostEnable();

    public abstract FragaliciousPlugin getGenericInstance();

    public abstract void reload();

    public abstract void registerModules();

    public abstract void registerCommands();

    public void log(String log){
        logger.fine(log);
    }
    public void logWarning(String warn){
        logger.warning(warn);
    }
    public void logError(String error){
        logger.error(error);
    }

}
