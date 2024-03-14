package com.flab.funding.domain.service;

import com.flab.funding.application.ports.input.RegisterPaymentMethodUseCase;
import com.flab.funding.application.ports.output.MemberPaymentMethodPort;
import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.PaymentMethod;
import com.flab.funding.infrastructure.config.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class MemberPaymentMethodService implements RegisterPaymentMethodUseCase {

    private final MemberPaymentMethodPort paymentMethodPort;
    private final MemberPort memberPort;

    @Override
    @Transactional
    public PaymentMethod registPaymentMethod(PaymentMethod paymentMethod) {
        Member member =
                memberPort.getMemberByUserKey(paymentMethod.getMember().getUserKey());

        return paymentMethodPort.savePaymentMethod(paymentMethod.register(member));
    }

    @Override
    public PaymentMethod getPaymentMethodByPaymentMethodKey(String paymentMethodKey) {
        return paymentMethodPort.getPaymentMethodByPaymentMethodKey(paymentMethodKey);
    }
}
