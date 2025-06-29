package org.brassbrewery.fragaliciousCombat.protection.config;

import org.brassbrewery.fragaliciousCombat.FragaliciousCombat;
import org.brassbrewery.fragaliciousCore.configs.FragaliciousConfig;
import org.brassbrewery.fragaliciousCore.configs.objects.*;

public class ProtectionConfig extends FragaliciousConfig {
    //on combat
    private BooleanConfigObject enableCombatProtection;
    private IntegerConfigObject combatProtectionCost;
    private StringConfigObject combatPvpProtectionEnableText;
    private StringConfigObject combatPvpProtectionDisableText;
    private StringConfigObject combatPvpProtectionEnableOnLogin ;
    private StringConfigObject combatPvpProtectionPurchaseReminder;
    private StringConfigObject combatProtectionApplyUpkeepText;
    private IntegerConfigObject combatProtectionUpkeep;
    private IntegerConfigObject combatProtectionUpkeepType;
    private IntegerConfigObject combatProtectionUpkeepHour;
    private StringConfigObject combatProtectionCannotAffordCostText;
    private StringConfigObject combatProtectionAlreadyOwned;
    private StringConfigObject combatPvpNotResidentText;
    private StringConfigObject combatPvpProtectionUpkeepExpiredText;
    private StringConfigObject combatPvpProtectionLoginExpireText;
    private BooleanConfigObject combatProtectionUpkeepWarning;
    private StringConfigObject combatPvpProtectionUpkeepWarnText;
    private StringConfigObject combatPvpProtectionCantAttackTargetText;
    private StringConfigObject combatPvpProtectionCantAttackWithProtectionText;

    //on join
    private BooleanConfigObject enableNewPlayerProtection;
    private IntegerConfigObject newPlayerProtectionDuration;
    private StringConfigObject newPlayerProtectionLoginText;
    private StringConfigObject newPlayerProtectionExpireText;

    //respawn protection
    private BooleanConfigObject enableRespawnProtection;
    private IntegerConfigObject respawnProtectionDuration;
    private StringConfigObject respawnProtectionRespawnText;
    private StringConfigObject respawnProtectionRespawnExpireText;
    //inventory protection
    private BooleanConfigObject keepInventoryOnDeath;
    private BooleanConfigObject keepInventoryInPeacefulTowns;
    public ProtectionConfig() {
        super(FragaliciousCombat.getInstance(), "protections");
    }

