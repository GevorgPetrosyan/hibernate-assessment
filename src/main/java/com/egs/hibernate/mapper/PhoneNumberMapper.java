package com.egs.hibernate.mapper;

import com.egs.hibernate.dto.response.PhoneNumberResponseDto;
import com.egs.hibernate.entity.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhoneNumberMapper {

    private final ModelMapper modelMapper;


    public PhoneNumberResponseDto toResponse(PhoneNumber phoneNumber) {
        return modelMapper.map(phoneNumber, PhoneNumberResponseDto.class);
    }
}
