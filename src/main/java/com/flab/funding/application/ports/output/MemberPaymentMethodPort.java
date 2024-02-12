package com.flab.funding.application.ports.output;

import com.flab.funding.domain.model.PaymentMethod;

public interface MemberPaymentMethodPort {
    PaymentMethod savePaymentMethod(PaymentMethod paymentMethod);
}
