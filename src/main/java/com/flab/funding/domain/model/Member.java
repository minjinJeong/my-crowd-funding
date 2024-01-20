package com.flab.funding.domain.model;

import java.math.BigInteger;
import java.sql.Timestamp;

public class Member {
    private BigInteger userId;
    private String userKey;
    private MemberStatus statusCode;
    private String linkType;
    private String email;
    private String userName;
    private String nickName;
    private String phoneNum;
    private MemberGender gender;
    private String birthDay;
    private String password;
    private Timestamp lastLoginAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Member activate() {
        this.statusCode = MemberStatus.ACTIVATE;
        return this;
    }
}
