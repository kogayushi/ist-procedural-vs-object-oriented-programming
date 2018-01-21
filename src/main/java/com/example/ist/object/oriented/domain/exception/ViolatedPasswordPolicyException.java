package com.example.ist.object.oriented.domain.exception;

public class ViolatedPasswordPolicyException extends DomainException {
    public ViolatedPasswordPolicyException(String message) {
        super(message);
    }

    public ViolatedPasswordPolicyException(String message, Throwable cause) {
        super(message, cause);
    }
}
