package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.DeliveryAddress;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberDeliveryAddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberDeliveryAddressPersistenceMapper {

    MemberDeliveryAddressPersistenceMapper INSTANCE = Mappers.getMapper(MemberDeliveryAddressPersistenceMapper.class);

    MemberDeliveryAddressEntity toMemberDeliveryAddressEntity(DeliveryAddress deliveryAddress);

    DeliveryAddress toDeliveryAddress(MemberDeliveryAddressEntity deliveryAddressEntity);
}
