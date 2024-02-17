package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.Funding;
import com.flab.funding.infrastructure.adapters.input.data.request.FundingRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.FundingInfoResponse;
import com.flab.funding.infrastructure.adapters.input.data.response.FundingRegisterResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-17T22:05:59+0900",
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

        if ( fundingRegisterRequest.getIsAdult() != null ) {
            funding.isAdult( fundingRegisterRequest.getIsAdult() );
        }
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

        if ( funding.getFundingKey() != null ) {
            fundingRegisterResponse.fundingKey( Long.parseLong( funding.getFundingKey() ) );
        }
        fundingRegisterResponse.status( funding.getStatus() );

        return fundingRegisterResponse.build();
    }

    @Override
    public FundingInfoResponse toFundingInfoResponse(Funding funding) {
        if ( funding == null ) {
            return null;
        }

        FundingInfoResponse.FundingInfoResponseBuilder fundingInfoResponse = FundingInfoResponse.builder();

        if ( funding.getFundingKey() != null ) {
            fundingInfoResponse.fundingKey( Long.parseLong( funding.getFundingKey() ) );
        }
        fundingInfoResponse.status( funding.getStatus() );

        return fundingInfoResponse.build();
    }
}
