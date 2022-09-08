package com.egs.hibernate.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@SequenceGenerator(name = "genSeq", sequenceName = "users_id_seq",allocationSize = 200)
@Entity(name = "users")
public class User extends BaseEntity {

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<PhoneNumber> phoneNumbers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Address> addresses;

}
