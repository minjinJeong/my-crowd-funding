package com.flab.funding.application.ports.output;

import com.flab.funding.domain.model.Support;
import com.flab.funding.domain.model.SupportDelivery;
import com.flab.funding.domain.model.SupportPayment;

public interface SupportPort {

    Support saveSupport(Support support);

    Support getSupportBySupportKey(String SupportKey);

    SupportDelivery getSupportDeliveryBySupportKey(String supportKey);

    SupportDelivery saveSupportDelivery(SupportDelivery supportDelivery);

    SupportPayment saveSupportPayment(SupportPayment supportPayment);
}
