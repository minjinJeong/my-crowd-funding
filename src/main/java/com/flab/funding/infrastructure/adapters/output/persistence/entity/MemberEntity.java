package com.flab.funding.infrastructure.adapters.output.persistence.entity;

import com.flab.funding.domain.model.MemberGender;
import com.flab.funding.domain.model.MemberLinkType;
import com.flab.funding.domain.model.MemberStatus;
import com.flab.funding.infrastructure.adapters.output.persistence.converter.GenderAttributeConverter;
import com.flab.funding.infrastructure.adapters.output.persistence.converter.MemberLinkTypeAttributeConverter;
import com.flab.funding.infrastructure.adapters.output.persistence.converter.MemberStatusAttributeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "user")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String userKey;
    @Convert(converter = MemberStatusAttributeConverter.class)
    private MemberStatus statusCode;
    @Convert(converter = MemberLinkTypeAttributeConverter.class)
    private MemberLinkType linkType;
    private String email;
    private String userName;
    private String nickName;
    private String phoneNum;
    @Convert(converter = GenderAttributeConverter.class)
    private MemberGender gender;
    private LocalDate birthday;
    private String password;
    private LocalDateTime lastLoginAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
