package com.flab.funding.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Table(name = "funding_creator")
public class FundingCreatorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funding_creator_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_id")
    private FundingEntity funding;

    @ColumnDefault("false")
    @Column(name = "is_valid", columnDefinition = "TINYINT(1)")
    private boolean valid;

    private String businessNum;

    private String representative;

    private String introduce;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
