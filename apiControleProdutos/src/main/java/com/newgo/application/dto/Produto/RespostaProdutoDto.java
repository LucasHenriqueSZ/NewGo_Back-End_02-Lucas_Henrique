package com.newgo.application.dto.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class RespostaProdutoDto {

    private UUID hash;
    private String nome;
    private String descricao;
    private String ean13;
    private BigDecimal preco;
    private Integer quantidade;
    private Integer estoque_min;
    private LocalDateTime dtcreate;
    private LocalDateTime dtupdate;
    private Boolean l_ativo;

    public RespostaProdutoDto() {
    }

    public RespostaProdutoDto(UUID hash, String nome, String descricao, String ean13, BigDecimal preco, Integer quantidade, Integer estoque_min, LocalDateTime dtcreate, LocalDateTime dtupdate, Boolean l_ativo) {
        this.hash = hash;
        this.nome = nome;
        this.descricao = descricao;
        this.ean13 = ean13;
        this.preco = preco;
        this.quantidade = quantidade;
        this.estoque_min = estoque_min;
        this.dtcreate = dtcreate;
        this.dtupdate = dtupdate;
        this.l_ativo = l_ativo;
    }

    public UUID getHash() {
        return hash;
    }

    public void setHash(UUID hash) {
        this.hash = hash;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEan13() {
        return ean13;
    }

    public void setEan13(String ean13) {
        this.ean13 = ean13;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getEstoque_min() {
        return estoque_min;
    }

    public void setEstoque_min(Integer estoque_min) {
        this.estoque_min = estoque_min;
    }

    public LocalDateTime getDtcreate() {
        return dtcreate;
    }

    public void setDtcreate(LocalDateTime dtcreate) {
        this.dtcreate = dtcreate;
    }

    public LocalDateTime getDtupdate() {
        return dtupdate;
    }

    public void setDtupdate(LocalDateTime dtupdate) {
        this.dtupdate = dtupdate;
    }

    public Boolean getL_ativo() {
        return l_ativo;
    }

    public void setL_ativo(Boolean l_ativo) {
        this.l_ativo = l_ativo;
    }
}
