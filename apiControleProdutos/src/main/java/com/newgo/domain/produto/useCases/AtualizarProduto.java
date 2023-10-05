package com.newgo.domain.produto.useCases;

import com.newgo.domain.produto.Produto;
import com.newgo.domain.produto.ProdutoRepository;
import com.newgo.domain.produto.useCases.exceptions.AtualizarProdutoException;
import com.newgo.domain.produto.useCases.exceptions.MensagensCasoDeUsoProdutoExceptions;
import com.newgo.domain.produto.useCases.util.ConversorUUID;

import java.time.LocalDateTime;
import java.util.UUID;

public class AtualizarProduto {

    private final ProdutoRepository produtoRepository;

    public AtualizarProduto(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto executar(String hash, Produto produto) {
        if (produto == null)
            throw new AtualizarProdutoException(MensagensCasoDeUsoProdutoExceptions.NOVOS_DADOS_DO_PRODUTO_INVALIDOS);

        verificarProdutoAtivo(ConversorUUID.converteStringParaUUID(hash));

        Produto produtoAtualizado = getProdutoAtualizado(hash, produto);

        try {
            produtoRepository.atualizar(produtoAtualizado);
        } catch (Exception e) {
            throw new AtualizarProdutoException(MensagensCasoDeUsoProdutoExceptions.ERROR_AO_ATUALIZAR);
        }

        try {
            return produtoRepository.consultarPorHash(ConversorUUID.converteStringParaUUID(hash));
        } catch (Exception e) {
            throw new AtualizarProdutoException(MensagensCasoDeUsoProdutoExceptions.ERROR_AO_CONSULTAR);
        }
    }

    private Produto getProdutoAtualizado(String hash, Produto produto) {
        Produto produtoAtualizado = new Produto();
        produtoAtualizado.setHash(ConversorUUID.converteStringParaUUID(hash));
        produtoAtualizado.setDescricao(produto.getDescricao());
        produtoAtualizado.setPreco(produto.getPreco());
        produtoAtualizado.setEstoque_min(produto.getEstoque_min());
        produtoAtualizado.setQuantidade(produto.getQuantidade());
        produtoAtualizado.setDtupdate(LocalDateTime.now());
        return produtoAtualizado;
    }

    private void verificarProdutoAtivo(UUID uuid) {
        try {
            Produto produto = produtoRepository.consultarPorHash(uuid);

            if (produto == null)
                throw new AtualizarProdutoException(MensagensCasoDeUsoProdutoExceptions.PRODUTO_NAO_ENCONTRADO);
            if (!produto.isL_ativo())
                throw new AtualizarProdutoException(MensagensCasoDeUsoProdutoExceptions.PRODUTO_INATIVO);
        } catch (AtualizarProdutoException e) {
            throw e;
        } catch (Exception e) {
            throw new AtualizarProdutoException(MensagensCasoDeUsoProdutoExceptions.ERROR_AO_CONSULTAR);
        }
    }

}
