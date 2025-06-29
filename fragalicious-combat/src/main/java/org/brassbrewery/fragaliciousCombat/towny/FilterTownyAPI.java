package org.brassbrewery.fragaliciousCombat.towny;

import org.brassbrewery.fragaliciousCore.structure.FragaliciousAPI;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class FilterTownyAPI extends FragaliciousAPI<TownyModule> {
    public FilterTownyAPI(TownyModule fragaliciouModule) {
        super(fragaliciouModule);
    }
    public boolean isPlayerInWilderness(Player player){
        return this.getModule().townyPlayerUtility.isPlayerInWilderness(player);
    }
    public boolean isPlayerInTown(Player player){
        return this.getModule().townyPlayerUtility.isPlayerInTown(player);
    }
    public boolean isPlayerInNation(Player player){
        return this.getModule().townyPlayerUtility.isPlayerInNation(player);
    }

    public boolean isLocationInWilderness(Location location){
        return this.getModule().townyPlayerUtility.isLocationInWilderness(location);
    }
    public boolean isInSafeTown(Location location){
        return this.getModule().townyPlayerUtility.isInSafeTown(location);
    }

}
