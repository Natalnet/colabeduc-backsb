package com.project.colabeduc.exceptions;

public class TokenInvalidoException extends RuntimeException {

    public TokenInvalidoException() {
        super();
    }

    public TokenInvalidoException(String message) {
        super(message);
    }

    public TokenInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenInvalidoException(Throwable cause) {
        super(cause);
    }
}

