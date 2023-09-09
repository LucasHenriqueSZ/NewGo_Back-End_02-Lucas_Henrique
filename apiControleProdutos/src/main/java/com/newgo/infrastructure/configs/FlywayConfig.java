package com.newgo.infrastructure.configs;

import org.flywaydb.core.Flyway;

import java.util.Properties;

public class FlywayConfig {
    public static void executarMigrations() {
        Properties properties = CarregaProperties.carregarProperties();
        Flyway flyway = Flyway.configure()
                .dataSource(
                        properties.getProperty("db.url"),
                        properties.getProperty("db.usuario"),
                        properties.getProperty("db.senha")
                ).locations("db/migrations")
                .load();

        flyway.migrate();
    }
}
