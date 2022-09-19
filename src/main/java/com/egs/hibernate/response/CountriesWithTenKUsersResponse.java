package com.egs.hibernate.response;

import com.neovisionaries.i18n.CountryCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountriesWithTenKUsersResponse {

    private List<CountryCode> countryCodes;
}
