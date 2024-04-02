package com.flab.funding.infrastructure.adapters.output.persistence;

import com.flab.funding.application.ports.output.MemberDeliveryAddressPort;
import com.flab.funding.domain.model.DeliveryAddress;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberDeliveryAddressEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.MemberDeliveryAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberDeliveryAddressPersistenceAdapter implements MemberDeliveryAddressPort {

    private final MemberDeliveryAddressRepository memberDeliveryAddressRepository;

    @Transactional
    @Override
    public DeliveryAddress saveDeliveryAddress(DeliveryAddress deliveryAddress) {
        MemberDeliveryAddressEntity deliveryAddressEntity = MemberDeliveryAddressEntity.from(deliveryAddress);
        MemberDeliveryAddressEntity savedEntity = memberDeliveryAddressRepository.save(deliveryAddressEntity);
        return savedEntity.toDeliveryAddress();
    }

    @Override
    public DeliveryAddress getDeliveryAddressByDeliveryAddressKey(String deliveryAddressKey) {
        MemberDeliveryAddressEntity findMemberDeliveryAddressEntity =
                memberDeliveryAddressRepository.findByDeliveryAddressKey(deliveryAddressKey)
                        .orElse(MemberDeliveryAddressEntity.builder().build());
        return findMemberDeliveryAddressEntity.toDeliveryAddress();
    }
}
