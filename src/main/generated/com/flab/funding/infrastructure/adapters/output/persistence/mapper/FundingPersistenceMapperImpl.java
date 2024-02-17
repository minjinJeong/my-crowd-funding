package com.flab.funding.infrastructure.adapters.output.persistence.mapper;

import com.flab.funding.domain.model.Funding;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-17T22:05:59+0900",
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
}
