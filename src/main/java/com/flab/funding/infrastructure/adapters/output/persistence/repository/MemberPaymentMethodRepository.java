package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberPaymentMethodEntity;

public interface MemberPaymentMethodRepository {
    MemberPaymentMethodEntity save(MemberPaymentMethodEntity paymentMethod);
}
