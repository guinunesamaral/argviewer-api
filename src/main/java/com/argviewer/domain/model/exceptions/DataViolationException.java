package com.argviewer.domain.model.exceptions;

public class DataViolationException extends RuntimeException{

    public DataViolationException(String message) {
        super(message);
    }
}
