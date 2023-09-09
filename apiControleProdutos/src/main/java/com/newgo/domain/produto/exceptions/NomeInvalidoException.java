package com.newgo.domain.produto.exceptions;

public class NomeInvalidoException extends IllegalArgumentException {

    public NomeInvalidoException(MensagensProdutoExceptions s) {
        super(s.getMensagem());
    }
}
