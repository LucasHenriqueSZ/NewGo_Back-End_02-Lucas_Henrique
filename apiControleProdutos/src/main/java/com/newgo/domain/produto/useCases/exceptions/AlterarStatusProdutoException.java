package com.newgo.domain.produto.useCases.exceptions;

public class AlterarStatusProdutoException extends IllegalArgumentException {
    public AlterarStatusProdutoException(MensagensCasoDeUsoProdutoExceptions s) {
        super(s.getMensagem());
    }
}
