package com.flab.funding.infrastructure.adapters.output.persistence;

import com.flab.funding.application.ports.output.MemberPaymentMethodPort;
import com.flab.funding.domain.model.MemberPaymentMethod;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberPaymentMethodEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.MemberPaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberPaymentMethodPersistenceAdapter implements MemberPaymentMethodPort {

    private final MemberPaymentMethodRepository memberPaymentMethodRepository;

    @Transactional
    @Override
    public MemberPaymentMethod savePaymentMethod(MemberPaymentMethod memberPaymentMethod) {
        MemberPaymentMethodEntity paymentMethodEntity = MemberPaymentMethodEntity.from(memberPaymentMethod);
        MemberPaymentMethodEntity savedEntity = memberPaymentMethodRepository.save(paymentMethodEntity);
        return savedEntity.toPaymentMethod();
    }

    @Override
    public MemberPaymentMethod getPaymentMethodByPaymentMethodKey(String paymentMethodKey) {
        MemberPaymentMethodEntity findPaymentMethodEntity = memberPaymentMethodRepository
                .findByPaymentMethodKey(paymentMethodKey)
                .orElse(MemberPaymentMethodEntity.builder().build());

        return findPaymentMethodEntity.toPaymentMethod();
    }
}
