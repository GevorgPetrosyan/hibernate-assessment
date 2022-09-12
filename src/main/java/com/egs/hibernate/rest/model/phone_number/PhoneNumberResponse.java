package com.egs.hibernate.rest.model.phone_number;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Arman Karapetyan
 * @date 2022-09-12
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PhoneNumberResponse {
    private String phoneNumber;
}
