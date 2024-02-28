package com.flab.funding.domain.service;

import com.flab.funding.application.ports.input.RegisterDeliveryAddressUseCase;
import com.flab.funding.application.ports.output.MemberDeliveryAddressPort;
import com.flab.funding.domain.model.DeliveryAddress;
import com.flab.funding.infrastructure.config.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class MemberDeliveryAddressService implements RegisterDeliveryAddressUseCase {

    private final MemberDeliveryAddressPort deliveryAddressPort;

    @Override
    public DeliveryAddress registDeliveryAddress(DeliveryAddress deliveryAddress) {
        return deliveryAddressPort.saveDeliveryAddress(deliveryAddress);
    }
}
