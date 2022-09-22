package com.egs.hibernate.utils;

import com.egs.hibernate.entity.Address;
import com.egs.hibernate.entity.PhoneNumber;
import com.egs.hibernate.entity.User;
import com.egs.hibernate.response.AddressResponse;
import com.egs.hibernate.response.PhoneNumberResponse;
import com.egs.hibernate.response.ResponseUser;

public interface Mapper {
    ResponseUser userToResponse(User user);
    AddressResponse addressToResponse(Address address);
    PhoneNumberResponse phoneNumberToResponse(PhoneNumber phoneNumber);
}
