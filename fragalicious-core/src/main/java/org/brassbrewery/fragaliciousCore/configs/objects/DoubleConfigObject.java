package org.brassbrewery.fragaliciousCore.configs.objects;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

public class DoubleConfigObject extends ConfigObject<Double> {


    public DoubleConfigObject(String path, Double defaultValue) {
        super(path, defaultValue);
    }

    public DoubleConfigObject(String path, Double defaultValue, String comment) {
        super(path, defaultValue, comment);
    }

    @Override
    public void readValueFromConfig(CommentedConfigurationNode rootNode) throws SerializationException {
        CommentedConfigurationNode childNode = getConfigurationNode(rootNode);
        setValue(childNode.getDouble());
    }
}