    @Override
    public void registerAllConfigObjects() {
        enableCombatProtection = new BooleanConfigObject("protection.pvp.enabled", true, "Should the player be able to obtain pvp protection at a cost? (Default: True)");
        combatProtectionCost = new IntegerConfigObject("protection.pvp.cost", 100, "The amount of money that will be charged when enabling combat protection. (Default: 100)");
        combatPvpProtectionEnableText = new StringConfigObject("protection.pvp.lang.enable_text", "&7You are now protected from PvP attacks!", "\nThe text that will be displayed to the player when they purchase combat protection. You can use {cost} and {upkeep} as variables");
        combatPvpProtectionDisableText = new StringConfigObject("protection.pvp.lang.disable_text", "&cYour PvP protection has been disabled.", "\nThe text that will be displayed to the player when their combat protection expires.");
        combatPvpProtectionEnableOnLogin = new StringConfigObject("protection.pvp.lang.login_enable_text", "&7You are currently protected from PvP attacks!", "\nThe text that will be displayed to the player when they purchase combat protection on login. You can use {cost} and {upkeep} as variables");
        combatPvpProtectionPurchaseReminder = new StringConfigObject("protection.pvp.lang.login_reminder", "&7You can currently be attacked from pvp, type &e/togglepvp force&7 to enable it!", "\nThe text that will be displayed to the player when a player logs in without pvp protection. You can use {cost} and {upkeep} as variables");
        combatProtectionApplyUpkeepText = new StringConfigObject("protection.pvp.lang.upkeep_text", "&7You were charged ${upkeep} to maintain your PvP protection", "\nThe text that will be displayed to the player when they check their status. You can use {cost} and {upkeep} as variables");
        combatProtectionUpkeep = new IntegerConfigObject("protection.pvp.upkeep.cost", 50, "The amount of money that will be charged to keep it enabled (Default: 100)");
        combatProtectionUpkeepType = new IntegerConfigObject("protection.pvp.upkeep.type", 1, "What type of upkeep should be used for this protection - please provide a number:" +
                "\n 0: No Upkeep - never runs upkeep - great if you just want a fee to enable protection once" +
                "\n 1: Towny Taxes - (Default) applies when a new day occurs in towny. Will apply to both offline and online players. Requires towny or it will act like option 0" +
                "\n 2: Ingame Day - Applies every morning ingame. This only applies to only online players " +
                "\n 3: Every 24 hours - Coming Soon (TM)"
        );
        combatProtectionCannotAffordCostText = new StringConfigObject("protection.pvp.lang.no_money_text", "&cYou don't have enough money to buy PvP protection! The cost is {cost}.", "\nThe text that will be displayed to the player when they try to purchase combat protection but do not have enough money. You can use {cost} and {upkeep} as variables");
        combatProtectionAlreadyOwned = new StringConfigObject("protection.pvp.lang.already_owned", "&cYou have either new player or death protection active. Type &e/togglepvp force'&c to disable your current protection", "\nThe text that will be displayed to the player when they try to purchase combat protection but has temp protection.");
        combatProtectionUpkeepHour = new IntegerConfigObject("protection.pvp.upkeep.hour", 3,"What local hour should the upkeep occur? This is used if using option 3");
        combatPvpNotResidentText = new StringConfigObject("protection.pvp.lang.not_resident_text", "&cYou must be a resident of a town to use PvP protection.", "\nThe text that will be displayed to the player when they try to purchase combat protection but are not part of a town");
        combatPvpProtectionUpkeepExpiredText = new StringConfigObject("protection.pvp.lang.upkeep_expired_text", "&cYour PvP protection has expired due to lack of funds.", "\nThe text that will be displayed to the player when their combat protection expires while ingame. You can use {cost} and {upkeep} as variables");
        combatPvpProtectionLoginExpireText = new StringConfigObject("protection.pvp.lang.login_expire_text", "&cYour PvP protection has expired while offline due to a lack of funds.", "\nThe text that will be displayed to the player when their combat protection expires after logging in.");
        combatProtectionUpkeepWarning = new BooleanConfigObject("protection.pvp.upkeep.warn", false, "Should players be warned if they will not be able to pay for the next upkeep? (Default: true)");
        combatPvpProtectionUpkeepWarnText = new StringConfigObject("protection.pvp.lang.upkeep_warn_text", "&cYou do not have enough money to pay for your PvP protection! It will expire soon!", "\nThe text that will be displayed to the player when they cannot afford upkeep. You can use {cost} and {upkeep} as variables");
        combatPvpProtectionCantAttackTargetText = new StringConfigObject("protection.pvp.lang.cant_attack_target_text", "&cYou cannot attack targets with PvP Protection enabled.", "\nThe text that will be displayed to the player when they attempt to attack another player who has pvp protection enabled.");
        combatPvpProtectionCantAttackWithProtectionText = new StringConfigObject("protection.pvp.lang.cant_attack_with_protection_text", "&cYou cannot attack other players while you have PvP protection enabled.", "\nThe text that will be displayed to the player when they attempt to attack another player with pvp protection enabled.");
        //on join
        enableNewPlayerProtection = new BooleanConfigObject("protection.join.enabled", true, "Should players receive protection when they first join the server? (Default: True)");
        newPlayerProtectionDuration = new IntegerConfigObject("protection.join.duration", 180, "How long in minutes of gameplay should the a new player's protection last after joining? (Default: 180)");
        newPlayerProtectionLoginText = new StringConfigObject("protection.join.lang.login_text", "&aWelcome to the server! You have been granted temporary PvP protection for {duration} minutes. You can opt out by typing &e/togglepvp force",  "\nThe text that will be displayed to the player when they log into the server and receive protection. \nUse {duration} as a placeholder for the duration.");
        newPlayerProtectionExpireText = new StringConfigObject("protection.join.lang.expire_text", "&cYour New Player PvP protection has expired.", "\nThe text that will be displayed to the player when their join protection expires.");

        //respawn protection
        enableRespawnProtection = new BooleanConfigObject("protection.respawn.enabled", true, "Should players receive protection upon respawn? (Default: True)");
        respawnProtectionDuration = new IntegerConfigObject("protection.respawn.duration", 60, "How long in seconds should a player have pvp protection afterdeath? (Default: 60)");
        respawnProtectionRespawnText = new StringConfigObject("protection.respawn.lang.respawn_text", "&aYou have been granted temporary PvP protection for {duration} seconds. You can opt out by typing &e/togglepvp force", "\nThe text that will be displayed to the player when they respawn and receive protection.\n Use {duration} as a placeholder for the duration.");
        respawnProtectionRespawnExpireText = new StringConfigObject("protection.respawn.lang.expire_text", "&cYour Respawn PvP protection has expired.", "\nThe text that will be displayed to the player when their respawn protection expires.");
        keepInventoryOnDeath = new BooleanConfigObject("protection.inventory.keep_inventory_on_death", true, "Should players keep their inventory when they die if they have protection? \nNOTE: If this has towny installed, this will only apply to the wilderness");
        keepInventoryInPeacefulTowns = new BooleanConfigObject("protection.inventory.keep_inventory_in_peaceful_towns", true, "If towny is installed, should players keep their inventory when they die in a non-war zone town area?");
        registerConfigObject(enableCombatProtection);
        registerConfigObject(combatProtectionCost);
        registerConfigObject(combatPvpProtectionEnableText);
        registerConfigObject(combatPvpProtectionDisableText);
        registerConfigObject(combatPvpProtectionEnableOnLogin);
        registerConfigObject(combatPvpProtectionPurchaseReminder);
        registerConfigObject(combatProtectionUpkeep);
        registerConfigObject(combatProtectionUpkeepType);
        registerConfigObject(combatPvpNotResidentText);
        registerConfigObject(combatPvpProtectionUpkeepExpiredText);
        registerConfigObject(combatPvpProtectionLoginExpireText);
        registerConfigObject(combatProtectionUpkeepWarning);
        registerConfigObject(combatPvpProtectionUpkeepWarnText);
        registerConfigObject(enableNewPlayerProtection);
        registerConfigObject(newPlayerProtectionDuration);
        registerConfigObject(newPlayerProtectionLoginText);
        registerConfigObject(newPlayerProtectionExpireText);
        registerConfigObject(enableRespawnProtection);
        registerConfigObject(respawnProtectionDuration);
        registerConfigObject(respawnProtectionRespawnText);
        registerConfigObject(respawnProtectionRespawnExpireText);
        registerConfigObject(combatPvpProtectionCantAttackWithProtectionText);
        registerConfigObject(combatPvpProtectionCantAttackTargetText);
        registerConfigObject(combatProtectionUpkeepHour);
        registerConfigObject(combatProtectionApplyUpkeepText);
        registerConfigObject(combatProtectionCannotAffordCostText);
        registerConfigObject(keepInventoryOnDeath);
        registerConfigObject(keepInventoryInPeacefulTowns);
    }


