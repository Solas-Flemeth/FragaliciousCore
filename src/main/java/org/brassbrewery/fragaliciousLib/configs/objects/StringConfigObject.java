package org.brassbrewery.fragaliciousLib.configs.objects;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

public class StringConfigObject extends ConfigObject<String> {
    public StringConfigObject(String path, String defaultValue) {
        super(path, defaultValue);
    }

    public StringConfigObject(String path, String defaultValue,  String comment) {
        super(path, defaultValue, comment);
    }

    @Override
    public void readValueFromConfig(CommentedConfigurationNode rootNode) throws SerializationException {
        CommentedConfigurationNode childNode = getConfigurationNode(rootNode);
        setValue(childNode.getString());
    }
}
