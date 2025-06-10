package org.brassbrewery.fragaliciousCore.anomaly;

import org.brassbrewery.fragaliciousCore.structure.FragaliciouModule;
import org.brassbrewery.fragaliciousCore.structure.FragaliciousPlugin;

public class AnomalyModule extends FragaliciouModule<AnomalyAPI> {
    private AnomalyService anomalyService;

    public AnomalyModule(FragaliciousPlugin fragaliciousPlugin) {
        super(fragaliciousPlugin);

    }

    @Override
    protected AnomalyAPI createAPI(boolean isEnabled) {
        return null;
    }

    @Override
    public void preInit() {
        anomalyService = new AnomalyService();
    }

    @Override
    public void postInit() {

    }

    @Override
    public void onReload() {

    }

    @Override
    public boolean canLaunchModule() {
        return true;
    }

    @Override
    public String moduleName() {
        return "Anomaly";
    }

    @Override
    public void registerListeners() {
        registerListener(anomalyService);
    }

    @Override
    public void registerServices() {

    }

    @Override
    public void registerCommands() {

    }
}
