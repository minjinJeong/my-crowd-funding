package com.flab.funding.service;

import com.flab.funding.application.ports.output.*;
import com.flab.funding.domain.exception.EmptyFundingException;
import com.flab.funding.domain.exception.EmptyMemberException;
import com.flab.funding.domain.model.*;
import com.flab.funding.domain.service.SupportService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.flab.funding.data.FundingTestData.getRealFunding;
import static com.flab.funding.data.MemberTestData.*;
import static com.flab.funding.data.SupportTestData.getSupport;
import static com.flab.funding.data.SupportTestData.getSupportDelivery;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith({MockitoExtension.class})
public class SupportServiceTest {

    @InjectMocks
    private SupportService supportService;

    @Mock
    private SupportPort supportPort;

    @Mock
    private MemberPort memberPort;

    @Mock
    private FundingPort fundingPort;

    @Mock
    private MemberDeliveryAddressPort memberDeliveryAddressPort;

    @Mock
    private MemberPaymentMethodPort memberPaymentMethodPort;
    
    @Test
    @DisplayName("후원 등록")
    public void registerSupport() {
        //given
        Support support = getSupport();

        given(memberPort.getMemberByUserKey(any()))
                .willReturn(getRealMember());

        given(fundingPort.getFundingByFundingKey(any()))
                .willReturn(getRealFunding());

        given(supportPort.saveSupport(any(Support.class)))
                .willAnswer(invocation -> invocation.getArguments()[0]);

        given(memberDeliveryAddressPort.getDeliveryAddressByDeliveryAddressKey(any()))
                .willReturn(getRealDeliveryAddress());

        given(memberPaymentMethodPort.getPaymentMethodByPaymentMethodKey(any()))
                .willReturn(getRealPaymentMethod());

        //when
        Support savedSupport = supportService.registerSupport(support);

        //then
        assertNotNull(savedSupport.getSupportKey());
        assertEquals(SupportStatus.RESERVATION, savedSupport.getStatus());
        assertNotNull(savedSupport.getMember().getId());
        assertNotNull(savedSupport.getFunding().getId());
        assertNotNull(savedSupport.getSupportDelivery().getMemberDeliveryAddress().getId());
        assertNotNull(savedSupport.getSupportPayment().getMemberPaymentMethod().getId());
    }

    @Test
    @DisplayName("후원 등록 시 잘못된 회원 예외")
    public void registerSupportMemberError() {
        //given
        Support support = getSupport();

        given(memberPort.getMemberByUserKey(any()))
                .willReturn(Member.builder().build());

        //when
        //then
        assertThrows(EmptyMemberException.class, () -> supportService.registerSupport(support));

    }

    @Test
    @DisplayName("후원 등록 시 잘못된 펀딩 예외")
    public void registerSupportFundingError() {
        //given
        Support support = getSupport();

        given(memberPort.getMemberByUserKey(any()))
                .willReturn(getRealMember());

        given(fundingPort.getFundingByFundingKey(any()))
                .willReturn(Funding.builder().build());

        //when
        //then
        assertThrows(EmptyFundingException.class, () -> supportService.registerSupport(support));

    }

    @Test
    @DisplayName("배송 시작")
    public void shippedOut() {
        //given
        Support support = getSupport();
        SupportDelivery supportDelivery = getSupportDelivery().with(support);

        given(supportPort.getSupportDeliveryBySupportKey(any()))
                .willReturn(supportDelivery);

        given(supportPort.saveSupportDelivery(any(SupportDelivery.class)))
                .willAnswer(invocation -> invocation.getArguments()[0]);
        
        //when
        SupportDelivery savedSupportDelivery = supportService.shippedOut(support.getSupportKey());

        //then
        assertEquals(SupportDeliveryStatus.SHIPPED, savedSupportDelivery.getStatus());
    }

    @Test
    @DisplayName("배송 중")
    public void outForDelivery() {
        //given
        Support support = getSupport();
        SupportDelivery supportDelivery = getSupportDelivery().with(support);;

        given(supportPort.getSupportDeliveryBySupportKey(any()))
                .willReturn(supportDelivery);

        given(supportPort.saveSupportDelivery(any(SupportDelivery.class)))
                .willAnswer(invocation -> invocation.getArguments()[0]);

        //when
        SupportDelivery savedSupportDelivery = supportService.outForDelivery(support.getSupportKey());

        //then
        assertEquals(SupportDeliveryStatus.IN_DELIVERY, savedSupportDelivery.getStatus());
    }
    
    @Test
    @DisplayName("배송 완료")
    public void deliveryComplete() {
        //given
        Support support = getSupport();
        SupportDelivery supportDelivery = getSupportDelivery().with(support);;

        given(supportPort.getSupportDeliveryBySupportKey(any()))
                .willReturn(supportDelivery);

        given(supportPort.saveSupportDelivery(any(SupportDelivery.class)))
                .willAnswer(invocation -> invocation.getArguments()[0]);

        //when
        SupportDelivery savedSupportDelivery = supportService.deliveryComplete(support.getSupportKey());

        //then
        assertEquals(SupportDeliveryStatus.COMPLETE, savedSupportDelivery.getStatus());
    }

}