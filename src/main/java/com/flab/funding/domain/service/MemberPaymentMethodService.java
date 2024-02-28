package com.flab.funding.domain.service;

import com.flab.funding.application.ports.input.RegisterPaymentMethodUseCase;
import com.flab.funding.application.ports.output.MemberPaymentMethodPort;
import com.flab.funding.domain.model.PaymentMethod;
import com.flab.funding.infrastructure.config.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class MemberPaymentMethodService implements RegisterPaymentMethodUseCase {

    private final MemberPaymentMethodPort paymentMethodPort;

    @Override
    public PaymentMethod registPaymentMethod(PaymentMethod paymentMethod) {
        return paymentMethodPort.savePaymentMethod(paymentMethod);
    }
}
