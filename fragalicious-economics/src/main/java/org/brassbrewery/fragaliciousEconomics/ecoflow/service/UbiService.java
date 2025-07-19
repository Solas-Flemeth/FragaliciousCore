package org.brassbrewery.fragaliciousEconomics.ecoflow.service;

import org.brassbrewery.fragaliciousCore.FragaliciousCore;
import org.brassbrewery.fragaliciousCore.exceptions.ModuleNotLoadedException;
import org.brassbrewery.fragaliciousCore.time.events.NewDayEvent;
import org.brassbrewery.fragaliciousCore.util.MessageUtil;
import org.brassbrewery.fragaliciousEconomics.ecoflow.EcoFlowModule;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import java.util.Collection;

public class UbiService extends EcoFlowService {


    public UbiService(EcoFlowModule ecoFlowModule) {
        super(ecoFlowModule);
    }

    @EventHandler
    public void onNewDay(NewDayEvent event){
        if(!getConfig().isUBIEnabled()){
            return;
        }
        boolean sendMessage = getConfig().isUbiSendTextEnabled();
        //normal info
        String message = getConfig().getUbiIncomeText();
        Integer amount = getConfig().getUbiIncome();
        //afk info
        String afkMessage = getConfig().getUbiAfkIncomeText();
        Integer afkAmount = getConfig().getUbiAfkIncome();
        Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
        try{
            int afkPlayers = 0;
            int totalUbi = 0;
            for (Player p : players){
                boolean isAfk = FragaliciousCore.getInstance().getAfkApi().isPlayerAfk(p);
                if(isAfk){
                    if(sendMessage){
                        MessageUtil.sendMessage(p, replaceVariables(afkMessage, afkAmount));
                    }
                    FragaliciousCore.economyAPI().deposit(afkAmount, p);
                    afkPlayers++;
                    totalUbi += afkAmount;
                }else{
                    if(sendMessage){
                        MessageUtil.sendMessage(p, replaceVariables(message, amount));
                    }
                    FragaliciousCore.economyAPI().deposit(amount, p);
                    totalUbi += amount;
                }
            }
            fine("Deposited  a total of $" + totalUbi + " over" + players.size() + "players.  ( " + afkPlayers + "players are AFK )");
        }catch (ModuleNotLoadedException e){
            warn("Could not apply UBI to players due to module(s) from core not being loaded. ");
        }
    }

}
