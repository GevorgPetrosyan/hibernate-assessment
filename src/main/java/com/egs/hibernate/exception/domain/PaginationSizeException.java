package com.egs.hibernate.exception.domain;

public class PaginationSizeException extends RuntimeException {
    public PaginationSizeException(String message) {
        super(message);
    }
}
