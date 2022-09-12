package com.egs.hibernate.mapper;

import com.egs.hibernate.dto.PhoneNumberDTO;
import com.egs.hibernate.entity.PhoneNumber;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhoneNumberMapper extends BaseMapper<PhoneNumber, PhoneNumberDTO> {
}
