package com.developerrafu.clientservice.controllers;

import com.developerrafu.clientservice.exceptions.CepNotFoundException;
import com.developerrafu.clientservice.exceptions.ClienteNotFoundException;
import com.developerrafu.clientservice.exceptions.ExternalRequestException;
import com.developerrafu.clientservice.exceptions.UnprocessedResponseException;
import com.developerrafu.clientservice.models.rest.responses.DefaultError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class ErrorController {
    @ExceptionHandler(CepNotFoundException.class)
    public ResponseEntity<DefaultError> handleCepNotFoundException(final CepNotFoundException ex, final WebRequest request) {
        final var error = DefaultError.builder().message("Erro ao localizar cep: " + ex.getCep()).code(HttpStatus.NOT_FOUND.value()).build();
        return ResponseEntity.status(error.getCode()).body(error);
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<DefaultError> handleClienteNotFoundException(final ClienteNotFoundException ex, final WebRequest request) {
        final var error = DefaultError.builder().message("Erro ao localizar cliente").code(HttpStatus.NOT_FOUND.value()).build();
        return ResponseEntity.status(error.getCode()).body(error);
    }

    @ExceptionHandler(ExternalRequestException.class)
    public ResponseEntity<DefaultError> handleExternalRequestException(final ExternalRequestException ex, final WebRequest request) {
        final var error = DefaultError.builder().message(ex.getIssue().getMessage()).code(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(UnprocessedResponseException.class)
    public ResponseEntity<DefaultError> handleUnprocessedResponseException(final UnprocessedResponseException ex, final WebRequest request) {
        final var error = DefaultError.builder().message(ex.getMessage()).code(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<DefaultError> handleRuntimeException(final RuntimeException ex, final WebRequest request) {
        final var error = DefaultError.builder().message("Erro interno no servidor, contate os administradores").code(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
