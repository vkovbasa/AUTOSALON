package com.example.hotelfullstack.exceptions;

import com.example.hotelfullstack.models.AppError;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalException {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    public Date getTimestamp() {
        return new Date();
    }

    @ExceptionHandler
    public AppError handleResourceNotFoundException (ResourceNotFoundException ex) {
        return new AppError(
                HttpStatus.NO_CONTENT.value(),
                ex.getMessage(),
                getTimestamp()
        );
    }

    @ExceptionHandler
    public AppError handleNoDataFoundException (NoDataFoundException ex) {
        return new AppError(
                HttpStatus.NO_CONTENT.value(),
                ex.getMessage(),
                getTimestamp()
        );
    }

    @ExceptionHandler
    public AppError handIllegalArgument (IllegalArgumentException ex) {
        return new AppError(
                HttpStatus.NO_CONTENT.value(),
                ex.getMessage(),
                getTimestamp()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AppError handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<String> errorMessages = result.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        return new AppError(HttpStatus.BAD_REQUEST.value(),
                "Validation failed" +  errorMessages, getTimestamp());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public AppError handleMethodNotAllowedException(HttpRequestMethodNotSupportedException ex) {
        return new AppError(
                HttpStatus.NO_CONTENT.value(),
                "Method Not Allowed. Supported methods are " + ex.getSupportedHttpMethods(),
                getTimestamp()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public AppError handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
       return new AppError(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request: Required request body is missing",
                getTimestamp()
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public AppError handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return new AppError(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid argument: " + ex.getName(),
                getTimestamp()
        );
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AppError handleNoSuchElementException(NoSuchElementException ex) {
        return new AppError(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                getTimestamp()
        );
    }
}
