package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupportRepository extends JpaRepository<SupportEntity, Long> {

    Optional<SupportEntity> getSupportBySupportKey(String supportKey);
}
