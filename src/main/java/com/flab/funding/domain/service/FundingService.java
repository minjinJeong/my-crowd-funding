package com.flab.funding.domain.service;

import com.flab.funding.application.ports.input.CancelFundingUseCase;
import com.flab.funding.application.ports.input.RegisterFundingUseCase;
import com.flab.funding.application.ports.input.ReviewFundingUseCase;
import com.flab.funding.application.ports.output.FundingPort;
import com.flab.funding.domain.model.Funding;
import com.flab.funding.domain.model.FundingCreator;
import com.flab.funding.domain.model.FundingItem;
import com.flab.funding.domain.model.FundingReward;
import com.flab.funding.infrastructure.config.UseCase;

// TODO : 펀딩 로직 생성 필요
@UseCase
public class FundingService implements RegisterFundingUseCase, ReviewFundingUseCase, CancelFundingUseCase {

    private final FundingPort fundingPort;

    public FundingService(FundingPort fundingPort) {
        this.fundingPort = fundingPort;
    }

    @Override
    public Funding registFunding(Funding funding) {
        return fundingPort.saveFunding(funding.regist());
    }

    @Override
    public FundingCreator registFundingCreator(FundingCreator fundingCreator) {
        return fundingPort.saveFundingCreator(fundingCreator);
    }

    // TODO : JPA List 저장 방식 공부 후, 아이템 옵션 저장 로직 수정
    @Override
    public FundingItem makeFundingItem(FundingItem fundingItem) {
        return fundingPort.saveFundingItem(fundingItem);
    }

    // TODO : JPA List 저장 방식 공부 후, 리워드 아이템 저장 로직 수정
    @Override
    public FundingReward makeFundingReward(FundingReward fundingReward) {
        return fundingPort.saveFundingReward(fundingReward);
    }

    @Override
    public Funding waitForFundingReview(String fundingId) {
        Funding funding = fundingPort.getFundingByFundingId(fundingId);
        return fundingPort.saveFunding(funding.waitReview());
    }

    @Override
    public Funding completeFundingReview(String fundingId) {
        Funding funding = fundingPort.getFundingByFundingId(fundingId);
        return fundingPort.saveFunding(funding.completeReview());
    }

    @Override
    public Funding cancelFunding(String fundingId) {
        Funding funding = fundingPort.getFundingByFundingId(fundingId);
        return fundingPort.saveFunding(funding.cancel());
    }
}
