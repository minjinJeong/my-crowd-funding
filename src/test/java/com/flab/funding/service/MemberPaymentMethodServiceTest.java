package com.flab.funding.service;

import com.flab.funding.application.ports.output.MemberPaymentMethodPort;
import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.MemberGender;
import com.flab.funding.domain.model.MemberLinkType;
import com.flab.funding.domain.model.MemberPaymentMethod;
import com.flab.funding.domain.service.MemberPaymentMethodService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
                .willReturn(getMember());

        given(memberPaymentMethodPort.savePaymentMethod(any(MemberPaymentMethod.class)))
                .willReturn(memberPaymentMethod);

        given(memberPaymentMethodPort.getPaymentMethodByPaymentMethodKey(any()))
                .willReturn(memberPaymentMethod);

        //when
        MemberPaymentMethod savedMemberPaymentMethod = memberPaymentMethodService.registerPaymentMethod(memberPaymentMethod);
        MemberPaymentMethod findMemberPaymentMethod =
                memberPaymentMethodService.getPaymentMethodByPaymentMethodKey(savedMemberPaymentMethod.getPaymentMethodKey());

        //then
        assertEquals(savedMemberPaymentMethod.getId(), findMemberPaymentMethod.getId());
        assertEquals(savedMemberPaymentMethod.getPaymentMethodKey(), findMemberPaymentMethod.getPaymentMethodKey());
        assertEquals(savedMemberPaymentMethod.getPaymentNumber(), findMemberPaymentMethod.getPaymentNumber());
    }

    private MemberPaymentMethod getPaymentMethod() {
        return MemberPaymentMethod.builder()
                .member(getMember())
                .isDefault(true)
                .paymentNumber("3565 43")
                .build();
    }

    private Member getMember() {
        return Member.builder()
                .userKey("MM-0001")
                .linkType(MemberLinkType.NONE)
                .email("Test@gmail.com")
                .userName("홍길순")
                .nickName("테스터")
                .phoneNumber("010-1111-2222")
                .gender(MemberGender.FEMALE)
                .birthday(LocalDate.of(1998, 1, 30))
                .password("")
                .build();
    }
}