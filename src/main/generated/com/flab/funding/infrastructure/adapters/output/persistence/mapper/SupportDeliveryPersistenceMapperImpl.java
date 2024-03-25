package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.Funding;
import com.flab.funding.domain.model.FundingItem;
import com.flab.funding.domain.model.FundingItemOption;
import com.flab.funding.domain.model.FundingReward;
import com.flab.funding.domain.model.FundingRewardItem;
import com.flab.funding.domain.model.FundingTag;
import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.Support;
import com.flab.funding.domain.model.SupportDelivery;
import com.flab.funding.domain.model.SupportPayment;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingItemEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingItemOptionEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingRewardEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingRewardItemEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingTagEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportDeliveryEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportPaymentEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-25T18:50:00+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class SupportDeliveryPersistenceMapperImpl implements SupportDeliveryPersistenceMapper {

    @Override
    public SupportDeliveryEntity SupportDeliveryEntity(SupportDelivery supportDelivery) {
        if ( supportDelivery == null ) {
            return null;
        }

        SupportDeliveryEntity.SupportDeliveryEntityBuilder supportDeliveryEntity = SupportDeliveryEntity.builder();

        supportDeliveryEntity.id( supportDelivery.getId() );
        supportDeliveryEntity.support( supportToSupportEntity( supportDelivery.getSupport() ) );
        supportDeliveryEntity.status( supportDelivery.getStatus() );
        supportDeliveryEntity.shipmentName( supportDelivery.getShipmentName() );
        supportDeliveryEntity.shipmentAt( supportDelivery.getShipmentAt() );
        supportDeliveryEntity.trackingName( supportDelivery.getTrackingName() );
        supportDeliveryEntity.trackingAt( supportDelivery.getTrackingAt() );
        supportDeliveryEntity.createdAt( supportDelivery.getCreatedAt() );
        supportDeliveryEntity.updatedAt( supportDelivery.getUpdatedAt() );

        return supportDeliveryEntity.build();
    }

    @Override
    public SupportDelivery toSupportDelivery(SupportDeliveryEntity supportDeliveryEntity) {
        if ( supportDeliveryEntity == null ) {
            return null;
        }

        SupportDelivery.SupportDeliveryBuilder supportDelivery = SupportDelivery.builder();

        supportDelivery.id( supportDeliveryEntity.getId() );
        supportDelivery.support( supportEntityToSupport( supportDeliveryEntity.getSupport() ) );
        supportDelivery.status( supportDeliveryEntity.getStatus() );
        supportDelivery.shipmentName( supportDeliveryEntity.getShipmentName() );
        supportDelivery.shipmentAt( supportDeliveryEntity.getShipmentAt() );
        supportDelivery.trackingName( supportDeliveryEntity.getTrackingName() );
        supportDelivery.trackingAt( supportDeliveryEntity.getTrackingAt() );
        supportDelivery.createdAt( supportDeliveryEntity.getCreatedAt() );
        supportDelivery.updatedAt( supportDeliveryEntity.getUpdatedAt() );

        return supportDelivery.build();
    }

    protected MemberEntity memberToMemberEntity(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberEntity.MemberEntityBuilder memberEntity = MemberEntity.builder();

        memberEntity.id( member.getId() );
        memberEntity.userKey( member.getUserKey() );
        memberEntity.status( member.getStatus() );
        memberEntity.linkType( member.getLinkType() );
        memberEntity.email( member.getEmail() );
        memberEntity.userName( member.getUserName() );
        memberEntity.nickName( member.getNickName() );
        memberEntity.phoneNumber( member.getPhoneNumber() );
        memberEntity.gender( member.getGender() );
        memberEntity.birthday( member.getBirthday() );
        memberEntity.password( member.getPassword() );
        memberEntity.lastLoginAt( member.getLastLoginAt() );
        memberEntity.createdAt( member.getCreatedAt() );
        memberEntity.updatedAt( member.getUpdatedAt() );

        return memberEntity.build();
    }

    protected FundingTagEntity fundingTagToFundingTagEntity(FundingTag fundingTag) {
        if ( fundingTag == null ) {
            return null;
        }

        FundingTagEntity.FundingTagEntityBuilder fundingTagEntity = FundingTagEntity.builder();

        fundingTagEntity.id( fundingTag.getId() );
        fundingTagEntity.funding( fundingToFundingEntity( fundingTag.getFunding() ) );
        fundingTagEntity.tag( fundingTag.getTag() );
        fundingTagEntity.createdAt( fundingTag.getCreatedAt() );
        fundingTagEntity.updatedAt( fundingTag.getUpdatedAt() );

        return fundingTagEntity.build();
    }

    protected List<FundingTagEntity> fundingTagListToFundingTagEntityList(List<FundingTag> list) {
        if ( list == null ) {
            return null;
        }

        List<FundingTagEntity> list1 = new ArrayList<FundingTagEntity>( list.size() );
        for ( FundingTag fundingTag : list ) {
            list1.add( fundingTagToFundingTagEntity( fundingTag ) );
        }

        return list1;
    }

    protected FundingEntity fundingToFundingEntity(Funding funding) {
        if ( funding == null ) {
            return null;
        }

        FundingEntity.FundingEntityBuilder fundingEntity = FundingEntity.builder();

        fundingEntity.id( funding.getId() );
        fundingEntity.fundingKey( funding.getFundingKey() );
        fundingEntity.member( memberToMemberEntity( funding.getMember() ) );
        fundingEntity.isAdult( funding.getIsAdult() );
        fundingEntity.pricePlan( funding.getPricePlan() );
        fundingEntity.category( funding.getCategory() );
        fundingEntity.expectAmount( funding.getExpectAmount() );
        fundingEntity.status( funding.getStatus() );
        fundingEntity.title( funding.getTitle() );
        fundingEntity.fundingDescription( funding.getFundingDescription() );
        fundingEntity.fundingIntroduce( funding.getFundingIntroduce() );
        fundingEntity.budgetDescription( funding.getBudgetDescription() );
        fundingEntity.scheduleDescription( funding.getScheduleDescription() );
        fundingEntity.teamDescription( funding.getTeamDescription() );
        fundingEntity.rewardDescription( funding.getRewardDescription() );
        fundingEntity.tags( fundingTagListToFundingTagEntityList( funding.getTags() ) );
        fundingEntity.startAt( funding.getStartAt() );
        fundingEntity.endAt( funding.getEndAt() );
        fundingEntity.createdAt( funding.getCreatedAt() );
        fundingEntity.createdBy( funding.getCreatedBy() );
        fundingEntity.updatedAt( funding.getUpdatedAt() );
        fundingEntity.updatedBy( funding.getUpdatedBy() );

        return fundingEntity.build();
    }

    protected FundingItemOptionEntity fundingItemOptionToFundingItemOptionEntity(FundingItemOption fundingItemOption) {
        if ( fundingItemOption == null ) {
            return null;
        }

        FundingItemOptionEntity.FundingItemOptionEntityBuilder fundingItemOptionEntity = FundingItemOptionEntity.builder();

        fundingItemOptionEntity.id( fundingItemOption.getId() );
        fundingItemOptionEntity.fundingItem( fundingItemToFundingItemEntity( fundingItemOption.getFundingItem() ) );
        fundingItemOptionEntity.createdAt( fundingItemOption.getCreatedAt() );
        fundingItemOptionEntity.updatedAt( fundingItemOption.getUpdatedAt() );

        return fundingItemOptionEntity.build();
    }

    protected List<FundingItemOptionEntity> fundingItemOptionListToFundingItemOptionEntityList(List<FundingItemOption> list) {
        if ( list == null ) {
            return null;
        }

        List<FundingItemOptionEntity> list1 = new ArrayList<FundingItemOptionEntity>( list.size() );
        for ( FundingItemOption fundingItemOption : list ) {
            list1.add( fundingItemOptionToFundingItemOptionEntity( fundingItemOption ) );
        }

        return list1;
    }

    protected FundingItemEntity fundingItemToFundingItemEntity(FundingItem fundingItem) {
        if ( fundingItem == null ) {
            return null;
        }

        FundingItemEntity.FundingItemEntityBuilder fundingItemEntity = FundingItemEntity.builder();

        fundingItemEntity.id( fundingItem.getId() );
        fundingItemEntity.funding( fundingToFundingEntity( fundingItem.getFunding() ) );
        fundingItemEntity.itemName( fundingItem.getItemName() );
        fundingItemEntity.optionType( fundingItem.getOptionType() );
        fundingItemEntity.fundingItemOptions( fundingItemOptionListToFundingItemOptionEntityList( fundingItem.getFundingItemOptions() ) );
        fundingItemEntity.createdAt( fundingItem.getCreatedAt() );
        fundingItemEntity.updatedAt( fundingItem.getUpdatedAt() );

        return fundingItemEntity.build();
    }

    protected FundingRewardItemEntity fundingRewardItemToFundingRewardItemEntity(FundingRewardItem fundingRewardItem) {
        if ( fundingRewardItem == null ) {
            return null;
        }

        FundingRewardItemEntity.FundingRewardItemEntityBuilder fundingRewardItemEntity = FundingRewardItemEntity.builder();

        fundingRewardItemEntity.id( fundingRewardItem.getId() );
        fundingRewardItemEntity.funding( fundingToFundingEntity( fundingRewardItem.getFunding() ) );
        fundingRewardItemEntity.fundingReward( fundingRewardToFundingRewardEntity( fundingRewardItem.getFundingReward() ) );
        fundingRewardItemEntity.fundingItem( fundingItemToFundingItemEntity( fundingRewardItem.getFundingItem() ) );
        fundingRewardItemEntity.createdAt( fundingRewardItem.getCreatedAt() );
        fundingRewardItemEntity.updatedAt( fundingRewardItem.getUpdatedAt() );

        return fundingRewardItemEntity.build();
    }

    protected List<FundingRewardItemEntity> fundingRewardItemListToFundingRewardItemEntityList(List<FundingRewardItem> list) {
        if ( list == null ) {
            return null;
        }

        List<FundingRewardItemEntity> list1 = new ArrayList<FundingRewardItemEntity>( list.size() );
        for ( FundingRewardItem fundingRewardItem : list ) {
            list1.add( fundingRewardItemToFundingRewardItemEntity( fundingRewardItem ) );
        }

        return list1;
    }

    protected FundingRewardEntity fundingRewardToFundingRewardEntity(FundingReward fundingReward) {
        if ( fundingReward == null ) {
            return null;
        }

        FundingRewardEntity.FundingRewardEntityBuilder fundingRewardEntity = FundingRewardEntity.builder();

        fundingRewardEntity.id( fundingReward.getId() );
        fundingRewardEntity.funding( fundingToFundingEntity( fundingReward.getFunding() ) );
        fundingRewardEntity.isDelivery( fundingReward.getIsDelivery() );
        fundingRewardEntity.rewardTitle( fundingReward.getRewardTitle() );
        fundingRewardEntity.amount( fundingReward.getAmount() );
        fundingRewardEntity.fundingRewardItems( fundingRewardItemListToFundingRewardItemEntityList( fundingReward.getFundingRewardItems() ) );
        fundingRewardEntity.countLimit( fundingReward.getCountLimit() );
        fundingRewardEntity.personalLimit( fundingReward.getPersonalLimit() );
        fundingRewardEntity.expectDate( fundingReward.getExpectDate() );
        fundingRewardEntity.createdAt( fundingReward.getCreatedAt() );
        fundingRewardEntity.updatedAt( fundingReward.getUpdatedAt() );

        return fundingRewardEntity.build();
    }

    protected SupportPaymentEntity supportPaymentToSupportPaymentEntity(SupportPayment supportPayment) {
        if ( supportPayment == null ) {
            return null;
        }

        SupportPaymentEntity.SupportPaymentEntityBuilder supportPaymentEntity = SupportPaymentEntity.builder();

        supportPaymentEntity.id( supportPayment.getId() );
        supportPaymentEntity.support( supportToSupportEntity( supportPayment.getSupport() ) );
        supportPaymentEntity.status( supportPayment.getStatus() );
        supportPaymentEntity.amount( supportPayment.getAmount() );
        supportPaymentEntity.paymentAt( supportPayment.getPaymentAt() );
        supportPaymentEntity.createdAt( supportPayment.getCreatedAt() );
        supportPaymentEntity.updatedAt( supportPayment.getUpdatedAt() );

        return supportPaymentEntity.build();
    }

    protected SupportEntity supportToSupportEntity(Support support) {
        if ( support == null ) {
            return null;
        }

        SupportEntity.SupportEntityBuilder supportEntity = SupportEntity.builder();

        supportEntity.id( support.getId() );
        supportEntity.member( memberToMemberEntity( support.getMember() ) );
        supportEntity.funding( fundingToFundingEntity( support.getFunding() ) );
        supportEntity.reward( fundingRewardToFundingRewardEntity( support.getReward() ) );
        supportEntity.supportKey( support.getSupportKey() );
        supportEntity.status( support.getStatus() );
        supportEntity.supportDelivery( SupportDeliveryEntity( support.getSupportDelivery() ) );
        supportEntity.supportPayment( supportPaymentToSupportPaymentEntity( support.getSupportPayment() ) );
        supportEntity.createdAt( support.getCreatedAt() );
        supportEntity.createdBy( support.getCreatedBy() );
        supportEntity.updatedAt( support.getUpdatedAt() );
        supportEntity.updatedBy( support.getUpdatedBy() );

        return supportEntity.build();
    }

    protected Member memberEntityToMember(MemberEntity memberEntity) {
        if ( memberEntity == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.id( memberEntity.getId() );
        member.userKey( memberEntity.getUserKey() );
        member.status( memberEntity.getStatus() );
        member.linkType( memberEntity.getLinkType() );
        member.email( memberEntity.getEmail() );
        member.userName( memberEntity.getUserName() );
        member.nickName( memberEntity.getNickName() );
        member.phoneNumber( memberEntity.getPhoneNumber() );
        member.gender( memberEntity.getGender() );
        member.birthday( memberEntity.getBirthday() );
        member.password( memberEntity.getPassword() );
        member.lastLoginAt( memberEntity.getLastLoginAt() );
        member.createdAt( memberEntity.getCreatedAt() );
        member.updatedAt( memberEntity.getUpdatedAt() );

        return member.build();
    }

    protected FundingTag fundingTagEntityToFundingTag(FundingTagEntity fundingTagEntity) {
        if ( fundingTagEntity == null ) {
            return null;
        }

        FundingTag.FundingTagBuilder fundingTag = FundingTag.builder();

        fundingTag.id( fundingTagEntity.getId() );
        fundingTag.funding( fundingEntityToFunding( fundingTagEntity.getFunding() ) );
        fundingTag.tag( fundingTagEntity.getTag() );
        fundingTag.createdAt( fundingTagEntity.getCreatedAt() );
        fundingTag.updatedAt( fundingTagEntity.getUpdatedAt() );

        return fundingTag.build();
    }

    protected List<FundingTag> fundingTagEntityListToFundingTagList(List<FundingTagEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<FundingTag> list1 = new ArrayList<FundingTag>( list.size() );
        for ( FundingTagEntity fundingTagEntity : list ) {
            list1.add( fundingTagEntityToFundingTag( fundingTagEntity ) );
        }

        return list1;
    }

    protected Funding fundingEntityToFunding(FundingEntity fundingEntity) {
        if ( fundingEntity == null ) {
            return null;
        }

        Funding.FundingBuilder funding = Funding.builder();

        funding.id( fundingEntity.getId() );
        funding.fundingKey( fundingEntity.getFundingKey() );
        funding.member( memberEntityToMember( fundingEntity.getMember() ) );
        funding.isAdult( fundingEntity.getIsAdult() );
        funding.pricePlan( fundingEntity.getPricePlan() );
        funding.category( fundingEntity.getCategory() );
        funding.expectAmount( fundingEntity.getExpectAmount() );
        funding.status( fundingEntity.getStatus() );
        funding.title( fundingEntity.getTitle() );
        funding.fundingDescription( fundingEntity.getFundingDescription() );
        funding.fundingIntroduce( fundingEntity.getFundingIntroduce() );
        funding.budgetDescription( fundingEntity.getBudgetDescription() );
        funding.scheduleDescription( fundingEntity.getScheduleDescription() );
        funding.teamDescription( fundingEntity.getTeamDescription() );
        funding.rewardDescription( fundingEntity.getRewardDescription() );
        funding.tags( fundingTagEntityListToFundingTagList( fundingEntity.getTags() ) );
        funding.startAt( fundingEntity.getStartAt() );
        funding.endAt( fundingEntity.getEndAt() );
        funding.createdAt( fundingEntity.getCreatedAt() );
        funding.createdBy( fundingEntity.getCreatedBy() );
        funding.updatedAt( fundingEntity.getUpdatedAt() );
        funding.updatedBy( fundingEntity.getUpdatedBy() );

        return funding.build();
    }

    protected FundingItemOption fundingItemOptionEntityToFundingItemOption(FundingItemOptionEntity fundingItemOptionEntity) {
        if ( fundingItemOptionEntity == null ) {
            return null;
        }

        FundingItemOption.FundingItemOptionBuilder fundingItemOption = FundingItemOption.builder();

        fundingItemOption.id( fundingItemOptionEntity.getId() );
        fundingItemOption.fundingItem( fundingItemEntityToFundingItem( fundingItemOptionEntity.getFundingItem() ) );
        fundingItemOption.createdAt( fundingItemOptionEntity.getCreatedAt() );
        fundingItemOption.updatedAt( fundingItemOptionEntity.getUpdatedAt() );

        return fundingItemOption.build();
    }

    protected List<FundingItemOption> fundingItemOptionEntityListToFundingItemOptionList(List<FundingItemOptionEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<FundingItemOption> list1 = new ArrayList<FundingItemOption>( list.size() );
        for ( FundingItemOptionEntity fundingItemOptionEntity : list ) {
            list1.add( fundingItemOptionEntityToFundingItemOption( fundingItemOptionEntity ) );
        }

        return list1;
    }

    protected FundingItem fundingItemEntityToFundingItem(FundingItemEntity fundingItemEntity) {
        if ( fundingItemEntity == null ) {
            return null;
        }

        FundingItem.FundingItemBuilder fundingItem = FundingItem.builder();

        fundingItem.id( fundingItemEntity.getId() );
        fundingItem.funding( fundingEntityToFunding( fundingItemEntity.getFunding() ) );
        fundingItem.itemName( fundingItemEntity.getItemName() );
        fundingItem.optionType( fundingItemEntity.getOptionType() );
        fundingItem.fundingItemOptions( fundingItemOptionEntityListToFundingItemOptionList( fundingItemEntity.getFundingItemOptions() ) );
        fundingItem.createdAt( fundingItemEntity.getCreatedAt() );
        fundingItem.updatedAt( fundingItemEntity.getUpdatedAt() );

        return fundingItem.build();
    }

    protected FundingRewardItem fundingRewardItemEntityToFundingRewardItem(FundingRewardItemEntity fundingRewardItemEntity) {
        if ( fundingRewardItemEntity == null ) {
            return null;
        }

        FundingRewardItem.FundingRewardItemBuilder fundingRewardItem = FundingRewardItem.builder();

        fundingRewardItem.id( fundingRewardItemEntity.getId() );
        fundingRewardItem.funding( fundingEntityToFunding( fundingRewardItemEntity.getFunding() ) );
        fundingRewardItem.fundingReward( fundingRewardEntityToFundingReward( fundingRewardItemEntity.getFundingReward() ) );
        fundingRewardItem.fundingItem( fundingItemEntityToFundingItem( fundingRewardItemEntity.getFundingItem() ) );
        fundingRewardItem.createdAt( fundingRewardItemEntity.getCreatedAt() );
        fundingRewardItem.updatedAt( fundingRewardItemEntity.getUpdatedAt() );

        return fundingRewardItem.build();
    }

    protected List<FundingRewardItem> fundingRewardItemEntityListToFundingRewardItemList(List<FundingRewardItemEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<FundingRewardItem> list1 = new ArrayList<FundingRewardItem>( list.size() );
        for ( FundingRewardItemEntity fundingRewardItemEntity : list ) {
            list1.add( fundingRewardItemEntityToFundingRewardItem( fundingRewardItemEntity ) );
        }

        return list1;
    }

    protected FundingReward fundingRewardEntityToFundingReward(FundingRewardEntity fundingRewardEntity) {
        if ( fundingRewardEntity == null ) {
            return null;
        }

        FundingReward.FundingRewardBuilder fundingReward = FundingReward.builder();

        fundingReward.id( fundingRewardEntity.getId() );
        fundingReward.funding( fundingEntityToFunding( fundingRewardEntity.getFunding() ) );
        fundingReward.isDelivery( fundingRewardEntity.getIsDelivery() );
        fundingReward.rewardTitle( fundingRewardEntity.getRewardTitle() );
        fundingReward.amount( fundingRewardEntity.getAmount() );
        fundingReward.fundingRewardItems( fundingRewardItemEntityListToFundingRewardItemList( fundingRewardEntity.getFundingRewardItems() ) );
        fundingReward.countLimit( fundingRewardEntity.getCountLimit() );
        fundingReward.personalLimit( fundingRewardEntity.getPersonalLimit() );
        fundingReward.expectDate( fundingRewardEntity.getExpectDate() );
        fundingReward.createdAt( fundingRewardEntity.getCreatedAt() );
        fundingReward.updatedAt( fundingRewardEntity.getUpdatedAt() );

        return fundingReward.build();
    }

    protected SupportPayment supportPaymentEntityToSupportPayment(SupportPaymentEntity supportPaymentEntity) {
        if ( supportPaymentEntity == null ) {
            return null;
        }

        SupportPayment.SupportPaymentBuilder supportPayment = SupportPayment.builder();

        supportPayment.id( supportPaymentEntity.getId() );
        supportPayment.support( supportEntityToSupport( supportPaymentEntity.getSupport() ) );
        supportPayment.status( supportPaymentEntity.getStatus() );
        supportPayment.amount( supportPaymentEntity.getAmount() );
        supportPayment.paymentAt( supportPaymentEntity.getPaymentAt() );
        supportPayment.createdAt( supportPaymentEntity.getCreatedAt() );
        supportPayment.updatedAt( supportPaymentEntity.getUpdatedAt() );

        return supportPayment.build();
    }

    protected Support supportEntityToSupport(SupportEntity supportEntity) {
        if ( supportEntity == null ) {
            return null;
        }

        Support.SupportBuilder support = Support.builder();

        support.id( supportEntity.getId() );
        support.member( memberEntityToMember( supportEntity.getMember() ) );
        support.funding( fundingEntityToFunding( supportEntity.getFunding() ) );
        support.reward( fundingRewardEntityToFundingReward( supportEntity.getReward() ) );
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
}
