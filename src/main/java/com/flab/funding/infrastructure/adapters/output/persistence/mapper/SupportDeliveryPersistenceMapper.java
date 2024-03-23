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
    @Mapping(source = "memberDeliveryAddressKey", target = "memberDeliveryAddress.deliveryAddressKey")
    SupportDeliveryEntity SupportDeliveryEntity(SupportDelivery supportDelivery);

    @Mapping(source = "support.id", target = "supportId")
    @Mapping(source = "support.supportKey", target = "supportKey")
    @Mapping(source = "memberDeliveryAddress.id", target = "memberDeliveryAddressId")
    @Mapping(source = "memberDeliveryAddress.deliveryAddressKey", target = "memberDeliveryAddressKey")
    SupportDelivery toSupportDelivery(SupportDeliveryEntity supportDeliveryEntity);
}
