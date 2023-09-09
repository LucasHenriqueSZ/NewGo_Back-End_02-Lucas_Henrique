package com.newgo.domain.produto.exceptions;

public class HashInvalidoException extends IllegalArgumentException {
    public HashInvalidoException(MensagensProdutoExceptions s) {
        super(s.getMensagem());
    }
}
