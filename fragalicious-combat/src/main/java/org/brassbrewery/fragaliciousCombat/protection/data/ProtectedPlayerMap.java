package org.brassbrewery.fragaliciousCombat.protection.data;

import org.brassbrewery.fragaliciousCombat.database.DaoRegistry;
import org.bukkit.entity.Player;

import java.util.*;

public class ProtectedPlayerMap {
    private HashMap<UUID, ProtectedPlayer> playerProtectionMap;

    public ProtectedPlayerMap(){
        playerProtectionMap = new HashMap<>();
    }

    public boolean hasProtection(UUID uuid){
        if(playerProtectionMap.containsKey(uuid)){
            return playerProtectionMap.get(uuid).getProtectionType() != ProtectionType.NONE;
        }
        return false;
    }
    public boolean doesPlayerHaveProtection(UUID uuid) {
        return playerProtectionMap.containsKey(uuid);
    }
    public void addProtectedPlayer(ProtectedPlayer protectedPlayer) {
        playerProtectionMap.put(protectedPlayer.getPlayer(), protectedPlayer);
    }
    public void updateProtectedPlayer(ProtectedPlayer protectedPlayer){
        if(playerProtectionMap.containsKey(protectedPlayer.getPlayer())){
            playerProtectionMap.replace(protectedPlayer.getPlayer(), protectedPlayer);
        }
    }
    public ProtectedPlayer removeProtectedPlayer(UUID uuid) {
        return playerProtectionMap.remove(uuid);
    }
    public ProtectedPlayer getProtectedPlayer(UUID uuid) {
        return playerProtectionMap.get(uuid);
    }
    public ProtectedPlayer loadPlayerFromDatabase(Player player) {
        ProtectedPlayer protectedPlayer = DaoRegistry.get().protectedPlayerDao().getProtectedPlayerByUuid(player.getUniqueId());
        addProtectedPlayer(protectedPlayer);
        return getProtectedPlayer(protectedPlayer.getPlayer());
    }
    public int saveProtectedPlayersToDatabase(ProtectedPlayer player) {
        return DaoRegistry.get().protectedPlayerDao().addOrUpdateProtectedPlayer(player);
    }
    public int saveAndRemovePlayerFromDatabase(UUID playerId) {
        ProtectedPlayer protectedPlayer = removeProtectedPlayer(playerId);
        return saveProtectedPlayersToDatabase(protectedPlayer);
    }

    public List<ProtectedPlayer> getList() {
        return new ArrayList<ProtectedPlayer>(playerProtectionMap.values());
    }
    public List<ProtectedPlayer> getAllPaidProtectedPlayers() {
        List<ProtectedPlayer> paidProtectedPlayers = new ArrayList<>();
        for (ProtectedPlayer protectedPlayer : playerProtectionMap.values()) {
            if(protectedPlayer.getProtectionType().equals(ProtectionType.PAID)) {
                paidProtectedPlayers.add(protectedPlayer);
            }
        }
        return paidProtectedPlayers;
    }
    public Boolean containsPlayer(UUID uuid){return playerProtectionMap.containsKey(uuid);}
}
