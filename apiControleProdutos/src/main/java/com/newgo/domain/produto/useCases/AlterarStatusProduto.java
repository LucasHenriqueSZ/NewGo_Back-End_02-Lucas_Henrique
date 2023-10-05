package com.newgo.domain.produto.useCases;

import com.newgo.domain.produto.Produto;
import com.newgo.domain.produto.ProdutoRepository;
import com.newgo.domain.produto.useCases.exceptions.AlterarStatusProdutoException;
import com.newgo.domain.produto.useCases.exceptions.MensagensCasoDeUsoProdutoExceptions;
import com.newgo.domain.produto.useCases.util.ConversorUUID;

import java.time.LocalDateTime;
import java.util.UUID;

public class AlterarStatusProduto {

    private final ProdutoRepository produtoRepository;

    private final String ATIVAR = "ativar";
    private final String DESATIVAR = "desativar";

    public AlterarStatusProduto(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto executar(String hashProduto, String statusProduto) {

        UUID hash = ConversorUUID.converteStringParaUUID(hashProduto);

        validarStatusProduto(statusProduto);

        Produto produto = produtoRepository.consultarPorHash(hash);

        if (produto == null)
            throw new AlterarStatusProdutoException(MensagensCasoDeUsoProdutoExceptions.PRODUTO_NAO_ENCONTRADO);

        if (statusProduto.equalsIgnoreCase(ATIVAR)) {
            if (!produto.isL_ativo())
                produto.setDtupdate(LocalDateTime.now());
            produto.setL_ativo(true);
        } else {
            if (produto.isL_ativo())
                produto.setDtupdate(LocalDateTime.now());
            produto.setL_ativo(false);
        }

        try {
            produtoRepository.atualizarStatus(produto);
            return produtoRepository.consultarPorHash(hash);
        } catch (Exception e) {
            throw new AlterarStatusProdutoException(MensagensCasoDeUsoProdutoExceptions.ERROR_AO_ATUALIZAR);
        }
    }

    private void validarStatusProduto(String statusProduto) {
        if (statusProduto == null || statusProduto.isEmpty())
            throw new AlterarStatusProdutoException(MensagensCasoDeUsoProdutoExceptions.STATUS_PRODUTO_INVALIDO);
        if (!statusProduto.equalsIgnoreCase(ATIVAR) && !statusProduto.equalsIgnoreCase(DESATIVAR))
            throw new AlterarStatusProdutoException(MensagensCasoDeUsoProdutoExceptions.STATUS_PRODUTO_INVALIDO);
    }
}
