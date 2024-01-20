package com.flab.funding.infrastructure.adapters.input.data.request;

import com.flab.funding.domain.model.MemberGender;

public class MemberCreateRequest {
    private String linkType;
    private String email;
    private String userName;
    private String nickName;
    private String phoneNum;
    private MemberGender gender;
    private String birthDay;
    private String password;
}
