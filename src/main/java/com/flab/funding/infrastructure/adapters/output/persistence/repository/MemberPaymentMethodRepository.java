package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberPaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberPaymentMethodRepository extends JpaRepository<MemberPaymentMethodEntity, Long> {
    Optional<MemberPaymentMethodEntity> findByPaymentMethodKey(String paymentMethodKey);
}
