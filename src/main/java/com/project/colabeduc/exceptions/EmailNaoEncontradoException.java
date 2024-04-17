package com.project.colabeduc.exceptions;

public class EmailNaoEncontradoException extends RuntimeException {

    public EmailNaoEncontradoException() {
        super();
    }

    public EmailNaoEncontradoException(String message) {
        super(message);
    }

    public EmailNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailNaoEncontradoException(Throwable cause) {
        super(cause);
    }
}
