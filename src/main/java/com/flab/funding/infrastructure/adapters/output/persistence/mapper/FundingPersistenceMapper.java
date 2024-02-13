package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.Funding;
import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FundingPersistenceMapper {

    FundingPersistenceMapper INSTANCE = Mappers.getMapper(FundingPersistenceMapper.class);

    FundingEntity toFundingEntity(Funding funding);

    Funding toFunding(FundingEntity fundingEntity);
}
