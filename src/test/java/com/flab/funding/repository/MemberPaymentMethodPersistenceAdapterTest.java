package com.flab.funding.repository;

import com.flab.funding.application.ports.output.MemberPaymentMethodPort;
import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.MemberGender;
import com.flab.funding.domain.model.MemberLinkType;
import com.flab.funding.domain.model.PaymentMethod;
import com.flab.funding.infrastructure.adapters.output.persistence.MemberPaymentMethodPersistenceAdapter;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.MemberPaymentMethodRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MemberPaymentMethodPersistenceAdapterTest {

    private final MemberPaymentMethodPort memberPaymentMethodPort;

    @Autowired
    public MemberPaymentMethodPersistenceAdapterTest(MemberPaymentMethodRepository memberPaymentMethodRepository) {
        this.memberPaymentMethodPort = new MemberPaymentMethodPersistenceAdapter(memberPaymentMethodRepository);
    }

    @Test
    @DisplayName("결제수단 등록")
    public void savePaymentMethod() {
        //given
        PaymentMethod paymentMethod = getPaymentMethod().register(getMember());

        //when
        PaymentMethod savedPaymentMethod = memberPaymentMethodPort.savePaymentMethod(paymentMethod);

        //then
        assertNotNull(savedPaymentMethod.getId());
        assertNotNull(savedPaymentMethod.getPaymentMethodKey());
        assertTrue(savedPaymentMethod.getIsDefault());
        assertEquals("3565 43", savedPaymentMethod.getPaymentNumber());

    }

    private PaymentMethod getPaymentMethod() {
        return PaymentMethod.builder()
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

    @Test
    @DisplayName("결제수단 조회")
    public void getPaymentMethodByPaymentMethodKey() {
        //given
        PaymentMethod paymentMethod = getPaymentMethod().register(getMember());
        PaymentMethod savedPaymentMethod = memberPaymentMethodPort.savePaymentMethod(paymentMethod);

        //when
        PaymentMethod findPaymentMethod = memberPaymentMethodPort.getPaymentMethodByPaymentMethodKey(
                savedPaymentMethod.getPaymentMethodKey()
        );

        //then
        assertEquals(savedPaymentMethod.getPaymentMethodKey(), findPaymentMethod.getPaymentMethodKey());
        assertEquals(savedPaymentMethod.getPaymentNumber(), findPaymentMethod.getPaymentNumber());

    }
}