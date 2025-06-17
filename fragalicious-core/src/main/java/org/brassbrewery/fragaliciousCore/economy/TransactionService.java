package org.brassbrewery.fragaliciousCore.economy;

import net.milkbowl.vault2.economy.Economy;
import org.brassbrewery.fragaliciousCore.FragaliciousCore;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.math.BigDecimal;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class TransactionService {
    private Economy econ;
    private EconomyModule module;
    private String pluginName;
    public TransactionService(EconomyModule economyModule){
        pluginName = FragaliciousCore.getInstance().getName();
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            module.error("You must have Vault installed for the economy system to work!");
            throw new IllegalStateException("Vault not found");
        }
        econ = rsp.getProvider();

    }

    //DEPOSIT
    public void deposit(double amount, Player player){
        deposit(amount, player.getUniqueId());
    }
    public void deposit(double amount, OfflinePlayer player){
        deposit(amount, player.getUniqueId());
    }
    public void deposit(double amount, UUID player){
        econ.deposit(pluginName, player,  bigDecimal(amount));
    }
    // WITHDRAW
    public void withdraw(double amount, Player player){
        withdraw(amount, player.getUniqueId());
    }
    public void withdraw(double amount, OfflinePlayer player){
        withdraw(amount, player.getUniqueId());
    }
    public void withdraw(double amount, UUID player){
        econ.withdraw(pluginName, player, bigDecimal(amount));
    }
    // GET BALANCE
    public BigDecimal getBalance(Player player){return getBalance(player.getUniqueId());}
    public BigDecimal getBalance(OfflinePlayer player){return getBalance(player.getUniqueId());}
    public BigDecimal getBalance(UUID player){
        return econ.balance(pluginName, player);
    }
    // CAN AFFORD
    public boolean canAfford(double amount, Player player){
        return getBalance(player).doubleValue() >= amount;
    }
    public boolean canAfford(double amount, OfflinePlayer player){
        return getBalance(player).doubleValue() >= amount;
    }
    public boolean canAfford(double amount, UUID player){
        return getBalance(player).doubleValue() >= amount;
    }
    // TRANSFER MONEY
    public boolean transfer(double amount, Player sender, Player receiver){
        if(canAfford(amount, sender)){
            withdraw(amount, sender);
            deposit(amount, receiver);
            return true;
        }
        return false;
    }
    public boolean transfer(double amount, OfflinePlayer sender, OfflinePlayer receiver){
        if(canAfford(amount, sender)){
            withdraw(amount, sender);
            deposit(amount, receiver);
            return true;
        }
        return false;
    }
    public boolean transfer(double amount, UUID sender, UUID receiver){
        if(canAfford(amount, sender)){
            withdraw(amount, sender);
            deposit(amount, receiver);
            return true;
        }
        return false;
    }
    public boolean transfer(double amount, Player sender, Player receiver, float taxPercentage){
        double taxAmount = amount * taxPercentage;
        if(canAfford(amount, sender)){
            withdraw(amount, sender);
            deposit(amount-taxAmount, receiver);
            return true;
        }
        return false;
    }
    public boolean transfer(double amount, OfflinePlayer sender, OfflinePlayer receiver,  float taxPercentage){
        double taxAmount = amount * taxPercentage;
        if(canAfford(amount, sender)){
            withdraw(amount, sender);
            deposit(amount-taxAmount, receiver);
            return true;
        }
        return false;
    }
    public boolean transfer(double amount, UUID sender, UUID receiver,  float taxPercentage){
        double taxAmount = amount * taxPercentage;
        if(canAfford(amount, sender)){
            withdraw(amount, sender);
            deposit(amount-taxAmount, receiver);
            return true;
        }
        return false;
    }
    private BigDecimal bigDecimal(double amount){
        return BigDecimal.valueOf(amount);
    }
}
