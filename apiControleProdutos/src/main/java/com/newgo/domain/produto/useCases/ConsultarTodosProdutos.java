package com.newgo.domain.produto.useCases;

import com.newgo.domain.produto.Produto;
import com.newgo.domain.produto.ProdutoRepository;

import java.util.List;

public class ConsultarTodosProdutos {

    private final ProdutoRepository produtoRepository;

    public ConsultarTodosProdutos(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> executar() {
        return produtoRepository.consultarTodos();
    }

}
