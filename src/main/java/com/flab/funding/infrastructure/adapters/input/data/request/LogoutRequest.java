package com.flab.funding.infrastructure.adapters.input.data.request;

import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.adapters.input.mapper.MemberMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class LogoutRequest {

    private String userKey;

    public Member toMember() {

        return MemberMapper.INSTANCE.toMember(this);
    }
}
