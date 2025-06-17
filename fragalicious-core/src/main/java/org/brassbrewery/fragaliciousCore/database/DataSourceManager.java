package org.brassbrewery.fragaliciousCore.database;

import com.zaxxer.hikari.HikariConfig;
import org.brassbrewery.fragaliciousCore.structure.FragaliciousLogger;
import org.brassbrewery.fragaliciousCore.structure.FragaliciousPlugin;

import javax.sql.DataSource;
import java.io.File;

public abstract class DataSourceManager {
    private FragaliciousPlugin fragaliciousPlugin;
    private FragaliciousLogger logger;
    private HikariConfig hikariConfig;
    private DataSource dataSource;
    private DatabaseConfig databaseConfig;
    public DataSourceManager(FragaliciousPlugin plugin) {
        this.fragaliciousPlugin = plugin;
        hikariConfig = new HikariConfig();
        logger = new FragaliciousLogger(plugin, "database");
        databaseConfig = new DatabaseConfig(plugin, "database");
        if(databaseConfig.getUseMYSQL()){
            dataSource = getSqlDataSource();
            initalizeMySqlDatabase();
        }else{
            dataSource = getSqlLiteDataSource();
            initializeSQLiteDatabase();
        }
    }
    public DataSource getSqlDataSource(){
        logger.fine("Creating MySql Connection");
        hikariConfig.setJdbcUrl(databaseConfig.getJdbcUrl());
        hikariConfig.setUsername(databaseConfig.getUsername());
        hikariConfig.setPassword(databaseConfig.getPassword());
        hikariConfig.setMaximumPoolSize(databaseConfig.getPoolSize());
        hikariConfig.setConnectionTimeout(databaseConfig.getConnectionTimeout());
        hikariConfig.setMaxLifetime(databaseConfig.getMaxLifetime());
        return new com.zaxxer.hikari.HikariDataSource(hikariConfig);
    }
    public DataSource getSqlLiteDataSource() {
        logger.fine("Creating SQLite Connection");
        File file = new File(fragaliciousPlugin.getDataFolder(), "database.db");
        String jdbcUrl = "jdbc:sqlite:" + file.getAbsolutePath();

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setMaximumPoolSize(1); // set to 1 due to issue of read/write corruption
        config.setConnectionTestQuery("SELECT 1");
        config.setPoolName("Fragalicious-SQLite-Pool-" + fragaliciousPlugin.getName());

        return new com.zaxxer.hikari.HikariDataSource(config);
    }

    protected abstract void initalizeMySqlDatabase();
    protected abstract void initializeSQLiteDatabase();
    public DataSource getDataSource() {
        return dataSource;
    }

    public String getTablePrefix(){
        return databaseConfig.getTablePrefix();
    }
}