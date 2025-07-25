package org.brassbrewery.fragaliciousEconomics.mcmmo;

import org.brassbrewery.fragaliciousCore.configs.FragaliciousConfig;
import org.brassbrewery.fragaliciousCore.configs.objects.BooleanConfigObject;
import org.brassbrewery.fragaliciousCore.configs.objects.DoubleConfigObject;
import org.brassbrewery.fragaliciousCore.configs.objects.StringConfigObject;
import org.brassbrewery.fragaliciousEconomics.FragaliciousEconomics;

public class McMMOEcoConfig extends FragaliciousConfig {
    //leveling
    private BooleanConfigObject levelingEnabled;
    private BooleanConfigObject sendMessageOnLevelUp;
    private StringConfigObject levelingMessage;
    private DoubleConfigObject levelupIncomeCoefficent;
    private DoubleConfigObject levelupIncomeConstant;
    //skilling
    private BooleanConfigObject skillingEnabled;
    private BooleanConfigObject sendMessageOnSkillIncome;
    private StringConfigObject skillingMessage;
    private DoubleConfigObject skillingIncomeCoefficent;
    private DoubleConfigObject skillingChanceCoefficent; //multiplier for the amount chance
    //

    public McMMOEcoConfig(FragaliciousEconomics plugin) {
        super(plugin,"mcmmo" );
    }

    @Override
    public void registerAllConfigObjects() {
        registerLevelingConfig();
        registerSkillingConfig();
    }
    private void registerLevelingConfig(){
        levelingEnabled = new BooleanConfigObject("leveling.enabled", true, "Should the player get paid money when leveling up in MCMMO. \nThe formula used is Income = skillLevel * incomeCoefficent + incomeConstant");
        sendMessageOnLevelUp = new BooleanConfigObject("leveling.text.enabled", true, "Should a message be sent to the player about their income gained when leveling up?");
        levelingMessage = new StringConfigObject("leveling.text.message", "&aYou reached level {level} in {skill}! You earned &6${amount}!", "The message that will be sent to the player when they level up.");
        levelupIncomeCoefficent = new DoubleConfigObject("leveling.formula.coefficent", 0.5d, "The coefficient for how much each skill gives. The formula used is Income = skillLevel * incomeCoefficent + incomeConstant");
        levelupIncomeConstant = new DoubleConfigObject("leveling.formula.base", 5d, "The constant added to the income calculation. The formula used is Income = skillLevel * incomeCoefficent + incomeConstant");

        registerConfigObject(levelingEnabled);
        registerConfigObject(sendMessageOnLevelUp);
        registerConfigObject(levelingMessage);
        registerConfigObject(levelupIncomeCoefficent);
        registerConfigObject(levelupIncomeConstant);
    }
    private void registerSkillingConfig(){

    }
    public boolean isLevelingEnabled(){return levelingEnabled.getValue();}
    public boolean shouldSendMessageOnLevelUp(){return sendMessageOnLevelUp.getValue();}
    public String getLevelingMessage(){return levelingMessage.getValue();}
    public double getLevelupIncomeCoefficent(){return levelupIncomeCoefficent.getValue();}
    public double getLevelupIncomeConstant(){return levelupIncomeConstant.getValue();}
    public boolean isSkillingEnabled(){return skillingEnabled.getValue();}

}
