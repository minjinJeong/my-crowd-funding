package com.flab.funding.domain.model;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Member {
    private Long userId;
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

    public Member activate() {
        this.statusCode = MemberStatus.ACTIVATE;
        return this;
    }
}
