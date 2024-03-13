package com.flab.funding.infrastructure.adapters.output.persistence;

import com.flab.funding.application.ports.output.SupportPort;
import com.flab.funding.domain.model.Support;
import com.flab.funding.domain.model.SupportDelivery;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportDeliveryEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.SupportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupportPersistenceAdapter implements SupportPort {

    private final SupportRepository supportRepository;

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

    @Override
    public SupportDelivery saveSupportDelivery(SupportDelivery supportDelivery) {
        SupportDeliveryEntity supportDeliveryEntity = SupportDeliveryEntity.from(supportDelivery);
        SupportDeliveryEntity savedSupportDelivery = supportRepository.save(supportDeliveryEntity);
        return savedSupportDelivery.toSupportDelivery();
    }

    @Override
    public SupportDelivery getSupportDeliveryBySupportKey(String supportKey) {
        SupportDeliveryEntity findSupportDeliveryEntity
                = supportRepository.getSupportDeliveryBySupportKey(supportKey)
                    .orElse(SupportDeliveryEntity.builder().build());

        return findSupportDeliveryEntity.toSupportDelivery();
    }
}
