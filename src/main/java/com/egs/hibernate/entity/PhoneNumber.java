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

    /**
     * using sequence-based id generation instead of auto
     */
    @Id
    @GeneratedValue(generator = "phone_number_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "phone_number_seq",
            sequenceName = "phone_number_seq"
    )
    private Long id;

    @Column(name = "phone_number", length = 9, nullable = false)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
