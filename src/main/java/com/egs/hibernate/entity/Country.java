package com.egs.hibernate.entity;

import com.neovisionaries.i18n.CountryCode;
import lombok.*;

import javax.persistence.*;

@Entity(name = "country")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "my_seq",sequenceName = "country_seq")
public class Country extends BaseEntity {

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "country_code", unique = true)
    @Enumerated(EnumType.STRING)
    private CountryCode countryCode;
}
