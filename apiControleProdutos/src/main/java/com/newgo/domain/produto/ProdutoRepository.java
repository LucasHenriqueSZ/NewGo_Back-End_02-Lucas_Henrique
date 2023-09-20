package com.newgo.domain.produto;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface ProdutoRepository {

    void cadastrar(Produto produto) throws SQLException;

    void atualizar(Produto produto) throws SQLException;

    Produto consultarPorEan13(String ean13);

    Produto consultarPorId(Long id);

    Produto consultarPorHash(UUID hash);

    Produto consultarPorNome(String nome);

    void excluir(Produto produto) throws SQLException;

    List<Produto> consultarTodos();
}
