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

    public UserDto userEntityToDto(User user) {
        UserDto userDto = new UserDto();
        AddressDto addressDto = new AddressDto();

        addressDto.setCity(user.getAddresses().stream().iterator().next().getCity());
        addressDto.setStreet(user.getAddresses().stream().iterator().next().getStreet());
        addressDto.setAddress_line_1(user.getAddresses().stream().iterator().next().getAddressLine1());
        addressDto.setAddress_line_2(user.getAddresses().stream().iterator().next().getAddressLine2());
        addressDto.setPostalCode(user.getAddresses().stream().iterator().next().getPostalCode());
        addressDto.setCountry(user.getAddresses().stream().iterator().next().getCountry().getCountryCode());

        PhoneNumberDto phoneNumberDto = new PhoneNumberDto();
        phoneNumberDto.setPhoneNumber(user.getPhoneNumbers().stream().iterator().next().getPhoneNumber());

        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setBirthdate(user.getBirthdate());
        userDto.setAddress(addressDto);
        userDto.setPhoneNumber(phoneNumberDto);

        return userDto;
    }
}