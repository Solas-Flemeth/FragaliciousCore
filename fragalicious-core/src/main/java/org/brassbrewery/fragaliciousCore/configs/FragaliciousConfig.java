package org.brassbrewery.fragaliciousCore.configs;

import org.brassbrewery.fragaliciousCore.configs.objects.ConfigObject;
import org.brassbrewery.fragaliciousCore.structure.FragaliciousLogger;
import org.brassbrewery.fragaliciousCore.structure.FragaliciousPlugin;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;

public abstract class FragaliciousConfig {
    private FragaliciousPlugin plugin;
    private Path directoryPath;
    private Path configPath;
    private HoconConfigurationLoader loader;
    private HashMap<String, ConfigObject> configMap;
    private CommentedConfigurationNode configNode;
    private FragaliciousLogger fragaliciousLogger;
    public FragaliciousConfig(FragaliciousPlugin plugin, Path directoryPath, Path configPath) {
        this.plugin = plugin;
        this.directoryPath = directoryPath;
        this.configPath = configPath;
        fragaliciousLogger = new FragaliciousLogger(plugin, "Config");
        configMap = new HashMap<String, ConfigObject>();
        this.loader = HoconConfigurationLoader.builder()
                .path(configPath)
                .defaultOptions(opts -> opts.shouldCopyDefaults(true))
                .build();
        loadConfig();
    }
    public FragaliciousConfig(FragaliciousPlugin plugin, String configName) {
        this(plugin, Paths.get(plugin.getDataFolder().getPath()) , Paths.get(plugin.getDataFolder().getPath() + File.separator + configName + ".conf"));
    }
    public abstract void registerAllConfigObjects();
    public void loadConfig() {
        try {
            if (!Files.exists(configPath)) {
                Files.createDirectories(directoryPath);
                Files.createFile(configPath);
                fragaliciousLogger.warning("No config of type " + configPath.getFileName().toString() + " found. Creating new one at " + configPath.toUri() + ".");
                configNode = loader.createNode();
                registerAllConfigObjects();
                populateDefaultConfig(false);
                saveConfig();
            } else{
                configNode = loader.load();
                registerAllConfigObjects();
                loadVariables();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void overrideConfig() throws IOException {
        fragaliciousLogger.warning("A newer version of the config has been detected! Overriding old config of " + configPath.toUri());
        configNode = loader.createNode();
        Path backupConfigPath = Paths.get(configPath.toString() + ".backup");
        Files.deleteIfExists(backupConfigPath);
        Files.copy(configPath, backupConfigPath, StandardCopyOption.REPLACE_EXISTING);
        fragaliciousLogger.warning("A backup of the previous config has been created at " + backupConfigPath.toUri());
        Files.delete(configPath);
        Files.createFile(configPath);
        populateDefaultConfig(true);
        saveConfig();
    }
    public void reloadConfig(){
        loadConfig();
    }
    private void loadVariables() throws SerializationException {
        boolean needsOverrode = false;
        for (ConfigObject configObject : configMap.values()){
            try{
                if(!configObject.getConfigurationNode(configNode).isNull()){
                    configObject.readValueFromConfig(configNode);
                }else{ //override the value when null to prevent it from defaulting to type default
                    needsOverrode = true;
                    configObject.setValue(configObject.getDefaultValue());
                }
            }catch (NullPointerException e){
                needsOverrode = true;
            }
        }
        if(needsOverrode){
            try {
                overrideConfig();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * This method will be called when the config file does not exist and needs to be created.
     */
    public void saveConfig() {
        try {
            loader.save(configNode);
        } catch (ConfigurateException e) {
            throw new RuntimeException(e);
        }
    }

    public void populateDefaultConfig(boolean isOverride) throws SerializationException {
        for (ConfigObject config : configMap.values()) {
            if(isOverride){
                config.writeValueToConfig(getConfigNode());
            }else{
                config.writeDefaultValueToConfig(getConfigNode());
            }
        }
    }
    public CommentedConfigurationNode getConfigNode() {
        return configNode;
    }

    public void registerConfigObject(ConfigObject configObject){
        configMap.put(configObject.getPath(), configObject);
    }
    public String getDirectoryPath(){
        return configPath.toAbsolutePath().toString();
    }
}
