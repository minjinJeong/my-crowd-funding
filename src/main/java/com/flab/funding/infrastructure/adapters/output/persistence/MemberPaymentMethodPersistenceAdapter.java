package com.flab.funding.infrastructure.adapters.output.persistence;

import com.flab.funding.application.ports.output.MemberPaymentMethodPort;
import com.flab.funding.domain.model.PaymentMethod;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberPaymentMethodEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.MemberPaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberPaymentMethodPersistenceAdapter implements MemberPaymentMethodPort {
    private final MemberPaymentMethodRepository memberPaymentMethodRepository;

    @Override
    public PaymentMethod savePaymentMethod(PaymentMethod paymentMethod) {
        MemberPaymentMethodEntity paymentMethodEntity = MemberPaymentMethodEntity.from(paymentMethod);
        MemberPaymentMethodEntity savedEntity = memberPaymentMethodRepository.save(paymentMethodEntity);
        return savedEntity.toPaymentMethod();
    }

    @Override
    public PaymentMethod getPaymentMethodByPaymentMethodKey(String paymentMethodKey) {
        MemberPaymentMethodEntity findPaymentMethodEntity = memberPaymentMethodRepository
                .findByPaymentMethodKey(paymentMethodKey)
                .orElse(MemberPaymentMethodEntity.builder().build());

        return findPaymentMethodEntity.toPaymentMethod();
    }
}
