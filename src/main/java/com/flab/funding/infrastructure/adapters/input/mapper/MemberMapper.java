package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberCreateRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberCreateResponse;

public interface MemberMapper {
    Member toMember(MemberCreateRequest memberCreateRequest);

    MemberCreateResponse toMemberCreateResponse(Member member);
}
