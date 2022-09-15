package com.egs.hibernate.mapper;

import com.egs.hibernate.dto.AddressDto;
import com.egs.hibernate.dto.PhoneNumberDto;
import com.egs.hibernate.dto.UserDto;
import com.egs.hibernate.entity.User;

/**
 * @author Artur Tomeyan
 * @date 08/09/2022
 */
public class Mapper {

    private Mapper() {
    }

    public static UserDto userEntityToDto(User user) {
        UserDto userDto = new UserDto();

        AddressDto addressDto = new AddressDto();

        addressDto.setStreet(user.getAddresses().stream().iterator().next().getStreet());
        addressDto.setAddress_line_1(user.getAddresses().stream().iterator().next().getAddressLine1());
        addressDto.setAddress_line_2(user.getAddresses().stream().iterator().next().getAddressLine2());
        addressDto.setCity(user.getAddresses().stream().iterator().next().getCity());
        addressDto.setPostalCode(user.getAddresses().stream().iterator().next().getPostalCode());
        addressDto.setCountry(user.getAddresses().stream().iterator().next().getCountry().getCountryCode().toString());

        PhoneNumberDto phoneNumberDto = new PhoneNumberDto();
        phoneNumberDto.setPhoneNumber(user.getPhoneNumbers().stream().iterator().next().getPhoneNumber());

        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setBirthdate(user.getBirthdate());
        userDto.setAddress(addressDto);
        userDto.setPhoneNumber(phoneNumberDto);

//        userDto.setStreet(user.getAddresses().stream().iterator().next().getStreet());
//        userDto.setAddress_line_1(user.getAddresses().stream().iterator().next().getAddressLine1());
//        userDto.setAddress_line_2(user.getAddresses().stream().iterator().next().getAddressLine2());
//        userDto.setCity(user.getAddresses().stream().iterator().next().getCity());
//        userDto.setPostalCode(user.getAddresses().stream().iterator().next().getPostalCode());
//        userDto.setCountry(user.getAddresses().stream().iterator().next().getCountry().getCountryCode());
//
//        userDto.setPhoneNumber(user.getPhoneNumbers().stream().iterator().next().getPhoneNumber());

        return userDto;
    }
}
