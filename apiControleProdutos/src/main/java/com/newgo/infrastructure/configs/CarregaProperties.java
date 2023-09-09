package com.newgo.infrastructure.configs;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CarregaProperties {
    public static Properties carregarProperties() {
        Properties properties = new Properties();
        try (InputStream input = CarregaProperties.class.getClassLoader().getResourceAsStream(".properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                throw new RuntimeException("Arquivo não encontrado no classpath.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar as configurações.", e);
        }
        return properties;
    }
}
