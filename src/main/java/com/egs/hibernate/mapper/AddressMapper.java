package com.egs.hibernate.mapper;

import com.egs.hibernate.dto.AddressDTO;
import com.egs.hibernate.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper extends BaseMapper<Address, AddressDTO> {
}
