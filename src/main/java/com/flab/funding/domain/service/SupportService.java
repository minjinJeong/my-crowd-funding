package com.flab.funding.domain.service;

import com.flab.funding.application.ports.input.RegisterSupportUseCase;
import com.flab.funding.application.ports.input.SupportDeliveryUseCase;
import com.flab.funding.application.ports.input.SupportPaymentUseCase;
import com.flab.funding.application.ports.output.SupportPort;
import com.flab.funding.domain.model.Support;
import com.flab.funding.domain.model.SupportDelivery;
import com.flab.funding.domain.model.SupportPayment;
import com.flab.funding.infrastructure.config.UseCase;
import lombok.RequiredArgsConstructor;

// TODO : JPA 연동 후 로직 수정
@UseCase
@RequiredArgsConstructor
public class SupportService implements RegisterSupportUseCase, SupportDeliveryUseCase, SupportPaymentUseCase {

    private final SupportPort supportPort;

    @Override
    public Support registerSupport(Support support) {
        return supportPort.saveSupport(support);
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
