package com.flab.funding.data;

import com.flab.funding.domain.model.Support;
import com.flab.funding.domain.model.SupportDelivery;
import com.flab.funding.domain.model.SupportPayment;

import static com.flab.funding.data.FundingTestData.getFundingRequest;
import static com.flab.funding.data.FundingTestData.getRewardRequest;
import static com.flab.funding.data.MemberTestData.*;

public class SupportTestData {

    public static Support getSupport() {
        return Support.builder()
                .member(getMemberRequest())
                .funding(getFundingRequest())
                .reward(getRewardRequest())
                .supportDelivery(getSupportDelivery())
                .supportPayment(getSupportPayment())
                .build();
    }

    private static Support getSupportRequest() {
        return Support.builder()
                .supportKey("SS-0001")
                .build();
    }

    private static SupportPayment getSupportPayment() {
        return SupportPayment.builder()
                .memberPaymentMethod(getPaymentMethodRequest())
                .build();
    }

    public static SupportDelivery getSupportDelivery() {
        return SupportDelivery.builder()
                .support(getSupportRequest())
                .memberDeliveryAddress(getDeliveryAddressRequest())
                .build();
    }
}
