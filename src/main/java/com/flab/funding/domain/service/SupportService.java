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

@UseCase
@RequiredArgsConstructor
public class SupportService implements RegisterSupportUseCase, SupportDeliveryUseCase, SupportPaymentUseCase {

    private final SupportPort supportPort;

    @Override
    public Support registSupport(Support support) {
        return supportPort.saveSupport(support);
    }

    // TODO : support API 완성 필요
    @Override
    public SupportDelivery shippedOut(SupportDelivery supportDelivery) {
        return null;
    }

    @Override
    public SupportDelivery outForDelivery(SupportDelivery supportDelivery) {
        return null;
    }

    @Override
    public SupportDelivery deliveryComplete(SupportDelivery supportDelivery) {
        return null;
    }

    @Override
    public SupportPayment payForSupport(SupportPayment supportPayment) {
        return null;
    }
}
