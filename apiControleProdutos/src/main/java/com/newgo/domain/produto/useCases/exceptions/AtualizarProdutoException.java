package com.newgo.domain.produto.useCases.exceptions;

public class AtualizarProdutoException extends IllegalArgumentException {
    public AtualizarProdutoException(MensagensCasoDeUsoProdutoExceptions s) {
        super(s.getMensagem());
    }
}
