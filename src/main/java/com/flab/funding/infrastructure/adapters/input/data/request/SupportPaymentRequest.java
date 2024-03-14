package com.flab.funding.infrastructure.adapters.input.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SupportPaymentRequest {

    private Long memberPaymentMethodId;
}
