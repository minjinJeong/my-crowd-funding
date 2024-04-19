package com.flab.funding.service;

import com.flab.funding.application.ports.output.FundingPort;
import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.model.*;
import com.flab.funding.domain.service.FundingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.flab.funding.data.FundingTestData.*;
import static com.flab.funding.data.MemberTestData.getRealMember;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith({MockitoExtension.class})
public class FundingServiceTest {

    @InjectMocks
    private FundingService fundingService;

    @Mock
    private FundingPort fundingPort;

    @Mock
    private MemberPort memberPort;

    @Test
    @DisplayName("펀딩 등록")
    public void registerFunding() {
        //given
        Funding funding = getFunding();

        given(memberPort.getMemberByUserKey(eq(funding.getMember().getUserKey())))
                .willReturn(getRealMember());

        given(fundingPort.saveFunding(any(Funding.class)))
                .willAnswer(invocation -> invocation.getArguments()[0]);

        //when
        Funding savedFunding = fundingService.registerFunding(funding);

        //then
        assertNotNull(savedFunding.getFundingKey());
        assertNotNull(savedFunding.getMember().getId());
        assertEquals(FundingStatus.REGISTER, savedFunding.getStatus());

    }

    @Test
    @DisplayName("펀딩 창장자 등록")
    public void registerFundingCreator() {
        //given
        FundingCreator fundingCreator = getFundingCreator();

        given(fundingPort.getFundingByFundingKey(any()))
                .willReturn(getRealFunding());

        given(fundingPort.saveFundingCreator(any(FundingCreator.class)))
                .willAnswer(invocation -> invocation.getArguments()[0]);

        //when
        FundingCreator savedFundingCreator = fundingService.registerFundingCreator(fundingCreator);

        //then
        assertNotNull(savedFundingCreator.getFunding().getId());
        assertTrue(savedFundingCreator.getIsValid());
        assertEquals(getFundingCreator().getBusinessNumber(), savedFundingCreator.getBusinessNumber());
        assertEquals(getFundingCreator().getRepresentative(), savedFundingCreator.getRepresentative());
        assertEquals(getFundingCreator().getIntroduce(), savedFundingCreator.getIntroduce());
    }

    @Test
    @DisplayName("펀딩 아이템 생성")
    public void makeFundingItem() {
        //given
        FundingItem fundingItem = getFundingItem();

        given(fundingPort.getFundingByFundingKey(any()))
                .willReturn(getRealFunding());

        given(fundingPort.saveFundingItem(any(FundingItem.class)))
                .willAnswer(invocation -> invocation.getArguments()[0]);

        //when
        FundingItem savedFundingItem = fundingService.makeFundingItem(fundingItem);

        //then
        assertNotNull(savedFundingItem.getFunding().getId());
        assertEquals(getFundingItem().getItemName(), savedFundingItem.getItemName());
        assertEquals(getFundingItem().getOptionType(), savedFundingItem.getOptionType());
        assertEquals(getFundingItem().getFundingItemOptions().size(), savedFundingItem.getFundingItemOptions().size());
    }

    @Test
    @DisplayName("펀딩 리워드 생성")
    public void makeFundingReward() {
        //given
        FundingReward fundingReward = getFundingReward();

        given(fundingPort.getFundingByFundingKey(any()))
                .willReturn(getRealFunding());

        given(fundingPort.saveFundingReward(any(FundingReward.class)))
                .willAnswer(invocation -> invocation.getArguments()[0]);

        given(fundingPort.saveFundingRewardItems(any()))
                .willAnswer(invocation -> invocation.getArguments()[0]);

        //when
        FundingReward savedFundingReward = fundingService.makeFundingReward(fundingReward);

        //then
        assertNotNull(savedFundingReward.getFunding().getId());
        assertTrue(savedFundingReward.getIsDelivery());
        assertEquals(getFundingReward().getFundingRewardItems().size(), savedFundingReward.getFundingRewardItems().size());
    }
    
    @Test
    @DisplayName("펀딩 심사대기")
    public void waitForReview() {
        //given
        Funding funding = getFunding().register();

        given(fundingPort.getFundingByFundingKey(any()))
                .willReturn(funding);

        given(fundingPort.saveFunding(any(Funding.class)))
                .willAnswer(invocation -> invocation.getArguments()[0]);
        
        //when
        Funding savedFunding = fundingService.waitForFundingReview(funding.getFundingKey());

        //then
        assertEquals(FundingStatus.REVIEW_WAIT, savedFunding.getStatus());
    }
    
    @Test
    @DisplayName("펀딩 심사완료")
    public void completeReview() {
        //given
        Funding funding = getFunding().register();

        given(fundingPort.getFundingByFundingKey(any()))
                .willReturn(funding);

        given(fundingPort.saveFunding(any(Funding.class)))
                .willAnswer(invocation -> invocation.getArguments()[0]);

        //when
        Funding savedFunding = fundingService.completeFundingReview(funding.getFundingKey());

        //then
        assertEquals(FundingStatus.OPEN_WAIT, savedFunding.getStatus());
    }
    
    @Test
    @DisplayName("펀딩 취소")
    public void cancel() {
        //given
        Funding funding = getFunding().register();

        given(fundingPort.getFundingByFundingKey(any()))
                .willReturn(funding);

        given(fundingPort.saveFunding(any(Funding.class)))
                .willAnswer(invocation -> invocation.getArguments()[0]);

        //when
        Funding savedFunding = fundingService.cancelFunding(funding.getFundingKey());

        //then
        assertEquals(FundingStatus.CANCEL, savedFunding.getStatus());
    }
}