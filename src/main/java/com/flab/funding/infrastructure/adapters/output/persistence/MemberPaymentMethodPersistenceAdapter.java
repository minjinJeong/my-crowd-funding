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
        System.out.println("paymentMethod = " + paymentMethod.isDefault());
        MemberPaymentMethodEntity paymentMethodEntity = MemberPaymentMethodEntity.from(paymentMethod);
        System.out.println("paymentMethodEntity = " + paymentMethodEntity);
        MemberPaymentMethodEntity savedEntity = memberPaymentMethodRepository.save(paymentMethodEntity);
        System.out.println("savedEntity = " + savedEntity);
        return savedEntity.toPaymentMethod();
    }
}
