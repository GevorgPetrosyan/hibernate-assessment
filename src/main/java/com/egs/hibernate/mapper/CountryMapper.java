package com.egs.hibernate.mapper;

import com.egs.hibernate.dto.response.AddressResponseDto;
import com.egs.hibernate.dto.response.CountryResponseDto;
import com.egs.hibernate.entity.Address;
import com.egs.hibernate.entity.Country;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CountryMapper {

    private final ModelMapper modelMapper;


    public CountryResponseDto toResponse(Country country) {
        return modelMapper.map(country, CountryResponseDto.class);
    }
}
