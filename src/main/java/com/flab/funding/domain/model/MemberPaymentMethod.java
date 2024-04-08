package com.flab.funding.domain.model;

import com.flab.funding.domain.utils.MakeDomainKeyUtils;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class MemberPaymentMethod {

    private Long id;
    private String paymentMethodKey;
    private Member member;
    private boolean isDefault;
    private String paymentNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MemberPaymentMethod member(Member member) {
        this.member = member;
        return this;
    }

    public MemberPaymentMethod register() {
        this.paymentMethodKey = MakeDomainKeyUtils.newKey("PM");
        return this;
    }
}
