package com.flab.funding.domain.service;

import com.flab.funding.application.ports.input.RegisterDeliveryAddressUseCase;
import com.flab.funding.application.ports.output.MemberDeliveryAddressPort;
import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.model.DeliveryAddress;
import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.config.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class MemberDeliveryAddressService implements RegisterDeliveryAddressUseCase {

    private final MemberDeliveryAddressPort deliveryAddressPort;
    private final MemberPort memberPort;

    @Override
    @Transactional
    public DeliveryAddress registDeliveryAddress(DeliveryAddress deliveryAddress) {
        Member member =
                memberPort.getMemberByUserKey(deliveryAddress.getMember().getUserKey());

        return deliveryAddressPort.saveDeliveryAddress(deliveryAddress.register(member));
    }

    @Override
    public DeliveryAddress getDeliveryAddressByDeliveryAddressKey(String deliveryAddressKey) {
        return deliveryAddressPort.getDeliveryAddressByDeliveryAddressKey(deliveryAddressKey);
    }
}
