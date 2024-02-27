package com.flab.funding.application.ports.input;

import com.flab.funding.domain.model.SupportPayment;

public interface SupportPaymentUseCase {
    SupportPayment payForSupport(SupportPayment supportPayment);
}
