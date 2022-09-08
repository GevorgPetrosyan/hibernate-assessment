package com.egs.hibernate.exception.domein;

public class PaginationSizeException extends RuntimeException {
    public PaginationSizeException(String message) {
        super(message);
    }
}
