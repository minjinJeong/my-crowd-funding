package com.flab.funding.infrastructure.adapters.input.data.response;

import com.flab.funding.domain.model.MemberStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberCreateResponse {
    private String userKey;
    private MemberStatus statusCode;
}
