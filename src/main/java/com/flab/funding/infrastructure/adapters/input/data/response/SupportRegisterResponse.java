package com.flab.funding.infrastructure.adapters.input.data.response;

import com.flab.funding.domain.model.Support;
import com.flab.funding.domain.model.SupportStatus;
import com.flab.funding.infrastructure.adapters.input.mapper.SupportMapper;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SupportRegisterResponse {

    private String supportKey;
    private SupportStatus status;

    public static SupportRegisterResponse from(Support support) {
        return SupportMapper.INSTANCE.toSupportRegisterResponse(support);
    }
}
