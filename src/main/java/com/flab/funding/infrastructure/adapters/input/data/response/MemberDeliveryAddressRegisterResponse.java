package com.flab.funding.infrastructure.adapters.input.data.response;

import com.flab.funding.domain.model.DeliveryAddress;
import com.flab.funding.infrastructure.adapters.input.mapper.MemberDeliveryAddressMapper;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class MemberDeliveryAddressRegisterResponse {

    private String deliveryAddressKey;
    private String userKey;
    private boolean isDefault;
    private String zipCode;
    private String address;
    private String addressDetail;
    private String recipientName;
    private String recipientPhone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static MemberDeliveryAddressRegisterResponse from(DeliveryAddress deliveryAddress) {
        return MemberDeliveryAddressMapper.INSTANCE.toMemberDeliveryAddressRegisterResponse(deliveryAddress);
    }
}
