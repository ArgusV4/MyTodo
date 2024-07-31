package com.argus.mytodo.exceptionhandler;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String entity, Long id) {
        super(entity + " not found with ID: " + id);
    }
}
