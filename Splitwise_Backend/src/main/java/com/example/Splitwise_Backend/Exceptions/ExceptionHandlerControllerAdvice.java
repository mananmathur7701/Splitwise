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
        userNotFoundErrorResponse.setMessage(userNotFoundErrorResponse.getMessage());
        return new ResponseEntity<>(userNotFoundErrorResponse,HttpStatus.NOT_FOUND);
    }
}
