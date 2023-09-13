package com.newgo.application.util;

import com.newgo.domain.produto.ProdutoRepository;
import com.newgo.domain.produto.useCases.CadastrarProduto;
import com.newgo.domain.produto.useCases.ConsultarProduto;
import com.newgo.infrastructure.configs.CarregaProperties;
import com.newgo.infrastructure.conta.persistencia.ProdutoRepositoryPostgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class LocalizadorDeServico {

    private static ProdutoRepository produtoRepository() throws SQLException {
        return new ProdutoRepositoryPostgres(conexao());
    }

    public static ConsultarProduto consultaProduto() throws SQLException {
        return new ConsultarProduto(produtoRepository());
    }

    public static CadastrarProduto cadastrarProduto() throws SQLException {
        return new CadastrarProduto(produtoRepository());
    }


    private static Connection conexao() throws SQLException {
        Properties properties = CarregaProperties.carregarProperties();
        return DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.usuario"),
                properties.getProperty("db.senha"));
    }

}
