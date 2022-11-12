package com.bruno.felix.todobackendspringboot.exception;

public class ToDoException extends RuntimeException {
    private final int status;

    public ToDoException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
