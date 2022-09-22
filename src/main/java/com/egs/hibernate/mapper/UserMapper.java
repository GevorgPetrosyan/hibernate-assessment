package com.egs.hibernate.mapper;

import com.egs.hibernate.dto.response.UserResponse;
import com.egs.hibernate.entity.Address;
import com.egs.hibernate.entity.PhoneNumber;
import com.egs.hibernate.entity.User;
import com.egs.hibernate.repository.AddressRepository;
import com.egs.hibernate.repository.PhoneNumberRepository;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper implements  BaseMapper<User, UserResponse>{
  private final ModelMapper modelMapper;
  private final AddressRepository addressRepository;
  private final PhoneNumberRepository phoneNumberRepository;


  @Override
  public UserResponse toResponse(User user) {
    Set<Address> addresses = mapAll(addressRepository.findAllByUserId(user.getId()), Address.class);
    Set<PhoneNumber> phoneNumbers = mapAll(phoneNumberRepository.findAllByUserId(user.getId()), PhoneNumber.class);
    UserResponse userResponse = modelMapper.map(user, UserResponse.class);
    userResponse.setAddresses(addresses);
    userResponse.setPhoneNumbers(phoneNumbers);
    return userResponse;
  }

  private  <D, T> Set<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
    return entityList.stream()
        .map(entity -> modelMapper.map(entity, outCLass))
        .collect(Collectors.toSet());
  }
}
