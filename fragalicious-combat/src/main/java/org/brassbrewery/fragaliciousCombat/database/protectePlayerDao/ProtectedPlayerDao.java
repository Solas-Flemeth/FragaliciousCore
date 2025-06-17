package org.brassbrewery.fragaliciousCombat.database.protectePlayerDao;

import org.brassbrewery.fragaliciousCombat.protection.data.ProtectedPlayer;

import java.util.List;
import java.util.UUID;

public interface ProtectedPlayerDao {

    /**
     * Creates the table for protected players.
     * DATABASE TABLE: PROTECTED_PLAYERS
     * UUID PLAYER_ID (PRIMARY KEY)
     * STRING PROTECTION_TYPE
     * INT DURATION
     * BOOLEAN NEED NOTIFICATION
     *
     * //long LAST LOGIN DATE
     * @return
     */
    public boolean createTable();


    /**
     * Adds a new protected player. If there already exists one with the same UUID,
     * it will be updated instead.
     *
     *
     * @param protectedPlayer The protected player object to save.
     *
     * @return The number of rows affected by this operation.
     */
    public Integer addOrUpdateProtectedPlayer(ProtectedPlayer protectedPlayer);

    /**
     * Adds a list of protected players. If there already exists one with the same UUID,
     * it will be updated instead.
     *
     * @param protectedPlayers The list of protected players to save.
     *
     * @return The number of rows affected by this operation.
     */
    public Integer addOrUpdateProtectedPlayer(List<ProtectedPlayer> protectedPlayers);

    /**
     * Gets a protected player by its UUID.
     *
     * @param uuid The UUID of the protected player to retrieve.
     *
     * @return The protected player object, or null if none were found.
     */
    public ProtectedPlayer getProtectedPlayerByUuid(UUID uuid);

    /**
     * Gets all protected players that have a PROTECTION_TYPE of PAID.
     *
     * @return A list of protected players.
     */
    public List<ProtectedPlayer> getProtectedPlayersWithUpkeep();


    /**
     * Deletes old protected players based on their last login date and the current time.
     *
     * @param time The amount of milliseconds since epoch to use as the cutoff point.
     *
     * @return The number of rows deleted.
     */
    public Integer deleteOldProtectedPlayers(long time);
}
