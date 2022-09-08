package com.egs.hibernate.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "phone_number")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class PhoneNumber extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_phone")
    @SequenceGenerator(name = "seq_gen_phone", sequenceName = "seq")
    private Long id;

    @Column(name = "phone_number", length = 9, nullable = false)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
