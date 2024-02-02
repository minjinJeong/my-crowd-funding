package com.flab.funding.infrastructure.adapters.input.data.response;

import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.MemberStatus;
import com.flab.funding.infrastructure.adapters.input.mapper.MemberMapper;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberCreateResponse {
    private String userKey;
    private MemberStatus status;

    public static MemberCreateResponse from(Member member) {
        return MemberMapper.INSTANCE.toMemberCreateResponse(member);
    }
}
