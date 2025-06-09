package org.brassbrewery.fragaliciousLib.configs.objects;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

public class BooleanConfigObject extends ConfigObject<Boolean> {


    public BooleanConfigObject(String path, Boolean defaultValue) {
        super(path, defaultValue);
    }

    public BooleanConfigObject(String path, Boolean defaultValue, String comment) {
        super(path, defaultValue, comment);
    }

    @Override
    public void readValueFromConfig(CommentedConfigurationNode rootNode) throws SerializationException {
        CommentedConfigurationNode childNode = getConfigurationNode(rootNode);
        setValue(childNode.getBoolean());
    }
}
