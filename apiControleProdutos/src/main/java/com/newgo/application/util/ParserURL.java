package com.newgo.application.util;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserURL {

    private final String recurso;
    private boolean encontrado;

    private enum Acoes {PRODUTOS, PRODUTO_ESPECIFICO}

    private Acoes acao;
    private String hashProduto;

    public ParserURL(String recurso) {
        this.recurso = Objects.requireNonNull(recurso, "O recurso é obrigatório");
        processarRecurso();
    }

    private void processarRecurso() {
        Pattern regex = Pattern.compile("^\\/produtos(?:\\/([a-f0-9\\-]+))?$");
        Matcher matcher = regex.matcher(recurso);
        encontrado = matcher.find();

        if (!encontrado) {
            return;
        }

        String hashProd = matcher.group(1);

        if (hashProd != null) {
            hashProduto = hashProd;
            acao = Acoes.PRODUTO_ESPECIFICO;
        } else {
            acao = Acoes.PRODUTOS;
        }
    }

    public boolean encontrado() {
        return encontrado;
    }

    public boolean endpointProdutos() {
        return acao == Acoes.PRODUTOS;
    }

    public boolean endpointProdutoEspecifico() {
        return acao == Acoes.PRODUTO_ESPECIFICO;
    }

    public String getHashProduto() {
        return hashProduto;
    }

}