package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.Support;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupportPersistenceMapper {

    SupportPersistenceMapper INSTANCE = Mappers.getMapper(SupportPersistenceMapper.class);

    @Mapping(source = "userId", target = "member.id")
    SupportEntity toSupportEntity(Support support);

    @Mapping(source = "member.id", target = "userId")
    Support toSupport(SupportEntity supportEntity);
}
