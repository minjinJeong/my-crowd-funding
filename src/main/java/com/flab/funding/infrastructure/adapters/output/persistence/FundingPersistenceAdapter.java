package com.flab.funding.infrastructure.adapters.output.persistence;

import com.flab.funding.application.ports.output.FundingPort;
import com.flab.funding.domain.model.Funding;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.FundingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FundingPersistenceAdapter implements FundingPort {

    private final FundingRepository fundingRepository;

    @Override
    public Funding saveFunding(Funding funding) {
        FundingEntity fundingEntity = FundingEntity.from(funding);
        FundingEntity savedFunding = fundingRepository.save(fundingEntity);
        return savedFunding.toFunding();
    }

    @Override
    public Funding getFundingByFundingId(String fundingId) {
        FundingEntity fundingEntity = fundingRepository.findByFundingId(fundingId).orElse(FundingEntity.builder().build());;
        return fundingEntity.toFunding();
    }
}
