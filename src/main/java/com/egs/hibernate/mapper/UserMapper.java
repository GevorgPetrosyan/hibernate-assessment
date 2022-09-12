package com.egs.hibernate.mapper;

import com.egs.hibernate.dto.UserDTO;
import com.egs.hibernate.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {PhoneNumberMapper.class, AddressMapper.class})
public interface UserMapper extends BaseMapper<User, UserDTO> {

  @Override
  @Mappings({
          @Mapping(source = "user.phoneNumbers", target = "phoneNumberDTOS"),
          @Mapping(source = "user.addresses", target = "addressDTOS")
  })
  UserDTO entityToDTO(User user);
}
