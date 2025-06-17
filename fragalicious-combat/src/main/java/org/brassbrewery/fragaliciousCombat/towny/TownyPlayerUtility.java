package org.brassbrewery.fragaliciousCombat.towny;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.event.NewDayEvent;
import org.brassbrewery.fragaliciousCombat.FragaliciousCombat;
import org.brassbrewery.fragaliciousCombat.protection.data.ProtectionUpkeepType;
import org.brassbrewery.fragaliciousCore.exceptions.ModuleNotLoadedException;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class TownyPlayerUtility implements Listener {
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
    public void onNewDay(NewDayEvent e){
        try{
            FragaliciousCombat.getInstance().getProtectionAPI().runUpkeep(ProtectionUpkeepType.TOWNY);
        } catch (ModuleNotLoadedException ignored) {
            //if it somehow fails, oh well, we just don't tax the people :)
        }
    }
}
