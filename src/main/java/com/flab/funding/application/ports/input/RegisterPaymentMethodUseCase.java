package com.flab.funding.application.ports.input;

import com.flab.funding.domain.model.MemberPaymentMethod;

public interface RegisterPaymentMethodUseCase {

    MemberPaymentMethod registerPaymentMethod(MemberPaymentMethod deliveryAddress);

    MemberPaymentMethod getPaymentMethodByPaymentMethodKey(String paymentMethodKey);
}
