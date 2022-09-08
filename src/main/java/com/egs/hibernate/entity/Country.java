package com.egs.hibernate.entity;

import com.neovisionaries.i18n.CountryCode;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "genSeq", sequenceName = "country_id_seq",allocationSize = 200)
@Entity(name = "country")
public class Country extends BaseEntity {

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "country_code", unique = true)
    @Enumerated(EnumType.STRING)
    private CountryCode countryCode;
}
