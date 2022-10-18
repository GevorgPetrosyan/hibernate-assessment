package com.egs.hibernate.entity;

import com.neovisionaries.i18n.CountryCode;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity(name = "country")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Country extends BaseEntity {

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "country_code", unique = true)
    @Enumerated(EnumType.STRING)
    private CountryCode countryCode;
}
