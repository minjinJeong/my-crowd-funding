package com.flab.funding.infrastructure.adapters.input.data.request;

import com.flab.funding.domain.model.DeliveryAddress;
import com.flab.funding.infrastructure.adapters.input.mapper.MemberDeliveryAddressMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MemberDeliveryAddressRegisterRequest {
    private String userKey;
    private boolean defaultYN;
    private String zipCode;
    private String address;
    private String addressDetail;
    private String recipientName;
    private String recipientPhone;

    public DeliveryAddress toDeliveryAddress() {
        return MemberDeliveryAddressMapper.INSTANCE.toDeliveryAddress(this);
    }
}
