package com.flab.funding.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.funding.domain.model.FundingCategory;
import com.flab.funding.infrastructure.adapters.input.data.request.FundingRegisterRequest;
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

import java.math.BigInteger;
import java.time.LocalDateTime;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class FundingRestAdapterTest {

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
    void registerFunding() throws Exception {

        // given
        FundingRegisterRequest request = FundingRegisterRequest.builder()
                .userKey("1L")
                .adult(false)
                .pricePlan("00")
                .categoryCode(FundingCategory.FOOD)
                .expectAmount(BigInteger.valueOf(100000))
                .title("제목")
                .fundingDesc("펀딩 상세")
                .fundingIntroduce("펀딩 소개글")
                .budgetDesc("예산 계획")
                .scheduleDesc("펀딩 계획")
                .teamDesc("팀 소개")
                .rewardDesc("리워드 소개")
                .startAt(LocalDateTime.of(2024, 2, 1, 12, 0))
                .endAt(LocalDateTime.of(2024, 2, 28, 12, 0))
                .build();

        // when
        // then
        this.mockMvc.perform(post("/funding")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        requestFields(
                                fieldWithPath("userKey").description("회원번호(외부용)"),
                                fieldWithPath("adult").description("이메일"),
                                fieldWithPath("pricePlan").description("이름"),
                                fieldWithPath("categoryCode").description("닉네임"),
                                fieldWithPath("expectAmount").description("핸드폰 번호"),
                                fieldWithPath("title").description("성별"),
                                fieldWithPath("fundingDesc").description("생년월일"),
                                fieldWithPath("fundingIntroduce").description("비밀번호"),
                                fieldWithPath("budgetDesc").description("비밀번호"),
                                fieldWithPath("scheduleDesc").description("비밀번호"),
                                fieldWithPath("teamDesc").description("비밀번호"),
                                fieldWithPath("rewardDesc").description("비밀번호"),
                                fieldWithPath("startAt").description("비밀번호"),
                                fieldWithPath("endAt").description("비밀번호")
                        ),
                        responseFields(
                                fieldWithPath("id").description("펀딩번호(외부용)"),
                                fieldWithPath("status").description("회원상태")
                        )));
    }
}
