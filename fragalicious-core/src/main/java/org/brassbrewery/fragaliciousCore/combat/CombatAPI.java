package org.brassbrewery.fragaliciousCore.combat;

import org.brassbrewery.fragaliciousCore.structure.FragaliciousAPI;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CombatAPI extends FragaliciousAPI<CombatModule> {
    public CombatAPI(CombatModule combatModule) {
        super(combatModule);
    }

    /**
     * Checks what the default timer a player has to be without combat to leave combat
     * @return the value set in the configuration file
     */
    public Integer getCombatTimer(){
        return getModule().getConfig().getCombatTimer();
    }

    /**
     * Check whether the player is currently in pvp combat
     * @param player
     * @return true if in combat, false if not in combat
     */
    public Boolean isInCombat(Player player){
        return getModule().getPvpCheckerService().isInCombat(player);
    }

    /**
     * Check whether the player is currently in pvp combat
     * @param uuid
     * @return true if in combat, false if not in combat
     */
    public Boolean isInCombat(UUID uuid){
        return getModule().getPvpCheckerService().isInCombat(uuid);
    }

    /**
     * Gets how much time till a player leaves combat
     * @param player
     * @return time remaining. If player is not in combat, will return 0
     */
    public Integer getTimeTillOutOfCombat(Player player){
        return getModule().getPvpCheckerService().getTimeTillOutOfCombat(player);
    }

    /**
     * Gets how much time till a player leaves combat
     * @param uuid
     * @return time remaining. If player is not in combat, will return 0
     */
    public Integer getTimeTillOutOfCombat(UUID uuid){
        return getModule().getPvpCheckerService().getTimeTillOutOfCombat(uuid);
    }

}
