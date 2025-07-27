package org.brassbrewery.fragaliciousEconomics.mcmmo;

import org.brassbrewery.fragaliciousCore.configs.FragaliciousConfig;
import org.brassbrewery.fragaliciousCore.configs.objects.BooleanConfigObject;
import org.brassbrewery.fragaliciousCore.configs.objects.DoubleConfigObject;
import org.brassbrewery.fragaliciousCore.configs.objects.IntegerConfigObject;
import org.brassbrewery.fragaliciousCore.configs.objects.StringConfigObject;
import org.brassbrewery.fragaliciousEconomics.FragaliciousEconomics;

public class McMMOEcoConfig extends FragaliciousConfig {
    ////////////leveling//////////////
    private BooleanConfigObject levelingEnabled;
    private BooleanConfigObject sendMessageOnLevelUp;
    private StringConfigObject levelingMessage;
    private DoubleConfigObject levelupIncomeCoefficent;
    private DoubleConfigObject levelupIncomeConstant;

    //////////////////////skilling///////////////////////
    private BooleanConfigObject skillingEnabled;
    private BooleanConfigObject sendMessageOnSkillIncome;
    private StringConfigObject skillingMessage;
    private IntegerConfigObject skillingLoginCooldown;
    private IntegerConfigObject skillingCooldownDuration; //requires restart of server to take effect.
    private DoubleConfigObject skillingIncomeCap;
    //modifiers
    private DoubleConfigObject skillingIncomeCoefficent;
    private DoubleConfigObject skillingChanceCoefficent; //multiplier for the amount chance
    private DoubleConfigObject skillingAverageLevelModifier;
    private DoubleConfigObject skillingCurrentSkillModifier;
    private DoubleConfigObject skillingCooldownModifier;


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
        skillingEnabled = new BooleanConfigObject("skilling.enabled", true, "Should the player have a chance to get money when skilling in mcmmo?");
        sendMessageOnSkillIncome = new BooleanConfigObject("skilling.text.enabled", true, "Should a message be sent to the player about their income gained from skilling?");
        skillingMessage = new StringConfigObject("skilling.text.message", "&aYou earned &6${amount}&a by skilling in {skill}.", "The message that will be sent to the player when they earn money from skilling.");
        skillingLoginCooldown = new IntegerConfigObject("skilling.cooldown.login", 5, "How many cooldown points should a player receive on login. This is to prevent abusing relogs for resetting the cooldown"); //seconds
        skillingCooldownDuration = new IntegerConfigObject("skilling.cooldown.duration", 60, "How long should each cooldown point last in seconds?");
        skillingIncomeCoefficent = new DoubleConfigObject("skilling.multipliers.income", 2d, "The final multiplier to impact the income formula");
        skillingChanceCoefficent = new DoubleConfigObject("skilling.multipliers.chance", 0.01d, "The final multiplier to the players chance ");
        skillingAverageLevelModifier = new DoubleConfigObject("skilling.modifiers.average-level", 0.3d, "A modifier based off of the average level of all skills");
        skillingCurrentSkillModifier = new DoubleConfigObject("skilling.modifiers.current-skill", 0.7d, "A modifier based off of the current skill being worked on");
        skillingCooldownModifier = new DoubleConfigObject("skilling.modifiers.cooldown", 10d, "A modifier based off of the remaining time until the next cooldown reset.");
        skillingIncomeCap = new DoubleConfigObject("skilling.modifier.income-cap", 50d, "The maximum amount of money a player can gain per skilling event.");

        registerConfigObject(skillingEnabled);
        registerConfigObject(sendMessageOnSkillIncome);
        registerConfigObject(skillingMessage);
        registerConfigObject(skillingLoginCooldown);
        registerConfigObject(skillingCooldownDuration);
        registerConfigObject(skillingIncomeCoefficent);
        registerConfigObject(skillingChanceCoefficent);
        registerConfigObject(skillingAverageLevelModifier);
        registerConfigObject(skillingCurrentSkillModifier);
        registerConfigObject(skillingCooldownModifier);
        registerConfigObject(skillingIncomeCap);

    }
    public boolean isLevelingEnabled(){return levelingEnabled.getValue();}
    public boolean shouldSendMessageOnLevelUp(){return sendMessageOnLevelUp.getValue();}
    public String getLevelingMessage(){return levelingMessage.getValue();}
    public double getLevelupIncomeCoefficent(){return levelupIncomeCoefficent.getValue();}
    public double getLevelupIncomeConstant(){return levelupIncomeConstant.getValue();}
    public boolean isSkillingEnabled(){return skillingEnabled.getValue();}

    public boolean shouldSendMessageOnSkillIncome(){return sendMessageOnSkillIncome.getValue();}

    public String getSkillingMessage(){return skillingMessage.getValue();}
    public int getSkillingLoginCooldown(){return skillingLoginCooldown.getValue();}
    public int getSkillingCooldownDuration(){return skillingCooldownDuration.getValue();}
    public double getSkillingIncomeCoefficent(){return skillingIncomeCoefficent.getValue();}
    public double getSkillingChanceCoefficent(){return skillingChanceCoefficent.getValue();}
    public double getSkillingAverageLevelModifier(){return skillingAverageLevelModifier.getValue();}
    public double getSkillingCurrentSkillModifier(){return skillingCurrentSkillModifier.getValue();}
    public double getSkillingCooldownModifier(){return skillingCooldownModifier.getValue();}
    public double getSkillingIncomeCap(){return skillingIncomeCap.getValue();}


    //skilling
}
