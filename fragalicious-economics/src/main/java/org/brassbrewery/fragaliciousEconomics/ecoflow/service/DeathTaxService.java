package org.brassbrewery.fragaliciousEconomics.ecoflow.service;

import org.brassbrewery.fragaliciousCore.FragaliciousCore;
import org.brassbrewery.fragaliciousCore.exceptions.ModuleNotLoadedException;
import org.brassbrewery.fragaliciousCore.util.MessageUtil;
import org.brassbrewery.fragaliciousEconomics.ecoflow.EcoFlowModule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.math.BigDecimal;

public class DeathTaxService extends EcoFlowService {

    public DeathTaxService(EcoFlowModule ecoFlowModule) {
        super(ecoFlowModule);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerDeath(PlayerDeathEvent event){
        if(!getConfig().isDeathTaxEnabled()){
            return; //dont do anything if disabled
        }
        Player player = event.getEntity();
        try {
            BigDecimal bankAmount = FragaliciousCore.economyAPI().getBalance(player);
            if(bankAmount.doubleValue()  >= getConfig().getMinimumBalanceForDeathTax()){
                applyDeathTax(player, bankAmount);
            }
        } catch (ModuleNotLoadedException e) {
            warn("Failed to apply death tax as economy module was not loaded");
        }
    }

    private void applyDeathTax(Player player, BigDecimal balance){
        double doubleBalance = balance.intValue();
        int taxFloor = getConfig().getMinimumBalanceForDeathTax();
        int taxCeiling = getConfig().getMaximumLimitForDeathTax();
        double startingPercentage = getConfig().getMinScalePercentage();
        double maximumPercentage = getConfig().getMaxScalePercentage();
        double percentage;
        if(doubleBalance > taxCeiling){ //if the balance is above the ceiling, just use the ceiling
            percentage = maximumPercentage;
        }

        //see spreadsheet: =(B9-C9)/ (D9-C9) * (F9-E9) + E9
        percentage = ((doubleBalance-taxFloor)/(taxCeiling-taxFloor)) * (maximumPercentage-startingPercentage)+startingPercentage;
        double taxAmount = doubleBalance * percentage;
        try{
            FragaliciousCore.economyAPI().withdraw(doubleBalance, player);
            if(getConfig().isSendDeathTaxTextEnabled()){
                MessageUtil.sendMessage(player, replaceVariables(getConfig().getDeathTaxText(), taxAmount));
            }
            fine("Player " + player.getName() + " was taxed " + taxAmount + " due to dying");
        }catch(ModuleNotLoadedException e){
            warn("Failed to withdraw death tax from player " + player.getName() + " account");
        }
    }
}
