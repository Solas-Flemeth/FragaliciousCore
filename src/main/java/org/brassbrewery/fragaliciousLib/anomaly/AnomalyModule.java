package org.brassbrewery.fragaliciousLib.anomaly;

import org.brassbrewery.fragaliciousLib.structure.FragaliciouModule;
import org.brassbrewery.fragaliciousLib.structure.FragaliciousPlugin;

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
