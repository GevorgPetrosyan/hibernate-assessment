package com.egs.hibernate.entity;

import com.egs.hibernate.utils.DBConstants;
import com.egs.hibernate.utils.SequenceGeneratorNames;
import lombok.*;

import javax.persistence.*;

@SequenceGenerator(name = SequenceGeneratorNames.GENERATOR_NAME,
        sequenceName = SequenceGeneratorNames.ADDRESS_SEQUENCE_NAME,
        allocationSize = DBConstants.ALLOCATION_SIZE)
@Entity(name = DBConstants.ENTITY_ADDRESS_NAME)
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Address extends BaseEntity {

    @Column(name = DBConstants.COLUMN_STREET)
    private String street;

    @Column(name = DBConstants.COLUMN_ADDRESS_LINE_1)
    private String addressLine1;

    @Column(name = DBConstants.COLUMN_ADDRESS_LINE_2)
    private String addressLine2;

    @Column(name = DBConstants.COLUMN_CITY)
    private String city;

    @Column(name = DBConstants.COLUMN_POSTAL_CODE,
            length = DBConstants.COLUMN_POSTAL_CODE_LENGTH)
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = DBConstants.COLUMN_COUNTRY_ID)
    private Country country;

    @ManyToOne
    @JoinColumn(name = DBConstants.COLUMN_USER_ID)
    private User user;
}
