package com.newgo.domain.produto;

import com.newgo.domain.produto.exceptions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Produto {

    private Long id;
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

    public Produto(long id, UUID hash, String nome, String descricao, String ean13, BigDecimal preco, int quantidade, int estoque_min, LocalDateTime dtcreate, LocalDateTime dtupdate, boolean lativo) {
        setId(id);
        setHash(hash);
        setNome(nome);
        setDescricao(descricao);
        setEan13(ean13);
        setPreco(preco);
        setEstoque_min(estoque_min);
        setQuantidade(quantidade);
        setDtcreate(dtcreate);
        setDtupdate(dtupdate);
        setL_ativo(lativo);
    }

    public Produto( String nome, String descricao, String ean13, BigDecimal preco, int quantidade, int estoque_min) {
        setNome(nome);
        setDescricao(descricao);
        setEan13(ean13);
        setPreco(preco);
        setQuantidade(quantidade);
        setEstoque_min(estoque_min);
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public UUID getHash() {
        return hash;
    }

    public void setHash(UUID hash) {
        if (this.hash != null)
            throw new HashInvalidoException(MensagensProdutoExceptions.HASH_JA_ATRIBUIDO);
        if (hash == null)
            throw new ArgumentoNuloException(MensagensProdutoExceptions.HASH_NULA);

        this.hash = hash;
    }

    public String getNome() {
        return nome;
    }

    private void setNome(String nome) {
        if (nome == null || nome.isEmpty())
            throw new ArgumentoNuloException(MensagensProdutoExceptions.NOME_NULO);
        if (nome.length() > 200)
            throw new NomeInvalidoException(MensagensProdutoExceptions.NOME_MAIOR_QUE_150_CARACTERES);
        if (nome.length() < 3)
            throw new NomeInvalidoException(MensagensProdutoExceptions.NOME_MENOR_QUE_3_CARACTERES);
        if (nome.matches("^[a-zA-ZÀ-ÿ0-9 ]+$\n"))
            throw new NomeInvalidoException(MensagensProdutoExceptions.NOME_CARACTERES_INVALIDOS);

        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.isEmpty())
            throw new ArgumentoNuloException(MensagensProdutoExceptions.DESCRICAO_NULA);
        if (descricao.length() < 5)
            throw new DescricaoInvalidaException(MensagensProdutoExceptions.DESCRICAO_MENOR_QUE_5_CARACTERES);

        this.descricao = descricao;
    }

    public String getEan13() {
        return ean13;
    }

    private void setEan13(String ean13) {
        if (ean13 == null || ean13.isEmpty())
            throw new ArgumentoNuloException(MensagensProdutoExceptions.EAN13_NULO);
        if (ean13.length() != 13)
            throw new Ean13InvalidoException(MensagensProdutoExceptions.EAN13_DIFERENTE_DE_13_CARACTERES);
        if (ean13.matches("^[0-9]+$\n"))
            throw new Ean13InvalidoException(MensagensProdutoExceptions.EAN13_CARACTERES_INVALIDOS);

        this.ean13 = ean13;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        if (preco == null)
            throw new ArgumentoNuloException(MensagensProdutoExceptions.PRECO_NULO);
        if (preco.compareTo(BigDecimal.ZERO) < 0)
            throw new PrecoInvalidoException(MensagensProdutoExceptions.PRECO_MENOR_QUE_ZERO);

        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 0)
            throw new QuantidadeEstoqueException(MensagensProdutoExceptions.QUANTIDADE_ESTOQUE_NEGATIVA);
        if (quantidade < this.estoque_min)
            throw new QuantidadeEstoqueException(MensagensProdutoExceptions.QUANTIDADE_ESTOQUE_MIN);

        this.quantidade = quantidade;
    }

    public int getEstoque_min() {
        return estoque_min;
    }

    public void setEstoque_min(int estoque_min) {
        if (estoque_min <= 0)
            throw new QuantidadeEstoqueException(MensagensProdutoExceptions.QUANTIDADE_ESTOQUE_MIN_MENOR_QUE_ZERO);

        this.estoque_min = estoque_min;
    }

    public LocalDateTime getDtcreate() {
        return dtcreate;
    }

    public void setDtcreate(LocalDateTime dtcreate) {
        if (this.dtcreate != null)
            throw new DataInvalidaException(MensagensProdutoExceptions.DATA_CRIACAO_JA_ATRIBUIDA);
        this.dtcreate = dtcreate;
    }

    public LocalDateTime getDtupdate() {
        return dtupdate;
    }

    public void setDtupdate(LocalDateTime dtupdate) {
        this.dtupdate = dtupdate;
    }

    public boolean isL_ativo() {
        return l_ativo;
    }

    public void setL_ativo(boolean l_ativo) {
        this.l_ativo = l_ativo;
    }
}
