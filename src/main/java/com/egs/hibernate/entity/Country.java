package com.egs.hibernate.entity;

import com.neovisionaries.i18n.CountryCode;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "country")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Country extends BaseEntity {

    /**
     * using sequence-based id generation instead of auto
     */
    @Id
    @GeneratedValue(generator = "country_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "country_seq",
            sequenceName = "country_seq"
    )
    private Long id;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "country_code", unique = true)
    @Enumerated(EnumType.STRING)
    private CountryCode countryCode;
}
