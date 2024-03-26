package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingCreatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FundingCreatorRepository extends JpaRepository<FundingCreatorEntity, Long> {

    Optional<FundingCreatorEntity> findByFundingKey(String fundingKey);
}
