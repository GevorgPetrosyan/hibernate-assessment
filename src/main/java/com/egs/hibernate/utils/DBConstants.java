package com.egs.hibernate.utils;

/**
 * @author Arman Karapetyan
 * @date 2022-09-09
 **/
public final class DBConstants {

    private DBConstants() {
        //Empty constructor
    }

    // Entity names
    public static final String ENTITY_USER_NAME = "users";
    public static final String ENTITY_ADDRESS_NAME = "address";
    public static final String ENTITY_COUNTRY_NAME = "country";
    public static final String ENTITY_PHONE_NUMBER_NAME = "phone_number";

    // General purpose classes names are stored here
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATE_CREATED = "created";
    public static final String COLUMN_DATE_UPDATED = "updated";

    // User table column data
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_FIRSTNAME = "first_name";
    public static final String COLUMN_LASTNAME = "last_name";
    public static final String COLUMN_BIRTHDATE = "birthdate";

    // Address table column data
    public static final String COLUMN_STREET = "street";
    public static final String COLUMN_ADDRESS_LINE_1 = "address_line_1";
    public static final String COLUMN_ADDRESS_LINE_2 = "address_line_2";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_POSTAL_CODE = "postal_code";
    public static final String COLUMN_COUNTRY_ID = "country_id";
    public static final String COLUMN_USER_ID = "user_id";

    public static final int COLUMN_POSTAL_CODE_LENGTH = 6;

    // Country table column data
    public static final String COLUMN_DISPLAY_NAME = "display_name";
    public static final String COLUMN_COUNTRY_CODE = "country_code";

    // Phone number table column data
    public static final String COLUMN_PHONE_NUMBER = "phone_number";

    public static final int COLUMN_PHONE_NUMBER_LENGTH = 9;

    // Mapped field name
    public static final String MAPPED_FIELD_NAME_USER = "user";

    // Sequence allocate size
    public static final int ALLOCATION_SIZE = 100;

}

