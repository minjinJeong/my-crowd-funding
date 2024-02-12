package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberDeliveryAddressEntity;

public interface MemberDeliveryAddressRepository {
    MemberDeliveryAddressEntity save(MemberDeliveryAddressEntity deliveryAddress);
}
