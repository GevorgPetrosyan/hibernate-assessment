package com.egs.hibernate.entity;

import com.egs.hibernate.utils.DBConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    private Long id;

    @Column(name = DBConstants.COLUMN_DATE_CREATED)
    @CreatedDate
    private LocalDateTime created;

    @Column(name = DBConstants.COLUMN_DATE_UPDATED)
    @LastModifiedDate
    private LocalDateTime updated;
}
