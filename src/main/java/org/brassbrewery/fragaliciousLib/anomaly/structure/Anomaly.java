package org.brassbrewery.fragaliciousLib.anomaly.structure;

import org.brassbrewery.fragaliciousLib.anomaly.config.AnomalyConfig;
import org.brassbrewery.fragaliciousLib.anomaly.event.AnomalyEndEvent;
import org.brassbrewery.fragaliciousLib.anomaly.event.AnomalyJoinEvent;
import org.brassbrewery.fragaliciousLib.anomaly.event.AnomalyStartEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public abstract class Anomaly {
    private final AnomalyConfig config;

    public Anomaly(AnomalyConfig config) {
        this.config = config;
    }

    public String getId() {
        return config.getId();
    }

    public AnomalyConfig getConfig() {
        return config;
    }

    public boolean isEnabled() {
        return config.getIsEnabled();
    }

    public boolean isJoinable() {
        return config.getIsJoinable();
    }

    public int getWeight() {
        return config.getWeight();
    }

    public String getName() {
        return config.getName();
    }

    public String getDescription() {
        return config.getDescription();
    }
    public boolean shouldSendDiscordNotification() {return config.getSendEmbed();}
    public String getDiscordTitle() {return config.getDiscordTitle();}
    public String getDiscordDescription() {return config.getDiscordDescription();}
    public int getColor() {return config.getColor();}
    public abstract void start(AnomalyStartEvent event);
    public abstract void end(AnomalyEndEvent event);
    public abstract void playerJoin(AnomalyJoinEvent event);
}


