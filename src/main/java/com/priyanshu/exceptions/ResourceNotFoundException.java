package com.priyanshu.exceptions;

/**
 * Custom exception to be thrown when a requested resource is not found.
 * This exception can be used in service layers to indicate that a specific
 * entity or resource could not be located in the database or any other storage.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
