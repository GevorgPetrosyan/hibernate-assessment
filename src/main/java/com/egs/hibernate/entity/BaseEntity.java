package com.egs.hibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @SequenceGenerator(name = "id_generator", sequenceName = "id_seq")
    private Long id;

    @Column(name = "created")
    @CreatedDate
    private LocalDateTime created;

    @Column(name = "updated")
    @LastModifiedDate
    private LocalDateTime updated;
}
