package com.flab.funding.infrastructure.adapters.output.persistence.entity;

import com.flab.funding.domain.model.MemberStatus;
import jakarta.persistence.Entity;

import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
public class MemberEntity {
    private BigInteger userId;
    private String userKey;
    private String statusCode;
    private String linkType;
    private String email;
    private String userName;
    private String nickName;
    private String phoneNum;
    private char gender;
    private String birthDay;
    private String password;
    private Timestamp lastLoginAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
