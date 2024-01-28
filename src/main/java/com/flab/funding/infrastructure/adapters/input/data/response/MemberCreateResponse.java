package com.flab.funding.infrastructure.adapters.input.data.response;

import com.flab.funding.domain.model.MemberStatus;
import lombok.Getter;

@Getter
public class MemberCreateResponse {
    private String userKey;
    private MemberStatus status;
}
