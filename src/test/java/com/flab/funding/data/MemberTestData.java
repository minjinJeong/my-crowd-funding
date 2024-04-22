package com.flab.funding.data;

import com.flab.funding.domain.model.*;

import java.time.LocalDate;

public class MemberTestData {

    public static Member getMember() {
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

    static Member getMemberRequest() {
        return Member.builder()
                .userKey("MM-0001")
                .build();
    }

    public static Member getRealMember() {
        return Member.builder()
                .id(1L)
                .userKey("MM-0001")
                .linkType(MemberLinkType.NONE)
                .email("Test@gmail.com")
                .userName("홍길순")
                .nickName("테스터")
                .phoneNumber("010-1111-2222")
                .gender(MemberGender.FEMALE)
                .birthday(LocalDate.of(1998, 1, 30))
                .password("")
                .status(MemberStatus.ACTIVATE)
                .build();
    }

    public static MemberPaymentMethod getPaymentMethod() {
        return MemberPaymentMethod.builder()
                .member(getMemberRequest())
                .isDefault(true)
                .paymentNumber("3565 43")
                .build();
    }

    static MemberPaymentMethod getPaymentMethodRequest() {
        return MemberPaymentMethod.builder()
                .paymentMethodKey("PM-0001")
                .build();
    }

    public static MemberPaymentMethod getRealPaymentMethod() {
        return MemberPaymentMethod.builder()
                .id(1L)
                .paymentMethodKey("PM-0001")
                .member(getMemberRequest())
                .isDefault(true)
                .paymentNumber("3565 43")
                .build();
    }

    public static MemberDeliveryAddress getDeliveryAddress() {

        return MemberDeliveryAddress.builder()
                .member(getMemberRequest())
                .isDefault(true)
                .zipCode("01234")
                .address("서울특별시 강서구")
                .addressDetail("OO 아파트 xxx동 xxxx호")
                .recipientName("홍길동")
                .recipientPhone("010-1111-2222")
                .build();
    }

    static MemberDeliveryAddress getDeliveryAddressRequest() {
        return MemberDeliveryAddress.builder()
                .deliveryAddressKey("DA-0001")
                .build();
    }

    public static MemberDeliveryAddress getRealDeliveryAddress() {

        return MemberDeliveryAddress.builder()
                .id(1L)
                .deliveryAddressKey("DA-0001")
                .member(getMemberRequest())
                .isDefault(true)
                .zipCode("01234")
                .address("서울특별시 강서구")
                .addressDetail("OO 아파트 xxx동 xxxx호")
                .recipientName("홍길동")
                .recipientPhone("010-1111-2222")
                .build();
    }
}
