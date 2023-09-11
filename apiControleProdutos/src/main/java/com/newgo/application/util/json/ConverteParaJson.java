package com.newgo.application.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newgo.application.util.json.configs.LocalDateTimeModule;
import com.newgo.domain.produto.Produto;

public class ConverteParaJson {
    public static String converter(Produto produto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new LocalDateTimeModule());
        return mapper.writeValueAsString(produto);
    }
}
