package org.brassbrewery.fragaliciousEconomics.ecoflow;

import org.brassbrewery.fragaliciousCore.structure.FragaliciousAPI;
import org.brassbrewery.fragaliciousEconomics.ecoflow.config.EcoFlowConfig;

import java.util.List;
import java.util.UUID;

public class EcoFlowApi extends FragaliciousAPI<EcoFlowModule> {

    public EcoFlowApi(EcoFlowModule fragaliciouModule) {
        super(fragaliciouModule);
    }
    public EcoFlowConfig getConfig() {
        return this.getModule().getConfig();
    }
    public void runWealthTax(List<UUID> playersUUIDs){
        this.getModule().getWealthTaxService().runWealthTax(playersUUIDs);
    }
}
