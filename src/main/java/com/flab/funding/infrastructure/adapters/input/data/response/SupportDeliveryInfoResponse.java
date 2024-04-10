package com.flab.funding.infrastructure.adapters.input.data.response;

import com.flab.funding.domain.model.SupportDelivery;
import com.flab.funding.domain.model.SupportDeliveryStatus;
import com.flab.funding.infrastructure.adapters.input.mapper.SupportMapper;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SupportDeliveryInfoResponse {

    private String supportKey;
    private SupportDeliveryStatus status;

    public static SupportDeliveryInfoResponse from(SupportDelivery supportDelivery) {

        return SupportMapper.INSTANCE.toSupportDeliveryInfoResponse(supportDelivery);
    }

}