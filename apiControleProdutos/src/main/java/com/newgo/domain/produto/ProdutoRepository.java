package com.newgo.domain.produto;

import java.util.UUID;

public interface ProdutoRepository {

    void cadastrar(Produto produto);

    void atualizar(Produto produto);

    Produto consultarPorEan13(String ean13);

    Produto consultarPorId(Long id);

    Produto consultarPorHash(UUID hash);

    Produto consultarPorNome(String nome);

    void excluir(Produto produto);
}
