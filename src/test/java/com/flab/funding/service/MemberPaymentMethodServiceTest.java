package com.flab.funding.service;

import com.flab.funding.application.ports.output.MemberPaymentMethodPort;
import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.model.MemberPaymentMethod;
import com.flab.funding.domain.service.MemberPaymentMethodService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.flab.funding.data.MemberTestData.getPaymentMethod;
import static com.flab.funding.data.MemberTestData.getRealMember;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith({MockitoExtension.class})
public class MemberPaymentMethodServiceTest {

    @InjectMocks
    private MemberPaymentMethodService memberPaymentMethodService;

    @Mock
    private MemberPaymentMethodPort memberPaymentMethodPort;

    @Mock
    private MemberPort memberPort;

    @Test
    @DisplayName("결제수단 등록")
    public void registerPaymentMethod() {
        //given
        MemberPaymentMethod memberPaymentMethod = getPaymentMethod();

        given(memberPort.getMemberByUserKey(eq(memberPaymentMethod.getMember().getUserKey())))
                .willReturn(getRealMember());

        given(memberPaymentMethodPort.savePaymentMethod(any(MemberPaymentMethod.class)))
                .willAnswer(invocation -> invocation.getArguments()[0]);

        //when
        MemberPaymentMethod savedMemberPaymentMethod = memberPaymentMethodService.registerPaymentMethod(memberPaymentMethod);

        //then
        assertNotNull(savedMemberPaymentMethod.getPaymentMethodKey());
        assertNotNull(savedMemberPaymentMethod.getMember().getId());
        assertTrue(savedMemberPaymentMethod.getIsDefault());
        assertEquals(getPaymentMethod().getPaymentNumber(), savedMemberPaymentMethod.getPaymentNumber());
    }
}