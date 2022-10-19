package com.egs.hibernate.entity;

import com.neovisionaries.i18n.CountryCode;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;

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
