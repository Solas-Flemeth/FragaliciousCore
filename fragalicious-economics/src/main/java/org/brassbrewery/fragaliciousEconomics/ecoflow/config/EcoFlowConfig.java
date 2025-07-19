package org.brassbrewery.fragaliciousEconomics.ecoflow.config;

import org.brassbrewery.fragaliciousCore.configs.FragaliciousConfig;
import org.brassbrewery.fragaliciousCore.configs.objects.BooleanConfigObject;
import org.brassbrewery.fragaliciousCore.configs.objects.DoubleConfigObject;
import org.brassbrewery.fragaliciousCore.configs.objects.IntegerConfigObject;
import org.brassbrewery.fragaliciousCore.configs.objects.StringConfigObject;
import org.brassbrewery.fragaliciousEconomics.FragaliciousEconomics;

public class EcoFlowConfig extends FragaliciousConfig {
    //UBI
    private BooleanConfigObject isUBIEnabled;
    private IntegerConfigObject ubiIncome;
    private IntegerConfigObject ubiAfkIncome;
    private BooleanConfigObject ubiSendText;
    private StringConfigObject ubiIncomeText;
    private StringConfigObject ubiAfkIncomeText;
    //Death Tax
    private BooleanConfigObject isDeathTaxEnabled;
    private IntegerConfigObject minimumBalanceForDeathTax;
    private IntegerConfigObject maximumLimitForDeathTax;
    private DoubleConfigObject minScalePercentage;
    private DoubleConfigObject maxScalePercentage;
    private BooleanConfigObject sendDeathTaxText;
    private StringConfigObject deathTaxText;
    //Wealth Tax
    private BooleanConfigObject isWealthTaxEnabled;
    private IntegerConfigObject wealthTaxMinimumBalance;
    private IntegerConfigObject wealthTaxMaximumLimit;
    private DoubleConfigObject wealthTaxMinScalePercentage;
    private DoubleConfigObject wealthTaxMaxScalePercentage;
    private BooleanConfigObject sendWealthTaxText;
    private StringConfigObject wealthTaxText;
    private BooleanConfigObject sendWealthTaxBroadcast;
    private StringConfigObject wealthTaxBroadcastText;
    public EcoFlowConfig() {
        super(FragaliciousEconomics.getInstance(), "EconomicFlow");
    }

    @Override
    public void registerAllConfigObjects() {
        registerUBIConfig();
        registerDeathTaxConfig();
        registerWealthTaxConfig();
    }
    private void registerUBIConfig() {
        this.isUBIEnabled = new BooleanConfigObject("faucets.UBI.enabled", true, "Whether or not Universal basic income should be enabled. This triggers every ingame morning");
        this.ubiIncome = new IntegerConfigObject("faucets.UBI.income", 100, "How much you get paid by ubi while not afk");
        this.ubiAfkIncome = new IntegerConfigObject("faucets.UBI.afk_income", 50, "How much you get paid by ubi while afk");
        this.ubiSendText = new BooleanConfigObject("faucets.UBI.send_text", true, "Should you send a message to the player when they get their UBI payment");
        this.ubiIncomeText = new StringConfigObject("faucets.UBI.text.income", "&aYou have received &6{amount}", "The text that will be sent to players who receive UBI while not afk");
        this.ubiAfkIncomeText = new StringConfigObject("faucets.UBI.text.afk_income", "&aYou have received &4{amount} while AFK", "The text that will be sent to players who receive UBI while afk");

        registerConfigObject(isUBIEnabled);
        registerConfigObject(ubiIncome);
        registerConfigObject(ubiAfkIncome);
        registerConfigObject(ubiSendText);
        registerConfigObject(ubiIncomeText);
        registerConfigObject(ubiAfkIncomeText);

    }
    private void registerDeathTaxConfig() {
     this.isDeathTaxEnabled = new BooleanConfigObject("sinks.death_tax.enabled", true, "Whether a death tax should be applied when someone dies");
        this.minimumBalanceForDeathTax = new IntegerConfigObject("sinks.death_tax.minimum_balance_amount", 500, "The minimum balance required for the death tax to trigger");
        this.maximumLimitForDeathTax = new IntegerConfigObject("sinks.death_tax.maximum_limit", 1000000, "The maximum limit for the death tax before the percentage stops increasing");
        this.minScalePercentage = new DoubleConfigObject("sinks.death_tax.min_scale_percentage", 0.0005, "The lowest percentage that can be applied");
        this.maxScalePercentage = new DoubleConfigObject("sinks.death_tax.max_scale_percentage", 0.03, "The highest percentage that can be applied");
        this.sendDeathTaxText = new BooleanConfigObject("sinks.death_tax.send_text", true, "Should you send a message to the player when they die and are taxed");
        this.deathTaxText = new StringConfigObject("sinks.death_tax.text", "&cYour balance has been taxed a total of {amount} as a death tax", "The text that will be sent to players when they die and are taxed");

        registerConfigObject(isDeathTaxEnabled);
        registerConfigObject(minimumBalanceForDeathTax);
        registerConfigObject(maximumLimitForDeathTax);
        registerConfigObject(minScalePercentage);
        registerConfigObject(maxScalePercentage);
        registerConfigObject(sendDeathTaxText);
        registerConfigObject(deathTaxText);
        registerConfigObject(sendDeathTaxText);
        registerConfigObject(deathTaxText);
        registerConfigObject(sendDeathTaxText);
    }
    private void registerWealthTaxConfig() {
        this.isWealthTaxEnabled = new BooleanConfigObject("sinks.wealth_tax.enabled", false, "Whether a wealth tax should run every towny day. NOTE: This requires towny to work");
        this.wealthTaxMinimumBalance = new IntegerConfigObject("sinks.wealth_tax.minimum_balance_amount", 1000, "The minimum balance required for the wealth tax to apply to a player");
        this.wealthTaxMaximumLimit = new IntegerConfigObject("sinks.wealth_tax.maximum_limit", 1000000, "The maximum limit for the wealth tax before the percentage stops increasing");
        this.wealthTaxMinScalePercentage = new DoubleConfigObject("sinks.wealth_tax.min_scale_percentage", 0.001, "The lowest percentage that can be applied");
        this.wealthTaxMaxScalePercentage = new DoubleConfigObject("sinks.wealth_tax.max_scale_percentage", 0.05, "The highest percentage that can be applied");
        this.sendWealthTaxText = new BooleanConfigObject("sinks.wealth_tax.send_text", false, "Should you send a message to the player when they are taxed");
        this.wealthTaxText = new StringConfigObject("sinks.wealth_tax.text", "&cYour balance has been taxed a total of {amount} as a wealth tax", "The text that will be sent to players when they are taxed");
        this.sendWealthTaxBroadcast = new BooleanConfigObject("sinks.wealth_tax.broadcast", false, "Should you broadcast a message to all players on");
        this.wealthTaxBroadcastText = new StringConfigObject("sinks.wealth_tax.broadcast_text", "A total of {amount} was taxed from the economy.", "The text that will be broadcasted when the wealth tax runs");

        registerConfigObject(isWealthTaxEnabled);
        registerConfigObject(wealthTaxMinimumBalance);
        registerConfigObject(wealthTaxMaximumLimit);
        registerConfigObject(wealthTaxMinScalePercentage);
        registerConfigObject(wealthTaxMaxScalePercentage);
        registerConfigObject(sendWealthTaxText);
        registerConfigObject(wealthTaxText);
        registerConfigObject(sendWealthTaxBroadcast);
        registerConfigObject(wealthTaxBroadcastText);
    }

