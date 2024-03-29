package com.flab.funding.service;

import com.flab.funding.application.ports.output.MemberPaymentMethodPort;
import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.MemberGender;
import com.flab.funding.domain.model.MemberLinkType;
import com.flab.funding.domain.model.PaymentMethod;
import com.flab.funding.domain.service.MemberPaymentMethodService;
import jdk.jfr.Name;
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
    @Name("결제수단 등록")
    public void registerPaymentMethod() {
        //given
        PaymentMethod paymentMethod = getPaymentMethod();

        given(memberPort.getMemberByUserKey(eq(paymentMethod.getMember().getUserKey())))
                .willReturn(getMember());

        given(memberPaymentMethodPort.savePaymentMethod(any(PaymentMethod.class)))
                .willReturn(paymentMethod);

        given(memberPaymentMethodPort.getPaymentMethodByPaymentMethodKey(any()))
                .willReturn(paymentMethod);

        //when
        PaymentMethod savedPaymentMethod = memberPaymentMethodService.registerPaymentMethod(paymentMethod);
        PaymentMethod findPaymentMethod =
                memberPaymentMethodService.getPaymentMethodByPaymentMethodKey(savedPaymentMethod.getPaymentMethodKey());

        //then
        assertEquals(savedPaymentMethod.getId(), findPaymentMethod.getId());
        assertEquals(savedPaymentMethod.getPaymentMethodKey(), findPaymentMethod.getPaymentMethodKey());
        assertEquals(savedPaymentMethod.getMember().getId(), findPaymentMethod.getMember().getId());
        assertEquals(savedPaymentMethod.getIsDefault(), findPaymentMethod.getIsDefault());
        assertEquals(savedPaymentMethod.getPaymentNumber(), findPaymentMethod.getPaymentNumber());
    }

    private PaymentMethod getPaymentMethod() {
        return PaymentMethod.builder()
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