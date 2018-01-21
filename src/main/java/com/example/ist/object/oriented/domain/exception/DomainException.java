package com.example.ist.object.oriented.domain.exception;

public class DomainException extends RuntimeException {
    static final long serialVersionUID = -7034897190745766939L;

    public DomainException() {
        super();
    }

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainException(Throwable cause) {
        super(cause);
    }

}
