package com.egs.hibernate.utils;

/**
 * @author Arman Karapetyan
 * @date 2022-09-13
 **/
public final class EntityGraphsNames {
    private EntityGraphsNames() {
        //Empty constructor
    }

    // general EntityGraph name
    public static final String NAMED_ENTITY_GRAPH = "withAddressesAndPhoneNumbers";

    // AttributeNode name
    public static final String ATTRIBUTE_NODE_PHONE_NUMBER = "phoneNumbers";
    public static final String ATTRIBUTE_NODE_ADDRESS = "addresses";

    // aub AttributeNode name
    public static final String SUB_ATTRIBUTE_NODE_COUNTRY = "country";

    // SubEntityGraph name
    public static final String SUB_NAMED_ENTITY_GRAPH = "countries";
}
