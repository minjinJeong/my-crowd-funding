package com.flab.funding.application.ports.input;

import com.flab.funding.domain.model.SupportDelivery;

public interface SupportDeliveryUseCase {
    SupportDelivery shippedOut(SupportDelivery supportDelivery);
    SupportDelivery outForDelivery(SupportDelivery supportDelivery);
    SupportDelivery deliveryComplete(SupportDelivery supportDelivery);
}
