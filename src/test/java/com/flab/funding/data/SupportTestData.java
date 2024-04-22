package com.flab.funding.data;

import com.flab.funding.domain.model.*;

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

    public static SupportPayment getSupportPayment() {
        return SupportPayment.builder()
                .memberPaymentMethod(getPaymentMethodRequest())
                .status(SupportPaymentStatus.READY)
                .build();
    }

    public static SupportDelivery getSupportDelivery() {
        return SupportDelivery.builder()
                .memberDeliveryAddress(getDeliveryAddressRequest())
                .status(SupportDeliveryStatus.READY)
                .build();
    }
}
