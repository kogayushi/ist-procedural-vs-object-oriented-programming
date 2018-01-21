package com.example.ist.object.oriented.domain.exception;

public class ViolatedCredentialPolicyException extends DomainException {
    public ViolatedCredentialPolicyException(String message) {
        super(message);
    }

    public ViolatedCredentialPolicyException(String message, Throwable cause) {
        super(message, cause);
    }
}
