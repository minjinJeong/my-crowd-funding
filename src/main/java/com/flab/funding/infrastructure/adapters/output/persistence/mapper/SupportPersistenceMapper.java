package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.Support;
import com.flab.funding.domain.model.SupportDelivery;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportDeliveryEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupportPersistenceMapper {

    SupportPersistenceMapper INSTANCE = Mappers.getMapper(SupportPersistenceMapper.class);

    SupportEntity toSupportEntity(Support support);

    Support toSupport(SupportEntity supportEntity);

    SupportDelivery toSupportDelivery(SupportDeliveryEntity supportDelivery);
}
