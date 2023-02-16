package com.developerrafu.clientservice.exceptions;

public class InternalExecutionException extends RuntimeException {
    public InternalExecutionException(final Exception ex) {
        super(ex);
    }
}
