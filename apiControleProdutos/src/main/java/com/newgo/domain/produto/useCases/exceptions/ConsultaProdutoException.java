package com.newgo.domain.produto.useCases.exceptions;

public class ConsultaProdutoException extends IllegalArgumentException {

    public ConsultaProdutoException(MensagensCasoDeUsoProdutoExceptions s) {
        super(s.getMensagem());
    }
}
