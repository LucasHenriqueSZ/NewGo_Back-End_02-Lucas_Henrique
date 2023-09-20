package com.newgo.domain.produto.useCases.exceptions;

public class DeletarProdutoException extends IllegalArgumentException{
    public DeletarProdutoException(MensagensCasoDeUsoProdutoExceptions s) {
        super(s.getMensagem());
    }
}
