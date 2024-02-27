package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.SupportPayment;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportPaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SupportPaymentPersistenceMapper {

    SupportPaymentPersistenceMapper INSTANCE = Mappers.getMapper(SupportPaymentPersistenceMapper.class);

    @Mapping(source = "supportId", target = "support.id")
    @Mapping(source = "memberPaymentMethodId", target = "memberPaymentMethod.id")
    SupportPaymentEntity toSupportPaymentEntity(SupportPayment supportPayment);

    @Mapping(source = "support.id", target = "supportId")
    @Mapping(source = "memberPaymentMethod.id", target = "memberPaymentMethodId")
    SupportPayment toSupportPayment(SupportPaymentEntity supportPaymentEntity);
}
