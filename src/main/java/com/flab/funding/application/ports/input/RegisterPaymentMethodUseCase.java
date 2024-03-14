package com.flab.funding.application.ports.input;

import com.flab.funding.domain.model.PaymentMethod;

public interface RegisterPaymentMethodUseCase {

    PaymentMethod registPaymentMethod(PaymentMethod deliveryAddress);

    PaymentMethod getPaymentMethodByPaymentMethodKey(String paymentMethodKey);
}
