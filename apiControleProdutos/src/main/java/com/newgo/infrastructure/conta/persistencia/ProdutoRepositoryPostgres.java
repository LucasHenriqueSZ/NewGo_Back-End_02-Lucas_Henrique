package com.newgo.infrastructure.conta.persistencia;

import com.newgo.domain.produto.Produto;
import com.newgo.domain.produto.ProdutoRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ProdutoRepositoryPostgres implements ProdutoRepository {

    private final Connection conexao;

    public ProdutoRepositoryPostgres(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void cadastrar(Produto produto) {
        throw new RuntimeException("Não implementado");
    }

    @Override
    public void atualizar(Produto produto) {
        throw new RuntimeException("Não implementado");
    }

    @Override
    public Produto consultarPorEan13(String ean13) {
        throw new RuntimeException("Não implementado");
    }

    @Override
    public Produto consultarPorId(Long id) {
        throw new RuntimeException("Não implementado");
    }

    @Override
    public Produto consultarPorHash(UUID hash) {
        PreparedStatement sql = null;
        try {
            sql = conexao.prepareStatement("SELECT 	* FROM 	produtos WHERE	hash = ?");
            sql.setObject(1, hash);
            ResultSet rs = sql.executeQuery();
            Produto produto = null;
            if (rs.next()) {
                produto = new Produto(
                        rs.getLong("id"),
                        UUID.fromString(rs.getString("hash")),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getString("ean13"),
                        BigDecimal.valueOf(rs.getDouble("preco")),
                        rs.getInt("quantidade"),
                        rs.getInt("estoque_min"),
                        rs.getTimestamp("dtcreate").toLocalDateTime(),
                        rs.getTimestamp("dtupdate").toLocalDateTime(),
                        rs.getBoolean("l_ativo")
                );
            }

            rs.close();
            sql.close();
            return produto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Produto consultarPorNome(String nome) {
        throw new RuntimeException("Não implementado");
    }

    @Override
    public void excluir(Produto produto) {
        throw new RuntimeException("Não implementado");
    }
}
