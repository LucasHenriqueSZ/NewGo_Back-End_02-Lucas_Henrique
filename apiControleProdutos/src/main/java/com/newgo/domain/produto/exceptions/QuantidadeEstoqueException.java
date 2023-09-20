package com.newgo.domain.produto.exceptions;

public class QuantidadeEstoqueException extends IllegalArgumentException {

    public QuantidadeEstoqueException(MensagensProdutoExceptions s) {
        super(s.getMensagem());
    }
}
