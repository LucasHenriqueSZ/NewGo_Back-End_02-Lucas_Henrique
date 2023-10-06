package com.newgo.domain.produto.useCases;

import com.newgo.domain.produto.Produto;
import com.newgo.domain.produto.ProdutoRepository;
import com.newgo.domain.produto.useCases.exceptions.ConsultaProdutoException;
import com.newgo.domain.produto.useCases.exceptions.MensagensCasoDeUsoProdutoExceptions;

import java.util.List;

public class ConsultarTodosProdutosPorStatus {

    private static final String STATUS_PRODUTO_ATIVO = "ativo";
    private static final String STATUS_PRODUTO_INATIVO = "inativo";

    private final ProdutoRepository produtoRepository;

    public ConsultarTodosProdutosPorStatus(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> executar(String statusProduto) {
        boolean status = validarStatusProduto(statusProduto);
        return produtoRepository.consultarTodosPorStatus(status);
    }

    private boolean validarStatusProduto(String statusProduto) {
        if (statusProduto == null || statusProduto.isEmpty()) {
            throw new ConsultaProdutoException(MensagensCasoDeUsoProdutoExceptions.STATUS_PRODUTO_OBRIGATORIO);
        }
        if (!statusProduto.equalsIgnoreCase(STATUS_PRODUTO_ATIVO) && !statusProduto.equalsIgnoreCase(STATUS_PRODUTO_INATIVO)) {
            throw new ConsultaProdutoException(MensagensCasoDeUsoProdutoExceptions.STATUS_PRODUTO_INVALIDO);
        }
        return statusProduto.equalsIgnoreCase(STATUS_PRODUTO_ATIVO);
    }

}
