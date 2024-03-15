package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.Funding;
import com.flab.funding.domain.model.FundingCreator;
import com.flab.funding.domain.model.FundingItem;
import com.flab.funding.domain.model.FundingItemOption;
import com.flab.funding.domain.model.FundingReward;
import com.flab.funding.domain.model.FundingRewardItem;
import com.flab.funding.domain.model.FundingTag;
import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingCreatorEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingItemEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingItemOptionEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingRewardEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingRewardItemEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingTagEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-15T22:17:34+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class FundingPersistenceMapperImpl implements FundingPersistenceMapper {

    @Override
    public FundingEntity toFundingEntity(Funding funding) {
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

    @Override
    public Funding toFunding(FundingEntity fundingEntity) {
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

    @Override
    public FundingCreatorEntity toFundingCreatorEntity(FundingCreator fundingCreator) {
        if ( fundingCreator == null ) {
            return null;
        }

        FundingCreatorEntity.FundingCreatorEntityBuilder fundingCreatorEntity = FundingCreatorEntity.builder();

        fundingCreatorEntity.funding( fundingCreatorToFundingEntity( fundingCreator ) );
        fundingCreatorEntity.id( fundingCreator.getId() );
        fundingCreatorEntity.isValid( fundingCreator.getIsValid() );
        fundingCreatorEntity.businessNumber( fundingCreator.getBusinessNumber() );
        fundingCreatorEntity.representative( fundingCreator.getRepresentative() );
        fundingCreatorEntity.introduce( fundingCreator.getIntroduce() );
        fundingCreatorEntity.createdAt( fundingCreator.getCreatedAt() );
        fundingCreatorEntity.updatedAt( fundingCreator.getUpdatedAt() );

        return fundingCreatorEntity.build();
    }

    @Override
    public FundingCreator toFundingCreator(FundingCreatorEntity fundingCreatorEntity) {
        if ( fundingCreatorEntity == null ) {
            return null;
        }

        FundingCreator.FundingCreatorBuilder fundingCreator = FundingCreator.builder();

        fundingCreator.fundingId( fundingCreatorEntityFundingId( fundingCreatorEntity ) );
        fundingCreator.fundingKey( fundingCreatorEntityFundingFundingKey( fundingCreatorEntity ) );
        fundingCreator.id( fundingCreatorEntity.getId() );
        fundingCreator.isValid( fundingCreatorEntity.getIsValid() );
        fundingCreator.businessNumber( fundingCreatorEntity.getBusinessNumber() );
        fundingCreator.representative( fundingCreatorEntity.getRepresentative() );
        fundingCreator.introduce( fundingCreatorEntity.getIntroduce() );
        fundingCreator.createdAt( fundingCreatorEntity.getCreatedAt() );
        fundingCreator.updatedAt( fundingCreatorEntity.getUpdatedAt() );

        return fundingCreator.build();
    }

    @Override
    public FundingItemEntity toFundingItemEntity(FundingItem fundingItem) {
        if ( fundingItem == null ) {
            return null;
        }

        FundingItemEntity.FundingItemEntityBuilder fundingItemEntity = FundingItemEntity.builder();

        fundingItemEntity.funding( fundingItemToFundingEntity( fundingItem ) );
        fundingItemEntity.id( fundingItem.getId() );
        fundingItemEntity.itemName( fundingItem.getItemName() );
        fundingItemEntity.optionType( fundingItem.getOptionType() );
        fundingItemEntity.fundingItemOptions( fundingItemOptionListToFundingItemOptionEntityList( fundingItem.getFundingItemOptions() ) );
        fundingItemEntity.createdAt( fundingItem.getCreatedAt() );
        fundingItemEntity.updatedAt( fundingItem.getUpdatedAt() );

        return fundingItemEntity.build();
    }

    @Override
    public FundingItem toFundingItem(FundingItemEntity fundingItemEntity) {
        if ( fundingItemEntity == null ) {
            return null;
        }

        FundingItem.FundingItemBuilder fundingItem = FundingItem.builder();

        fundingItem.fundingId( fundingItemEntityFundingId( fundingItemEntity ) );
        fundingItem.fundingKey( fundingItemEntityFundingFundingKey( fundingItemEntity ) );
        fundingItem.id( fundingItemEntity.getId() );
        fundingItem.itemName( fundingItemEntity.getItemName() );
        fundingItem.optionType( fundingItemEntity.getOptionType() );
        fundingItem.fundingItemOptions( fundingItemOptionEntityListToFundingItemOptionList( fundingItemEntity.getFundingItemOptions() ) );
        fundingItem.createdAt( fundingItemEntity.getCreatedAt() );
        fundingItem.updatedAt( fundingItemEntity.getUpdatedAt() );

        return fundingItem.build();
    }

    @Override
    public FundingRewardEntity toFundingRewardEntity(FundingReward fundingReward) {
        if ( fundingReward == null ) {
            return null;
        }

        FundingRewardEntity.FundingRewardEntityBuilder fundingRewardEntity = FundingRewardEntity.builder();

        fundingRewardEntity.funding( fundingRewardToFundingEntity( fundingReward ) );
        fundingRewardEntity.id( fundingReward.getId() );
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

    @Override
    public FundingReward toFundingReward(FundingRewardEntity fundingRewardEntity) {
        if ( fundingRewardEntity == null ) {
            return null;
        }

        FundingReward.FundingRewardBuilder fundingReward = FundingReward.builder();

        fundingReward.fundingId( fundingRewardEntityFundingId( fundingRewardEntity ) );
        fundingReward.fundingKey( fundingRewardEntityFundingFundingKey( fundingRewardEntity ) );
        fundingReward.id( fundingRewardEntity.getId() );
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

    @Override
    public FundingRewardItemEntity toFundingRewardItemEntity(FundingRewardItem fundingRewardItem) {
        if ( fundingRewardItem == null ) {
            return null;
        }

        FundingRewardItemEntity.FundingRewardItemEntityBuilder fundingRewardItemEntity = FundingRewardItemEntity.builder();

        fundingRewardItemEntity.funding( fundingRewardItemToFundingEntity( fundingRewardItem ) );
        fundingRewardItemEntity.fundingReward( fundingRewardItemToFundingRewardEntity( fundingRewardItem ) );
        fundingRewardItemEntity.fundingItem( fundingRewardItemToFundingItemEntity( fundingRewardItem ) );
        fundingRewardItemEntity.id( fundingRewardItem.getId() );
        fundingRewardItemEntity.createdAt( fundingRewardItem.getCreatedAt() );
        fundingRewardItemEntity.updatedAt( fundingRewardItem.getUpdatedAt() );

        return fundingRewardItemEntity.build();
    }

    @Override
    public FundingRewardItem toFundingRewardItem(FundingRewardItemEntity fundingRewardItemEntity) {
        if ( fundingRewardItemEntity == null ) {
            return null;
        }

        FundingRewardItem.FundingRewardItemBuilder fundingRewardItem = FundingRewardItem.builder();

        fundingRewardItem.fundingId( fundingRewardItemEntityFundingId( fundingRewardItemEntity ) );
        fundingRewardItem.fundingRewardId( fundingRewardItemEntityFundingRewardId( fundingRewardItemEntity ) );
        fundingRewardItem.fundingItemId( fundingRewardItemEntityFundingItemId( fundingRewardItemEntity ) );
        fundingRewardItem.id( fundingRewardItemEntity.getId() );
        fundingRewardItem.createdAt( fundingRewardItemEntity.getCreatedAt() );
        fundingRewardItem.updatedAt( fundingRewardItemEntity.getUpdatedAt() );

        return fundingRewardItem.build();
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

    protected FundingEntity fundingCreatorToFundingEntity(FundingCreator fundingCreator) {
        if ( fundingCreator == null ) {
            return null;
        }

        FundingEntity.FundingEntityBuilder fundingEntity = FundingEntity.builder();

        fundingEntity.fundingKey( fundingCreator.getFundingKey() );

        return fundingEntity.build();
    }

    private Long fundingCreatorEntityFundingId(FundingCreatorEntity fundingCreatorEntity) {
        if ( fundingCreatorEntity == null ) {
            return null;
        }
        FundingEntity funding = fundingCreatorEntity.getFunding();
        if ( funding == null ) {
            return null;
        }
        Long id = funding.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String fundingCreatorEntityFundingFundingKey(FundingCreatorEntity fundingCreatorEntity) {
        if ( fundingCreatorEntity == null ) {
            return null;
        }
        FundingEntity funding = fundingCreatorEntity.getFunding();
        if ( funding == null ) {
            return null;
        }
        String fundingKey = funding.getFundingKey();
        if ( fundingKey == null ) {
            return null;
        }
        return fundingKey;
    }

    protected FundingEntity fundingItemToFundingEntity(FundingItem fundingItem) {
        if ( fundingItem == null ) {
            return null;
        }

        FundingEntity.FundingEntityBuilder fundingEntity = FundingEntity.builder();

        fundingEntity.id( fundingItem.getFundingId() );
        fundingEntity.fundingKey( fundingItem.getFundingKey() );

        return fundingEntity.build();
    }

    protected FundingItemOptionEntity fundingItemOptionToFundingItemOptionEntity(FundingItemOption fundingItemOption) {
        if ( fundingItemOption == null ) {
            return null;
        }

        FundingItemOptionEntity.FundingItemOptionEntityBuilder fundingItemOptionEntity = FundingItemOptionEntity.builder();

        fundingItemOptionEntity.id( fundingItemOption.getId() );
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

    private Long fundingItemEntityFundingId(FundingItemEntity fundingItemEntity) {
        if ( fundingItemEntity == null ) {
            return null;
        }
        FundingEntity funding = fundingItemEntity.getFunding();
        if ( funding == null ) {
            return null;
        }
        Long id = funding.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String fundingItemEntityFundingFundingKey(FundingItemEntity fundingItemEntity) {
        if ( fundingItemEntity == null ) {
            return null;
        }
        FundingEntity funding = fundingItemEntity.getFunding();
        if ( funding == null ) {
            return null;
        }
        String fundingKey = funding.getFundingKey();
        if ( fundingKey == null ) {
            return null;
        }
        return fundingKey;
    }

    protected FundingItemOption fundingItemOptionEntityToFundingItemOption(FundingItemOptionEntity fundingItemOptionEntity) {
        if ( fundingItemOptionEntity == null ) {
            return null;
        }

        FundingItemOption.FundingItemOptionBuilder fundingItemOption = FundingItemOption.builder();

        fundingItemOption.id( fundingItemOptionEntity.getId() );
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

    protected FundingEntity fundingRewardToFundingEntity(FundingReward fundingReward) {
        if ( fundingReward == null ) {
            return null;
        }

        FundingEntity.FundingEntityBuilder fundingEntity = FundingEntity.builder();

        fundingEntity.id( fundingReward.getFundingId() );
        fundingEntity.fundingKey( fundingReward.getFundingKey() );

        return fundingEntity.build();
    }

    protected List<FundingRewardItemEntity> fundingRewardItemListToFundingRewardItemEntityList(List<FundingRewardItem> list) {
        if ( list == null ) {
            return null;
        }

        List<FundingRewardItemEntity> list1 = new ArrayList<FundingRewardItemEntity>( list.size() );
        for ( FundingRewardItem fundingRewardItem : list ) {
            list1.add( toFundingRewardItemEntity( fundingRewardItem ) );
        }

        return list1;
    }

    private Long fundingRewardEntityFundingId(FundingRewardEntity fundingRewardEntity) {
        if ( fundingRewardEntity == null ) {
            return null;
        }
        FundingEntity funding = fundingRewardEntity.getFunding();
        if ( funding == null ) {
            return null;
        }
        Long id = funding.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String fundingRewardEntityFundingFundingKey(FundingRewardEntity fundingRewardEntity) {
        if ( fundingRewardEntity == null ) {
            return null;
        }
        FundingEntity funding = fundingRewardEntity.getFunding();
        if ( funding == null ) {
            return null;
        }
        String fundingKey = funding.getFundingKey();
        if ( fundingKey == null ) {
            return null;
        }
        return fundingKey;
    }

    protected List<FundingRewardItem> fundingRewardItemEntityListToFundingRewardItemList(List<FundingRewardItemEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<FundingRewardItem> list1 = new ArrayList<FundingRewardItem>( list.size() );
        for ( FundingRewardItemEntity fundingRewardItemEntity : list ) {
            list1.add( toFundingRewardItem( fundingRewardItemEntity ) );
        }

        return list1;
    }

    protected FundingEntity fundingRewardItemToFundingEntity(FundingRewardItem fundingRewardItem) {
        if ( fundingRewardItem == null ) {
            return null;
        }

        FundingEntity.FundingEntityBuilder fundingEntity = FundingEntity.builder();

        fundingEntity.id( fundingRewardItem.getFundingId() );

        return fundingEntity.build();
    }

    protected FundingRewardEntity fundingRewardItemToFundingRewardEntity(FundingRewardItem fundingRewardItem) {
        if ( fundingRewardItem == null ) {
            return null;
        }

        FundingRewardEntity.FundingRewardEntityBuilder fundingRewardEntity = FundingRewardEntity.builder();

        fundingRewardEntity.id( fundingRewardItem.getFundingRewardId() );

        return fundingRewardEntity.build();
    }

    protected FundingItemEntity fundingRewardItemToFundingItemEntity(FundingRewardItem fundingRewardItem) {
        if ( fundingRewardItem == null ) {
            return null;
        }

        FundingItemEntity.FundingItemEntityBuilder fundingItemEntity = FundingItemEntity.builder();

        fundingItemEntity.id( fundingRewardItem.getFundingItemId() );

        return fundingItemEntity.build();
    }

    private Long fundingRewardItemEntityFundingId(FundingRewardItemEntity fundingRewardItemEntity) {
        if ( fundingRewardItemEntity == null ) {
            return null;
        }
        FundingEntity funding = fundingRewardItemEntity.getFunding();
        if ( funding == null ) {
            return null;
        }
        Long id = funding.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long fundingRewardItemEntityFundingRewardId(FundingRewardItemEntity fundingRewardItemEntity) {
        if ( fundingRewardItemEntity == null ) {
            return null;
        }
        FundingRewardEntity fundingReward = fundingRewardItemEntity.getFundingReward();
        if ( fundingReward == null ) {
            return null;
        }
        Long id = fundingReward.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long fundingRewardItemEntityFundingItemId(FundingRewardItemEntity fundingRewardItemEntity) {
        if ( fundingRewardItemEntity == null ) {
            return null;
        }
        FundingItemEntity fundingItem = fundingRewardItemEntity.getFundingItem();
        if ( fundingItem == null ) {
            return null;
        }
        Long id = fundingItem.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
