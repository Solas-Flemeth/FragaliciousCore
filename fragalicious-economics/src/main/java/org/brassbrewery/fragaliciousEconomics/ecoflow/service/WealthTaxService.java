package org.brassbrewery.fragaliciousEconomics.ecoflow.service;

import org.brassbrewery.fragaliciousCore.FragaliciousCore;
import org.brassbrewery.fragaliciousCore.exceptions.ModuleNotLoadedException;
import org.brassbrewery.fragaliciousCore.util.MessageUtil;
import org.brassbrewery.fragaliciousEconomics.ecoflow.EcoFlowModule;

import java.util.List;
import java.util.UUID;

public class WealthTaxService extends EcoFlowService {

    public WealthTaxService(EcoFlowModule ecoFlowModule) {
        super(ecoFlowModule);
    }

    public void runWealthTax(List<UUID> playersUUIDs) {
        if(!getConfig().isWealthTaxEnabled()){
            warn("Skipping wealth tax because it's disabled in the config.");
            return;
        }
        try {
            fine("Starting wealth tax.");
            double totalTaxedAmount = 0.0; //how much was taxed overall
            int taxedPlayers = 0; //players taxed
            boolean sendMessage = getConfig().isSendWealthTaxTextEnabled();
            Integer balanceFloor = getConfig().getWealthTaxMinimumBalance();
            for (UUID uuid : playersUUIDs){
                Double balance = FragaliciousCore.economyAPI().getBalance(uuid).doubleValue();
                if(balance > balanceFloor){
                    double taxedAmount = calculateTax(balance);
                    FragaliciousCore.economyAPI().withdraw(taxedAmount, uuid);
                    if(sendMessage)
                    totalTaxedAmount += taxedAmount;
                    taxedPlayers++;
                }
            }
            if(getConfig().isSendWealthTaxBroadcastEnabled()){
                MessageUtil.broadcastMessage(replaceVariables(getConfig().getWealthTaxBroadcastText(), totalTaxedAmount, taxedPlayers));
            }
            fine("Taxed a total of " + taxedPlayers + " players out of " + playersUUIDs.size() + ". Total taxed was $" + totalTaxedAmount);
        }catch (ModuleNotLoadedException e){
            warn("Failed to apply wealth tax as economy module is not loaded. Please verify you have vault installed and configured correctly");
        }
    }
    private double calculateTax(Double balance){
        Integer balanceCeiling = getConfig().getWealthTaxMaximumLimit();
        Integer balanceFloor = getConfig().getWealthTaxMinimumBalance();
        double scaleFloor = getConfig().getWealthTaxMinScalePercentage();
        double scaleCeiling = getConfig().getWealthTaxMaxScalePercentage();
        double percentage;
        double balanceDif = balanceCeiling-balanceFloor;
        double scaleDif = scaleCeiling-scaleFloor;
        if(balance > balanceCeiling){
            percentage = scaleCeiling;
        }else{
            percentage = (scaleDif / balanceDif) * balance;
        }
        return percentage*balance;
    }

}
