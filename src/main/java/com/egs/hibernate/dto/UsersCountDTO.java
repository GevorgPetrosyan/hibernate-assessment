package com.egs.hibernate.dto;

import com.neovisionaries.i18n.CountryCode;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersCountDTO {

    private CountryCode countryCode;
    private Long count;
}
