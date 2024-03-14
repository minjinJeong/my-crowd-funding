package com.flab.funding.application.ports.input;

import com.flab.funding.domain.model.DeliveryAddress;

public interface RegisterDeliveryAddressUseCase {

    DeliveryAddress registDeliveryAddress(DeliveryAddress deliveryAddress);

    DeliveryAddress getDeliveryAddressByDeliveryAddressKey(String deliveryAddressKey);
}
