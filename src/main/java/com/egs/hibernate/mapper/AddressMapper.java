package com.egs.hibernate.mapper;

import com.egs.hibernate.dto.response.AddressResponseDto;
import com.egs.hibernate.entity.Address;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressMapper {

    private final ModelMapper modelMapper;
    public AddressResponseDto toResponse(Address address) {
        return modelMapper.map(address, AddressResponseDto.class);
    }
}
