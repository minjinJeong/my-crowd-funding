package com.flab.funding.domain.service;

import com.flab.funding.application.ports.input.RegisterSupportUseCase;
import com.flab.funding.application.ports.input.SupportDeliveryUseCase;
import com.flab.funding.application.ports.input.SupportPaymentUseCase;
import com.flab.funding.application.ports.output.*;
import com.flab.funding.domain.model.*;
import com.flab.funding.infrastructure.config.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class SupportService implements RegisterSupportUseCase, SupportDeliveryUseCase, SupportPaymentUseCase {

    private final MemberPort memberPort;
    private final MemberDeliveryAddressPort memberDeliveryAddressPort;
    private final MemberPaymentMethodPort memberPaymentMethodPort;
    private final FundingPort fundingPort;
    private final SupportPort supportPort;

    // TODO : 포트를 너무 많이 사용한다. 리팩토링이 가능할 지 고민
    @Override
    public Support registerSupport(Support support) {

        Member member =
                memberPort.getMemberByUserKey(support.getMember().getUserKey());

        Funding funding =
                fundingPort.getFundingByFundingKey(support.getFunding().getFundingKey());

        Support savedSupport = supportPort.saveSupport(
                support.member(member).funding(funding).register()
        );

        registerSupportDelivery(support.getSupportDelivery().support(savedSupport));

        registerSupportPayment(support.getSupportPayment().support(savedSupport));

        return savedSupport;
    }

    private void registerSupportDelivery(SupportDelivery supportDelivery) {

        if (supportDelivery == null || supportDelivery.getMemberDeliveryAddress() == null) {

            return;
        }

        MemberDeliveryAddress memberDeliveryAddress = supportDelivery.getMemberDeliveryAddress();

        MemberDeliveryAddress deliveryAddress =
                memberDeliveryAddressPort.getDeliveryAddressByDeliveryAddressKey(memberDeliveryAddress.getDeliveryAddressKey());

        supportPort.saveSupportDelivery(
                supportDelivery.memberDeliveryAddress(deliveryAddress)
        );
    }

    private void registerSupportPayment(SupportPayment supportPayment) {

        if (supportPayment == null || supportPayment.getMemberPaymentMethod() == null) {

            return;
        }

        MemberPaymentMethod memberPaymentMethod = supportPayment.getMemberPaymentMethod();

        MemberPaymentMethod paymentMethod =
                memberPaymentMethodPort.getPaymentMethodByPaymentMethodKey(memberPaymentMethod.getPaymentMethodKey());

        supportPort.saveSupportPayment(
                supportPayment.memberPaymentMethod(paymentMethod)
        );
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
