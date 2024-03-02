package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportDeliveryEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportEntity;

import java.util.Optional;

public interface SupportRepository {
    SupportEntity save(SupportEntity support);

    Optional<SupportEntity> getSupportBySupportKey(String supportKey);

    SupportDeliveryEntity save(SupportDeliveryEntity support);

    Optional<SupportDeliveryEntity> getSupportDeliveryBySupportKey(String supportKey);
}
