package com.flab.funding.infrastructure.adapters.input.data.response;

import com.flab.funding.domain.model.PaymentMethod;
import com.flab.funding.infrastructure.adapters.input.mapper.MemberPaymentMethodMapper;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class MemberPaymentMethodRegisterResponse {
    private String userKey;
    private boolean defaultYN;
    private String paymentNum;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static MemberPaymentMethodRegisterResponse from(PaymentMethod paymentMethod) {
        return MemberPaymentMethodMapper.INSTANCE.toMemberPaymentMethodRegisterResponse(paymentMethod);
    }
}
