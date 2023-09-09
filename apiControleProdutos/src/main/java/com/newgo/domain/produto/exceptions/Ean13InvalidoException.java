package com.newgo.domain.produto.exceptions;

public class Ean13InvalidoException extends IllegalArgumentException {

    public Ean13InvalidoException(MensagensProdutoExceptions s) {
        super(s.getMensagem());
    }

}
