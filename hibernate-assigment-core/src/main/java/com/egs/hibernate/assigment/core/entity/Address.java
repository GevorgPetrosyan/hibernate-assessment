package com.egs.hibernate.assigment.core.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "address")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
