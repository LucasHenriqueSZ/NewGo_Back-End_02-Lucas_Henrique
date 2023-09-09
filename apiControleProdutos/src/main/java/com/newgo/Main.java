package com.newgo;

import com.newgo.infrastructure.ServidorTomcat;
import com.newgo.infrastructure.configs.FlywayConfig;

public class Main {

    public static void main(String[] args) {
        FlywayConfig.executarMigrations();
        ServidorTomcat servidorTomcat = new ServidorTomcat();
        servidorTomcat.iniciar();
    }

}
