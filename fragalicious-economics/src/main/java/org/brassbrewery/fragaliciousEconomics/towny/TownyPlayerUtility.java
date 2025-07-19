package org.brassbrewery.fragaliciousEconomics.towny;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.event.NewDayEvent;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import org.brassbrewery.fragaliciousCore.exceptions.ModuleNotLoadedException;
import org.brassbrewery.fragaliciousEconomics.FragaliciousEconomics;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.UUID;

public class TownyPlayerUtility implements Listener {
    private TownyModule module;
    public TownyPlayerUtility(TownyModule townyModule){
        this.module = townyModule;
    }
    public boolean isPlayerInWilderness(Player player){
        return TownyAPI.getInstance().isWilderness(player.getLocation());
    }
    public boolean isPlayerInTown(Player player){
        return !this.isPlayerInWilderness(player);
    }
    public boolean isPlayerInNation(Player player){
        return this.isPlayerInTown(player) && TownyAPI.getInstance().getResident(player).getTownOrNull().hasNation();
    }
    public boolean isLocationInWilderness(Location location){
        return TownyAPI.getInstance().isWilderness(location);
    }
    public boolean isInSafeTown(Location location){
        Town town =  TownyAPI.getInstance().getTown(location);
        if(town == null){
            return false;
        }
        return town.hasActiveWar();
    }
    @EventHandler
    public void onNewDay(NewDayEvent e){
        try{
            module.fine("Attempting to run wealth tax");
            List<UUID> uuidList = TownyAPI.getInstance().getResidents().stream().map(Resident::getUUID).toList();
            FragaliciousEconomics.getInstance().getEcoFlowApi().runWealthTax(uuidList);
        } catch (ModuleNotLoadedException ignored) {
            module.warn("Unable to call run wealthtax because the EcoFlow module was not loaded");
        }
    }
}
