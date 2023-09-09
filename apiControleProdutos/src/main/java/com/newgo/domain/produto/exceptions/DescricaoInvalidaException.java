package com.newgo.domain.produto.exceptions;

public class DescricaoInvalidaException extends IllegalArgumentException {

    public DescricaoInvalidaException(MensagensProdutoExceptions s) {
        super(s.getMensagem());
    }
}
