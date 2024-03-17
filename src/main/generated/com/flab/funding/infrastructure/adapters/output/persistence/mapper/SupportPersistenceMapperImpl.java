package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.Support;
import com.flab.funding.domain.model.SupportDelivery;
import com.flab.funding.domain.model.SupportPayment;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingRewardEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportDeliveryEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportPaymentEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-17T20:12:32+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class SupportPersistenceMapperImpl implements SupportPersistenceMapper {

    @Override
    public SupportEntity toSupportEntity(Support support) {
        if ( support == null ) {
            return null;
        }

        SupportEntity.SupportEntityBuilder supportEntity = SupportEntity.builder();

        supportEntity.member( supportToMemberEntity( support ) );
        supportEntity.funding( supportToFundingEntity( support ) );
        supportEntity.reward( supportToFundingRewardEntity( support ) );
        supportEntity.id( support.getId() );
        supportEntity.supportKey( support.getSupportKey() );
        supportEntity.status( support.getStatus() );
        supportEntity.supportDelivery( supportDeliveryToSupportDeliveryEntity( support.getSupportDelivery() ) );
        supportEntity.supportPayment( supportPaymentToSupportPaymentEntity( support.getSupportPayment() ) );
        supportEntity.createdAt( support.getCreatedAt() );
        supportEntity.createdBy( support.getCreatedBy() );
        supportEntity.updatedAt( support.getUpdatedAt() );
        supportEntity.updatedBy( support.getUpdatedBy() );

        return supportEntity.build();
    }

    @Override
    public Support toSupport(SupportEntity supportEntity) {
        if ( supportEntity == null ) {
            return null;
        }

        Support.SupportBuilder support = Support.builder();

        support.userId( supportEntityMemberId( supportEntity ) );
        support.fundingId( supportEntityFundingId( supportEntity ) );
        support.rewardId( supportEntityRewardId( supportEntity ) );
        support.id( supportEntity.getId() );
        support.supportKey( supportEntity.getSupportKey() );
        support.status( supportEntity.getStatus() );
        support.supportDelivery( toSupportDelivery( supportEntity.getSupportDelivery() ) );
        support.supportPayment( supportPaymentEntityToSupportPayment( supportEntity.getSupportPayment() ) );
        support.createdAt( supportEntity.getCreatedAt() );
        support.createdBy( supportEntity.getCreatedBy() );
        support.updatedAt( supportEntity.getUpdatedAt() );
        support.updatedBy( supportEntity.getUpdatedBy() );

        return support.build();
    }

    @Override
    public SupportDelivery toSupportDelivery(SupportDeliveryEntity supportDelivery) {
        if ( supportDelivery == null ) {
            return null;
        }

        SupportDelivery.SupportDeliveryBuilder supportDelivery1 = SupportDelivery.builder();

        supportDelivery1.supportKey( supportDeliverySupportSupportKey( supportDelivery ) );
        supportDelivery1.id( supportDelivery.getId() );
        supportDelivery1.status( supportDelivery.getStatus() );
        supportDelivery1.shipmentName( supportDelivery.getShipmentName() );
        supportDelivery1.shipmentAt( supportDelivery.getShipmentAt() );
        supportDelivery1.trackingName( supportDelivery.getTrackingName() );
        supportDelivery1.trackingAt( supportDelivery.getTrackingAt() );
        supportDelivery1.createdAt( supportDelivery.getCreatedAt() );
        supportDelivery1.updatedAt( supportDelivery.getUpdatedAt() );

        return supportDelivery1.build();
    }

    protected MemberEntity supportToMemberEntity(Support support) {
        if ( support == null ) {
            return null;
        }

        MemberEntity.MemberEntityBuilder memberEntity = MemberEntity.builder();

        memberEntity.id( support.getUserId() );

        return memberEntity.build();
    }

    protected FundingEntity supportToFundingEntity(Support support) {
        if ( support == null ) {
            return null;
        }

        FundingEntity.FundingEntityBuilder fundingEntity = FundingEntity.builder();

        fundingEntity.id( support.getFundingId() );

        return fundingEntity.build();
    }

    protected FundingRewardEntity supportToFundingRewardEntity(Support support) {
        if ( support == null ) {
            return null;
        }

        FundingRewardEntity.FundingRewardEntityBuilder fundingRewardEntity = FundingRewardEntity.builder();

        fundingRewardEntity.id( support.getRewardId() );

        return fundingRewardEntity.build();
    }

    protected SupportDeliveryEntity supportDeliveryToSupportDeliveryEntity(SupportDelivery supportDelivery) {
        if ( supportDelivery == null ) {
            return null;
        }

        SupportDeliveryEntity.SupportDeliveryEntityBuilder supportDeliveryEntity = SupportDeliveryEntity.builder();

        supportDeliveryEntity.id( supportDelivery.getId() );
        supportDeliveryEntity.status( supportDelivery.getStatus() );
        supportDeliveryEntity.shipmentName( supportDelivery.getShipmentName() );
        supportDeliveryEntity.shipmentAt( supportDelivery.getShipmentAt() );
        supportDeliveryEntity.trackingName( supportDelivery.getTrackingName() );
        supportDeliveryEntity.trackingAt( supportDelivery.getTrackingAt() );
        supportDeliveryEntity.createdAt( supportDelivery.getCreatedAt() );
        supportDeliveryEntity.updatedAt( supportDelivery.getUpdatedAt() );

        return supportDeliveryEntity.build();
    }

    protected SupportPaymentEntity supportPaymentToSupportPaymentEntity(SupportPayment supportPayment) {
        if ( supportPayment == null ) {
            return null;
        }

        SupportPaymentEntity.SupportPaymentEntityBuilder supportPaymentEntity = SupportPaymentEntity.builder();

        supportPaymentEntity.id( supportPayment.getId() );
        supportPaymentEntity.status( supportPayment.getStatus() );
        supportPaymentEntity.amount( supportPayment.getAmount() );
        supportPaymentEntity.paymentAt( supportPayment.getPaymentAt() );
        supportPaymentEntity.createdAt( supportPayment.getCreatedAt() );
        supportPaymentEntity.updatedAt( supportPayment.getUpdatedAt() );

        return supportPaymentEntity.build();
    }

    private Long supportEntityMemberId(SupportEntity supportEntity) {
        if ( supportEntity == null ) {
            return null;
        }
        MemberEntity member = supportEntity.getMember();
        if ( member == null ) {
            return null;
        }
        Long id = member.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long supportEntityFundingId(SupportEntity supportEntity) {
        if ( supportEntity == null ) {
            return null;
        }
        FundingEntity funding = supportEntity.getFunding();
        if ( funding == null ) {
            return null;
        }
        Long id = funding.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long supportEntityRewardId(SupportEntity supportEntity) {
        if ( supportEntity == null ) {
            return null;
        }
        FundingRewardEntity reward = supportEntity.getReward();
        if ( reward == null ) {
            return null;
        }
        Long id = reward.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected SupportPayment supportPaymentEntityToSupportPayment(SupportPaymentEntity supportPaymentEntity) {
        if ( supportPaymentEntity == null ) {
            return null;
        }

        SupportPayment.SupportPaymentBuilder supportPayment = SupportPayment.builder();

        supportPayment.id( supportPaymentEntity.getId() );
        supportPayment.status( supportPaymentEntity.getStatus() );
        supportPayment.amount( supportPaymentEntity.getAmount() );
        supportPayment.paymentAt( supportPaymentEntity.getPaymentAt() );
        supportPayment.createdAt( supportPaymentEntity.getCreatedAt() );
        supportPayment.updatedAt( supportPaymentEntity.getUpdatedAt() );

        return supportPayment.build();
    }

    private String supportDeliverySupportSupportKey(SupportDeliveryEntity supportDeliveryEntity) {
        if ( supportDeliveryEntity == null ) {
            return null;
        }
        SupportEntity support = supportDeliveryEntity.getSupport();
        if ( support == null ) {
            return null;
        }
        String supportKey = support.getSupportKey();
        if ( supportKey == null ) {
            return null;
        }
        return supportKey;
    }
}
