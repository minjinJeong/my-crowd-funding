package com.flab.funding.infrastructure.adapters.input.data.response;

import com.flab.funding.domain.model.SupportDelivery;
import com.flab.funding.domain.model.SupportStatus;
import com.flab.funding.infrastructure.adapters.input.mapper.SupportMapper;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SupportInfoResponse {

    private String supportKey;
    private SupportStatus status;

    public static SupportInfoResponse from(SupportDelivery supportDelivery) {
        return SupportMapper.INSTANCE.toSupportInfoResponse(supportDelivery);
    }

}
