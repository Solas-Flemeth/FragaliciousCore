package org.brassbrewery.fragaliciousCore.anomaly.structure;

import java.util.Optional;

public class AnomalyFactory {
    public static Optional<ActiveAnomaly> create(Anomaly anomaly) {
        return anomaly.isEnabled() ? Optional.of(new ActiveAnomaly(anomaly)) : Optional.empty();
    }
}

