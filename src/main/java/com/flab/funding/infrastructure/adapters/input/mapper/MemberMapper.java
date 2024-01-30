package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.MemberStatus;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberCreateRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberCreateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    Member toMember(MemberCreateRequest memberCreateRequest);

    MemberCreateResponse toMemberCreateResponse(Member member);

    default MemberStatus toMemberStatus(String status) {
        return MemberStatus.valueOf(status);
    }

    default String toStatus(MemberStatus memberStatus) {
        return memberStatus.getStatus();
    }
}
