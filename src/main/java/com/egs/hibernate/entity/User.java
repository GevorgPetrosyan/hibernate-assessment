package com.egs.hibernate.entity;

import com.egs.hibernate.utils.DBConstants;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@SequenceGenerator(name = "my_seq" , sequenceName = "user_id_seq" , allocationSize = 100)
@Entity(name = "users")
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<PhoneNumber> phoneNumbers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Address> addresses;

}
