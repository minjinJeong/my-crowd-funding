package com.flab.funding.service;

import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.MemberGender;
import com.flab.funding.domain.model.MemberLinkType;
import com.flab.funding.domain.model.PaymentMethod;
import com.flab.funding.domain.service.MemberPaymentMethodService;
import com.flab.funding.domain.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MemberPaymentMethodServiceTest {

    @Autowired
    private MemberPaymentMethodService memberPaymentMethodService;

    @Autowired
    private MemberService memberService;

    private String userKey;

    @BeforeEach
    void setUp() {
        Member savedMember = memberService.registMember(getMember());
        userKey = savedMember.getUserKey();
    }

    private Member getMember() {
        return Member.builder()
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

    @Test
    public void 결제수단_등록() throws Exception {
        //given
        PaymentMethod paymentMethod = getPaymentMethod();

        //when
        PaymentMethod savedPaymentMethod = memberPaymentMethodService.registPaymentMethod(paymentMethod);
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
                .member(getSavedMember())
                .isDefault(true)
                .paymentNumber("3565 43")
                .build();
    }

    private Member getSavedMember() {
        return Member.builder().userKey(userKey).build();
    }
}