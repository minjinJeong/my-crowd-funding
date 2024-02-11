package com.flab.funding.infrastructure.adapters.output.persistence.entity;

import com.flab.funding.domain.model.BankCode;
import com.flab.funding.infrastructure.adapters.output.persistence.converter.BankCodeAttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("BANK")
@SuperBuilder
@Getter
public class BankEntity extends MemberPaymentMethodEntity {

    @Convert(converter = BankCodeAttributeConverter.class)
    private BankCode bankCode;

    private String accountHolder;

    private LocalDate accountBirthday;
}
