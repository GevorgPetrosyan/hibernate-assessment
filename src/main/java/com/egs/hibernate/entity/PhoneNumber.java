package com.egs.hibernate.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@SequenceGenerator(name = "genSeq", sequenceName = "phone_number_id_seq",allocationSize = 200)
@Entity(name = "phone_number")
public class PhoneNumber extends BaseEntity {

    @Column(name = "phone_number", length = 9, nullable = false)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
