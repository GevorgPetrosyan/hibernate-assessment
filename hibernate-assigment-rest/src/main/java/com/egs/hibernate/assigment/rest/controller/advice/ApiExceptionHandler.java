package com.egs.hibernate.assigment.rest.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * This Component is responsible for handling exceptions and returning
 * the ResponseEntity exception which contains a description of the thrown exception.
 */
@ControllerAdvice
@Slf4j
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiError apiError = new ApiError(BAD_REQUEST, ex.getMessage(), errors);
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }

    @ExceptionHandler(value = {EntityExistsException.class})
    public ResponseEntity<ApiError> handleEntityExistsException(RuntimeException exception) {
        logger.error(CONFLICT + " | message: " + exception.getMessage());
        return ResponseEntity.status(CONFLICT)
                .body(new ApiError(CONFLICT, exception.getMessage(), Collections.emptyList()));
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ApiError> handleEntityNotFoundException(RuntimeException exception) {
        logger.error(NOT_FOUND + " | message: " + exception.getMessage());
        return ResponseEntity.status(NOT_FOUND)
                .body(new ApiError(NOT_FOUND, exception.getMessage(), Collections.emptyList()));
    }
}