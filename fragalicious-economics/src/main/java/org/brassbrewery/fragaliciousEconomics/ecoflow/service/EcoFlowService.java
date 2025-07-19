package org.brassbrewery.fragaliciousEconomics.ecoflow.service;

import org.brassbrewery.fragaliciousEconomics.ecoflow.EcoFlowModule;
import org.brassbrewery.fragaliciousEconomics.ecoflow.config.EcoFlowConfig;
import org.bukkit.event.Listener;

public abstract class EcoFlowService implements Listener {
    private EcoFlowModule ecoFlowModule;

    public EcoFlowService(EcoFlowModule ecoFlowModule){
        this.ecoFlowModule = ecoFlowModule;
    }

    public EcoFlowConfig getConfig() { return this.ecoFlowModule.getConfig(); }

    public String replaceVariables(String text, double amount) {
        return text.replace("{amount}", String.valueOf(amount));
    }
    public String replaceVariables(String text, double amount, int players) {
        String answer;
        answer = text.replace("{amount}", String.valueOf(amount));
        answer = answer.replace("{players}", String.valueOf(players));
        return answer;
    }
    public void fine(String fineText){
        ecoFlowModule.fine(fineText);
    }
    public void warn(String warnText){
        ecoFlowModule.warn(warnText);
    }
    public void error(String errorText){
        ecoFlowModule.error(errorText);
    }
}
