package com.newgo.application.util;

import java.util.Objects;

public class ParserProdutoURL {

    private final String recurso;
    private boolean encontrado;

    private AcoesEndpoints acao;
    private String hashProduto;

    private String statusProduto;

    public ParserProdutoURL(String recurso) {
        this.recurso = Objects.requireNonNull(recurso, "O recurso é obrigatório");
        processarRecurso();
    }

    private void processarRecurso() {
        String[] recursos = recurso.split("/");

        encontrado = recursos[1].equalsIgnoreCase("produtos");

        if (!encontrado) {
            return;
        }

        String hashProd = (recursos.length > 2 ? recursos[2] : null);
        String acaoProd = (recursos.length > 3 ? recursos[3] : null);

        verificarAcaoEndpoint(hashProd, acaoProd);
    }

    private void verificarAcaoEndpoint(String hashProd, String acaoProd) {
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

}