package com.newgo.domain.produto.exceptions;

public class PrecoInvalidoException extends IllegalArgumentException {

    public PrecoInvalidoException(MensagensProdutoExceptions s) {
        super(s.getMensagem());
    }
}
