package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingRewardItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingRewardItemRepository extends JpaRepository<FundingRewardItemEntity, Long> {

}
