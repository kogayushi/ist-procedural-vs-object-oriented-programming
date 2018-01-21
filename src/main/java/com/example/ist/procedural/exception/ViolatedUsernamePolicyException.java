package com.example.ist.procedural.exception;

public class ViolatedUsernamePolicyException extends RuntimeException {
    public ViolatedUsernamePolicyException(String message) {
        super(message);
    }

    public ViolatedUsernamePolicyException(String message, Throwable cause) {
        super(message, cause);
    }
}
