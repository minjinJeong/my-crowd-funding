package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportDeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupportDeliveryRepository extends JpaRepository<SupportDeliveryEntity, Long> {

    Optional<SupportDeliveryEntity> getSupportDeliveryBySupport_SupportKey(String supportKey);
}
