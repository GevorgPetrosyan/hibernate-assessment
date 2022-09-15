package com.egs.hibernate.exception.handler;

import com.egs.hibernate.exception.CountryByCodeNotFoundException;
import com.egs.hibernate.exception.util.ResponseHttp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.egs.hibernate.exception.util.ResponseHttp.createBodyHttpResponse;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CountryByCodeNotFoundException.class)
    public ResponseEntity<ResponseHttp> paginationSortException(CountryByCodeNotFoundException e) {
        return createBodyHttpResponse(BAD_REQUEST, e.getMessage());
    }
}
