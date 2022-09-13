package com.egs.hibernate.utils;

/**
 * @author Arman Karapetyan
 * @date 2022-09-13
 **/
public final class SequenceGeneratorNames {

    private SequenceGeneratorNames() {
        // Empty constructor
    }

    // general sequence name
    public static final String GENERATOR_NAME = "my_seq";

    // sequence names
    public static final String USER_SEQUENCE_NAME = "user_id_seq";
    public static final String ADDRESS_SEQUENCE_NAME = "address_id_seq";
    public static final String PHONE_NUMBER_SEQUENCE_NAME = "phoneNumber_id_seq";
    public static final String COUNTRY_SEQUENCE_NAME = "country_id_seq";
}
