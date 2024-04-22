package com.flab.funding.infrastructure.adapters.input.data.request;

import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.adapters.input.mapper.MemberMapper;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class LoginRequest {

    @NotEmpty
    @Email
    private String email;

    private String password;

    public Member toMember() {

        return MemberMapper.INSTANCE.toMember(this);
    }
}
