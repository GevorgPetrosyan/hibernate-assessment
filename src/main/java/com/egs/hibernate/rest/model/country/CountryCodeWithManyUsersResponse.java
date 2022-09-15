package com.egs.hibernate.rest.model.country;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Arman Karapetyan
 * @date 2022-09-15
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CountryCodeWithManyUsersResponse {
    private String country;
}
