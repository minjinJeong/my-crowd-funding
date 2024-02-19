package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.DeliveryAddress;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberDeliveryAddressEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberDeliveryAddressPersistenceMapper {

    MemberDeliveryAddressPersistenceMapper INSTANCE = Mappers.getMapper(MemberDeliveryAddressPersistenceMapper.class);

    @Mapping(source = "userKey", target = "member", qualifiedByName = "toMemberEntity")
    @Mapping(source = "default", target = "isDefault")
    MemberDeliveryAddressEntity toMemberDeliveryAddressEntity(DeliveryAddress deliveryAddress);

    @Mapping(source = "member.userKey", target = "userKey")
    @Mapping(source = "default", target = "isDefault")
    DeliveryAddress toDeliveryAddress(MemberDeliveryAddressEntity deliveryAddressEntity);

    @Named("toMemberEntity")
    default MemberEntity toMemberEntity(String value) {
        if (value == null) {
            return null;
        }

        return MemberEntity.builder().userKey(value).build();
    }
}
