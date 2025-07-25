package org.brassbrewery.fragaliciousEconomics.mcmmo.service;

import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.datatypes.skills.PrimarySkillType;
import com.gmail.nossr50.events.experience.McMMOPlayerExperienceEvent;
import com.gmail.nossr50.util.player.UserManager;
import org.brassbrewery.fragaliciousEconomics.mcmmo.McMMOEcoModule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class SkillingIncomeService extends McMMOEcoService {
    public SkillingIncomeService(McMMOEcoModule mcMMOEcoModule) {
        super(mcMMOEcoModule);
    }

    @EventHandler
    public void onXPGain(McMMOPlayerExperienceEvent event){
        double averageLevel = getAverageSkillLevel(event.getPlayer());
        double skillLevel = event.getSkillLevel();
    }
    public double getAverageSkillLevel(Player player){
        McMMOPlayer mcMMOPlayer = UserManager.getPlayer(player);
        double totalLevel = 0;
        totalLevel += mcMMOPlayer.getSkillLevel(PrimarySkillType.ACROBATICS);
        totalLevel += mcMMOPlayer.getSkillLevel(PrimarySkillType.ALCHEMY);
        totalLevel += mcMMOPlayer.getSkillLevel(PrimarySkillType.ARCHERY);
        totalLevel += mcMMOPlayer.getSkillLevel(PrimarySkillType.AXES);
        totalLevel += mcMMOPlayer.getSkillLevel(PrimarySkillType.CROSSBOWS);
        totalLevel += mcMMOPlayer.getSkillLevel(PrimarySkillType.EXCAVATION);
        totalLevel += mcMMOPlayer.getSkillLevel(PrimarySkillType.FISHING);
        totalLevel += mcMMOPlayer.getSkillLevel(PrimarySkillType.HERBALISM);
        totalLevel += mcMMOPlayer.getSkillLevel(PrimarySkillType.MINING);
        totalLevel += mcMMOPlayer.getSkillLevel(PrimarySkillType.REPAIR);
        totalLevel += mcMMOPlayer.getSkillLevel(PrimarySkillType.SWORDS);
        totalLevel += mcMMOPlayer.getSkillLevel(PrimarySkillType.TAMING);
        totalLevel += mcMMOPlayer.getSkillLevel(PrimarySkillType.TRIDENTS);
        totalLevel += mcMMOPlayer.getSkillLevel(PrimarySkillType.UNARMED);
        totalLevel += mcMMOPlayer.getSkillLevel(PrimarySkillType.WOODCUTTING);
        return totalLevel/15d;
    }
    public double getChance(Double skillLevel, Double averageLevel, double cooldown){
        skillLevel = 1.0 * skillLevel; //todo: get coefficient from config and replace 1.0
        averageLevel = 1.0 * averageLevel; //todo: get coefficient from config and replace 1.0
        cooldown = 1.0 * cooldown; //todo: get coefficient from config and replace 1.0
        double chanceMultiplier = 1.0; //todo: get coefficient from config and replace 1.0
        double chance = Math.sqrt( (averageLevel/skillLevel) + (averageLevel / (skillLevel + cooldown) ) )
                / (cooldown+100) * chanceMultiplier;
        if(chance > 0.5){ //todo: get maximum chance from config and replace 0.5
            return 0.5;}
        else {
            return chance;
        }
    }
    public double getIncome(Double skillLevel, Double averageLevel){
        skillLevel = 1.0 * skillLevel; //todo: get coefficient from config and replace 1.0
        averageLevel = 1.0 * averageLevel; //todo: get coefficient from config and replace 1.0
        double incomeMultiplier = 1.0; //todo: get coefficient from config and replace 1.0
        double income =  Math.sqrt(
                0.66 * Math.sqrt(skillLevel)  + 0.33 * Math.sqrt(averageLevel)
        )*incomeMultiplier;
        if(income > 1.0 ){ //todo: get maximum income from config and replace 1.0
            return 1.0;
        }
        return income;
    }
}
