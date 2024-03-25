package com.flab.funding.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.funding.domain.model.Support;
import com.flab.funding.domain.model.SupportDelivery;
import com.flab.funding.domain.model.SupportDeliveryStatus;
import com.flab.funding.domain.model.SupportStatus;
import com.flab.funding.domain.service.SupportService;
import com.flab.funding.infrastructure.adapters.input.data.request.SupportDeliveryRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.SupportPaymentRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.SupportRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.rest.SupportRestAdapter;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(SupportRestAdapter.class)
@AutoConfigureRestDocs
public class SupportRestAdapterTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SupportService supportService;

    @Test
    void registerSupport() throws Exception {

        // given
        SupportRegisterRequest request = SupportRegisterRequest.builder()
                .userKey("MM-0001")
                .fundingKey("FF-0001")
                .rewardId(1L)
                .supportDelivery(getDeliveryRequest())
                .supportPayment(getPaymentRequest())
                .build();

        Support response = Support.builder()
                .supportKey("SS-0001")
                .status(SupportStatus.RESERVATION)
                .build();

        given(supportService.registerSupport(any(request.toSupport().getClass())))
                .willReturn(response);

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
                                fieldWithPath("supportDelivery.memberDeliveryAddressKey").description("배송지 번호"),
                                fieldWithPath("supportPayment").description("결제수단"),
                                fieldWithPath("supportPayment.memberPaymentMethodKey").description("결제수단 번호")
                        ),
                        responseFields(
                                fieldWithPath("supportKey").description("후원번호(외부용)"),
                                fieldWithPath("status").description("후원상태")
                        )));
    }

    private SupportDeliveryRequest getDeliveryRequest() {
        return SupportDeliveryRequest.builder()
                .memberDeliveryAddressKey("DA-0001")
                .build();
    }

    private SupportPaymentRequest getPaymentRequest() {
        return SupportPaymentRequest.builder()
                .memberPaymentMethodKey("PM-0001")
                .build();
    }

    @Test
    public void shippedOut() throws Exception {
        //given
        String request = "SS-0001";

        SupportDelivery response = SupportDelivery.builder()
                .support(getSupportRequest())
                .status(SupportDeliveryStatus.SHIPPED)
                .build();

        given(supportService.shippedOut(eq(request))).willReturn(response);

        //when
        //then
        this.mockMvc.perform(patch("/supports/{supportKey}/shipped-out", request))
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

    private Support getSupportRequest() {
        return Support.builder()
                .supportKey("SS-0001")
                .build();
    }

    @Test
    public void inDelivery() throws Exception {
        //given
        String request = "SS-0001";

        SupportDelivery response = SupportDelivery.builder()
                .support(getSupportRequest())
                .status(SupportDeliveryStatus.IN_DELIVERY)
                .build();

        given(supportService.outForDelivery(eq(request))).willReturn(response);

        //when
        //then
        this.mockMvc.perform(patch("/supports/{supportKey}/in-delivery", request))
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
        String request = "SS-0001";

        SupportDelivery response = SupportDelivery.builder()
                .support(getSupportRequest())
                .status(SupportDeliveryStatus.COMPLETE)
                .build();

        given(supportService.deliveryComplete(eq(request))).willReturn(response);

        //when
        //then
        this.mockMvc.perform(patch("/supports/{supportKey}/complete", request))
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
