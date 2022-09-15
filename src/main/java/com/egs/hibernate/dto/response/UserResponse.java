package com.egs.hibernate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String username;

    private String firstName;

    private String lastName;

    private LocalDate birthdate;

    private List<PhoneNumberResponseDto> phoneNumberResponseDto;

    private List<AddressResponseDto> addressResponseDto;
}
