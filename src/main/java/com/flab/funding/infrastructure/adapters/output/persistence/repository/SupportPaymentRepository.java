package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportPaymentRepository extends JpaRepository<SupportPaymentEntity, Long> {

}
