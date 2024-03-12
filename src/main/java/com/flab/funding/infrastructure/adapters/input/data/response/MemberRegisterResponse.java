package com.flab.funding.infrastructure.adapters.input.data.response;

import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.MemberStatus;
import com.flab.funding.infrastructure.adapters.input.mapper.MemberMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MemberRegisterResponse {
    private String userKey;
    private MemberStatus status;

    public static MemberRegisterResponse from(Member member) {
        return MemberMapper.INSTANCE.toMemberRegisterResponse(member);
    }
}
