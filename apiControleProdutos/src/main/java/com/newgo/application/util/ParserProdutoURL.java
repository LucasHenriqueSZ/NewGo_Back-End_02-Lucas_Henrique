package com.newgo.application.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ParserProdutoURL {

    private final String recurso;
    private final HashMap<String, String> queryParameters;
    private String filtro;
    private boolean encontrado;

    private AcoesEndpoints acao;
    private String hashProduto;

    private String statusProduto;

    public ParserProdutoURL(String recurso, HashMap<String, String> queryParameters) {
        this.recurso = Objects.requireNonNull(recurso, "O recurso é obrigatório");
        this.queryParameters = queryParameters;
        processarRecurso();
    }

    private void processarRecurso() {

        verificarFiltros();

        String[] recursos = recurso.split("/");

        encontrado = recursos[1].equalsIgnoreCase("produtos");

        if (!encontrado) {
            return;
        }

        String hashProd = (recursos.length > 2 ? recursos[2] : null);
        String acaoProd = (recursos.length > 3 ? recursos[3] : null);

        verificarAcaoEndpoint(hashProd, acaoProd);
    }

    private void verificarFiltros() {
        if (queryParameters == null || queryParameters.isEmpty()) {
            return;
        }

        List<String> filtrosInvalidos = new ArrayList<>();
        for (String queryParameter : queryParameters.keySet()) {
            if (queryParameter.equalsIgnoreCase("statusProduto")) {
                if (filtro != null) {
                    throw new IllegalArgumentException("Só é possivel utilizar um filtro por vez. " +
                            "Filtro(s) inválido(s): " + queryParameter);
                }
                filtro = queryParameters.get(queryParameter).toLowerCase();
            } else {
                filtrosInvalidos.add(queryParameter);
            }
        }

        if (!filtrosInvalidos.isEmpty())
            throw new IllegalArgumentException("Filtro(s) inválido(s): " + filtrosInvalidos);
    }

    private void verificarAcaoEndpoint(String hashProd, String acaoProd) {
        if (hashProd == null && acaoProd == null && filtro != null) {
            acao = AcoesEndpoints.PRODUTOS_FILTRADOS;
            return;
        }


        if (hashProd == null && acaoProd == null) {
            acao = AcoesEndpoints.PRODUTOS;
            return;
        }

        if (hashProd != null && acaoProd == null) {
            acao = AcoesEndpoints.PRODUTO_ESPECIFICO;
            hashProduto = hashProd;
            return;
        }

        if (hashProd != null && acaoProd != null) {
            if (acaoProd.equalsIgnoreCase("ativar") || acaoProd.equalsIgnoreCase("desativar")) {
                acao = AcoesEndpoints.ALTERAR_STATUS_PRODUTO;
                hashProduto = hashProd;
                statusProduto = acaoProd.toLowerCase();
                return;
            }
        }

        throw new IllegalArgumentException("Recurso inválido");
    }

    public String getHashProduto() {
        return hashProduto;
    }

    public String getStatusProduto() {
        return statusProduto;
    }

    public String getFiltro() {
        return filtro;
    }

    public boolean encontrado() {
        return encontrado;
    }

    public boolean endpointProdutos() {
        return acao == AcoesEndpoints.PRODUTOS;
    }

    public boolean endpointProdutoEspecifico() {
        return acao == AcoesEndpoints.PRODUTO_ESPECIFICO;
    }

    public boolean endpointAlterarStatusProduto() {
        return acao == AcoesEndpoints.ALTERAR_STATUS_PRODUTO;
    }

    public boolean endpointProdutosFiltrados() {
        return acao == AcoesEndpoints.PRODUTOS_FILTRADOS;
    }

}