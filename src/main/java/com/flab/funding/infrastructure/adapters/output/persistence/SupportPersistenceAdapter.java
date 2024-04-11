package com.flab.funding.infrastructure.adapters.output.persistence;

import com.flab.funding.application.ports.output.SupportPort;
import com.flab.funding.domain.model.Support;
import com.flab.funding.domain.model.SupportDelivery;
import com.flab.funding.domain.model.SupportPayment;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportDeliveryEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportPaymentEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.SupportDeliveryRepository;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.SupportPaymentRepository;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.SupportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SupportPersistenceAdapter implements SupportPort {

    private final SupportRepository supportRepository;
    private final SupportDeliveryRepository supportDeliveryRepository;
    private final SupportPaymentRepository supportPaymentRepository;

    @Transactional
    @Override
    public Support saveSupport(Support support) {
        SupportEntity supportEntity = SupportEntity.from(support);
        SupportEntity savedSupport = supportRepository.save(supportEntity);
        return savedSupport.toSupport();
    }

    @Override
    public Support getSupportBySupportKey(String SupportKey) {
        SupportEntity findSupportEntity
                = supportRepository.getSupportBySupportKey(SupportKey)
                .orElse(SupportEntity.builder().build());

        return findSupportEntity.toSupport();
    }

    @Transactional
    @Override
    public SupportDelivery saveSupportDelivery(SupportDelivery supportDelivery) {
        SupportDeliveryEntity supportDeliveryEntity = SupportDeliveryEntity.from(supportDelivery);
        SupportDeliveryEntity savedSupportDelivery = supportDeliveryRepository.save(supportDeliveryEntity);
        return savedSupportDelivery.toSupportDelivery();
    }

    @Override
    public SupportDelivery getSupportDeliveryBySupportKey(String supportKey) {
        SupportDeliveryEntity findSupportDeliveryEntity
                = supportDeliveryRepository.getSupportDeliveryBySupport_SupportKey(supportKey)
                .orElse(SupportDeliveryEntity.builder().build());

        return findSupportDeliveryEntity.toSupportDelivery();
    }

    @Transactional
    @Override
    public SupportPayment saveSupportPayment(SupportPayment supportPayment) {
        SupportPaymentEntity supportPaymentEntity = SupportPaymentEntity.from(supportPayment);
        SupportPaymentEntity savedSupportPayment = supportPaymentRepository.save(supportPaymentEntity);
        return savedSupportPayment.toSupportPayment();
    }
}