    // ======= UBI Getters =======
    public Boolean isUBIEnabled() {
        return isUBIEnabled.getValue();
    }

    public int getUbiIncome() {
        return ubiIncome.getValue();
    }

    public int getUbiAfkIncome() {
        return ubiAfkIncome.getValue();
    }

    public Boolean isUbiSendTextEnabled() {
        return ubiSendText.getValue();
    }

    public String getUbiIncomeText() {
        return ubiIncomeText.getValue();
    }

    public String getUbiAfkIncomeText() {
        return ubiAfkIncomeText.getValue();
    }

    // ======= Death Tax Getters =======
    public Boolean isDeathTaxEnabled() {
        return isDeathTaxEnabled.getValue();
    }

    public int getMinimumBalanceForDeathTax() {
        return minimumBalanceForDeathTax.getValue();
    }

    public int getMaximumLimitForDeathTax() {
        return maximumLimitForDeathTax.getValue();
    }

    public double getMinScalePercentage() {
        return minScalePercentage.getValue();
    }

    public double getMaxScalePercentage() {
        return maxScalePercentage.getValue();
    }

    public Boolean isSendDeathTaxTextEnabled() {
        return sendDeathTaxText.getValue();
    }

    public String getDeathTaxText() {
        return deathTaxText.getValue();
    }

    // ======= Wealth Tax Getters =======
    public Boolean isWealthTaxEnabled() {
        return isWealthTaxEnabled.getValue();
    }

    public int getWealthTaxMinimumBalance() {
        return wealthTaxMinimumBalance.getValue();
    }

    public int getWealthTaxMaximumLimit() {
        return wealthTaxMaximumLimit.getValue();
    }

    public double getWealthTaxMinScalePercentage() {
        return wealthTaxMinScalePercentage.getValue();
    }

    public double getWealthTaxMaxScalePercentage() {
        return wealthTaxMaxScalePercentage.getValue();
    }

    public Boolean isSendWealthTaxTextEnabled() {
        return sendWealthTaxText.getValue();
    }

    public String getWealthTaxText() {
        return wealthTaxText.getValue();
    }

    public Boolean isSendWealthTaxBroadcastEnabled() {
        return sendWealthTaxBroadcast.getValue();
    }

    public String getWealthTaxBroadcastText() {
        return wealthTaxBroadcastText.getValue();
    }

}
