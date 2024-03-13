package com.flab.funding.infrastructure.adapters.input.data.request;

import com.flab.funding.domain.model.PaymentMethod;
import com.flab.funding.infrastructure.adapters.input.mapper.MemberPaymentMethodMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MemberPaymentMethodRegisterRequest {
    private String userKey;
    private boolean isDefault;
    private String paymentNumber;

    public PaymentMethod toPaymentMethod() {
        return MemberPaymentMethodMapper.INSTANCE.toPaymentMethod(this);
    }
}