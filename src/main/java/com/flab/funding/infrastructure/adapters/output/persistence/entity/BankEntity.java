package com.flab.funding.infrastructure.adapters.output.persistence.entity;

import com.flab.funding.domain.model.BankCode;
import com.flab.funding.infrastructure.adapters.output.persistence.converter.BankCodeAttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

@Entity
@DiscriminatorValue("BANK")
@Builder
@Getter
public class BankEntity extends MemberPaymentMethodEntity {

    @Convert(converter = BankCodeAttributeConverter.class)
    private BankCode bankCode;

    private String accountHolder;

    private String accountBirthday;
}
