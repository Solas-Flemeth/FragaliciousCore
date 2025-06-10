package org.brassbrewery.fragaliciousCore.anomaly.config;

import org.brassbrewery.fragaliciousCore.configs.objects.BooleanConfigObject;
import org.brassbrewery.fragaliciousCore.configs.objects.IntegerConfigObject;
import org.brassbrewery.fragaliciousCore.configs.objects.StringConfigObject;

public abstract class AnomalyConfigObject{
    private BooleanConfigObject isEnabled;
    //generic
    private IntegerConfigObject weight;
    private BooleanConfigObject isJoinable;
    //ingame info
    private StringConfigObject name;
    private StringConfigObject description;
    //discord
    private BooleanConfigObject sendEmbed;
    private StringConfigObject discordDescription;
    private StringConfigObject discordTitle;
    private IntegerConfigObject color;

    public AnomalyConfigObject(){
        isEnabled = new BooleanConfigObject("main.isEnabled",getDefaultIsEnabled(), "mark true for this event to be enabled. Mark false for it to be disabled");
        weight = new IntegerConfigObject("main.weight", getDefaultWeight(), "How often the event will occur compared to others");
        isJoinable = new BooleanConfigObject("main.isJoinable", getDefaultIsJoinable(), "If the player must join the event to be included. " +
                "\nMark this false if the player is meant to join ");
        //ingame
        name = new StringConfigObject("game.name", getDefaultName(), "The name of the event");
        description = new StringConfigObject("game.description", getDefaultDescription(),"A short description of what happens in the event");
        //discord
        sendEmbed = new BooleanConfigObject("discord.sendEmbed", getDefaultSendDiscordNotification(),
                "Set true to send a notification to discord if enabled");
        discordDescription = new StringConfigObject("discord.description", getDefaultDiscordDescription());
        discordTitle = new StringConfigObject("discord.title", getDefaultDiscordTitle());
        color = new IntegerConfigObject("discord.color", getDefaultColor());
    }
    public abstract Boolean getDefaultIsEnabled();
    public abstract Integer getDefaultWeight();
    public abstract Boolean getDefaultIsJoinable();
    //ingame
    public abstract String getDefaultName();
    public abstract String getDefaultDescription();

    //discord
    public abstract boolean getDefaultSendDiscordNotification();
    public abstract String getDefaultDiscordTitle();
    public abstract String getDefaultDiscordDescription();
    public abstract int getDefaultColor();

    public BooleanConfigObject getIsEnabled() {
        return isEnabled;
    }

    public IntegerConfigObject getWeight() {
        return weight;
    }

    public BooleanConfigObject getIsJoinable() {
        return isJoinable;
    }

    public StringConfigObject getName() {
        return name;
    }

    public StringConfigObject getDescription() {
        return description;
    }

    public BooleanConfigObject getSendEmbed() {
        return sendEmbed;
    }

    public StringConfigObject getDiscordDescription() {
        return discordDescription;
    }

    public StringConfigObject getDiscordTitle() {
        return discordTitle;
    }

    public IntegerConfigObject getColor() {
        return color;
    }
}
