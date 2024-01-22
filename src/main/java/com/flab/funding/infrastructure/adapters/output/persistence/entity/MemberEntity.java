package com.flab.funding.infrastructure.adapters.output.persistence.entity;

import com.flab.funding.domain.model.MemberGender;
import com.flab.funding.domain.model.MemberStatus;
import com.flab.funding.infrastructure.adapters.output.persistence.converter.GenderAttributeConverter;
import com.flab.funding.infrastructure.adapters.output.persistence.converter.MemberStatusAttributeConverter;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "user")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userKey;
    @Convert(converter = MemberStatusAttributeConverter.class)
    private MemberStatus statusCode;
    private String linkType;
    private String email;
    private String userName;
    private String nickName;
    private String phoneNum;
    @Convert(converter = GenderAttributeConverter.class)
    private MemberGender gender;
    private LocalDate birthDay;
    private String password;
    private Timestamp lastLoginAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
