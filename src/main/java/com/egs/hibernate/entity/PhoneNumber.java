package com.egs.hibernate.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "phone_number")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@SequenceGenerator(name = "my_seq" , sequenceName = "phoneNumber_seq")
public class PhoneNumber extends BaseEntity {

    @Column(name = "phone_number", length = 9, nullable = false)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
