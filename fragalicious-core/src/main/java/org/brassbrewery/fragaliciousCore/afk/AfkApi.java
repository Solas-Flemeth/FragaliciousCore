package org.brassbrewery.fragaliciousCore.afk;

import org.brassbrewery.fragaliciousCore.exceptions.UnknownPlayerException;
import org.brassbrewery.fragaliciousCore.structure.FragaliciousAPI;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AfkApi extends FragaliciousAPI<AfkModule> {
    public AfkApi(AfkModule fragaliciouModule) {
        super(fragaliciouModule);
    }

    /**
     * Checks if a player is currently AFK
     * @param player
     * @return true if they are AFK
     */
    public boolean isPlayerAfk(Player player){
        try {
            return getModule().getAfkService().isAfk(player.getUniqueId());
        } catch (UnknownPlayerException e) {
            return false; // Since passing a player, we should see it. Assume false if cannot find
        }

    }

    /**
     * Marks the player as AFK
     * @param player
     */
    public void setAfk(Player player){
        getModule().getAfkService().setAfk(player);
    }
}
