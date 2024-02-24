package com.flab.funding.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.funding.domain.model.FundingCategory;
import com.flab.funding.domain.model.FundingItemOptionType;
import com.flab.funding.infrastructure.adapters.input.data.request.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
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
                .isAdult(false)
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
                                fieldWithPath("isAdult").description("성인전용"),
                                fieldWithPath("pricePlan").description("요금제"),
                                fieldWithPath("categoryCode").description("펀딩분류코드"),
                                fieldWithPath("expectAmount").description("목표금액"),
                                fieldWithPath("title").description("프로젝트 명"),
                                fieldWithPath("fundingDesc").description("프로젝트 요약 소개글"),
                                fieldWithPath("fundingIntroduce").description("프로젝트 소개"),
                                fieldWithPath("budgetDesc").description("프로젝트 예산"),
                                fieldWithPath("scheduleDesc").description("프로젝트 일정"),
                                fieldWithPath("teamDesc").description("프로젝트 팀 소개"),
                                fieldWithPath("rewardDesc").description("프로젝트 선물 설명"),
                                fieldWithPath("startAt").description("펀딩 시작일"),
                                fieldWithPath("endAt").description("펀딩 종료일")
                        ),
                        responseFields(
                                fieldWithPath("fundingKey").description("펀딩번호(외부용)"),
                                fieldWithPath("status").description("펀딩상태")
                        )));
    }

    @Test
    public void waitForFundingReview() throws Exception {
        //given
        FundingInfoRequest request = FundingInfoRequest.builder()
                .fundingKey("1")
                .build();

        //when

        //then
        this.mockMvc.perform(RestDocumentationRequestBuilders.patch("/funding/{fundingKey}/wait"
                        , request.getFundingKey()))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        pathParameters(
                              parameterWithName("fundingKey").description("펀딩번호(외부용)")
                        ),
                        responseFields(
                                fieldWithPath("fundingKey").description("펀딩번호(외부용)"),
                                fieldWithPath("status").description("펀딩상태")
                        )));

    }

    @Test
    public void completeFundingReview() throws Exception {
        //given
        FundingInfoRequest request = FundingInfoRequest.builder()
                .fundingKey("1")
                .build();

        //when

        //then
        this.mockMvc.perform(RestDocumentationRequestBuilders.patch("/funding/{fundingKey}/complete"
                        , request.getFundingKey()))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        pathParameters(
                                parameterWithName("fundingKey").description("펀딩번호(외부용)")
                        ),
                        responseFields(
                                fieldWithPath("fundingKey").description("펀딩번호(외부용)"),
                                fieldWithPath("status").description("펀딩상태")
                        )));

    }

    @Test
    public void cancelFunding() throws Exception {
        //given
        FundingInfoRequest request = FundingInfoRequest.builder()
                .fundingKey("1")
                .build();

        //when

        //then
        this.mockMvc.perform(RestDocumentationRequestBuilders.patch("/funding/{fundingKey}/cancel"
                        , request.getFundingKey()))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        pathParameters(
                                parameterWithName("fundingKey").description("펀딩번호(외부용)")
                        ),
                        responseFields(
                                fieldWithPath("fundingKey").description("펀딩번호(외부용)"),
                                fieldWithPath("status").description("펀딩상태")
                        )));

    }

    @Test
    void registerFundingCreator() throws Exception {

        // given
        FundingCreatorRegisterRequest request = FundingCreatorRegisterRequest.builder()
                .fundingKey("1")
                .isValid(true)
                .businessNum("12345678")
                .representative("홍길동")
                .introduce("안녕하세요, 개인 사업자 홍길동입니다.")
                .build();

        // when
        // then
        this.mockMvc.perform(post("/funding/creators")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        requestFields(
                                fieldWithPath("fundingKey").description("펀딩번호(외부용)"),
                                fieldWithPath("isValid").description("본인인증 여부"),
                                fieldWithPath("businessNum").description("사업자번호"),
                                fieldWithPath("representative").description("대표자"),
                                fieldWithPath("introduce").description("창작자 소개")
                                ),
                        responseFields(
                                fieldWithPath("fundingKey").description("펀딩번호(외부용)")
                        )));
    }

    @Test
    void makeFundingItem() throws Exception {

        // given
        FundingItemOptionRequest itemOption = FundingItemOptionRequest.builder()
                .fundingItemId("1")
                .option(FundingItemOptionType.NONE.getOptionType())
                .build();
        List<FundingItemOptionRequest> itemOptions = new  ArrayList<>();
        itemOptions.add(itemOption);

        FundingItemRegisterRequest request = FundingItemRegisterRequest.builder()
                .fundingKey("1")
                .itemName("수제 귀걸이")
                .optionType(FundingItemOptionType.NONE)
                .fundingItemOptions(itemOptions)
                .build();

        // when
        // then
        this.mockMvc.perform(post("/funding/items")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        requestFields(
                                fieldWithPath("fundingKey").description("펀딩번호(외부용)"),
                                fieldWithPath("itemName").description("아이템 이름"),
                                fieldWithPath("optionType").description("옵션 조건"),
                                fieldWithPath("fundingItemOptions").description("아이템 옵션 리스트"),
                                fieldWithPath("fundingItemOptions[].fundingItemId").description("아이템 옵션 리스트"),
                                fieldWithPath("fundingItemOptions[].option").description("아이템 옵션 리스트")
                        ),
                        responseFields(
                                fieldWithPath("fundingKey").description("펀딩번호(외부용)")
                        )));
    }

    @Test
    void makeFundingReward() throws Exception {

        // given
        FundingRewardItemRequest rewardItem = FundingRewardItemRequest.builder()
                .fundingId("1")
                .fundingRewardId("1")
                .fundingItemId("1")
                .build();
        List<FundingRewardItemRequest> rewardItems = new ArrayList<>();
        rewardItems.add(rewardItem);

        FundingRewardRegisterRequest request = FundingRewardRegisterRequest.builder()
                .fundingKey("1")
                .isDelivery(true)
                .rewardTitle("귀걸이 세트")
                .amount(BigInteger.valueOf(15000))
                .fundingRewardItems(rewardItems)
                .countLimit(10)
                .personalLimit(5)
                .expectDate(LocalDate.of(2024, 3, 31))
                .build();

        // when
        // then
        this.mockMvc.perform(post("/funding/rewards")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        requestFields(
                                fieldWithPath("fundingKey").description("펀딩번호(외부용)"),
                                fieldWithPath("isDelivery").description("배송 필요 여부"),
                                fieldWithPath("rewardTitle").description("리워드 이름"),
                                fieldWithPath("amount").description("리워드 금액"),
                                fieldWithPath("fundingRewardItems").description("리워드 아이템"),
                                fieldWithPath("fundingRewardItems[].fundingId").description("리워드 아이템"),
                                fieldWithPath("fundingRewardItems[].fundingRewardId").description("리워드 아이템"),
                                fieldWithPath("fundingRewardItems[].fundingItemId").description("리워드 아이템"),
                                fieldWithPath("countLimit").description("수량 제한"),
                                fieldWithPath("personalLimit").description("1인당 최대 수량 제한"),
                                fieldWithPath("expectDate").description("예상 전달일")
                                ),
                        responseFields(
                                fieldWithPath("fundingKey").description("펀딩번호(외부용)")
                        )));
    }
}
