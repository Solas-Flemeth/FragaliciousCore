package org.brassbrewery.fragaliciousCore.structure;

import org.brassbrewery.fragaliciousCore.exceptions.ModuleNotLoadedException;
import org.brassbrewery.fragaliciousCore.exceptions.ServiceNotLoadedException;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;


/**
 * Base module class for isolated plugin logic.
 * Supports lifecycle hooks and optional API exposure.
 *
 * @param <A> The type of the module's exposed API.
 */
public abstract class FragaliciouModule<A extends FragaliciousAPI> {
    private final FragaliciousLogger logger;
    private final FragaliciousPlugin plugin;
    private boolean isEnabled = false;
    private A api;

    public FragaliciouModule(FragaliciousPlugin plugin) {
        this.plugin = plugin;
        this.logger = new FragaliciousLogger(plugin, moduleName());
        enable();
        this.api = createAPI(isEnabled);
    }

    /**
     * Launches the module and initializes the API.
     * Should be called explicitly (not from constructor).
     */
    public final void enable() {
        if (!canLaunchModule()) {
            warn("Skipping Module: Launch conditions not met.");
            return;
        }
        try {
            fine("Starting module lifecycle");
            preInit();
            registerServices();
            registerListeners();
            registerCommands();
            postInit();
            isEnabled = true;
            fine("Module successfully enabled");
        } catch (Throwable t) {
            isEnabled = false;
            error("Failed to enable module: " + t.getMessage());
        }
    }

    /**
     * Returns the public API for this module.
     * Throws if accessed before enable() or if module failed.
     */
    public A getAPI() throws ModuleNotLoadedException{
        if (!isEnabled || api == null) {
            throw new ModuleNotLoadedException(moduleName());
        }
        return api;
    }

    /**
     * Called during enable() to produce the API.
     * You are expected to wrap your internal services here.
     */
    protected abstract A createAPI(boolean isEnabled);

    // === Module Lifecycle Hooks ===

    public abstract String moduleName();
    public abstract boolean canLaunchModule();
    public abstract void preInit();
    public abstract void registerListeners();
    public abstract void registerServices();
    public abstract void registerCommands();
    public abstract void postInit();

    // === Status ===
    public abstract void onReload();
    public boolean isEnabled() {
        return isEnabled;
    }

    // === Command / Listener Helpers ===

    public void registerCommand(String command, CommandExecutor executor) {
        plugin.getCommand(command).setExecutor(executor);
    }

    public void registerListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, plugin);
    }

    public boolean isPluginEnabled(String pluginName) {
        if (pluginName == null) return true;
        Plugin other = Bukkit.getPluginManager().getPlugin(pluginName);
        return other != null && other.isEnabled();
    }

    // === Logging Utilities ===

    public void fine(String log) {
        logger.fine(log);
    }

    public void warn(String warning) {
        logger.warning(warning);
    }

    public void error(String error) {
        logger.error(error);
    }

    public void debug(String debug) {
        logger.debug(debug); // Add debug logic as needed
    }

    // === Accessor ===

    public FragaliciousPlugin getPlugin() {
        return plugin;
    }
}

