package com.newgo.domain.produto.exceptions;

public class ArgumentoNuloException extends IllegalArgumentException{

        public ArgumentoNuloException(MensagensProdutoExceptions s) {
            super(s.getMensagem());
        }
}
