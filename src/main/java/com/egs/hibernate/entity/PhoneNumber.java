package com.egs.hibernate.entity;

import lombok.*;

import javax.persistence.*;

@SequenceGenerator(name = "genSeq", sequenceName = "phone_number_id_seq", allocationSize = 500)

@Entity(name = "phone_number")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class PhoneNumber extends BaseEntity {

    @Column(name = "phone_number", length = 9, nullable = false)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
