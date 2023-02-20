package com.developerrafu.clientservice.helpers;

import lombok.Getter;

@Getter
public enum LogEnum {
    GET_CLIENTE_REQUEST("Get Client Request | CPF: %s"),
    GET_CLIENTE_RESPONSE("Get Client Response | Response: %s | Status: %s"),
    GET_ENDERECO_BY_CEP_REQUEST("Get CEP Endereco Request | CEP: %s"),
    GET_ENDERECO_BY_CEP_RESPONSE("Get CEP Endereco Response | Response: %s"),
    UPSERT_ENDERECO_REQUEST("Upsert Endereco Request | Request: %s"),
    UPSERT_ENDERECO_RESPONSE("Upsert Endereco Response | Response: %s | Status: %s"),
    GET_ESTADOS_REQUEST("Get Estados Endereco Request"),
    GET_ESTADOS_RESPONSE("Get Estados Endereco Response | Response: %s"),
    GET_MUNICIPIOS_REQUEST("Get Municipios Endereco Request | EstadoId: %s"),
    GET_MUNICIPIOS_RESPONSE("Get Municipios Endereco Response | Response: %s | Status: %s"),
    GET_ESTADOS_LOCALIDADE_SERVICE_RESPONSE("Get Estados Localidade Service Response | Response: %s"),
    GET_MUNCIPIOS_LOCALIDADE_SERVICE_REQUEST("Get Municipios Localidade Service Request | EstadoId: %s"),
    GET_MUNICIPIOS_LOCALIDADE_SERVICE_RESPONSE("Get Municipios Localidade Service Response | Response: %s"),
    GET_VIA_CEP_REQUEST("Get Cep Via Cep WS Request | CEP: %s"),
    GET_VIA_CEP_RESPONSE("Get Cep Via Cep WS Response | Response: %s");
    private final String message;

    LogEnum(final String message) {
        this.message = message;
    }

    public String getFormatteMessage(final Object... params) {
        return String.format(getMessage(), params);
    }
}
