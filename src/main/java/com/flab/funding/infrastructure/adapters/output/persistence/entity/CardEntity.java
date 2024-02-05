package com.flab.funding.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("C")
@Getter
public class CardEntity extends MemberPaymentMethodEntity {

    private String cardPassword;

    private LocalDate cardDate;

    private LocalDate cardHolderBirthday;
}
