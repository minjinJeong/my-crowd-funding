package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberCreateRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberCreateResponse;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberInfoResponse;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    Member toMember(MemberCreateRequest memberCreateRequest);

    MemberCreateResponse toMemberCreateResponse(Member member);

    Member toMember(MemberRequest memberRequest);

    MemberResponse toMemberResponse(Member member);

    MemberInfoResponse toMemberInfoResponse(Member member);
}
