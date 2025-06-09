package org.brassbrewery.fragaliciousLib.configs.objects;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

public abstract class ConfigObject<T> extends GenericConfigObject {
    private T value;
    private T defaultValue;

    public ConfigObject(String path, T defaultValue) {
        super(path);
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }

    public ConfigObject(String path,  T defaultValue, String comment) {
        super(path, comment);
        this.value = defaultValue;
        this.defaultValue = defaultValue;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void writeValueToConfig(CommentedConfigurationNode rootNode) throws SerializationException{
        CommentedConfigurationNode childNode = getConfigurationNode(rootNode);
        childNode.set(getValue());
        childNode.comment(getComment());
    }

    public void writeDefaultValueToConfig(CommentedConfigurationNode rootNode) throws SerializationException{
        CommentedConfigurationNode childNode = getConfigurationNode(rootNode);
        childNode.set(getDefaultValue());
        childNode.comment(getComment());
    }


    public abstract void readValueFromConfig(CommentedConfigurationNode rootNode) throws SerializationException, NullPointerException;

}
