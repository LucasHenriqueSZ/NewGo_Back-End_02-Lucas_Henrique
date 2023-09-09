package com.newgo.domain.produto.useCases;

import com.newgo.domain.produto.Produto;
import com.newgo.domain.produto.ProdutoRepository;
import com.newgo.domain.produto.useCases.exceptions.ConsultaProdutoException;
import com.newgo.domain.produto.useCases.exceptions.MensagensCasoDeUsoProdutoExceptions;

import java.util.UUID;

public class ConsultarProduto {
    private final ProdutoRepository produtoRepository;

    public ConsultarProduto(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto executar(String hash) {
        if (hash == null || hash.isEmpty())
            throw new ConsultaProdutoException(MensagensCasoDeUsoProdutoExceptions.HASH_INVALIDO);

        UUID uuid = converteStringParaUUID(hash);

        Produto produto = realizaConsulta(uuid);

        if (produto == null)
            throw new ConsultaProdutoException(MensagensCasoDeUsoProdutoExceptions.PRODUTO_NAO_ENCONTRADO);

        return produto;
    }

    private Produto realizaConsulta(UUID uuid) {
        Produto produto;
        try {
            produto = produtoRepository.consultarPorHash(uuid);
        } catch (Exception e) {
            throw new ConsultaProdutoException(MensagensCasoDeUsoProdutoExceptions.ERROR_AO_CONSULTAR);
        }
        return produto;
    }

    private UUID converteStringParaUUID(String hash) {
        try {
            return UUID.fromString(hash);
        } catch (Exception e) {
            throw new ConsultaProdutoException(MensagensCasoDeUsoProdutoExceptions.HASH_INVALIDO);
        }
    }
}
