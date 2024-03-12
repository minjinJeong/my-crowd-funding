package com.flab.funding.service;

import com.flab.funding.domain.model.DeliveryAddress;
import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.MemberGender;
import com.flab.funding.domain.model.MemberLinkType;
import com.flab.funding.domain.service.MemberDeliveryAddressService;
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
public class MemberDeliveryAddressServiceTest {

    @Autowired
    private MemberDeliveryAddressService memberDeliveryAddressService;

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
                .email("DeliveryAddress@gmail.com")
                .userName("홍길순")
                .nickName("테스터")
                .phoneNumber("010-1111-2222")
                .gender(MemberGender.FEMALE)
                .birthday(LocalDate.of(1998, 1, 30))
                .password("")
                .build();
    }

    @Test
    public void 배송지_등록() throws Exception {
        //given
        DeliveryAddress deliveryAddress = getDeliveryAddress();

        //when
        DeliveryAddress savedDeliveryAddress =
                memberDeliveryAddressService.registDeliveryAddress(deliveryAddress);

        DeliveryAddress findDeliveryAddress =
                memberDeliveryAddressService.getDeliveryAddressByDeliveryAddressKey(savedDeliveryAddress.getDeliveryAddressKey());


        //then
        assertEquals(savedDeliveryAddress.getId(), findDeliveryAddress.getId());
        assertEquals(savedDeliveryAddress.getDeliveryAddressKey(), findDeliveryAddress.getDeliveryAddressKey());
        assertEquals(savedDeliveryAddress.getMember().getId(), findDeliveryAddress.getMember().getId());
        assertEquals(savedDeliveryAddress.getIsDefault(), findDeliveryAddress.getIsDefault());
        assertEquals(savedDeliveryAddress.getZipCode(), findDeliveryAddress.getZipCode());
        assertEquals(savedDeliveryAddress.getAddress(), findDeliveryAddress.getAddress());
        assertEquals(savedDeliveryAddress.getAddressDetail(), findDeliveryAddress.getAddressDetail());
        assertEquals(savedDeliveryAddress.getRecipientName(), findDeliveryAddress.getRecipientName());
        assertEquals(savedDeliveryAddress.getRecipientPhone(), findDeliveryAddress.getRecipientPhone());
    }

    private DeliveryAddress getDeliveryAddress() {

        return DeliveryAddress.builder()
                .member(getSavedMember())
                .isDefault(true)
                .zipCode("01234")
                .address("서울특별시 강서구")
                .addressDetail("OO 아파트 xxx동 xxxx호")
                .recipientName("홍길동")
                .recipientPhone("010-1111-2222")
                .build();
    }

    private Member getSavedMember() {
        return Member.builder().userKey(userKey).build();
    }
}