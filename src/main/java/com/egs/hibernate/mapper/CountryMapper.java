package com.egs.hibernate.mapper;

import com.egs.hibernate.entity.Country;
import com.egs.hibernate.response.CountryResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CountryMapper implements BaseMapper<Country, CountryResponse> {

    private final ModelMapper modelMapper;

    @Override
    public CountryResponse toResponse(Country country) {
        return modelMapper.map(country, CountryResponse.class);
    }

}
