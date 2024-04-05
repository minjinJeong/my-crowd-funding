package com.flab.funding.application.ports.input;

import com.flab.funding.domain.model.MemberDeliveryAddress;

public interface RegisterDeliveryAddressUseCase {

    MemberDeliveryAddress registerDeliveryAddress(MemberDeliveryAddress memberDeliveryAddress);

    MemberDeliveryAddress getDeliveryAddressByDeliveryAddressKey(String deliveryAddressKey);
}
