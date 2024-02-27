package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.SupportDelivery;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportDeliveryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SupportDeliveryPersistenceMapper {

    SupportDeliveryPersistenceMapper INSTANCE = Mappers.getMapper(SupportDeliveryPersistenceMapper.class);

    @Mapping(source = "supportId", target = "support.id")
    @Mapping(source = "memberDeliveryAddressId", target = "memberDeliveryAddress.id")
    SupportDeliveryEntity SupportDeliveryEntity(SupportDelivery supportDelivery);

    @Mapping(source = "support.id", target = "supportId")
    @Mapping(source = "memberDeliveryAddress.id", target = "memberDeliveryAddressId")
    SupportDelivery toSupportDelivery(SupportDeliveryEntity supportDeliveryEntity);
}
