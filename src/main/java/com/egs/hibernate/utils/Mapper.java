package com.egs.hibernate.utils;

import com.egs.hibernate.entity.Address;
import com.egs.hibernate.entity.Country;
import com.egs.hibernate.entity.PhoneNumber;
import com.egs.hibernate.entity.User;
import com.egs.hibernate.model.*;
import com.neovisionaries.i18n.CountryCode;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Mapper {

    public UserResponse userToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(user.getUsername());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setBirthDate(user.getBirthdate());
        userResponse.setAddress(user.getAddresses()
                .stream()
                .map(this::fromAddressToAddressResponse)
                .collect(Collectors.toSet()));
        userResponse.setPhoneNumber(user.getPhoneNumbers()
                .stream()
                .map(this::fromPhoneNumberToPhoneNumberResponse)
                .collect(Collectors.toSet()));
        return userResponse;
    }


    private AddressResponse fromAddressToAddressResponse(Address address) {
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setCity(address.getCity());
        addressResponse.setStreet(address.getStreet());
        addressResponse.setPostalCode(address.getPostalCode());
        addressResponse.setCountryName(fromCountryToCountryResponse(address.getCountry()).getCountryName());
        return addressResponse;
    }

    private PhoneNumberResponse fromPhoneNumberToPhoneNumberResponse(PhoneNumber phoneNumber) {
        PhoneNumberResponse phoneNumberResponse = new PhoneNumberResponse();
        phoneNumberResponse.setPhoneNumber(phoneNumber.getPhoneNumber());
        return phoneNumberResponse;
    }

    private CountryResponse fromCountryToCountryResponse(Country country){
        CountryResponse countryResponse = new CountryResponse();
        countryResponse.setCountryName(country.getDisplayName());
        return countryResponse;
    }

}

