package com.newgo.domain.produto.useCases.util;

import com.newgo.domain.produto.useCases.exceptions.AtualizarProdutoException;
import com.newgo.domain.produto.useCases.exceptions.MensagensCasoDeUsoProdutoExceptions;

import java.util.UUID;

public class ConversorUUID {

    public static UUID converteStringParaUUID(String hash) {
        try {
            return UUID.fromString(hash);
        } catch (Exception e) {
            throw new AtualizarProdutoException(MensagensCasoDeUsoProdutoExceptions.HASH_INVALIDO);
        }
    }

    public static String converteUUIDParaString(UUID hash) {
        try {
            return hash.toString();
        } catch (Exception e) {
            throw new AtualizarProdutoException(MensagensCasoDeUsoProdutoExceptions.HASH_INVALIDO);
        }
    }
}
