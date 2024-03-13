package com.flab.funding.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberPaymentMethodRegisterRequest;
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
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class MemberPaymentMethodRestAdapterTest {

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
    public void registerPaymentMethod() throws Exception {
        // given
        MemberPaymentMethodRegisterRequest request = MemberPaymentMethodRegisterRequest.builder()
                .userKey("1L")
                .isDefault(true)
                .paymentNumber("3565 43")
                .build();

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