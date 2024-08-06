package com.argus.mytodo.exceptionhandler;

public class NotAuthorized extends RuntimeException {
    public NotAuthorized(String message) {
        super(message);
    }
}
