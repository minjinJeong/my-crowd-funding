package com.flab.funding.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.funding.domain.model.MemberGender;
import com.flab.funding.domain.model.MemberLinkType;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberDeliveryAddressRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberRegisterResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class MemberDeliveryAddressRestAdapterTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String userKey;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();

        registerMember();
    }

    private void registerMember() throws Exception {
        // given
        MemberRegisterRequest request = MemberRegisterRequest.builder()
                .linkType(MemberLinkType.NONE)
                .email("Test@gmail.com")
                .userName("홍길순")
                .nickName("테스터")
                .phoneNumber("010-1111-2222")
                .gender(MemberGender.FEMALE)
                .birthday(LocalDate.of(1998,1,30))
                .password("")
                .build();

        // when
        // then
        ResultActions resultActions = this.mockMvc.perform(post("/members")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        MemberRegisterResponse response = objectMapper.readValue(
                resultActions.andReturn().getResponse().getContentAsString(),
                MemberRegisterResponse.class);

        userKey = response.getUserKey();
    }

    @Test
    void registerDeliveryAddress() throws Exception {
        // given
        MemberDeliveryAddressRegisterRequest request = MemberDeliveryAddressRegisterRequest.builder()
                .userKey(userKey)
                .isDefault(true)
                .zipCode("01234")
                .address("서울특별시 강서구")
                .addressDetail("OO 아파트 xxx동 xxxx호")
                .recipientName("홍길동")
                .recipientPhone("010-1111-2222")
                .build();

        System.out.println("objectMapper.writeValueAsString(request) = " + objectMapper.writeValueAsString(request));

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