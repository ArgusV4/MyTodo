package com.argus.mytodo.exceptionhandler;

public class AlreadyExistsExeption extends RuntimeException {
    public AlreadyExistsExeption(String entity, String value) {
        super(entity + " with value: "+ value + " already exists.");
    }
}
