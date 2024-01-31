package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MemberPersistenceMapper {

    MemberEntity toMemberEntity(Member member);

    Member toMember(MemberEntity memberEntity);
}
