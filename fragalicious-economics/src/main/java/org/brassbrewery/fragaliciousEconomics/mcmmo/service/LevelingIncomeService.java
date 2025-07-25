package org.brassbrewery.fragaliciousEconomics.mcmmo.service;

import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;
import org.brassbrewery.fragaliciousCore.FragaliciousCore;
import org.brassbrewery.fragaliciousCore.exceptions.ModuleNotLoadedException;
import org.brassbrewery.fragaliciousCore.util.MessageUtil;
import org.brassbrewery.fragaliciousEconomics.mcmmo.McMMOEcoModule;
import org.bukkit.event.EventHandler;

public class LevelingIncomeService extends McMMOEcoService{

    public LevelingIncomeService(McMMOEcoModule mcMMOEcoModule) {
        super(mcMMOEcoModule);
    }

    @EventHandler
    public void onPlayerLevelUp(McMMOPlayerLevelUpEvent event){
        if(!getConfig().isLevelingEnabled()){
            return; //skip as its not enabled
        }
        double income = getConfig().getLevelupIncomeCoefficent() * ((double)event.getSkillLevel()) + getConfig().getLevelupIncomeConstant();
        String skill  = event.getSkill().name();
        try {
            FragaliciousCore.economyAPI().deposit(income, event.getPlayer());
            if(getConfig().shouldSendMessageOnLevelUp()){
                MessageUtil.sendMessage(event.getPlayer(), replaceVariables(getConfig().getLevelingMessage(), income, skill, event.getSkillLevel()));
            }
            fine(event.getPlayer().getName()+" gained " + income + " coins from leveling skill " + event.getSkill().name() + ".");
        } catch (ModuleNotLoadedException e) {
            warn("Could not deposit money on level up due to economy module not being loaded. Please verify vault is installed and working correctly.");
        }
    }

}
