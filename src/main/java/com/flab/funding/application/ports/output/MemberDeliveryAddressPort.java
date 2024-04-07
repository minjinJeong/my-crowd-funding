package com.flab.funding.application.ports.output;

import com.flab.funding.domain.model.MemberDeliveryAddress;

public interface MemberDeliveryAddressPort {

    MemberDeliveryAddress saveDeliveryAddress(MemberDeliveryAddress memberDeliveryAddress);

    MemberDeliveryAddress getDeliveryAddressByDeliveryAddressKey(String deliveryAddressKey);
}
