package com.newgo.domain.produto.exceptions;

public enum MensagensProdutoExceptions {

    QUANTIDADE_ESTOQUE_NEGATIVA("A quantidade do estoque não pode ficar menor que zero."),
    QUANTIDADE_ESTOQUE_MIN("A quantidade não pode ficar menor que a quantidade mínima."),
    QUANTIDADE_ESTOQUE_MIN_MENOR_QUE_ZERO("A quantidade mínima não pode ficar menor ou igual a zero."),
    PRECO_MENOR_QUE_ZERO("O preço não pode ficar menor que zero."),
    PRECO_NULO("O preço não pode ser nulo."),
    EAN13_CARACTERES_INVALIDOS("O EAN13 deve conter apenas números."),
    EAN13_DIFERENTE_DE_13_CARACTERES("O EAN13 deve conter 13 caracteres."),
    EAN13_NULO("O EAN13 não pode ser nulo."),
    DESCRICAO_MENOR_QUE_5_CARACTERES("A descrição deve conter no mínimo 5 caracteres."),
    DESCRICAO_NULA("A descrição não pode ser nula."),
    NOME_CARACTERES_INVALIDOS("O nome deve conter apenas letras."),
    NOME_MENOR_QUE_3_CARACTERES("O nome deve conter no mínimo 3 caracteres."),
    NOME_MAIOR_QUE_150_CARACTERES("O nome deve conter no máximo 150 caracteres."),
    NOME_NULO("O nome não pode ser nulo."),
    HASH_NULA("O hash não pode ser nulo."),
    DATA_CRIACAO_JA_ATRIBUIDA("A data de criação já foi atribuída."),
    HASH_JA_ATRIBUIDO("O hash já foi atribuído.");

    public String mensagem;

    MensagensProdutoExceptions(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
