package org.brassbrewery.fragaliciousLib.configs.objects;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

public class FloatConfigObject extends ConfigObject<Float>{


    public FloatConfigObject(String path, Float defaultValue) {
        super(path, defaultValue);
    }

    public FloatConfigObject(String path, Float defaultValue, String comment) {
        super(path, defaultValue, comment);
    }

    @Override
    public void readValueFromConfig(CommentedConfigurationNode rootNode) throws SerializationException {
        CommentedConfigurationNode childNode = getConfigurationNode(rootNode);
        setValue(childNode.getFloat());
    }
}
