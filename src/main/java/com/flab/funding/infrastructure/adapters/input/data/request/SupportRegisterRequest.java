package com.flab.funding.infrastructure.adapters.input.data.request;

import com.flab.funding.domain.model.Support;
import com.flab.funding.infrastructure.adapters.input.mapper.SupportMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SupportRegisterRequest {

    private String userKey;
    private String fundingKey;
    private Long rewardId;
    private SupportDeliveryRequest supportDelivery;
    private SupportPaymentRequest supportPayment;

    public Support toSupport() {
        return SupportMapper.INSTANCE.toSupport(this);
    }
}
