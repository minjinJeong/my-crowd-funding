package com.flab.funding.infrastructure.adapters.input.data.response;

import com.flab.funding.domain.model.MemberPaymentMethod;
import com.flab.funding.infrastructure.adapters.input.mapper.MemberPaymentMethodMapper;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class MemberPaymentMethodRegisterResponse {

    private String paymentMethodKey;
    private String userKey;
    private boolean isDefault;
    private String paymentNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static MemberPaymentMethodRegisterResponse from(MemberPaymentMethod memberPaymentMethod) {
        return MemberPaymentMethodMapper.INSTANCE.toMemberPaymentMethodRegisterResponse(memberPaymentMethod);
    }
}
