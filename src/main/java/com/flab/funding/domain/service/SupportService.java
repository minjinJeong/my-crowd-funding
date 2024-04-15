package com.flab.funding.domain.service;

import com.flab.funding.application.ports.input.RegisterSupportUseCase;
import com.flab.funding.application.ports.input.SupportDeliveryUseCase;
import com.flab.funding.application.ports.input.SupportPaymentUseCase;
import com.flab.funding.application.ports.output.*;
import com.flab.funding.domain.exception.EmptyFundingException;
import com.flab.funding.domain.exception.EmptyMemberException;
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

        return supportPort.saveSupport(
                support.member(getMemberByUserKey(support))
                        .funding(getFundingByFundingKey(support))
                        .supportDelivery(getSupportDelivery(support.getSupportDelivery()))
                        .supportPayment(getSupportPayment(support.getSupportPayment()))
                        .register()
        );
    }

    private Funding getFundingByFundingKey(Support support) {
        Funding funding =
                fundingPort.getFundingByFundingKey(support.getFunding().getFundingKey());

        if (funding.getId() == null) {
            throw new EmptyFundingException();
        }

        return funding;
    }

    private Member getMemberByUserKey(Support support) {
        Member member =
                memberPort.getMemberByUserKey(support.getMember().getUserKey());

        if (member.getId() == null) {
            throw new EmptyMemberException();
        }

        return member;
    }

    private SupportDelivery getSupportDelivery(SupportDelivery supportDelivery) {

        if (supportDelivery == null || supportDelivery.getMemberDeliveryAddress() == null) {

            return supportDelivery;
        }

        MemberDeliveryAddress memberDeliveryAddress = supportDelivery.getMemberDeliveryAddress();

        MemberDeliveryAddress deliveryAddress =
                memberDeliveryAddressPort.getDeliveryAddressByDeliveryAddressKey(memberDeliveryAddress.getDeliveryAddressKey());

        return supportDelivery.memberDeliveryAddress(deliveryAddress);
    }

    private SupportPayment getSupportPayment(SupportPayment supportPayment) {

        if (supportPayment == null || supportPayment.getMemberPaymentMethod() == null) {

            return supportPayment;
        }

        MemberPaymentMethod memberPaymentMethod = supportPayment.getMemberPaymentMethod();

        MemberPaymentMethod paymentMethod =
                memberPaymentMethodPort.getPaymentMethodByPaymentMethodKey(memberPaymentMethod.getPaymentMethodKey());

        return supportPayment.memberPaymentMethod(paymentMethod);
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
