package com.flab.funding.domain.service;

import com.flab.funding.application.ports.input.RegisterSupportUseCase;
import com.flab.funding.application.ports.input.SupportDeliveryUseCase;
import com.flab.funding.application.ports.input.SupportPaymentUseCase;
import com.flab.funding.application.ports.output.FundingPort;
import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.application.ports.output.SupportPort;
import com.flab.funding.domain.model.*;
import com.flab.funding.infrastructure.config.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class SupportService implements RegisterSupportUseCase, SupportDeliveryUseCase, SupportPaymentUseCase {

    private final MemberPort memberPort;

    private final FundingPort fundingPort;

    private final SupportPort supportPort;

    @Override
    public Support registerSupport(Support support) {

        Member member =
                memberPort.getMemberByUserKey(support.getMember().getUserKey());

        Funding funding =
                fundingPort.getFundingByFundingKey(support.getFunding().getFundingKey());

        // TODO : 후원 배송정보와 결제수단 조회 코드 추가

        Support saveSupport = Support.builder()
                .member(member)
                .funding(funding)
                .reward(support.getReward())
                .status(support.getStatus())
                .build();

        return supportPort.saveSupport(support.member(member).register());
    }

    @Override
    public Support getSupportBySupportKey(String supportKey) {
        return supportPort.getSupportBySupportKey(supportKey);
    }

    @Override
    public SupportDelivery shippedOut(String supportKey) {
        SupportDelivery findSupportDelivery = supportPort.getSupportDeliveryBySupportKey(supportKey);
        return supportPort.saveSupportDelivery(findSupportDelivery.shippedOut());
    }

    @Override
    public SupportDelivery outForDelivery(String supportKey) {
        SupportDelivery findSupportDelivery = supportPort.getSupportDeliveryBySupportKey(supportKey);
        return supportPort.saveSupportDelivery(findSupportDelivery.inDelivery());
    }

    @Override
    public SupportDelivery deliveryComplete(String supportKey) {
        SupportDelivery findSupportDelivery = supportPort.getSupportDeliveryBySupportKey(supportKey);
        return supportPort.saveSupportDelivery(findSupportDelivery.complete());
    }

    // TODO 결제는 스케줄러로 실행
    @Override
    public SupportPayment payForSupport(SupportPayment supportPayment) {
        return null;
    }
}
