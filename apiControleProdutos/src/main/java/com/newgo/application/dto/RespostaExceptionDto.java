package com.newgo.application.dto;

import java.time.LocalDateTime;

public class RespostaExceptionDto {

    private String mensagem;
    private LocalDateTime dataHora;

    public RespostaExceptionDto(String mensagem, LocalDateTime dataHora) {
        this.mensagem = mensagem;
        this.dataHora = dataHora;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

}
