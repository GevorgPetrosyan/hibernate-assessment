package com.egs.hibernate.entity;

import com.egs.hibernate.utils.DBConstants;
import com.egs.hibernate.utils.SequenceGeneratorNames;
import lombok.*;

import javax.persistence.*;

@SequenceGenerator(name = SequenceGeneratorNames.GENERATOR_NAME,
        sequenceName = SequenceGeneratorNames.PHONE_NUMBER_SEQUENCE_NAME,
        allocationSize = DBConstants.ALLOCATION_SIZE)
@Entity(name = DBConstants.ENTITY_PHONE_NUMBER_NAME)
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class PhoneNumber extends BaseEntity {

    @Column(name = DBConstants.COLUMN_PHONE_NUMBER,
            length = DBConstants.COLUMN_PHONE_NUMBER_LENGTH, nullable = false)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = DBConstants.COLUMN_USER_ID)
    private User user;
}
