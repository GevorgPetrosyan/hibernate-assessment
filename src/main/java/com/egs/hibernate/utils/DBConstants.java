package com.egs.hibernate.utils;

/**
 * @author Arman Karapetyan
 * @date 2022-09-09
 **/
public final class DBConstants {

    private DBConstants() {
        //Empty constructor
    }

    // General purpose classes names are stored here
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATE_CREATED = "created";
    public static final String COLUMN_DATE_UPDATED = "updated";

    // USER table column data
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_FIRSTNAME = "first_name";
    public static final String COLUMN_LASTNAME = "last_name";
    public static final String COLUMN_BIRTHDATE = "birthdate";
}

