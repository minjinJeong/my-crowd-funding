package com.flab.funding.repository;

import com.flab.funding.application.ports.output.MemberDeliveryAddressPort;
import com.flab.funding.domain.model.DeliveryAddress;
import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.MemberGender;
import com.flab.funding.domain.model.MemberLinkType;
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

import java.time.LocalDate;

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
        this.memberDeliveryAddressPort = new MemberDeliveryAddressPersistenceAdapter(memberDeliveryAddressRepository);
    }

    @BeforeEach
    public void setUp() {

        Member savedMember = Member.builder()
                .linkType(MemberLinkType.NONE)
                .email("Test@gmail.com")
                .userName("홍길순")
                .nickName("테스터")
                .phoneNumber("010-1111-2222")
                .gender(MemberGender.FEMALE)
                .birthday(LocalDate.of(1998, 1, 30))
                .password("")
                .build();

        MemberEntity memberEntity = entityManager.persist(MemberEntity.from(savedMember.activate()));
        member = memberEntity.toMember();
    }

    @Test
    @DisplayName("배송지 주소 등록")
    public void saveDeliveryAddress() {
        //given
        DeliveryAddress deliveryAddress = getDeliveryAddress().member(member).register();

        //when
        DeliveryAddress savedDeliveryAddress = memberDeliveryAddressPort.saveDeliveryAddress(deliveryAddress);

        //then
        assertNotNull(savedDeliveryAddress.getId());
        assertNotNull(savedDeliveryAddress.getDeliveryAddressKey());
        assertTrue(savedDeliveryAddress.getIsDefault());
        assertEquals("01234", savedDeliveryAddress.getZipCode());
        assertEquals("서울특별시 강서구", savedDeliveryAddress.getAddress());
        assertEquals("OO 아파트 xxx동 xxxx호", savedDeliveryAddress.getAddressDetail());
        assertEquals("홍길동", savedDeliveryAddress.getRecipientName());
        assertEquals("010-1111-2222", savedDeliveryAddress.getRecipientPhone());
    }

    private DeliveryAddress getDeliveryAddress() {

        return DeliveryAddress.builder()
                .isDefault(true)
                .zipCode("01234")
                .address("서울특별시 강서구")
                .addressDetail("OO 아파트 xxx동 xxxx호")
                .recipientName("홍길동")
                .recipientPhone("010-1111-2222")
                .build();
    }

    @Test
    @DisplayName("배송지 주소 조회")
    public void getDeliveryAddressByDeliveryAddressKey() {
        //given
        DeliveryAddress deliveryAddress = getDeliveryAddress().member(member).register();
        DeliveryAddress savedDeliveryAddress = memberDeliveryAddressPort.saveDeliveryAddress(deliveryAddress);

        //when
        DeliveryAddress findDeliveryAddress = memberDeliveryAddressPort.getDeliveryAddressByDeliveryAddressKey(
                savedDeliveryAddress.getDeliveryAddressKey()
        );

        //then
        assertEquals(savedDeliveryAddress.getDeliveryAddressKey(), findDeliveryAddress.getDeliveryAddressKey());
        assertEquals(savedDeliveryAddress.getZipCode(), findDeliveryAddress.getZipCode());
    }
}