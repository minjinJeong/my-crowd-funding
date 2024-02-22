package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.PaymentMethod;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberPaymentMethodEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberPaymentMethodPersistenceMapper {

    MemberPaymentMethodPersistenceMapper INSTANCE = Mappers.getMapper(MemberPaymentMethodPersistenceMapper.class);

    @Mapping(source = "userKey", target = "member", qualifiedByName = "toMemberEntity")
    MemberPaymentMethodEntity toMemberPaymentMethodEntity(PaymentMethod paymentMethod);

    @Mapping(source = "member.userKey", target = "userKey")
    PaymentMethod toPaymentMethod(MemberPaymentMethodEntity paymentMethodEntity);

    @Named("toMemberEntity")
    default MemberEntity toMemberEntity(String value) {
        if (value == null) {
            return null;
        }

        return MemberEntity.builder().userKey(value).build();
    }
}
