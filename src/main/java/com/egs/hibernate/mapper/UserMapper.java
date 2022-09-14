package com.egs.hibernate.mapper;

import com.egs.hibernate.entity.BaseUser;
import com.egs.hibernate.response.AddressResponse;
import com.egs.hibernate.response.CountryResponse;
import com.egs.hibernate.response.PhoneNumberResponse;
import com.egs.hibernate.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper implements BaseMapper<BaseUser, UserResponse> {

    private final ModelMapper modelMapper;

    @Override
    public UserResponse toResponse(BaseUser baseUser) {
        UserResponse userResponse = modelMapper.map(baseUser, UserResponse.class);
        if (!baseUser.getPhoneNumbers().isEmpty()) {
            Set<PhoneNumberResponse> collect =
                    baseUser.getPhoneNumbers()
                            .stream()
                            .map(n -> modelMapper.map(n, PhoneNumberResponse.class))
                            .collect(Collectors.toSet());
            userResponse.setPhoneNumbers(collect);
        }
        if (!baseUser.getAddresses().isEmpty()) {
            Set<AddressResponse> addressResponses = baseUser.getAddresses()
                    .stream()
                    .map(a -> {
                                AddressResponse addressResponse = modelMapper.map(a, AddressResponse.class);
                                if (a.getCountry() != null) {
                                    addressResponse.setCountry(modelMapper.map(a.getCountry(), CountryResponse.class));
                                }
                                return addressResponse;
                            }
                    )
                    .collect(Collectors.toSet());
            userResponse.setAddresses(addressResponses);
        }
        return userResponse;
    }
}
