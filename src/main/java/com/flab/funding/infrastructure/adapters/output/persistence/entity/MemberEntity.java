package com.flab.funding.infrastructure.adapters.output.persistence.entity;

import com.flab.funding.domain.model.MemberStatus;
import jakarta.persistence.Entity;

import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
public class MemberEntity {
    BigInteger userId;
    String userKey;
    String statusCode;
    String linkType;
    String email;
    String userName;
    String nickName;
    String phoneNum;
    char gender;
    String birthDay;
    String password;
    Timestamp lastLoginAt;
    Timestamp createdAt;
    Timestamp updatedAt;
}
