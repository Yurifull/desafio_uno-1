package com.evol.challenge.exception;

/**
 * Resource already exists exception class, used for 409 http status when resource already exists
 */
public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
