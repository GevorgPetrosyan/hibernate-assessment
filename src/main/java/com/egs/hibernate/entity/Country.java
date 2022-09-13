package com.egs.hibernate.entity;

import com.egs.hibernate.utils.DBConstants;
import com.egs.hibernate.utils.SequenceGeneratorNames;
import com.neovisionaries.i18n.CountryCode;
import lombok.*;

import javax.persistence.*;

@SequenceGenerator(name = SequenceGeneratorNames.GENERATOR_NAME,
        sequenceName = SequenceGeneratorNames.COUNTRY_SEQUENCE_NAME,
        allocationSize = DBConstants.ALLOCATION_SIZE)
@Entity(name = DBConstants.ENTITY_COUNTRY_NAME)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Country extends BaseEntity {

    @Column(name = DBConstants.COLUMN_DISPLAY_NAME)
    private String displayName;

    @Column(name = DBConstants.COLUMN_COUNTRY_CODE, unique = true)
    @Enumerated(EnumType.STRING)
    private CountryCode countryCode;
}
