package com.flab.funding.service;

import com.flab.funding.application.ports.output.MemberDeliveryAddressPort;
import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.MemberDeliveryAddress;
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
        MemberDeliveryAddress memberDeliveryAddress = getDeliveryAddress();

        given(memberPort.getMemberByUserKey(eq(memberDeliveryAddress.getMember().getUserKey())))
                .willReturn(getMember());

        given(memberDeliveryAddressPort.saveDeliveryAddress(any(MemberDeliveryAddress.class)))
                .willReturn(memberDeliveryAddress);

        given(memberDeliveryAddressPort.getDeliveryAddressByDeliveryAddressKey(any()))
                .willReturn(memberDeliveryAddress);

        //when
        MemberDeliveryAddress savedMemberDeliveryAddress =
                memberDeliveryAddressService.registerDeliveryAddress(memberDeliveryAddress);

        MemberDeliveryAddress findMemberDeliveryAddress =
                memberDeliveryAddressService.getDeliveryAddressByDeliveryAddressKey(savedMemberDeliveryAddress.getDeliveryAddressKey());


        //then
        assertEquals(savedMemberDeliveryAddress.getId(), findMemberDeliveryAddress.getId());
        assertEquals(savedMemberDeliveryAddress.getDeliveryAddressKey(), findMemberDeliveryAddress.getDeliveryAddressKey());
        assertEquals(savedMemberDeliveryAddress.getZipCode(), findMemberDeliveryAddress.getZipCode());
    }

    private MemberDeliveryAddress getDeliveryAddress() {

        return MemberDeliveryAddress.builder()
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