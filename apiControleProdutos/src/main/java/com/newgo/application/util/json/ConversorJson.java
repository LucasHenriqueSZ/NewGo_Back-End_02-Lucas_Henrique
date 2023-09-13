package com.newgo.application.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newgo.application.util.json.configs.LocalDateTimeModule;

public class ConversorJson {
    public static String converterToJson(Object objeto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new LocalDateTimeModule());
        return mapper.writeValueAsString(objeto);
    }

    public static <T> T converterParaObjeto(String json, Class<T> classe) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new LocalDateTimeModule());
        return mapper.readValue(json, classe);
    }
}
