package com.flab.funding.service;

import com.flab.funding.application.ports.output.MemberDeliveryAddressPort;
import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.model.MemberDeliveryAddress;
import com.flab.funding.domain.service.MemberDeliveryAddressService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.flab.funding.data.MemberTestData.getDeliveryAddress;
import static com.flab.funding.data.MemberTestData.getRealMember;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith({MockitoExtension.class})
public class MemberDeliveryAddressServiceTest {

    @InjectMocks
    private MemberDeliveryAddressService memberDeliveryAddressService;

    @Mock
    private MemberDeliveryAddressPort memberDeliveryAddressPort;

    @Mock
    private MemberPort memberPort;

    @Test
    @DisplayName("배송지 등록")
    public void registerDeliveryAddress() {
        //given
        MemberDeliveryAddress memberDeliveryAddress = getDeliveryAddress();

        given(memberPort.getMemberByUserKey(eq(memberDeliveryAddress.getMember().getUserKey())))
                .willReturn(getRealMember());

        given(memberDeliveryAddressPort.saveDeliveryAddress(any(MemberDeliveryAddress.class)))
                .willAnswer(invocation -> invocation.getArguments()[0]);

        //when
        MemberDeliveryAddress savedMemberDeliveryAddress =
                memberDeliveryAddressService.registerDeliveryAddress(memberDeliveryAddress);


        //then
        assertNotNull(savedMemberDeliveryAddress.getDeliveryAddressKey());
        assertNotNull(savedMemberDeliveryAddress.getMember().getId());
        assertTrue(savedMemberDeliveryAddress.getIsDefault());
        assertEquals(getDeliveryAddress().getZipCode(), savedMemberDeliveryAddress.getZipCode());
        assertEquals(getDeliveryAddress().getAddress(), savedMemberDeliveryAddress.getAddress());
        assertEquals(getDeliveryAddress().getAddressDetail(), savedMemberDeliveryAddress.getAddressDetail());
        assertEquals(getDeliveryAddress().getRecipientName(), savedMemberDeliveryAddress.getRecipientName());
        assertEquals(getDeliveryAddress().getRecipientPhone(), savedMemberDeliveryAddress.getRecipientPhone());
    }
}