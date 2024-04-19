package com.flab.funding.repository;

import com.flab.funding.application.ports.output.MemberPaymentMethodPort;
import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.MemberPaymentMethod;
import com.flab.funding.infrastructure.adapters.output.persistence.MemberPaymentMethodPersistenceAdapter;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.MemberPaymentMethodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.flab.funding.data.MemberTestData.getMember;
import static com.flab.funding.data.MemberTestData.getPaymentMethod;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MemberPaymentMethodPersistenceAdapterTest {

    private final MemberPaymentMethodPort memberPaymentMethodPort;

    private Member member;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    public MemberPaymentMethodPersistenceAdapterTest(MemberPaymentMethodRepository memberPaymentMethodRepository) {
        this.memberPaymentMethodPort = new MemberPaymentMethodPersistenceAdapter(memberPaymentMethodRepository);
    }

    @BeforeEach
    public void setUp() {

        Member savedMember = getMember();

        MemberEntity memberEntity = entityManager.persist(MemberEntity.from(savedMember.activate()));
        member = memberEntity.toMember();
    }

    @Test
    @DisplayName("결제수단 등록")
    public void savePaymentMethod() {
        //given
        MemberPaymentMethod memberPaymentMethod = getPaymentMethod().with(member).register();

        //when
        MemberPaymentMethod savedMemberPaymentMethod =
                memberPaymentMethodPort.savePaymentMethod(memberPaymentMethod);

        //then
        assertNotNull(savedMemberPaymentMethod.getId());
        assertNotNull(savedMemberPaymentMethod.getPaymentMethodKey());
        assertTrue(savedMemberPaymentMethod.getIsDefault());
        assertEquals("3565 43", savedMemberPaymentMethod.getPaymentNumber());

    }

    @Test
    @DisplayName("결제수단 조회")
    public void getPaymentMethodByPaymentMethodKey() {
        //given
        MemberPaymentMethod memberPaymentMethod = getPaymentMethod().with(member).register();

        MemberPaymentMethod savedMemberPaymentMethod =
                memberPaymentMethodPort.savePaymentMethod(memberPaymentMethod);

        //when
        MemberPaymentMethod findMemberPaymentMethod =
                memberPaymentMethodPort.getPaymentMethodByPaymentMethodKey(
                        savedMemberPaymentMethod.getPaymentMethodKey()
                );

        //then
        assertEquals(savedMemberPaymentMethod.getPaymentMethodKey(), findMemberPaymentMethod.getPaymentMethodKey());
        assertEquals(savedMemberPaymentMethod.getPaymentNumber(), findMemberPaymentMethod.getPaymentNumber());

    }
}