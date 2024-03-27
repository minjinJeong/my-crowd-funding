package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingCreatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FundingCreatorRepository extends JpaRepository<FundingCreatorEntity, Long> {

    @Query(value = "select fc from FundingCreatorEntity c" +
            " join FundingEntity f on c.fundingId = f.fundingId" +
            " where f.fundingKey = :fundingKey")
    Optional<FundingCreatorEntity> findByFundingKey(String fundingKey);
}
