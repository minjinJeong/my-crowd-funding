package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class Member {
    private Long id;
    private String userKey;
    private MemberStatus status;
    private MemberLinkType linkType;
    private String email;
    private String userName;
    private String nickName;
    private String phoneNumber;
    private MemberGender gender;
    private LocalDate birthday;
    private String password;
    private LocalDateTime lastLoginAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Member activate() {
        this.status = MemberStatus.ACTIVATE;
        this.userKey = makeKey();
        return this;
    }

    // TODO key 생성 로직 수정
    private String makeKey() {
        return UUID.randomUUID().toString();
    }

    public Member deactivate() {
        this.status = MemberStatus.WITHDRAW;
        return this;
    }
}
