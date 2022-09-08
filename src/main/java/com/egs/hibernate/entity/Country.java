package com.egs.hibernate.entity;

import com.neovisionaries.i18n.CountryCode;
import lombok.*;

import javax.persistence.*;

@SequenceGenerator(name="default_gen",sequenceName = "country_id_seq",allocationSize=500)
@Entity(name = "country")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Country extends BaseEntity {

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "country_code", unique = true)
    @Enumerated(EnumType.STRING)
    private CountryCode countryCode;
}
