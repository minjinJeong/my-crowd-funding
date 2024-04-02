package com.flab.funding.application.ports.output;

import com.flab.funding.domain.model.Support;
import com.flab.funding.domain.model.SupportDelivery;

public interface SupportPort {

    Support saveSupport(Support support);

    Support getSupportBySupportKey(String SupportKey);

    SupportDelivery getSupportDeliveryBySupportKey(String supportKey);

    SupportDelivery saveSupportDelivery(SupportDelivery supportDelivery);

}
