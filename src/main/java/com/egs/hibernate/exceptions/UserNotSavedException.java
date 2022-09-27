package com.egs.hibernate.exceptions;

public class UserNotSavedException extends RuntimeException {

    public UserNotSavedException(String massage){ super(massage);}
}
