package org.brassbrewery.fragaliciousCombat.protection.utility;

import org.brassbrewery.fragaliciousCombat.protection.config.ProtectionConfig;
import org.brassbrewery.fragaliciousCombat.protection.data.ProtectedPlayer;

public abstract class ProtectionStringUtil {
    private ProtectionConfig config;
    public ProtectionStringUtil(ProtectionConfig protectionConfig){
        this.config = protectionConfig;
    }
    public String getTogglePvpMessage(ProtectedPlayer protectedPlayer, boolean isEnabled){
        if(isEnabled){
            return  getFilteredMessage(protectedPlayer, config.getCombatPvpProtectionEnableText());
        }else{ //
            return getFilteredMessage(protectedPlayer, config.getCombatPvpProtectionDisableText());
        }
    }
    public String pvpProtectionEnableOnLogin(ProtectedPlayer protectedPlayer){
        return getFilteredMessage(protectedPlayer,config.getCombatPvpProtectionEnableOnLogin());
    }
    public String getPvpPurchaseReminder(ProtectedPlayer protectedPlayer){
        return getFilteredMessage(protectedPlayer,config.getCombatPvpProtectionPurchaseReminder());
    }
    public String getNotResidentMessage(ProtectedPlayer protectedPlayer){
        return getFilteredMessage(protectedPlayer,config.getCombatPvpNotResidentText());
    }
    public String getUpkeepExpiredLoginMessage(ProtectedPlayer protectedPlayer){
        return getFilteredMessage(protectedPlayer, config.getCombatPvpProtectionLoginExpireText());
    }
    public String getUpkeepExpiredMessage(ProtectedPlayer protectedPlayer){
        return getFilteredMessage(protectedPlayer,config.getCombatPvpProtectionUpkeepExpiredText());
    }
    public String cannotAttackProtectedPlayer(ProtectedPlayer protectedPlayer){
        return getFilteredMessage(protectedPlayer,config.getCombatPvpProtectionCantAttackTargetText());
    }
    public String cannotAttackWhileProtected(ProtectedPlayer protectedPlayer){
        return getFilteredMessage(protectedPlayer,config.getCombatPvpProtectionCantAttackWithProtectionText());
    }
    public String cannotAffordUpkeepReminder(ProtectedPlayer protectedPlayer){
        return getFilteredMessage(protectedPlayer, config.getCombatPvpProtectionUpkeepWarnText());
    }
    public String newPlayerTempProtection(ProtectedPlayer protectedPlayer){
        return getFilteredMessage(protectedPlayer, config.getNewPlayerProtectionLoginText());
    }
    public String newPlayerProtectionExpire(ProtectedPlayer protectedPlayer){
        return getFilteredMessage(protectedPlayer, config.getNewPlayerProtectionExpireText());
    }
    public String respawnTempProtection(ProtectedPlayer protectedPlayer){
        return getFilteredMessage(protectedPlayer, config.getRespawnProtectionRespawnText());
    }
    public String respawnProtectionExpire(ProtectedPlayer protectedPlayer){
        return getFilteredMessage(protectedPlayer, config.getRespawnProtectionRespawnExpireText());
    }
    public String getUpkeepAppliedMessage(ProtectedPlayer protectedPlayer){
        return getFilteredMessage(protectedPlayer, config.getCombatProtectionApplyUpkeepText());
    }
    public String getAlreadyOwnsProtectionMessage(ProtectedPlayer protectedPlayer){
        return getFilteredMessage(protectedPlayer, config.getCombatProtectionAlreadyOwned());
    }
    public String getCannotAffordProtectionMessage(ProtectedPlayer protectedPlayer){
        return getFilteredMessage(protectedPlayer, config.getCombatProtectionCannotAffordCostText());
    }

    private String getFilteredMessage(ProtectedPlayer player, String message) {
        message = message.replace("{upkeep}", String.valueOf(config.getCombatProtectionUpkeep()));
        message = message.replace("{duration}", String.valueOf(player.getDuration()));
        message = message.replace("{cost}", String.valueOf(config.getCombatProtectionCost()));
        return message;
    }

    public ProtectionConfig getConfig() {
        return config;
    }
}
