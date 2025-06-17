package org.brassbrewery.fragaliciousCombat.database.protectePlayerDao;

import org.brassbrewery.fragaliciousCombat.protection.data.ProtectedPlayer;
import org.brassbrewery.fragaliciousCombat.protection.data.ProtectionType;
import org.brassbrewery.fragaliciousCore.database.DataSourceManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SqliteProtectedPlayerDao extends AbstractProtectedPlayerDao {

    public SqliteProtectedPlayerDao(DataSourceManager dataSourceManager, String tablePrefix) {
        super(dataSourceManager, tablePrefix);
    }

    @Override
    public boolean createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + table + " ("
                + "PLAYER_ID TEXT PRIMARY KEY,"
                + "PROTECTION_TYPE TEXT,"
                + "DURATION INTEGER,"
                + "NEED_NOTIFICATION INTEGER,"
                + "LAST_LOGIN INTEGER"
                + ")";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Integer addOrUpdateProtectedPlayer(ProtectedPlayer p) {
        String sql = "INSERT INTO " + table + " (PLAYER_ID, PROTECTION_TYPE, DURATION, NEED_NOTIFICATION, LAST_LOGIN) "
                + "VALUES (?, ?, ?, ?, ?) "
                + "ON CONFLICT(PLAYER_ID) DO UPDATE SET "
                + "PROTECTION_TYPE=excluded.PROTECTION_TYPE, "
                + "DURATION=excluded.DURATION, "
                + "NEED_NOTIFICATION=excluded.NEED_NOTIFICATION, "
                + "LAST_LOGIN=excluded.LAST_LOGIN";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            bindPlayer(stmt, p);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public ProtectedPlayer getProtectedPlayerByUuid(UUID uuid) {
        String sql = "SELECT PLAYER_ID, PROTECTION_TYPE, DURATION, NEED_NOTIFICATION, LAST_LOGIN " +
                "FROM " + table + " WHERE PLAYER_ID = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, uuid.toString());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ProtectedPlayer(
                            UUID.fromString(rs.getString("PLAYER_ID")),
                            rs.getString("PROTECTION_TYPE"),
                            rs.getInt("DURATION"),
                            rs.getInt("NEED_NOTIFICATION") != 0,
                            rs.getLong("LAST_LOGIN")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle appropriately
        }

        // Not found: return new player with default state
        return new ProtectedPlayer(uuid, "NONE", 0, false, System.currentTimeMillis());
    }


    @Override
    public Integer addOrUpdateProtectedPlayer(List<ProtectedPlayer> players) {
        String sql = "INSERT INTO " + table + " (PLAYER_ID, PROTECTION_TYPE, DURATION, NEED_NOTIFICATION, LAST_LOGIN) "
                + "VALUES (?, ?, ?, ?, ?) "
                + "ON CONFLICT(PLAYER_ID) DO UPDATE SET "
                + "PROTECTION_TYPE=excluded.PROTECTION_TYPE, "
                + "DURATION=excluded.DURATION, "
                + "NEED_NOTIFICATION=excluded.NEED_NOTIFICATION, "
                + "LAST_LOGIN=excluded.LAST_LOGIN";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            int count = 0;
            for (ProtectedPlayer p : players) {
                bindPlayer(stmt, p);
                stmt.addBatch();
            }
            int[] results = stmt.executeBatch();
            for (int r : results) count += r;
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<ProtectedPlayer> getProtectedPlayersWithUpkeep() {
        String sql = "SELECT * FROM " + table + " WHERE PROTECTION_TYPE = ?";
        List<ProtectedPlayer> result = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ProtectionType.PAID.name());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.add(mapResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer deleteOldProtectedPlayers(long time) {
        String sql = "DELETE FROM " + table + " WHERE LAST_LOGIN < ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, time);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}

