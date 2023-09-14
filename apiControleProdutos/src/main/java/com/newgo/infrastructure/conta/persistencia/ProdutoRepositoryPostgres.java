package com.newgo.infrastructure.conta.persistencia;

import com.newgo.domain.produto.Produto;
import com.newgo.domain.produto.ProdutoRepository;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.UUID;

public class ProdutoRepositoryPostgres implements ProdutoRepository {

    private final Connection conexao;

    public ProdutoRepositoryPostgres(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void cadastrar(Produto produto) throws SQLException {
        PreparedStatement sql = null;
        try {
            conexao.setAutoCommit(false);
            sql = conexao.prepareStatement(
                    "INSERT INTO "
                            + "				produtos "
                            + "				(hash, nome, descricao, ean13, preco, quantidade, estoque_min, dtcreate, dtupdate, l_ativo)"
                            + "VALUES		(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            sql.setObject(1, produto.getHash());
            sql.setString(2, produto.getNome());
            sql.setString(3, produto.getDescricao());
            sql.setString(4, produto.getEan13());
            sql.setDouble(5, produto.getPreco().doubleValue());
            sql.setInt(6, produto.getQuantidade());
            sql.setInt(7, produto.getEstoque_min());
            sql.setObject(8, produto.getDtcreate());
            sql.setObject(9, produto.getDtupdate());
            sql.setBoolean(10, produto.isL_ativo());

            sql.execute();
            conexao.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            conexao.rollback();
        } finally {
            sql.close();
        }
    }

    @Override
    public void atualizar(Produto produto) throws SQLException {
        PreparedStatement sql = null;
        try {
            conexao.setAutoCommit(false);
            sql = conexao.prepareStatement(
                    "UPDATE produtos SET  descricao = ?, preco = ?, quantidade = ?," +
                            " estoque_min = ?, dtupdate = ? WHERE hash = ?");

            sql.setString(1, produto.getDescricao());
            sql.setDouble(2, produto.getPreco().doubleValue());
            sql.setInt(3, produto.getQuantidade());
            sql.setInt(4, produto.getEstoque_min());
            sql.setObject(5, produto.getDtupdate());
            sql.setObject(6, produto.getHash());

            sql.execute();
            conexao.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            conexao.rollback();
        } finally {
            sql.close();
        }
    }

    @Override
    public Produto consultarPorEan13(String ean13) {
        PreparedStatement sql = null;
        try {
            sql = conexao.prepareStatement("SELECT 	* FROM 	produtos WHERE	ean13 = ?");
            sql.setObject(1, ean13);
            ResultSet rs = sql.executeQuery();
            Produto produto = null;
            if (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("dtupdate");
                LocalDateTime dtupdate = (timestamp != null) ? timestamp.toLocalDateTime() : null;

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
                        dtupdate,
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
                Timestamp timestamp = rs.getTimestamp("dtupdate");
                LocalDateTime dtupdate = (timestamp != null) ? timestamp.toLocalDateTime() : null;

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
                        dtupdate,
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
        PreparedStatement sql = null;
        try {
            sql = conexao.prepareStatement("SELECT 	* FROM 	produtos WHERE	nome = ?");
            sql.setObject(1, nome);
            ResultSet rs = sql.executeQuery();
            Produto produto = null;
            if (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("dtupdate");
                LocalDateTime dtupdate = (timestamp != null) ? timestamp.toLocalDateTime() : null;

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
                        dtupdate,
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
    public void excluir(Produto produto) throws SQLException {
        PreparedStatement sql = null;
        try {
            conexao.setAutoCommit(false);
            sql = conexao.prepareStatement("DELETE FROM produtos WHERE hash = ?");
            sql.setObject(1, produto.getHash());
            sql.execute();
            conexao.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            conexao.rollback();
        } finally {
            sql.close();
        }
    }
}
