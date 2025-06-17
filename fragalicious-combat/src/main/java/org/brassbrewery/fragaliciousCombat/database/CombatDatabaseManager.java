package org.brassbrewery.fragaliciousCombat.database;

import org.brassbrewery.fragaliciousCombat.FragaliciousCombat;
import org.brassbrewery.fragaliciousCombat.database.protectePlayerDao.MariaDbProtectedPlayerDao;
import org.brassbrewery.fragaliciousCombat.database.protectePlayerDao.SqliteProtectedPlayerDao;
import org.brassbrewery.fragaliciousCore.database.DataSourceManager;


public class CombatDatabaseManager extends DataSourceManager {
    public CombatDatabaseManager(FragaliciousCombat fragaliciousCombat) {
        super(fragaliciousCombat);
    }

    @Override
    protected void initalizeMySqlDatabase() {
        DaoRegistry.initialize(new MariaDbProtectedPlayerDao(this, getTablePrefix()));
    }

    @Override
    protected void initializeSQLiteDatabase() {
        DaoRegistry.initialize( new SqliteProtectedPlayerDao(this, getTablePrefix()));
    }
}
