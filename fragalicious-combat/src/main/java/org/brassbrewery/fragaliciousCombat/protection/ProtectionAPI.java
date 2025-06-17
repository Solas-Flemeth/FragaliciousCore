package org.brassbrewery.fragaliciousCombat.protection;

import org.brassbrewery.fragaliciousCombat.protection.config.ProtectionConfig;
import org.brassbrewery.fragaliciousCombat.protection.data.ProtectedPlayer;
import org.brassbrewery.fragaliciousCombat.protection.data.ProtectionUpkeepType;
import org.brassbrewery.fragaliciousCore.exceptions.ModuleNotLoadedException;
import org.brassbrewery.fragaliciousCore.structure.FragaliciousAPI;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ProtectionAPI extends FragaliciousAPI<ProtectionModule> {
    public ProtectionAPI(ProtectionModule fragaliciouModule) {
        super(fragaliciouModule);
    }

    /**
     * @return the config object for ProtectionModule
     */
    public ProtectionConfig getConfig(){
        return getModule().getConfig();
    }

    /**
     * Runs the upkeep for all Protection Cost.You can bypass config by using the MANAUL option.
     * This is asynchronous.
     * @param protectionUpkeepType The type of upkeep to be executed. Use {@link ProtectionUpkeepType} for more information.
     *
     */
    public void runUpkeep(ProtectionUpkeepType protectionUpkeepType){
        this.getModule().getProtectionUpkeepService().validateCanRunUpkeep(protectionUpkeepType);
    }
    public void toggleProtection(Player player, boolean forced) throws ModuleNotLoadedException {
        this.getModule().getProtectionService().toggleProtection(player, forced);
    }
    public ProtectedPlayer getPlayer(UUID uuid){
        return this.getModule().getProtectionService().getPlayer(uuid);
        //getPlayer(UUID uuid);
    }
}
