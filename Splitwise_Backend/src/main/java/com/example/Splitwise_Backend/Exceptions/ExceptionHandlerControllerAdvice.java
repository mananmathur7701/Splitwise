package com.example.Splitwise_Backend.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice
{
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> UserNotFoundResponse(UserNotFoundException userNotFoundException)
    {
        ErrorResponse userNotFoundErrorResponse = new ErrorResponse();
        userNotFoundErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        userNotFoundErrorResponse.setMessage(userNotFoundException.getMessage());
        return new ResponseEntity<>(userNotFoundErrorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> GroupNotFoundResponse(GroupNotFoundException groupNotFoundException)
    {
        ErrorResponse groupNotFoundErrorResponse = new ErrorResponse();
        groupNotFoundErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        groupNotFoundErrorResponse.setMessage(groupNotFoundException.getMessage());
        return new ResponseEntity<>(groupNotFoundErrorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> ExpenseNotFoundResponse(ExpenseNotFoundException expenseNotFoundException)
    {
        ErrorResponse expenseNotFoundErrorResponse = new ErrorResponse();
        expenseNotFoundErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        expenseNotFoundErrorResponse.setMessage(expenseNotFoundException.getMessage());
        return new ResponseEntity<>(expenseNotFoundErrorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> SquareOffTransactionNotFoundResponse(SquareOffTransactionNotFoundException squareOffTransactionNotFoundException)
    {
        ErrorResponse squareOffTransactionNotFoundErrorResponse = new ErrorResponse();
        squareOffTransactionNotFoundErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        squareOffTransactionNotFoundErrorResponse.setMessage(squareOffTransactionNotFoundException.getMessage());
        return new ResponseEntity<>(squareOffTransactionNotFoundErrorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> GeneralResponse(GeneralException generalException)
    {
        ErrorResponse generalResponse = new ErrorResponse();
        generalResponse.setStatus(HttpStatus.NOT_FOUND.value());
        generalResponse.setMessage(generalException.getMessage());
        return new ResponseEntity<>(generalResponse,HttpStatus.NOT_FOUND);
    }
}
