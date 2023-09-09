package com.newgo.domain.produto.useCases;

import com.newgo.domain.produto.Produto;
import com.newgo.domain.produto.ProdutoRepository;
import com.newgo.domain.produto.useCases.exceptions.MensagensCasoDeUsoProdutoExceptions;
import com.newgo.domain.produto.useCases.exceptions.ProdutoInvalidoException;

import java.time.LocalDateTime;
import java.util.UUID;

public class CadastrarProduto {

    private final ProdutoRepository produtoRepository;

    public CadastrarProduto(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto executar(Produto produto) {
        if (produto == null)
            throw new ProdutoInvalidoException(MensagensCasoDeUsoProdutoExceptions.PRODUTO_NULO);

        verificaExitenciaProduto(produto);

        produto.setDtcreate(LocalDateTime.now());
        produto.setHash(UUID.randomUUID());
        produto.setL_ativo(false);
        produto.setDtupdate(null);

        try {
            produtoRepository.cadastrar(produto);
        } catch (Exception e) {
            throw new ProdutoInvalidoException(MensagensCasoDeUsoProdutoExceptions.ERROR_AO_CADASTRAR);
        }

        return produtoRepository.consultarPorHash(produto.getHash());
    }

    private void verificaExitenciaProduto(Produto produto) {
        if (produtoRepository.consultarPorEan13(produto.getEan13()) != null)
            throw new ProdutoInvalidoException(MensagensCasoDeUsoProdutoExceptions.EAN13_JA_CADASTRADO);

        if (produtoRepository.consultarPorNome(produto.getNome()) != null)
            throw new ProdutoInvalidoException(MensagensCasoDeUsoProdutoExceptions.NOME_JA_CADASTRADO);
    }

}
