package org.brassbrewery.fragaliciousLib.anomaly;

import org.brassbrewery.fragaliciousLib.anomaly.event.AnomalyEndEvent;
import org.brassbrewery.fragaliciousLib.anomaly.event.AnomalyJoinEvent;
import org.brassbrewery.fragaliciousLib.anomaly.event.AnomalyStartEvent;
import org.brassbrewery.fragaliciousLib.anomaly.structure.ActiveAnomaly;
import org.brassbrewery.fragaliciousLib.anomaly.structure.Anomaly;
import org.brassbrewery.fragaliciousLib.anomaly.structure.AnomalyFactory;
import org.brassbrewery.fragaliciousLib.anomaly.structure.AnomalyListener;
import org.brassbrewery.fragaliciousLib.time.events.NewDayEvent;
import org.bukkit.Bukkit;

import java.util.*;
import java.util.stream.Collectors;

public class AnomalyService implements AnomalyListener {
    private final Map<String, Anomaly> registry = new HashMap<>();
    private final AnomalyFactory factory = new AnomalyFactory();
    private final Random random = new Random();

    private ActiveAnomaly activeAnomaly = null;
    private int missedDays = 0;
    private final int baseChance = 5;
    private Anomaly predictedAnomaly = null;

    public void registerAnomaly(Anomaly anomaly) {
        registry.put(anomaly.getId(), anomaly);
    }

    public Optional<ActiveAnomaly> getActiveAnomaly() {
        return Optional.ofNullable(activeAnomaly);
    }

    public Optional<Anomaly> getPredictedAnomaly() {
        return Optional.ofNullable(predictedAnomaly);
    }

    public void onNewDay() {
        if (activeAnomaly != null) return;

        int chance = Math.min(100, baseChance + missedDays * 5);
        if (random.nextInt(100) < chance) {
            selectRandomAnomaly().ifPresent(this::startAnomaly);
        } else {
            missedDays++;
            predictedAnomaly = selectRandomAnomaly().orElse(null);
        }
    }

    public void startAnomaly(Anomaly anomaly) {
        if (activeAnomaly != null) return;

        factory.create(anomaly).ifPresent(active -> {
            activeAnomaly = active;
            missedDays = 0;
            predictedAnomaly = null;
            Bukkit.getPluginManager().callEvent(new AnomalyStartEvent(active));
        });
    }

    public void endAnomaly() {
        if (activeAnomaly == null) return;

        Bukkit.getPluginManager().callEvent(new AnomalyEndEvent(activeAnomaly));
        activeAnomaly = null;
    }

    private Optional<Anomaly> selectRandomAnomaly() {
        List<Anomaly> available = registry.values().stream()
                .filter(Anomaly::isEnabled)
                .collect(Collectors.toList());

        int totalWeight = available.stream().mapToInt(Anomaly::getWeight).sum();
        if (totalWeight == 0) return Optional.empty();

        int roll = random.nextInt(totalWeight);
        int current = 0;
        for (Anomaly a : available) {
            current += a.getWeight();
            if (roll < current) return Optional.of(a);
        }
        return Optional.empty();
    }

    @Override
    public void onJoin(AnomalyJoinEvent anomalyJoinEvent) {

    }

    @Override
    public void onLeave(AnomalyJoinEvent anomalyJoinEvent) {

    }

    @Override
    public void onEnd(AnomalyEndEvent anomalyEndEvent) {

    }

    @Override
    public void onNewDay(NewDayEvent newDayEvent) {

    }
}


