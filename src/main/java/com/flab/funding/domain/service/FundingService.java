package com.flab.funding.domain.service;

import com.flab.funding.application.ports.input.CancelFundingUseCase;
import com.flab.funding.application.ports.input.RegisterFundingUseCase;
import com.flab.funding.application.ports.input.ReviewFundingUseCase;
import com.flab.funding.application.ports.output.FundingPort;
import com.flab.funding.domain.model.Funding;
import com.flab.funding.infrastructure.config.UseCase;

// TODO : 펀딩 로직 생성 필요
@UseCase
public class FundingService implements RegisterFundingUseCase, ReviewFundingUseCase, CancelFundingUseCase {

    private final FundingPort fundingPort;

    public FundingService(FundingPort fundingPort) {
        this.fundingPort = fundingPort;
    }

    @Override
    public Funding cancelFunding(String fundingId) {
        Funding funding = fundingPort.getFundingByFundingId(fundingId);
        return fundingPort.saveFunding(funding);
    }

    @Override
    public Funding registFunding(Funding funding) {
        return fundingPort.saveFunding(funding.regist());
    }

    @Override
    public Funding waitForFundingReview(String fundingId) {
        Funding funding = fundingPort.getFundingByFundingId(fundingId);
        return fundingPort.saveFunding(funding);
    }

    @Override
    public Funding completeFundingReview(String fundingId) {
        Funding funding = fundingPort.getFundingByFundingId(fundingId);
        return fundingPort.saveFunding(funding);
    }
}
