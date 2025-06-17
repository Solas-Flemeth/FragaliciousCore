package org.brassbrewery.fragaliciousCore.configs.objects;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

public class IntegerConfigObject extends ConfigObject<Integer>{


    public IntegerConfigObject(String path, Integer defaultValue) {
        super(path, defaultValue);
    }

    public IntegerConfigObject(String path, Integer defaultValue, String comment) {
        super(path, defaultValue, comment);
    }

    @Override
    public void readValueFromConfig(CommentedConfigurationNode rootNode) throws SerializationException {
        CommentedConfigurationNode childNode = getConfigurationNode(rootNode);
        setValue(childNode.getInt());
    }
}
