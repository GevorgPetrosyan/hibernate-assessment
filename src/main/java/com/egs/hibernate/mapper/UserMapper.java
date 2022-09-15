package com.egs.hibernate.mapper;

import com.egs.hibernate.dto.response.AddressResponseDto;
import com.egs.hibernate.dto.response.UserResponse;
import com.egs.hibernate.entity.Address;
import com.egs.hibernate.entity.Country;
import com.egs.hibernate.entity.PhoneNumber;
import com.egs.hibernate.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;
    private final AddressMapper addressMapper;
    private final PhoneNumberMapper phoneNumberMapper;

    private final CountryMapper countryMapper;


    public UserResponse toResponse(User user) {
        Set<Address> addresses = user.getAddresses();
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        List<AddressResponseDto> addressResponseDto = addresses.stream().map(addressMapper::toResponse).collect(Collectors.toList());
        userResponse.setAddressResponseDto(addressResponseDto);
        Set<PhoneNumber> phoneNumbers = user.getPhoneNumbers();
        userResponse.setPhoneNumberResponseDto(phoneNumbers.stream().map(phoneNumberMapper::toResponse).collect(Collectors.toList()));
        for (Address address : user.getAddresses()) {
            Country country = address.getCountry();
            if (country != null) {
                for (AddressResponseDto responseDto : addressResponseDto) {
                    if (Objects.equals(country.getId(), address.getCountry().getId())) {
                        responseDto.setCountryResponseDto(countryMapper.toResponse(country));
                    }
                }
            }
        }
        return userResponse;
    }
}
