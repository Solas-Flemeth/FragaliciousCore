package org.brassbrewery.fragaliciousLib.configs.objects;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ScopedConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.Collections;
import java.util.List;

public class StringListConfigObject extends ConfigObject<List<String>> {

    public StringListConfigObject(String path, List<String> defaultValue) {
        super(path, defaultValue);
    }

    public StringListConfigObject(String path, List<String> defaultValue, String comment) {
        super(path, defaultValue, comment);
    }

    @Override
    public void writeValueToConfig(CommentedConfigurationNode rootNode) throws SerializationException {
        CommentedConfigurationNode childNode = getConfigurationNode(rootNode);
        childNode.set(getValue());
        childNode.comment(getComment());
    }

    @Override
    public void writeDefaultValueToConfig(CommentedConfigurationNode rootNode) throws SerializationException {
        CommentedConfigurationNode childNode = getConfigurationNode(rootNode);
        childNode.set(getDefaultValue());
        childNode.comment(getComment());
    }

    @Override
    public void readValueFromConfig(CommentedConfigurationNode rootNode){
        ScopedConfigurationNode scopedConfigurationNode = getConfigurationNode(rootNode);
        try{
            setValue( scopedConfigurationNode.getList(String.class));
        } catch (SerializationException e) {
            setValue(Collections.emptyList()); //could not read list
        }
    }
}
