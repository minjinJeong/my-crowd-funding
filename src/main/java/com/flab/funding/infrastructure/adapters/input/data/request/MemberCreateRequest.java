package com.flab.funding.infrastructure.adapters.input.data.request;

import com.flab.funding.domain.model.MemberGender;

import java.time.LocalDate;

public class MemberCreateRequest {
    private String linkType;
    private String email;
    private String userName;
    private String nickName;
    private String phoneNum;
    private MemberGender gender;
    private LocalDate birthDay;
    private String password;
}
