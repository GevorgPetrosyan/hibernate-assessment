package com.egs.hibernate.entity;

import com.egs.hibernate.utils.DBConstants;
import com.egs.hibernate.utils.EntityGraphsNames;
import com.egs.hibernate.utils.SequenceGeneratorNames;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@NamedEntityGraph(name = EntityGraphsNames.NAMED_ENTITY_GRAPH,
        attributeNodes = {
                @NamedAttributeNode(EntityGraphsNames.ATTRIBUTE_NODE_PHONE_NUMBER),
                @NamedAttributeNode(value = EntityGraphsNames.ATTRIBUTE_NODE_ADDRESS,
                        subgraph = EntityGraphsNames.SUB_NAMED_ENTITY_GRAPH)
        },
        subgraphs = {
                @NamedSubgraph(name = EntityGraphsNames.SUB_NAMED_ENTITY_GRAPH,
                        attributeNodes = @NamedAttributeNode(EntityGraphsNames.SUB_ATTRIBUTE_NODE_COUNTRY))
        }
)
@SequenceGenerator(name = SequenceGeneratorNames.GENERATOR_NAME,
        sequenceName = SequenceGeneratorNames.USER_SEQUENCE_NAME,
        allocationSize = DBConstants.ALLOCATION_SIZE)
@Entity(name = DBConstants.ENTITY_USER_NAME)
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class User extends BaseEntity {

    @Column(name = DBConstants.COLUMN_USERNAME, unique = true)
    private String username;

    @Column(name = DBConstants.COLUMN_FIRSTNAME)
    private String firstName;

    @Column(name = DBConstants.COLUMN_LASTNAME)
    private String lastName;

    @Column(name = DBConstants.COLUMN_BIRTHDATE)
    private LocalDate birthdate;

    @OneToMany(mappedBy = DBConstants.MAPPED_FIELD_NAME,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PhoneNumber> phoneNumbers;

    @OneToMany(mappedBy = DBConstants.MAPPED_FIELD_NAME,
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Address> addresses;

}
