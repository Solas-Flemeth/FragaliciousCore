package org.brassbrewery.fragaliciousCore.economy;

import org.brassbrewery.fragaliciousCore.structure.FragaliciousAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.UUID;

public class BasicEconomyAPI extends FragaliciousAPI<EconomyModule> {
    public BasicEconomyAPI(EconomyModule fragaliciouModule) {
        super(fragaliciouModule);
    }
    public void deposit(double amount, Player player){
        getModule().getTransactionService().deposit(amount,player);
    }
    public void deposit(double amount, OfflinePlayer player){
        getModule().getTransactionService().deposit(amount,player);
    }
    public void deposit(double amount, UUID player){
        getModule().getTransactionService().deposit(amount,player);
    }
    public void withdraw(double amount, Player player){
        getModule().getTransactionService().withdraw(amount,player);
    }
    public void withdraw(double amount, OfflinePlayer player){
        getModule().getTransactionService().withdraw(amount,player);
    }
    public void withdraw(double amount, UUID player){
        getModule().getTransactionService().withdraw(amount,player);
    }
    public BigDecimal getBalance(Player player){
        return getModule().getTransactionService().getBalance(player);
    }
    public BigDecimal getBalance(OfflinePlayer player){
        return getModule().getTransactionService().getBalance(player);
    }
    public BigDecimal getBalance(UUID player){
        return getModule().getTransactionService().getBalance(player);
    }
    public boolean canAfford(double amount, Player player){
        return getModule().getTransactionService().canAfford(amount, player);
    }
    public boolean canAfford(double amount, OfflinePlayer player){
        return getModule().getTransactionService().canAfford(amount, player);
    }
    public boolean canAfford(double amount, UUID player){
        return getModule().getTransactionService().canAfford(amount, player);
    }
    // TRANSFER MONEY
    public boolean transfer(double amount, Player sender, Player receiver){
        return getModule().getTransactionService().transfer(amount, sender, receiver);
    }
    public boolean transfer(double amount, OfflinePlayer sender, OfflinePlayer receiver){
        return getModule().getTransactionService().transfer(amount, sender, receiver);
    }
    public boolean transfer(double amount, UUID sender, UUID receiver){
        return getModule().getTransactionService().transfer(amount, sender, receiver);
    }
    public boolean transfer(double amount, Player sender, Player receiver, float taxPercentage){
        return getModule().getTransactionService().transfer(amount, sender, receiver, taxPercentage);
    }
    public boolean transfer(double amount, OfflinePlayer sender, OfflinePlayer receiver,  float taxPercentage){
        return getModule().getTransactionService().transfer(amount, sender, receiver, taxPercentage);
    }
    public boolean transfer(double amount, UUID sender, UUID receiver,  float taxPercentage){
        return getModule().getTransactionService().transfer(amount, sender, receiver, taxPercentage);
    }
}
