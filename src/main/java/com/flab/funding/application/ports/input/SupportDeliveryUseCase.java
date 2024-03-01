package com.flab.funding.application.ports.input;

import com.flab.funding.domain.model.SupportDelivery;

public interface SupportDeliveryUseCase {
    SupportDelivery shippedOut(String supportKey);
    SupportDelivery outForDelivery(String supportKey);
    SupportDelivery deliveryComplete(String supportKey);
}
