package com.flab.funding.service;

import com.flab.funding.application.ports.output.MemberDeliveryAddressPort;
import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.model.DeliveryAddress;
import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.MemberGender;
import com.flab.funding.domain.model.MemberLinkType;
import com.flab.funding.domain.service.MemberDeliveryAddressService;
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
        DeliveryAddress deliveryAddress = getDeliveryAddress();

        given(memberPort.getMemberByUserKey(eq(deliveryAddress.getMember().getUserKey())))
                .willReturn(getMember());

        given(memberDeliveryAddressPort.saveDeliveryAddress(any(DeliveryAddress.class)))
                .willReturn(deliveryAddress);

        given(memberDeliveryAddressPort.getDeliveryAddressByDeliveryAddressKey(any()))
                .willReturn(deliveryAddress);

        //when
        DeliveryAddress savedDeliveryAddress =
                memberDeliveryAddressService.registerDeliveryAddress(deliveryAddress);

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
                .member(getMember())
                .isDefault(true)
                .zipCode("01234")
                .address("서울특별시 강서구")
                .addressDetail("OO 아파트 xxx동 xxxx호")
                .recipientName("홍길동")
                .recipientPhone("010-1111-2222")
                .build();
    }

    private Member getMember() {
        return Member.builder()
                .userKey("MM-0001")
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
}