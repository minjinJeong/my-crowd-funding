package com.flab.funding.application.ports.output;

import com.flab.funding.domain.model.DeliveryAddress;

public interface MemberDeliveryAddressPort {
    DeliveryAddress saveDeliveryAddress(DeliveryAddress deliveryAddress);

    DeliveryAddress getDeliveryAddressByDeliveryAddressKey(String deliveryAddressKey);
}
