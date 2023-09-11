package com.newgo.application.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newgo.application.util.json.configs.LocalDateTimeModule;

public class ConverteParaJson {
    public static String converter(Object objeto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new LocalDateTimeModule());
        return mapper.writeValueAsString(objeto);
    }
}
