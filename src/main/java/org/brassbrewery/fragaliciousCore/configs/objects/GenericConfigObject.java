package org.brassbrewery.fragaliciousCore.configs.objects;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.CommentedConfigurationNode;

import java.util.Arrays;
import java.util.List;

public abstract class GenericConfigObject {
    private final String path;
    private final @Nullable String comment;
    public GenericConfigObject(String path){
        this(path, null);
    }

    public GenericConfigObject(String path, String comment){
        this.path = path;
        this.comment = comment;
    }

    public String getPath() {
        return path;
    }

    public String getComment() {
        return comment;
    }
    public List<String> getPathNodeNames(){
        return Arrays.stream(path.split("\\.")).toList();
    }

    public CommentedConfigurationNode getConfigurationNode(CommentedConfigurationNode node){
        CommentedConfigurationNode configurationNode = node;
        for (String path : getPathNodeNames()){
            configurationNode = configurationNode.node(path);
        }
        return configurationNode;
    }
}
