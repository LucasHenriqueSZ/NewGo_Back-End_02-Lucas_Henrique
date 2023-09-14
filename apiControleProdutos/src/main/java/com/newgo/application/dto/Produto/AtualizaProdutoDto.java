package com.newgo.application.dto.Produto;

public class AtualizaProdutoDto {

    private String descricao;
    private double preco;
    private int quantidade;
    private int estoque_min;

    public AtualizaProdutoDto() {
    }

    public AtualizaProdutoDto(String descricao, double preco, int quantidade, int estoque_min) {
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.estoque_min = estoque_min;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getEstoque_min() {
        return estoque_min;
    }

    public void setEstoque_min(int estoque_min) {
        this.estoque_min = estoque_min;
    }
}
