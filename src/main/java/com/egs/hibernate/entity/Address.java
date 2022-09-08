package com.egs.hibernate.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "address")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Address extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_address")
    @SequenceGenerator(name = "seq_gen_address", sequenceName = "seq")
    private Long id;

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
