package org.brassbrewery.fragaliciousCombat.database;

import org.brassbrewery.fragaliciousCombat.database.protectePlayerDao.ProtectedPlayerDao;

public final class DaoRegistry {
    private static DaoRegistry instance;

    private final ProtectedPlayerDao protectedPlayerDao;

    private DaoRegistry(ProtectedPlayerDao protectedPlayerDao) {
        this.protectedPlayerDao = protectedPlayerDao;
    }

    public static void initialize(ProtectedPlayerDao protectedPlayerDao) {
        if (instance != null) throw new IllegalStateException("DAO registry already initialized.");
        instance = new DaoRegistry(protectedPlayerDao);
        protectedPlayerDao.createTable();
    }

    public static DaoRegistry get() {
        if (instance == null) throw new IllegalStateException("DAO registry not initialized yet.");
        return instance;
    }

    public ProtectedPlayerDao protectedPlayerDao() {
        return protectedPlayerDao;
    }
}
