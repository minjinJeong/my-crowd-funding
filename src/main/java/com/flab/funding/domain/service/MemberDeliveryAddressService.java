package com.flab.funding.domain.service;

import com.flab.funding.application.ports.input.RegisterDeliveryAddressUseCase;
import com.flab.funding.application.ports.output.MemberDeliveryAddressPort;
import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.model.MemberDeliveryAddress;
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
    public MemberDeliveryAddress registerDeliveryAddress(MemberDeliveryAddress memberDeliveryAddress) {

        Member member =
                memberPort.getMemberByUserKey(memberDeliveryAddress.getMember().getUserKey());

        return deliveryAddressPort.saveDeliveryAddress(memberDeliveryAddress.with(member).register());
    }

    @Override
    public MemberDeliveryAddress getDeliveryAddressByDeliveryAddressKey(String deliveryAddressKey) {
        return deliveryAddressPort.getDeliveryAddressByDeliveryAddressKey(deliveryAddressKey);
    }
}
