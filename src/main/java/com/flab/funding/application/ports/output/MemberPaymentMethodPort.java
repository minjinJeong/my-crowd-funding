package com.flab.funding.application.ports.output;

import com.flab.funding.domain.model.MemberPaymentMethod;

public interface MemberPaymentMethodPort {

    MemberPaymentMethod savePaymentMethod(MemberPaymentMethod memberPaymentMethod);

    MemberPaymentMethod getPaymentMethodByPaymentMethodKey(String paymentMethodKey);
}
