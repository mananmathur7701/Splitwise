package com.example.Splitwise_Backend.Exceptions;

public class GroupNotFoundException extends RuntimeException{
    public GroupNotFoundException(String message) {
        super(message);
    }

    public GroupNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GroupNotFoundException(Throwable cause) {
        super(cause);
    }
}
