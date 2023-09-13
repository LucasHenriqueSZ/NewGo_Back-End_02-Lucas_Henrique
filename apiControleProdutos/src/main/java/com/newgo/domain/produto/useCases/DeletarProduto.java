package com.newgo.domain.produto.useCases;

import com.newgo.domain.produto.Produto;
import com.newgo.domain.produto.ProdutoRepository;
import com.newgo.domain.produto.useCases.exceptions.DeletarProdutoException;
import com.newgo.domain.produto.useCases.exceptions.MensagensCasoDeUsoProdutoExceptions;

import java.util.UUID;

public class DeletarProduto {

    private final ProdutoRepository produtoRepository;

    public DeletarProduto(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void executar(String hash) {
        UUID uuid = converteStringParaUUID(hash);
        try {
            Produto produto = produtoRepository.consultarPorHash(uuid);
            if (produto == null)
                throw new DeletarProdutoException(MensagensCasoDeUsoProdutoExceptions.PRODUTO_NAO_ENCONTRADO);

            produtoRepository.excluir(produto);

        } catch (DeletarProdutoException e) {
            throw e;
        } catch (Exception e) {
            throw new DeletarProdutoException(MensagensCasoDeUsoProdutoExceptions.ERROR_AO_DELETAR);
        }
    }


    private UUID converteStringParaUUID(String hash) {
        try {
            return UUID.fromString(hash);
        } catch (Exception e) {
            throw new DeletarProdutoException(MensagensCasoDeUsoProdutoExceptions.HASH_INVALIDO);
        }
    }

}
