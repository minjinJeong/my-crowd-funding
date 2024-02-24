package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.Funding;
import com.flab.funding.domain.model.FundingCreator;
import com.flab.funding.domain.model.FundingItem;
import com.flab.funding.domain.model.FundingReward;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingCreatorEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingItemEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingRewardEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-24T23:26:28+0900",
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
        fundingEntity.member( funding.getMember() );
        fundingEntity.isAdult( funding.getIsAdult() );
        fundingEntity.pricePlan( funding.getPricePlan() );
        fundingEntity.categoryCode( funding.getCategoryCode() );
        fundingEntity.expectAmount( funding.getExpectAmount() );
        fundingEntity.status( funding.getStatus() );
        fundingEntity.title( funding.getTitle() );
        fundingEntity.fundingDesc( funding.getFundingDesc() );
        fundingEntity.fundingIntroduce( funding.getFundingIntroduce() );
        fundingEntity.budgetDesc( funding.getBudgetDesc() );
        fundingEntity.scheduleDesc( funding.getScheduleDesc() );
        fundingEntity.teamDesc( funding.getTeamDesc() );
        fundingEntity.rewardDesc( funding.getRewardDesc() );
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
        funding.member( fundingEntity.getMember() );
        funding.isAdult( fundingEntity.getIsAdult() );
        funding.pricePlan( fundingEntity.getPricePlan() );
        funding.categoryCode( fundingEntity.getCategoryCode() );
        funding.expectAmount( fundingEntity.getExpectAmount() );
        funding.status( fundingEntity.getStatus() );
        funding.title( fundingEntity.getTitle() );
        funding.fundingDesc( fundingEntity.getFundingDesc() );
        funding.fundingIntroduce( fundingEntity.getFundingIntroduce() );
        funding.budgetDesc( fundingEntity.getBudgetDesc() );
        funding.scheduleDesc( fundingEntity.getScheduleDesc() );
        funding.teamDesc( fundingEntity.getTeamDesc() );
        funding.rewardDesc( fundingEntity.getRewardDesc() );
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
        fundingCreatorEntity.businessNum( fundingCreator.getBusinessNum() );
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

        Long id = fundingCreatorEntityFundingId( fundingCreatorEntity );
        if ( id != null ) {
            fundingCreator.fundingId( String.valueOf( id ) );
        }
        fundingCreator.id( fundingCreatorEntity.getId() );
        fundingCreator.isValid( fundingCreatorEntity.getIsValid() );
        fundingCreator.businessNum( fundingCreatorEntity.getBusinessNum() );
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

        Long id = fundingItemEntityFundingId( fundingItemEntity );
        if ( id != null ) {
            fundingItem.fundingId( String.valueOf( id ) );
        }
        fundingItem.id( fundingItemEntity.getId() );
        fundingItem.itemName( fundingItemEntity.getItemName() );
        fundingItem.optionType( fundingItemEntity.getOptionType() );
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

        Long id = fundingRewardEntityFundingId( fundingRewardEntity );
        if ( id != null ) {
            fundingReward.fundingId( String.valueOf( id ) );
        }
        fundingReward.id( fundingRewardEntity.getId() );
        fundingReward.isDelivery( fundingRewardEntity.getIsDelivery() );
        fundingReward.rewardTitle( fundingRewardEntity.getRewardTitle() );
        fundingReward.amount( fundingRewardEntity.getAmount() );
        fundingReward.countLimit( fundingRewardEntity.getCountLimit() );
        fundingReward.personalLimit( fundingRewardEntity.getPersonalLimit() );
        fundingReward.expectDate( fundingRewardEntity.getExpectDate() );
        fundingReward.createdAt( fundingRewardEntity.getCreatedAt() );
        fundingReward.updatedAt( fundingRewardEntity.getUpdatedAt() );

        return fundingReward.build();
    }

    protected FundingEntity fundingCreatorToFundingEntity(FundingCreator fundingCreator) {
        if ( fundingCreator == null ) {
            return null;
        }

        FundingEntity.FundingEntityBuilder fundingEntity = FundingEntity.builder();

        if ( fundingCreator.getFundingId() != null ) {
            fundingEntity.id( Long.parseLong( fundingCreator.getFundingId() ) );
        }

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

    protected FundingEntity fundingItemToFundingEntity(FundingItem fundingItem) {
        if ( fundingItem == null ) {
            return null;
        }

        FundingEntity.FundingEntityBuilder fundingEntity = FundingEntity.builder();

        if ( fundingItem.getFundingId() != null ) {
            fundingEntity.id( Long.parseLong( fundingItem.getFundingId() ) );
        }

        return fundingEntity.build();
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

    protected FundingEntity fundingRewardToFundingEntity(FundingReward fundingReward) {
        if ( fundingReward == null ) {
            return null;
        }

        FundingEntity.FundingEntityBuilder fundingEntity = FundingEntity.builder();

        if ( fundingReward.getFundingId() != null ) {
            fundingEntity.id( Long.parseLong( fundingReward.getFundingId() ) );
        }

        return fundingEntity.build();
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
}
