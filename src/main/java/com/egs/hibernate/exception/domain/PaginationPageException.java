package com.egs.hibernate.exception.domain;

public class PaginationPageException extends RuntimeException {
    public PaginationPageException(String message) {
        super(message);
    }
}
