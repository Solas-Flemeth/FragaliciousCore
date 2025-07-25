package org.brassbrewery.fragaliciousEconomics.mcmmo.service;

import org.brassbrewery.fragaliciousEconomics.mcmmo.McMMOEcoModule;
import org.brassbrewery.fragaliciousEconomics.mcmmo.McMMOEcoConfig;
import org.bukkit.event.Listener;

public abstract class McMMOEcoService implements Listener {
    private McMMOEcoModule mcMMOEcoModule;

    public McMMOEcoService(McMMOEcoModule mcMMOEcoModule){
        this.mcMMOEcoModule = mcMMOEcoModule;
    }

    public McMMOEcoConfig getConfig() { return this.mcMMOEcoModule.getConfig(); }

    public String replaceVariables(String text, double amount,  String skill, int level) {
        String answer;
        answer = text.replace("{amount}", String.valueOf(amount));
        answer = answer.replace("{level}", String.valueOf(level));
        answer = answer.replace("{skill}", String.valueOf(skill));
        return answer;
    }
    public void fine(String fineText){
        mcMMOEcoModule.fine(fineText);
    }
    public void warn(String warnText){
        mcMMOEcoModule.warn(warnText);
    }
    public void error(String errorText){
        mcMMOEcoModule.error(errorText);
    }
}
