package com.flab.funding.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.PaymentMethod;
import com.flab.funding.domain.service.MemberPaymentMethodService;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberPaymentMethodRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.rest.MemberPaymentMethodRestAdapter;
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
@WebMvcTest(MemberPaymentMethodRestAdapter.class)
@AutoConfigureRestDocs
public class MemberPaymentMethodRestAdapterTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberPaymentMethodService memberPaymentMethodService;

    @Test
    public void registerPaymentMethod() throws Exception {
        // given
        MemberPaymentMethodRegisterRequest request = MemberPaymentMethodRegisterRequest.builder()
                .userKey("MM-0001")
                .isDefault(true)
                .paymentNumber("3565-43")
                .build();

        PaymentMethod response = PaymentMethod.builder()
                .paymentMethodKey("PM-0001")
                .member(Member.builder().userKey("MM-0001").build())
                .isDefault(true)
                .paymentNumber("3565-43")
                .createdAt(LocalDateTime.of(2024, 3, 15, 10, 9))
                .updatedAt(LocalDateTime.of(2024, 3, 15, 10, 9))
                .build();

        given(memberPaymentMethodService.registerPaymentMethod(any(request.toPaymentMethod().getClass()))).willReturn(response);

        //when

        //then
        this.mockMvc.perform(post("/paymentMethods")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        requestFields(
                                fieldWithPath("userKey").description("회원번호(외부용)"),
                                fieldWithPath("isDefault").description("대표 결제 수단"),
                                fieldWithPath("paymentNumber").description("카드 번호/계좌 번호")
                        ),
                        responseFields(
                                fieldWithPath("paymentMethodKey").description("결제 수단 ID(외부용)"),
                                fieldWithPath("userKey").description("회원번호(외부용)"),
                                fieldWithPath("isDefault").description("대표 결제 수단"),
                                fieldWithPath("paymentNumber").description("카드 번호/계좌 번호"),
                                fieldWithPath("createdAt").description("등록일자"),
                                fieldWithPath("updatedAt").description("수정일자")
                        )));
    }
}