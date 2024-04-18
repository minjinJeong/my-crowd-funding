package com.flab.funding.repository;

import com.flab.funding.application.ports.output.MemberDeliveryAddressPort;
import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.MemberDeliveryAddress;
import com.flab.funding.infrastructure.adapters.output.persistence.MemberDeliveryAddressPersistenceAdapter;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.MemberDeliveryAddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.flab.funding.data.MemberTestData.getDeliveryAddress;
import static com.flab.funding.data.MemberTestData.getMember;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MemberDeliveryAddressPersistenceAdapterTest {

    private final MemberDeliveryAddressPort memberDeliveryAddressPort;

    private Member member;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    public MemberDeliveryAddressPersistenceAdapterTest(MemberDeliveryAddressRepository memberDeliveryAddressRepository) {

        this.memberDeliveryAddressPort =
                new MemberDeliveryAddressPersistenceAdapter(memberDeliveryAddressRepository);
    }

    @BeforeEach
    public void setUp() {

        Member savedMember = getMember();

        MemberEntity memberEntity = entityManager.persist(MemberEntity.from(savedMember.activate()));
        member = memberEntity.toMember();
    }

    @Test
    @DisplayName("배송지 주소 등록")
    public void saveDeliveryAddress() {
        //given
        MemberDeliveryAddress memberDeliveryAddress = getDeliveryAddress().with(member).register();

        //when
        MemberDeliveryAddress savedMemberDeliveryAddress =
                memberDeliveryAddressPort.saveDeliveryAddress(memberDeliveryAddress);

        //then
        assertNotNull(savedMemberDeliveryAddress.getId());
        assertNotNull(savedMemberDeliveryAddress.getDeliveryAddressKey());
        assertTrue(savedMemberDeliveryAddress.getIsDefault());
        assertEquals("01234", savedMemberDeliveryAddress.getZipCode());
        assertEquals("서울특별시 강서구", savedMemberDeliveryAddress.getAddress());
        assertEquals("OO 아파트 xxx동 xxxx호", savedMemberDeliveryAddress.getAddressDetail());
        assertEquals("홍길동", savedMemberDeliveryAddress.getRecipientName());
        assertEquals("010-1111-2222", savedMemberDeliveryAddress.getRecipientPhone());
    }

    @Test
    @DisplayName("배송지 주소 조회")
    public void getDeliveryAddressByDeliveryAddressKey() {
        //given
        MemberDeliveryAddress memberDeliveryAddress = getDeliveryAddress().with(member).register();

        MemberDeliveryAddress savedMemberDeliveryAddress =
                memberDeliveryAddressPort.saveDeliveryAddress(memberDeliveryAddress);

        //when
        MemberDeliveryAddress findMemberDeliveryAddress =
                memberDeliveryAddressPort.getDeliveryAddressByDeliveryAddressKey(
                        savedMemberDeliveryAddress.getDeliveryAddressKey()
                );

        //then
        assertEquals(savedMemberDeliveryAddress.getDeliveryAddressKey(), findMemberDeliveryAddress.getDeliveryAddressKey());
        assertEquals(savedMemberDeliveryAddress.getZipCode(), findMemberDeliveryAddress.getZipCode());
    }
}