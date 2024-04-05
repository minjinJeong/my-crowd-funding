package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.MemberPaymentMethod;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberPaymentMethodEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberPaymentMethodPersistenceMapper {

    MemberPaymentMethodPersistenceMapper INSTANCE = Mappers.getMapper(MemberPaymentMethodPersistenceMapper.class);

    MemberPaymentMethodEntity toMemberPaymentMethodEntity(MemberPaymentMethod memberPaymentMethod);

    MemberPaymentMethod toPaymentMethod(MemberPaymentMethodEntity paymentMethodEntity);
}
