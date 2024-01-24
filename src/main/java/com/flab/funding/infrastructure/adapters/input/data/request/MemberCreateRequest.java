package com.flab.funding.infrastructure.adapters.input.data.request;

import com.flab.funding.domain.model.MemberGender;
import com.flab.funding.domain.model.MemberLinkType;

import java.time.LocalDate;

public class MemberCreateRequest {
    private MemberLinkType linkType;
    private String email;
    private String userName;
    private String nickName;
    private String phoneNum;
    private MemberGender gender;
    private LocalDate birthday;
    private String password;
}
