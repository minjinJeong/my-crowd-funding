package com.flab.funding.infrastructure.adapters.output.persistence;

import com.flab.funding.application.ports.output.MemberDeliveryAddressPort;
import com.flab.funding.domain.model.DeliveryAddress;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberDeliveryAddressEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.MemberDeliveryAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberDeliveryAddressPersistenceAdapter implements MemberDeliveryAddressPort {
    private final MemberDeliveryAddressRepository memberDeliveryAddressRepository;

    @Override
    public DeliveryAddress saveDeliveryAddress(DeliveryAddress deliveryAddress) {
        System.out.println("deliveryAddress = " + deliveryAddress.isDefault());
        MemberDeliveryAddressEntity deliveryAddressEntity = MemberDeliveryAddressEntity.from(deliveryAddress);
        System.out.println("deliveryAddressEntity = " + deliveryAddressEntity.isDefault());
        MemberDeliveryAddressEntity savedEntity = memberDeliveryAddressRepository.save(deliveryAddressEntity);
        System.out.println("savedEntity = " + savedEntity.isDefault());
        return savedEntity.toDeliveryAddress();
    }
}
