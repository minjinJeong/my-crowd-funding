package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingRewardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FundingRewardRepository extends JpaRepository<FundingRewardEntity, Long> {

    Optional<FundingRewardEntity> findByFunding_FundingKey(String fundingKey);
}
