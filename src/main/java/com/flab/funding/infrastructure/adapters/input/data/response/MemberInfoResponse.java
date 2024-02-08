package com.flab.funding.infrastructure.adapters.input.data.response;

import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.MemberLinkType;
import com.flab.funding.domain.model.MemberStatus;
import com.flab.funding.infrastructure.adapters.input.mapper.MemberMapper;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class MemberInfoResponse {
    private String userKey;
    private MemberStatus status;
    private String nickName;
    private String email;
    private String phoneNum;
    private MemberLinkType linkType;
    private LocalDateTime lastLoginAt;

    public static MemberInfoResponse from(Member member) {
        return MemberMapper.INSTANCE.toMemberInfoResponse(member);
    }
}
