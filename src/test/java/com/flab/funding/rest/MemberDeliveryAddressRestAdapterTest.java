package com.flab.funding.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.funding.domain.model.DeliveryAddress;
import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.service.MemberDeliveryAddressService;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberDeliveryAddressRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.rest.MemberDeliveryAddressRestAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(MemberDeliveryAddressRestAdapter.class)
@AutoConfigureRestDocs
public class MemberDeliveryAddressRestAdapterTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberDeliveryAddressService memberDeliveryAddressService;

    @Test
    @DisplayName("배송지 등록")
    void registerDeliveryAddress() throws Exception {

        // given
        MemberDeliveryAddressRegisterRequest request = MemberDeliveryAddressRegisterRequest.builder()
                .userKey("MM-0001")
                .isDefault(true)
                .zipCode("01234")
                .address("서울특별시 강서구")
                .addressDetail("OO 아파트 xxx동 xxxx호")
                .recipientName("홍길동")
                .recipientPhone("010-1111-2222")
                .build();

        DeliveryAddress response = DeliveryAddress.builder()
                .deliveryAddressKey("DA-0001")
                .member(Member.builder().userKey("MM-0001").build())
                .isDefault(true)
                .zipCode("01234")
                .address("서울특별시 강서구")
                .addressDetail("OO 아파트 xxx동 xxxx호")
                .recipientName("홍길동")
                .recipientPhone("010-1111-2222")
                .createdAt(LocalDateTime.of(2024, 3, 15, 10, 9))
                .updatedAt(LocalDateTime.of(2024, 3, 15, 10, 9))
                .build();

        given(memberDeliveryAddressService.registerDeliveryAddress(any(request.toDeliveryAddress().getClass()))).willReturn(response);

        //when
        //then
        this.mockMvc.perform(post("/deliveryAddresses")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        requestFields(
                                fieldWithPath("userKey").description("회원번호(외부용)"),
                                fieldWithPath("isDefault").description("대표 배송지 주소"),
                                fieldWithPath("zipCode").description("우편번호"),
                                fieldWithPath("address").description("도로명 주소"),
                                fieldWithPath("addressDetail").description("상세 주소"),
                                fieldWithPath("recipientName").description("받는 사람 이름"),
                                fieldWithPath("recipientPhone").description("받는 사람 연락처")
                        ),
                        responseFields(
                                fieldWithPath("deliveryAddressKey").description("배송지 주소 ID(외부용)"),
                                fieldWithPath("userKey").description("회원번호(외부용)"),
                                fieldWithPath("isDefault").description("대표 배송지 주소"),
                                fieldWithPath("zipCode").description("우편번호"),
                                fieldWithPath("address").description("도로명 주소"),
                                fieldWithPath("addressDetail").description("상세 주소"),
                                fieldWithPath("recipientName").description("받는 사람 이름"),
                                fieldWithPath("recipientPhone").description("받는 사람 연락처"),
                                fieldWithPath("createdAt").description("등록일자"),
                                fieldWithPath("updatedAt").description("수정일자")
                        )));
    }

}