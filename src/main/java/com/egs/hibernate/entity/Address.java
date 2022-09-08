package com.egs.hibernate.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "genSeq",sequenceName = "address_id_seq",allocationSize = 200)
@Entity(name = "address")
public class Address extends BaseEntity {

    @Column(name = "street")
    private String street;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code", length = 6)
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
