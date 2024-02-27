package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.Support;
import com.flab.funding.domain.model.SupportDelivery;
import com.flab.funding.domain.model.SupportPayment;
import com.flab.funding.infrastructure.adapters.input.data.request.SupportDeliveryRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.SupportPaymentRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.SupportRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.SupportRegisterResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-27T20:32:30+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class SupportMapperImpl implements SupportMapper {

    @Override
    public Support toSupport(SupportRegisterRequest supportRegisterRequest) {
        if ( supportRegisterRequest == null ) {
            return null;
        }

        Support.SupportBuilder support = Support.builder();

        support.rewardId( supportRegisterRequest.getRewardId() );
        support.supportDelivery( supportDeliveryRequestToSupportDelivery( supportRegisterRequest.getSupportDelivery() ) );
        support.supportPayment( supportPaymentRequestToSupportPayment( supportRegisterRequest.getSupportPayment() ) );

        return support.build();
    }

    @Override
    public SupportRegisterResponse toSupportRegisterResponse(Support support) {
        if ( support == null ) {
            return null;
        }

        SupportRegisterResponse.SupportRegisterResponseBuilder supportRegisterResponse = SupportRegisterResponse.builder();

        supportRegisterResponse.supportKey( support.getSupportKey() );
        supportRegisterResponse.status( support.getStatus() );

        return supportRegisterResponse.build();
    }

    protected SupportDelivery supportDeliveryRequestToSupportDelivery(SupportDeliveryRequest supportDeliveryRequest) {
        if ( supportDeliveryRequest == null ) {
            return null;
        }

        SupportDelivery.SupportDeliveryBuilder supportDelivery = SupportDelivery.builder();

        supportDelivery.memberDeliveryAddressId( supportDeliveryRequest.getMemberDeliveryAddressId() );

        return supportDelivery.build();
    }

    protected SupportPayment supportPaymentRequestToSupportPayment(SupportPaymentRequest supportPaymentRequest) {
        if ( supportPaymentRequest == null ) {
            return null;
        }

        SupportPayment.SupportPaymentBuilder supportPayment = SupportPayment.builder();

        supportPayment.memberPaymentMethodId( supportPaymentRequest.getMemberPaymentMethodId() );

        return supportPayment.build();
    }
}
