package com.newgo.application.util.json.configs;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.newgo.application.util.json.configs.jsonModules.LocalDateTimeDeserializer;
import com.newgo.application.util.json.configs.jsonModules.LocalDateTimeSerializer;

import java.time.LocalDateTime;


public class LocalDateTimeModule extends SimpleModule {
    public LocalDateTimeModule() {
        addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
    }
}
