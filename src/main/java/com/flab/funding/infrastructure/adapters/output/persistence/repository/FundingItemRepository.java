package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FundingItemRepository extends JpaRepository<FundingItemEntity, Long> {

    Optional<FundingItemEntity> findByFunding_FundingKey(String fundingKey);
}
