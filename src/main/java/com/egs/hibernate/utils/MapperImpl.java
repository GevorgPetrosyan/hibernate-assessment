package com.egs.hibernate.utils;

import com.egs.hibernate.entity.Address;
import com.egs.hibernate.entity.Country;
import com.egs.hibernate.entity.PhoneNumber;
import com.egs.hibernate.entity.User;
import com.egs.hibernate.response.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MapperImpl implements Mapper {
    @Override
    public ResponseUser userToResponse(User user) {
        ResponseUser responseUser = new ResponseUser();

        responseUser.setUsername(user.getUsername());
        responseUser.setFirstName(user.getFirstName());
        responseUser.setLastName(user.getLastName());
        responseUser.setBirthdate(user.getBirthdate());
        responseUser.setAddresses(user.getAddresses()
                .stream()
                .map(this::addressToResponse)
                .collect(Collectors.toSet()));
        responseUser.setPhoneNumbers(user.getPhoneNumbers()
                .stream()
                .map(this::phoneNumberToResponse)
                .collect(Collectors.toSet()));
        return responseUser;
    }

    @Override
    public AddressResponse addressToResponse(Address address) {
        AddressResponse addressResponse = new AddressResponse();

        addressResponse.setAddressLine1(address.getAddressLine1());
        addressResponse.setAddressLine2(address.getAddressLine2());
        addressResponse.setCity(address.getCity());
        addressResponse.setStreet(address.getStreet());
        addressResponse.setPostalCode(address.getPostalCode());
        addressResponse.setCountry(countryToResponse(address.getCountry()));
        return addressResponse;
    }

    private CountryResponse countryToResponse(Country country) {
        CountryResponse response = new CountryResponse();

        response.setCountryCode(country.getCountryCode().getAlpha2());
        response.setDisplayName(country.getDisplayName());

        return response;
    }

    @Override
    public PhoneNumberResponse phoneNumberToResponse(PhoneNumber phoneNumber) {
        PhoneNumberResponse phoneNumberResponse = new PhoneNumberResponse();

        phoneNumberResponse.setPhoneNumber(phoneNumber.getPhoneNumber());
        return phoneNumberResponse;
    }
}
