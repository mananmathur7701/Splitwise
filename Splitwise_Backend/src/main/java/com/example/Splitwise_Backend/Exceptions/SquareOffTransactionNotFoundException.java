package com.example.Splitwise_Backend.Exceptions;

public class SquareOffTransactionNotFoundException extends RuntimeException{
    public SquareOffTransactionNotFoundException(String message) {
        super(message);
    }

    public SquareOffTransactionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SquareOffTransactionNotFoundException(Throwable cause) {
        super(cause);
    }

}
