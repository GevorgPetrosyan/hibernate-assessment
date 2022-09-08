package com.egs.hibernate.assigment.core.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
