package com.flab.funding.domain.service;

import com.flab.funding.application.ports.input.RegisterPaymentMethodUseCase;
import com.flab.funding.application.ports.output.MemberPaymentMethodPort;
import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.MemberPaymentMethod;
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
    public MemberPaymentMethod registerPaymentMethod(MemberPaymentMethod memberPaymentMethod) {

        Member member =
                memberPort.getMemberByUserKey(memberPaymentMethod.getMember().getUserKey());

        return paymentMethodPort.savePaymentMethod(memberPaymentMethod.with(member).register());
    }

    @Override
    public MemberPaymentMethod getPaymentMethodByPaymentMethodKey(String paymentMethodKey) {
        return paymentMethodPort.getPaymentMethodByPaymentMethodKey(paymentMethodKey);
    }
}
