package com.newgo.domain.produto.exceptions;

public class DataInvalidaException extends IllegalArgumentException {
    public DataInvalidaException(MensagensProdutoExceptions s) {
        super(s.getMensagem());
    }
}
