package com.flab.funding.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.funding.domain.model.*;
import com.flab.funding.domain.service.FundingService;
import com.flab.funding.infrastructure.adapters.input.data.request.*;
import com.flab.funding.infrastructure.adapters.input.rest.FundingRestAdapter;
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

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
@WebMvcTest(FundingRestAdapter.class)
@AutoConfigureRestDocs
public class FundingRestAdapterTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FundingService fundingService;

    @Test
    @DisplayName("펀딩 등록")
    void registerFunding() throws Exception {

        // given
        List<FundingTagRegisterRequest> fundingTagRegisterRequests = new ArrayList<>();
        fundingTagRegisterRequests.add(createTag("검색 키워드1"));
        fundingTagRegisterRequests.add(createTag("검색 키워드2"));
        fundingTagRegisterRequests.add(createTag("검색 키워드3"));

        FundingRegisterRequest request = FundingRegisterRequest.builder()
                .userKey("MM-0001")
                .isAdult(false)
                .pricePlan("00")
                .category(FundingCategory.FOOD)
                .expectAmount(BigInteger.valueOf(100000))
                .title("제목")
                .fundingDescription("펀딩 상세")
                .fundingIntroduce("펀딩 소개글")
                .budgetDescription("예산 계획")
                .scheduleDescription("펀딩 계획")
                .teamDescription("팀 소개")
                .rewardDescription("리워드 소개")
                .tags(fundingTagRegisterRequests)
                .startAt(LocalDateTime.of(2024, 2, 1, 12, 0))
                .endAt(LocalDateTime.of(2024, 2, 28, 12, 0))
                .build();

        Funding response = Funding.builder()
                .fundingKey("FF-0001")
                .status(FundingStatus.REGISTER)
                .build();

        given(fundingService.registerFunding(any(request.toFunding().getClass())))
                .willReturn(response);

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
                                fieldWithPath("category").description("펀딩분류코드"),
                                fieldWithPath("expectAmount").description("목표금액"),
                                fieldWithPath("title").description("프로젝트 명"),
                                fieldWithPath("fundingDescription").description("프로젝트 요약 소개글"),
                                fieldWithPath("fundingIntroduce").description("프로젝트 소개"),
                                fieldWithPath("budgetDescription").description("프로젝트 예산"),
                                fieldWithPath("scheduleDescription").description("프로젝트 일정"),
                                fieldWithPath("teamDescription").description("프로젝트 팀 소개"),
                                fieldWithPath("rewardDescription").description("프로젝트 선물 설명"),
                                fieldWithPath("tags").description("검색 키워드 리스트"),
                                fieldWithPath("tags[].tag").description("검색 키워드"),
                                fieldWithPath("startAt").description("펀딩 시작일"),
                                fieldWithPath("endAt").description("펀딩 종료일")
                        ),
                        responseFields(
                                fieldWithPath("fundingKey").description("펀딩번호(외부용)"),
                                fieldWithPath("status").description("펀딩상태")
                        )));
    }

    private FundingTagRegisterRequest createTag(String tag) {
        return FundingTagRegisterRequest.builder()
                .tag(tag)
                .build();
    }

    @Test
    @DisplayName("펀딩 심사대기")
    public void waitForFundingReview() throws Exception {
        //given
        String request = "FF-0001";

        Funding response = Funding.builder()
                .fundingKey("FF-0001")
                .status(FundingStatus.REVIEW_WAIT)
                .build();

        given(fundingService.waitForFundingReview(eq(request))).willReturn(response);

        //when

        //then
        this.mockMvc.perform(patch("/funding/{fundingKey}/wait", request))
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
    @DisplayName("펀딩 심사완료")
    public void completeFundingReview() throws Exception {
        //given
        String request = "FF-0001";

        Funding response = Funding.builder()
                .fundingKey("FF-0001")
                .status(FundingStatus.OPEN_WAIT)
                .build();

        given(fundingService.completeFundingReview(eq(request))).willReturn(response);

        //when

        //then
        this.mockMvc.perform(patch("/funding/{fundingKey}/complete", request))
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
    @DisplayName("펀딩 취소")
    public void cancelFunding() throws Exception {
        //given
        String request = "FF-0001";

        Funding response = Funding.builder()
                .fundingKey("FF-0001")
                .status(FundingStatus.CANCEL)
                .build();

        given(fundingService.cancelFunding(eq(request))).willReturn(response);

        //when

        //then
        this.mockMvc.perform(patch("/funding/{fundingKey}/cancel", request))
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
    @DisplayName("펀딩 창장자 등록")
    void registerFundingCreator() throws Exception {

        // given
        FundingCreatorRegisterRequest request = FundingCreatorRegisterRequest.builder()
                .fundingKey("FF-0001")
                .isValid(true)
                .businessNumber("12345678")
                .representative("홍길동")
                .introduce("안녕하세요, 개인 사업자 홍길동입니다.")
                .build();

        FundingCreator response = FundingCreator.builder()
                .funding(getFundingRequest())
                .isValid(true)
                .businessNumber("12345678")
                .representative("홍길동")
                .introduce("안녕하세요, 개인 사업자 홍길동입니다.")
                .build();

        given(fundingService.registerFundingCreator(any(request.toFundingCreator().getClass())))
                .willReturn(response);

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
                                fieldWithPath("businessNumber").description("사업자번호"),
                                fieldWithPath("representative").description("대표자"),
                                fieldWithPath("introduce").description("창작자 소개")
                        ),
                        responseFields(
                                fieldWithPath("fundingKey").description("펀딩번호(외부용)"),
                                fieldWithPath("isValid").description("본인인증 여부"),
                                fieldWithPath("businessNumber").description("사업자번호"),
                                fieldWithPath("representative").description("대표자"),
                                fieldWithPath("introduce").description("창작자 소개")
                        )));
    }

    private Funding getFundingRequest() {
        return Funding.builder()
                .fundingKey("FF-0001")
                .build();
    }

    @Test
    @DisplayName("펀딩 아이템 생성")
    void makeFundingItem() throws Exception {

        // given
        List<FundingItemOptionRequest> requestItemOptions = new  ArrayList<>();
        requestItemOptions.add(createRequestItemOption("3mm"));
        requestItemOptions.add(createRequestItemOption("5mm"));
        requestItemOptions.add(createRequestItemOption("7mm"));
        requestItemOptions.add(createRequestItemOption("9mm"));

        FundingItemRegisterRequest request = FundingItemRegisterRequest.builder()
                .fundingKey("FF-0001")
                .itemName("은 귀걸이")
                .optionType(FundingItemOptionType.NONE)
                .fundingItemOptions(requestItemOptions)
                .build();

        List<FundingItemOption> responseItemOptions = new ArrayList<>();
        responseItemOptions.add(createResponseItemOption("3mm"));
        responseItemOptions.add(createResponseItemOption("5mm"));
        responseItemOptions.add(createResponseItemOption("7mm"));
        responseItemOptions.add(createResponseItemOption("9mm"));


        FundingItem response = FundingItem.builder()
                .funding(getFundingRequest())
                .itemName("은 귀걸이")
                .optionType(FundingItemOptionType.NONE)
                .fundingItemOptions(responseItemOptions)
                .build();

        given(fundingService.makeFundingItem(any(request.toFundingItem().getClass())))
                .willReturn(response);

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
                                fieldWithPath("fundingItemOptions[].optionName").description("아이템 옵션")
                        ),
                        responseFields(
                                fieldWithPath("fundingKey").description("펀딩번호(외부용)"),
                                fieldWithPath("itemName").description("아이템 이름"),
                                fieldWithPath("optionType").description("옵션 조건"),
                                fieldWithPath("fundingItemOptions").description("아이템 옵션 리스트"),
                                fieldWithPath("fundingItemOptions[].optionName").description("아이템 옵션")
                        )));
    }

    private FundingItemOptionRequest createRequestItemOption(String option) {
        return FundingItemOptionRequest.builder()
                .optionName(option)
                .build();
    }

    private FundingItemOption createResponseItemOption(String option) {
        return FundingItemOption.builder()
                .optionName(option)
                .build();
    }

    @Test
    @DisplayName("펀딩 리워드 생성")
    void makeFundingReward() throws Exception {

        // given
        List<FundingRewardItemRequest> requestRewardItems = new ArrayList<>();
        requestRewardItems.add(createRequestRewardItem(1L));
        requestRewardItems.add(createRequestRewardItem(2L));
        requestRewardItems.add(createRequestRewardItem(3L));

        FundingRewardRegisterRequest request = FundingRewardRegisterRequest.builder()
                .fundingKey("FF-0001")
                .isDelivery(true)
                .rewardTitle("귀걸이 세트")
                .amount(BigInteger.valueOf(15000))
                .fundingRewardItems(requestRewardItems)
                .countLimit(10)
                .personalLimit(5)
                .expectDate(LocalDate.of(2024, 3, 31))
                .build();

        List<FundingRewardItem> responseRewardItems = new ArrayList<>();
        responseRewardItems.add(createResponseRewardItem(1L));
        responseRewardItems.add(createResponseRewardItem(2L));
        responseRewardItems.add(createResponseRewardItem(3L));

        FundingReward response = FundingReward.builder()
                .funding(getFundingRequest())
                .isDelivery(true)
                .rewardTitle("귀걸이 세트")
                .amount(BigInteger.valueOf(15000))
                .fundingRewardItems(responseRewardItems)
                .countLimit(10)
                .personalLimit(5)
                .expectDate(LocalDate.of(2024, 3, 31))
                .build();

        given(fundingService.makeFundingReward(any(request.toFundingReward().getClass())))
                .willReturn(response);

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
                                fieldWithPath("fundingRewardItems").description("리워드에 속한 아이템들"),
                                fieldWithPath("fundingRewardItems[].fundingItemId").description("아이템 번호"),
                                fieldWithPath("countLimit").description("수량 제한"),
                                fieldWithPath("personalLimit").description("1인당 최대 수량 제한"),
                                fieldWithPath("expectDate").description("예상 전달일")
                        ),
                        responseFields(
                                fieldWithPath("fundingKey").description("펀딩번호(외부용)"),
                                fieldWithPath("isDelivery").description("배송 필요 여부"),
                                fieldWithPath("rewardTitle").description("리워드 이름"),
                                fieldWithPath("amount").description("리워드 금액"),
                                fieldWithPath("fundingRewardItems").description("리워드에 속한 아이템들"),
                                fieldWithPath("fundingRewardItems[].fundingItemId").description("아이템 번호"),
                                fieldWithPath("countLimit").description("수량 제한"),
                                fieldWithPath("personalLimit").description("1인당 최대 수량 제한"),
                                fieldWithPath("expectDate").description("예상 전달일")
                        )));
    }

    private FundingRewardItemRequest createRequestRewardItem(Long fundingItemId) {
        return FundingRewardItemRequest.builder()
                .fundingItemId(fundingItemId)
                .build();
    }

    private FundingRewardItem createResponseRewardItem(Long fundingItemId) {
        return FundingRewardItem.builder()
                .fundingItem(FundingItem.builder().id(fundingItemId).build())
                .build();
    }
}
