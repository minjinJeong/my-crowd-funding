package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.SupportPayment;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportPaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupportPaymentPersistenceMapper {

    SupportPaymentPersistenceMapper INSTANCE = Mappers.getMapper(SupportPaymentPersistenceMapper.class);

    SupportPaymentEntity toSupportPaymentEntity(SupportPayment supportPayment);

    SupportPayment toSupportPayment(SupportPaymentEntity supportPaymentEntity);
}
