package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingEntity;

import java.util.Optional;

public interface FundingRepository {
    FundingEntity save(FundingEntity funding);
    Optional<FundingEntity> findByFundingId(String fundingId);
}
