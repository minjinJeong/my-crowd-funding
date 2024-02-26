package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.Funding;
import com.flab.funding.domain.model.FundingCreator;
import com.flab.funding.domain.model.FundingItem;
import com.flab.funding.domain.model.FundingItemOption;
import com.flab.funding.domain.model.FundingReward;
import com.flab.funding.domain.model.FundingRewardItem;
import com.flab.funding.infrastructure.adapters.input.data.request.FundingCreatorRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.FundingItemOptionRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.FundingItemRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.FundingRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.FundingRewardItemRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.FundingRewardRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.FundingCreatorInfoResponse;
import com.flab.funding.infrastructure.adapters.input.data.response.FundingInfoResponse;
import com.flab.funding.infrastructure.adapters.input.data.response.FundingItemInfoResponse;
import com.flab.funding.infrastructure.adapters.input.data.response.FundingRegisterResponse;
import com.flab.funding.infrastructure.adapters.input.data.response.FundingRewardInfoResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-26T21:15:58+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class FundingMapperImpl implements FundingMapper {

    @Override
    public Funding toFunding(FundingRegisterRequest fundingRegisterRequest) {
        if ( fundingRegisterRequest == null ) {
            return null;
        }

        Funding.FundingBuilder funding = Funding.builder();

        funding.isAdult( fundingRegisterRequest.getIsAdult() );
        funding.pricePlan( fundingRegisterRequest.getPricePlan() );
        funding.categoryCode( fundingRegisterRequest.getCategoryCode() );
        funding.expectAmount( fundingRegisterRequest.getExpectAmount() );
        funding.title( fundingRegisterRequest.getTitle() );
        funding.fundingDesc( fundingRegisterRequest.getFundingDesc() );
        funding.fundingIntroduce( fundingRegisterRequest.getFundingIntroduce() );
        funding.budgetDesc( fundingRegisterRequest.getBudgetDesc() );
        funding.scheduleDesc( fundingRegisterRequest.getScheduleDesc() );
        funding.teamDesc( fundingRegisterRequest.getTeamDesc() );
        funding.rewardDesc( fundingRegisterRequest.getRewardDesc() );
        funding.startAt( fundingRegisterRequest.getStartAt() );
        funding.endAt( fundingRegisterRequest.getEndAt() );

        return funding.build();
    }

    @Override
    public FundingRegisterResponse toFundingRegisterResponse(Funding funding) {
        if ( funding == null ) {
            return null;
        }

        FundingRegisterResponse.FundingRegisterResponseBuilder fundingRegisterResponse = FundingRegisterResponse.builder();

        fundingRegisterResponse.fundingKey( funding.getFundingKey() );
        fundingRegisterResponse.status( funding.getStatus() );

        return fundingRegisterResponse.build();
    }

    @Override
    public FundingInfoResponse toFundingInfoResponse(Funding funding) {
        if ( funding == null ) {
            return null;
        }

        FundingInfoResponse.FundingInfoResponseBuilder fundingInfoResponse = FundingInfoResponse.builder();

        fundingInfoResponse.fundingKey( funding.getFundingKey() );
        fundingInfoResponse.status( funding.getStatus() );

        return fundingInfoResponse.build();
    }

    @Override
    public FundingCreator toFundingCreator(FundingCreatorRegisterRequest fundingCreatorRegisterRequest) {
        if ( fundingCreatorRegisterRequest == null ) {
            return null;
        }

        FundingCreator.FundingCreatorBuilder fundingCreator = FundingCreator.builder();

        fundingCreator.fundingKey( fundingCreatorRegisterRequest.getFundingKey() );
        fundingCreator.isValid( fundingCreatorRegisterRequest.getIsValid() );
        fundingCreator.businessNum( fundingCreatorRegisterRequest.getBusinessNum() );
        fundingCreator.representative( fundingCreatorRegisterRequest.getRepresentative() );
        fundingCreator.introduce( fundingCreatorRegisterRequest.getIntroduce() );

        return fundingCreator.build();
    }

    @Override
    public FundingReward toFundingReward(FundingRewardRegisterRequest fundingRewardRegisterRequest) {
        if ( fundingRewardRegisterRequest == null ) {
            return null;
        }

        FundingReward.FundingRewardBuilder fundingReward = FundingReward.builder();

        fundingReward.fundingKey( fundingRewardRegisterRequest.getFundingKey() );
        fundingReward.isDelivery( fundingRewardRegisterRequest.getIsDelivery() );
        fundingReward.rewardTitle( fundingRewardRegisterRequest.getRewardTitle() );
        fundingReward.amount( fundingRewardRegisterRequest.getAmount() );
        fundingReward.fundingRewardItems( fundingRewardItemRequestListToFundingRewardItemList( fundingRewardRegisterRequest.getFundingRewardItems() ) );
        fundingReward.countLimit( fundingRewardRegisterRequest.getCountLimit() );
        fundingReward.personalLimit( fundingRewardRegisterRequest.getPersonalLimit() );
        fundingReward.expectDate( fundingRewardRegisterRequest.getExpectDate() );

        return fundingReward.build();
    }

    @Override
    public FundingItem toFundingItem(FundingItemRegisterRequest fundingItemRegisterRequest) {
        if ( fundingItemRegisterRequest == null ) {
            return null;
        }

        FundingItem.FundingItemBuilder fundingItem = FundingItem.builder();

        fundingItem.fundingKey( fundingItemRegisterRequest.getFundingKey() );
        fundingItem.itemName( fundingItemRegisterRequest.getItemName() );
        fundingItem.optionType( fundingItemRegisterRequest.getOptionType() );
        fundingItem.fundingItemOptions( fundingItemOptionRequestListToFundingItemOptionList( fundingItemRegisterRequest.getFundingItemOptions() ) );

        return fundingItem.build();
    }

    @Override
    public FundingCreatorInfoResponse toFundingCreatorInfoResponse(FundingCreator fundingCreator) {
        if ( fundingCreator == null ) {
            return null;
        }

        FundingCreatorInfoResponse.FundingCreatorInfoResponseBuilder fundingCreatorInfoResponse = FundingCreatorInfoResponse.builder();

        fundingCreatorInfoResponse.fundingKey( fundingCreator.getFundingKey() );
        fundingCreatorInfoResponse.isValid( fundingCreator.getIsValid() );
        fundingCreatorInfoResponse.businessNum( fundingCreator.getBusinessNum() );
        fundingCreatorInfoResponse.representative( fundingCreator.getRepresentative() );
        fundingCreatorInfoResponse.introduce( fundingCreator.getIntroduce() );

        return fundingCreatorInfoResponse.build();
    }

    @Override
    public FundingItemInfoResponse toFundingItemInfoResponse(FundingItem fundingItem) {
        if ( fundingItem == null ) {
            return null;
        }

        FundingItemInfoResponse.FundingItemInfoResponseBuilder fundingItemInfoResponse = FundingItemInfoResponse.builder();

        fundingItemInfoResponse.fundingKey( fundingItem.getFundingKey() );
        fundingItemInfoResponse.itemName( fundingItem.getItemName() );
        fundingItemInfoResponse.optionType( fundingItem.getOptionType() );
        fundingItemInfoResponse.fundingItemOptions( fundingItemOptionListToFundingItemOptionRequestList( fundingItem.getFundingItemOptions() ) );

        return fundingItemInfoResponse.build();
    }

    @Override
    public FundingRewardInfoResponse toFundingRewardInfoResponse(FundingReward fundingReward) {
        if ( fundingReward == null ) {
            return null;
        }

        FundingRewardInfoResponse.FundingRewardInfoResponseBuilder fundingRewardInfoResponse = FundingRewardInfoResponse.builder();

        fundingRewardInfoResponse.fundingKey( fundingReward.getFundingKey() );
        fundingRewardInfoResponse.isDelivery( fundingReward.getIsDelivery() );
        fundingRewardInfoResponse.rewardTitle( fundingReward.getRewardTitle() );
        fundingRewardInfoResponse.amount( fundingReward.getAmount() );
        fundingRewardInfoResponse.fundingRewardItems( fundingRewardItemListToFundingRewardItemRequestList( fundingReward.getFundingRewardItems() ) );
        fundingRewardInfoResponse.countLimit( fundingReward.getCountLimit() );
        fundingRewardInfoResponse.personalLimit( fundingReward.getPersonalLimit() );
        fundingRewardInfoResponse.expectDate( fundingReward.getExpectDate() );

        return fundingRewardInfoResponse.build();
    }

    protected FundingRewardItem fundingRewardItemRequestToFundingRewardItem(FundingRewardItemRequest fundingRewardItemRequest) {
        if ( fundingRewardItemRequest == null ) {
            return null;
        }

        FundingRewardItem.FundingRewardItemBuilder fundingRewardItem = FundingRewardItem.builder();

        fundingRewardItem.fundingItemId( fundingRewardItemRequest.getFundingItemId() );

        return fundingRewardItem.build();
    }

    protected List<FundingRewardItem> fundingRewardItemRequestListToFundingRewardItemList(List<FundingRewardItemRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<FundingRewardItem> list1 = new ArrayList<FundingRewardItem>( list.size() );
        for ( FundingRewardItemRequest fundingRewardItemRequest : list ) {
            list1.add( fundingRewardItemRequestToFundingRewardItem( fundingRewardItemRequest ) );
        }

        return list1;
    }

    protected FundingItemOption fundingItemOptionRequestToFundingItemOption(FundingItemOptionRequest fundingItemOptionRequest) {
        if ( fundingItemOptionRequest == null ) {
            return null;
        }

        FundingItemOption.FundingItemOptionBuilder fundingItemOption = FundingItemOption.builder();

        fundingItemOption.option( fundingItemOptionRequest.getOption() );

        return fundingItemOption.build();
    }

    protected List<FundingItemOption> fundingItemOptionRequestListToFundingItemOptionList(List<FundingItemOptionRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<FundingItemOption> list1 = new ArrayList<FundingItemOption>( list.size() );
        for ( FundingItemOptionRequest fundingItemOptionRequest : list ) {
            list1.add( fundingItemOptionRequestToFundingItemOption( fundingItemOptionRequest ) );
        }

        return list1;
    }

    protected FundingItemOptionRequest fundingItemOptionToFundingItemOptionRequest(FundingItemOption fundingItemOption) {
        if ( fundingItemOption == null ) {
            return null;
        }

        FundingItemOptionRequest.FundingItemOptionRequestBuilder fundingItemOptionRequest = FundingItemOptionRequest.builder();

        fundingItemOptionRequest.option( fundingItemOption.getOption() );

        return fundingItemOptionRequest.build();
    }

    protected List<FundingItemOptionRequest> fundingItemOptionListToFundingItemOptionRequestList(List<FundingItemOption> list) {
        if ( list == null ) {
            return null;
        }

        List<FundingItemOptionRequest> list1 = new ArrayList<FundingItemOptionRequest>( list.size() );
        for ( FundingItemOption fundingItemOption : list ) {
            list1.add( fundingItemOptionToFundingItemOptionRequest( fundingItemOption ) );
        }

        return list1;
    }

    protected FundingRewardItemRequest fundingRewardItemToFundingRewardItemRequest(FundingRewardItem fundingRewardItem) {
        if ( fundingRewardItem == null ) {
            return null;
        }

        FundingRewardItemRequest.FundingRewardItemRequestBuilder fundingRewardItemRequest = FundingRewardItemRequest.builder();

        fundingRewardItemRequest.fundingItemId( fundingRewardItem.getFundingItemId() );

        return fundingRewardItemRequest.build();
    }

    protected List<FundingRewardItemRequest> fundingRewardItemListToFundingRewardItemRequestList(List<FundingRewardItem> list) {
        if ( list == null ) {
            return null;
        }

        List<FundingRewardItemRequest> list1 = new ArrayList<FundingRewardItemRequest>( list.size() );
        for ( FundingRewardItem fundingRewardItem : list ) {
            list1.add( fundingRewardItemToFundingRewardItemRequest( fundingRewardItem ) );
        }

        return list1;
    }
}
