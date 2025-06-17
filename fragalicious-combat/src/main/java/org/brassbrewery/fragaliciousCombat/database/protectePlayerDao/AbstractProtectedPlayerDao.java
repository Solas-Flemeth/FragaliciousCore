package org.brassbrewery.fragaliciousCombat.database.protectePlayerDao;

import org.brassbrewery.fragaliciousCombat.protection.data.ProtectedPlayer;
import org.brassbrewery.fragaliciousCore.database.DataSourceManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public abstract class AbstractProtectedPlayerDao implements ProtectedPlayerDao {

    protected final DataSourceManager dataSourceManager;
    protected final String table;

    protected AbstractProtectedPlayerDao(DataSourceManager dataSourceManager, String tablePrefix) {
        this.dataSourceManager = dataSourceManager;
        this.table = tablePrefix + "PROTECTED_PLAYERS";
    }

    protected ProtectedPlayer mapResultSet(ResultSet rs) throws SQLException {
        return new ProtectedPlayer(
                UUID.fromString(rs.getString("PLAYER_ID")),
                rs.getString("PROTECTION_TYPE"),
                rs.getInt("DURATION"),
                rs.getBoolean("NEED_NOTIFICATION"),
                rs.getLong("LAST_LOGIN")
        );
    }

    protected void bindPlayer(PreparedStatement stmt, ProtectedPlayer p) throws SQLException {
        stmt.setString(1, p.getPlayer().toString());
        stmt.setString(2, p.getProtectionType().name());
        stmt.setInt(3, p.getDuration());
        stmt.setBoolean(4, p.getStatusNotificationNeedsSent());
        stmt.setLong(5, p.getLastLogin());
    }
    protected Connection getConnection() throws SQLException {
        return dataSourceManager.getDataSource().getConnection();
    }
}

