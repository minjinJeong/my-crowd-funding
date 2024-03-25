package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.SupportDelivery;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportDeliveryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupportDeliveryPersistenceMapper {

    SupportDeliveryPersistenceMapper INSTANCE = Mappers.getMapper(SupportDeliveryPersistenceMapper.class);

    SupportDeliveryEntity SupportDeliveryEntity(SupportDelivery supportDelivery);

    SupportDelivery toSupportDelivery(SupportDeliveryEntity supportDeliveryEntity);
}