        // <AUTO-GENERATED-GETTERS-START>
    public Boolean getEnableCombatProtection() {
        return this.enableCombatProtection.getValue();
    }

    public Integer getCombatProtectionCost() {
        return this.combatProtectionCost.getValue();
    }

    public String getCombatPvpProtectionEnableText() {
        return this.combatPvpProtectionEnableText.getValue();
    }

    public String getCombatPvpProtectionDisableText() {
        return this.combatPvpProtectionDisableText.getValue();
    }

    public String getCombatPvpProtectionEnableOnLogin() {
        return this.combatPvpProtectionEnableOnLogin.getValue();
    }

    public String getCombatPvpProtectionPurchaseReminder() {
        return this.combatPvpProtectionPurchaseReminder.getValue();
    }

    public Integer getCombatProtectionUpkeep() {
        return this.combatProtectionUpkeep.getValue();
    }

    public Integer getCombatProtectionUpkeepType() {
        return this.combatProtectionUpkeepType.getValue();
    }

    public String getCombatPvpNotResidentText() {
        return this.combatPvpNotResidentText.getValue();
    }

    public String getCombatPvpProtectionUpkeepExpiredText() {
        return this.combatPvpProtectionUpkeepExpiredText.getValue();
    }

    public String getCombatPvpProtectionLoginExpireText() {
        return this.combatPvpProtectionLoginExpireText.getValue();
    }

    public Boolean getCombatProtectionUpkeepWarning() {
        return this.combatProtectionUpkeepWarning.getValue();
    }

    public String getCombatPvpProtectionUpkeepWarnText() {
        return this.combatPvpProtectionUpkeepWarnText.getValue();
    }

    public Boolean getEnableNewPlayerProtection() {
        return this.enableNewPlayerProtection.getValue();
    }

    public Integer getNewPlayerProtectionDuration() {
        return this.newPlayerProtectionDuration.getValue();
    }

    public String getNewPlayerProtectionLoginText() {
        return this.newPlayerProtectionLoginText.getValue();
    }

    public String getNewPlayerProtectionExpireText() {
        return this.newPlayerProtectionExpireText.getValue();
    }

    public Boolean getEnableRespawnProtection() {
        return this.enableRespawnProtection.getValue();
    }

    public Integer getRespawnProtectionDuration() {
        return this.respawnProtectionDuration.getValue();
    }

    public String getRespawnProtectionRespawnText() {
        return this.respawnProtectionRespawnText.getValue();
    }

    public String getRespawnProtectionRespawnExpireText() {
        return this.respawnProtectionRespawnExpireText.getValue();
    }
    public String getCombatPvpProtectionCantAttackTargetText() {
        return this.combatPvpProtectionCantAttackTargetText.getValue();
    }

    public String getCombatPvpProtectionCantAttackWithProtectionText() {
        return this.combatPvpProtectionCantAttackWithProtectionText.getValue();
    }
    public String getCombatProtectionApplyUpkeepText(){
        return this.combatProtectionApplyUpkeepText.getValue();
    }
    public String getCombatProtectionAlreadyOwned(){
        return this.combatProtectionAlreadyOwned.getValue();
    }
    public String getCombatProtectionCannotAffordCostText(){
        return this.combatProtectionCannotAffordCostText.getValue();
    }
    public Boolean getKeepInventoryOnDeath(){
        return this.keepInventoryOnDeath.getValue();
    }
    public Boolean getKeepInventoryInPeacefulTowns(){
        return this.keepInventoryInPeacefulTowns.getValue();
    }
    // <AUTO-GENERATED-GETTERS-END>
}
