package com.newgo.domain.produto.useCases.exceptions;

public enum MensagensCasoDeUsoProdutoExceptions {
    PRODUTO_NULO("O produto não pode ser nulo."),
    EAN13_JA_CADASTRADO("O EAN13 já está cadastrado."),
    NOME_JA_CADASTRADO("O nome já está cadastrado."),
    ERROR_AO_CADASTRAR("Erro ao cadastrar o produto."),
    HASH_INVALIDO("O hash fornecido é inválido."),
    PRODUTO_NAO_ENCONTRADO("O produto não foi encontrado."),
    ERROR_AO_CONSULTAR("Erro ao consultar o produto."),
    ERROR_AO_DELETAR("Erro ao deletar o produto."),
    NOVOS_DADOS_DO_PRODUTO_INVALIDOS("Os novos dados do produto são inválidos."),
    ERROR_AO_ATUALIZAR("Erro ao atualizar o produto."),
    PRODUTO_INATIVO("O produto está inativo."),
    STATUS_PRODUTO_INVALIDO("O status do produto é inválido.");

    public String mensagem;

    MensagensCasoDeUsoProdutoExceptions(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
