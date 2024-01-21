package com.flab.funding.infrastructure.adapters.output.persistence.entity;

import com.flab.funding.domain.model.MemberGender;
import com.flab.funding.domain.model.MemberStatus;
import jakarta.persistence.Entity;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
public class MemberEntity {
    private BigInteger userId;
    private String userKey;
    private MemberStatus statusCode;
    private String linkType;
    private String email;
    private String userName;
    private String nickName;
    private String phoneNum;
    private MemberGender gender;
    private LocalDate birthDay;
    private String password;
    private Timestamp lastLoginAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
