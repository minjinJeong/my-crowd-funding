package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberCreateRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberCreateResponse;
import org.springframework.stereotype.Component;

// TODO : mapstruct 연동 후, 이 클래스 제거
@Component
public class MemberMapperTest implements MemberMapper {
    @Override
    public Member toMember(MemberCreateRequest memberCreateRequest) {
        return new Member();
    }

    @Override
    public MemberCreateResponse toMemberCreateResponse(Member member) {
        return new MemberCreateResponse();
    }
}
