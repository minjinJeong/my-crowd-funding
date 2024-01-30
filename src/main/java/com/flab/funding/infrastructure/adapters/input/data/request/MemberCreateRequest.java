package com.flab.funding.infrastructure.adapters.input.data.request;

import com.flab.funding.domain.model.MemberGender;
import com.flab.funding.domain.model.MemberLinkType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MemberCreateRequest {
    MemberLinkType linkType;
    private String email;
    private String userName;
    private String nickName;
    private String phoneNum;
    private MemberGender gender;
    private LocalDate birthday;
    private String password;
}
