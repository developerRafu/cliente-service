package com.developerrafu.clientservice.helpers;

import lombok.Getter;

@Getter
public enum ErrorsEnum {
    EXTERNAL_REQUEST_EXCEPTION("Erro ao acessar serviço %s"),
    UNPROCESSED_RESPONSE("Erro ao processar resposta de %s"),
    EXTERNAL_EXECUTION_EXCEPTION("Erro ao executar %s");
    private final String msg;

    ErrorsEnum(final String msg) {
        this.msg = msg;
    }

    public String getFormattedMessage(Object... params) {
        return String.format(getMsg(), params);
    }
}
