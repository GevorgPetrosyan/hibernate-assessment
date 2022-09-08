package com.egs.hibernate.exception;

import com.egs.hibernate.dto.response.ResponseHttp;
import com.egs.hibernate.exception.domein.PaginationSizeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.egs.hibernate.dto.response.ResponseHttp.createBodyHttpResponse;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(PaginationSizeException.class)
    public ResponseEntity<ResponseHttp> paginationSizeException(PaginationSizeException e) {
        return createBodyHttpResponse(BAD_REQUEST, e.getMessage());
    }
}
