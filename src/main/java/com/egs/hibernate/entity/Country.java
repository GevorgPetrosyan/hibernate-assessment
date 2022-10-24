package com.egs.hibernate.entity;

import com.neovisionaries.i18n.CountryCode;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;

@Entity(name = "country")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
// todo delete after 2d level cache tests
//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Country extends BaseEntity {

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "country_code", unique = true)
    @Enumerated(EnumType.STRING)
    private CountryCode countryCode;
}
