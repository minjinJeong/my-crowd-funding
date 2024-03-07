package com.flab.funding.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.funding.infrastructure.adapters.input.data.request.SupportDeliveryRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.SupportPaymentRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.SupportRegisterRequest;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class SupportRestAdapterTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    void registerSupport() throws Exception {

        // given
        SupportDeliveryRequest deliveryRequest = SupportDeliveryRequest.builder()
                .memberDeliveryAddressId(1L)
                .build();

        SupportPaymentRequest paymentRequest = SupportPaymentRequest.builder()
                .memberPaymentMethodId(1L)
                .build();

        SupportRegisterRequest request = SupportRegisterRequest.builder()
                .userKey("1")
                .fundingKey("1")
                .rewardId(1L)
                .supportDelivery(deliveryRequest)
                .supportPayment(paymentRequest)
                .build();

        // when
        // then
        this.mockMvc.perform(post("/supports")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        requestFields(
                                fieldWithPath("userKey").description("회원번호(외부용)"),
                                fieldWithPath("fundingKey").description("펀딩번호(외부용)"),
                                fieldWithPath("rewardId").description("후원한 리워드 번호"),
                                fieldWithPath("supportDelivery").description("배송지"),
                                fieldWithPath("supportDelivery.memberDeliveryAddressId").description("배송지 ID"),
                                fieldWithPath("supportPayment").description("결제수단"),
                                fieldWithPath("supportPayment.memberPaymentMethodId").description("결제수단 ID")
                        ),
                        responseFields(
                                fieldWithPath("supportKey").description("후원번호(외부용)"),
                                fieldWithPath("status").description("후원상태")
                        )));
    }

    @Test
    public void shippedOut() throws Exception {
        //given
        String supportKey = "1";

        //when

        //then
        this.mockMvc.perform(patch("/supports/{supportKey}/shipped-out"
                , supportKey))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        pathParameters(
                                parameterWithName("supportKey").description("후원번호(외부용)")
                        ),
                        responseFields(
                                fieldWithPath("supportKey").description("후원번호(외부용)"),
                                fieldWithPath("status").description("배송 상태")
                        )));
    }

    @Test
    public void inDelivery() throws Exception {
        //given
        String supportKey = "1";

        //when

        //then
        this.mockMvc.perform(patch("/supports/{supportKey}/in-delivery"
                        , supportKey))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        pathParameters(
                                parameterWithName("supportKey").description("후원번호(외부용)")
                        ),
                        responseFields(
                                fieldWithPath("supportKey").description("후원번호(외부용)"),
                                fieldWithPath("status").description("배송 상태")
                        )));

    }

    @Test
    public void complete() throws Exception {
        //given
        String supportKey = "1";

        //when

        //then
        this.mockMvc.perform(patch("/supports/{supportKey}/complete"
                        , supportKey))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        pathParameters(
                                parameterWithName("supportKey").description("후원번호(외부용)")
                        ),
                        responseFields(
                                fieldWithPath("supportKey").description("후원번호(외부용)"),
                                fieldWithPath("status").description("배송 상태")
                        )));

    }
}
