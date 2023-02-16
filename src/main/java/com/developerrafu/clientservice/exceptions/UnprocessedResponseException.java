package com.developerrafu.clientservice.exceptions;

public class UnprocessedResponseException extends RuntimeException {
    public UnprocessedResponseException(final String msg) {
        super(msg);
    }
}
