package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberDeliveryAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberDeliveryAddressRepository extends JpaRepository<MemberDeliveryAddressEntity, Long> {
    Optional<MemberDeliveryAddressEntity> findByDeliveryAddressKey(String deliveryAddressKey);
}
