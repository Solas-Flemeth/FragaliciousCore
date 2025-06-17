package org.brassbrewery.fragaliciousCore.anomaly.structure;

import org.brassbrewery.fragaliciousCore.anomaly.config.AnomalyConfig;
import org.brassbrewery.fragaliciousCore.anomaly.event.AnomalyEndEvent;
import org.brassbrewery.fragaliciousCore.anomaly.event.AnomalyJoinEvent;
import org.brassbrewery.fragaliciousCore.anomaly.event.AnomalyStartEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ActiveAnomaly {
    private final Anomaly anomaly;
    private final Set<UUID> participatingPlayers = new HashSet<>();

    public ActiveAnomaly(Anomaly anomaly) {
        this.anomaly = anomaly;
    }

    public Anomaly getAnomaly() {
        return anomaly;
    }

    public AnomalyConfig getConfig() {
        return anomaly.getConfig();
    }

    public void joinPlayer(Player player) {
        if (anomaly.isJoinable()) {
            participatingPlayers.add(player.getUniqueId());
            Bukkit.getPluginManager().callEvent(new AnomalyJoinEvent(this, player));
        }
    }

    public boolean isPlayerParticipating(UUID playerId) {
        return participatingPlayers.contains(playerId);
    }

    public Set<UUID> getParticipants() {
        return Collections.unmodifiableSet(participatingPlayers);
    }
    public void onStart(AnomalyStartEvent event){
        anomaly.start(event);
    }
    public void onEnd(AnomalyEndEvent event){
        anomaly.end(event);
    }
    public void playerJoin(AnomalyJoinEvent event){
        anomaly.playerJoin(event);
    }
}

