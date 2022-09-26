package com.egs.hibernate.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(value = {CountryNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(CountryNotFoundException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ExceptionResponse exception = new ExceptionResponse(
                e.getMessage(),
                badRequest.value(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exception, badRequest);
    }

}
