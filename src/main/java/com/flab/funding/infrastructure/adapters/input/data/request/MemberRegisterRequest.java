package com.flab.funding.infrastructure.adapters.input.data.request;

import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.MemberGender;
import com.flab.funding.domain.model.MemberLinkType;
import com.flab.funding.infrastructure.adapters.input.mapper.MemberMapper;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MemberRegisterRequest {

    @NotEmpty
    @Email
    private String email;
    private String password;
    private String userName;
    private String nickName;
    private String phoneNumber;
    private MemberGender gender;
    private LocalDate birthday;
    private MemberLinkType linkType;

    public Member toMember() {
        return MemberMapper.INSTANCE.toMember(this);
    }
}
