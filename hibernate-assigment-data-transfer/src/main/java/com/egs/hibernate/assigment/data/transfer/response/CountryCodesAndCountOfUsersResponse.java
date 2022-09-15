package com.egs.hibernate.assigment.data.transfer.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class CountryCodesAndCountOfUsersResponse {
    private String countryCode;
    private Long usersCount;
}
