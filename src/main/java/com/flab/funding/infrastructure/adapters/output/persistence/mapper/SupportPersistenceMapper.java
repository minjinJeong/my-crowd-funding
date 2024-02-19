package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.Support;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SupportPersistenceMapper {

    SupportPersistenceMapper INSTANCE = Mappers.getMapper(SupportPersistenceMapper.class);

    SupportEntity toSupportEntity(Support support);

    Support toSupport(SupportEntity supportEntity);
}
