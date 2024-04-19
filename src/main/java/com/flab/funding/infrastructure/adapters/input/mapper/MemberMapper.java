package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.adapters.input.data.request.LoginRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberInfoRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberInfoResponse;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberRegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    Member toMember(MemberRegisterRequest memberRegisterRequest);

    MemberRegisterResponse toMemberRegisterResponse(Member member);

    Member toMember(MemberInfoRequest memberInfoRequest);

    MemberInfoResponse toMemberInfoResponse(Member member);

    Member toMember(LoginRequest loginRequest);
}
