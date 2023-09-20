package com.newgo.domain.produto.useCases.exceptions;

public class ProdutoInvalidoException extends IllegalArgumentException {
    public ProdutoInvalidoException(MensagensCasoDeUsoProdutoExceptions s) {
        super(s.getMensagem());
    }
}
