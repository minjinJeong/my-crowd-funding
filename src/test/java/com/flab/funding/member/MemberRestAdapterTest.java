package com.flab.funding.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.funding.domain.model.MemberGender;
import com.flab.funding.domain.model.MemberLinkType;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberInfoRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberRegisterRequest;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class MemberRestAdapterTest {

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
    void registerMember() throws Exception {

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
        this.mockMvc.perform(post("/members")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        requestFields(
                                fieldWithPath("linkType").description("회원연동"),
                                fieldWithPath("email").description("이메일"),
                                fieldWithPath("userName").description("이름"),
                                fieldWithPath("nickName").description("닉네임"),
                                fieldWithPath("phoneNumber").description("핸드폰 번호"),
                                fieldWithPath("gender").description("성별"),
                                fieldWithPath("birthday").description("생년월일"),
                                fieldWithPath("password").description("비밀번호")
                        ),
                        responseFields(
                                fieldWithPath("userKey").description("회원번호(외부용)"),
                                fieldWithPath("status").description("회원상태")
                        )));
    }

    @Test
    void deregisterMember() throws Exception {
        //given
        MemberInfoRequest request = MemberInfoRequest.builder()
                .userKey("1")
                .build();

        //when

        //then
        this.mockMvc.perform(delete("/members")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("fields", "userKey,status"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userKey").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.otherField").doesNotExist())
                .andDo(document("{class-name}/{method-name}",
                        requestFields(
                                fieldWithPath("userKey").description("회원번호(외부용)")
                        ),
                        responseFields(
                                fieldWithPath("userKey").description("회원번호(외부용)"),
                                fieldWithPath("status").description("회원상태"),
                                fieldWithPath("nickName").description("닉네임"),
                                fieldWithPath("email").description("이메일"),
                                fieldWithPath("phoneNumber").description("핸드폰 번호"),
                                fieldWithPath("linkType").description("계정연동"),
                                fieldWithPath("lastLoginAt").description("최근 로그인 일자")
                        )));
    }

    @Test
    void getMember() throws Exception {
        //given
        MemberInfoRequest request = MemberInfoRequest.builder()
                .userKey("1")
                .build();

        //when

        //then
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/members/{userKey}"
                                , request.getUserKey()))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        pathParameters(
                                parameterWithName("userKey").description("회원번호(외부용)")
                        ),
                        responseFields(
                                fieldWithPath("userKey").description("회원번호(외부용)"),
                                fieldWithPath("status").description("회원상태"),
                                fieldWithPath("nickName").description("닉네임"),
                                fieldWithPath("email").description("이메일"),
                                fieldWithPath("phoneNumber").description("핸드폰 번호"),
                                fieldWithPath("linkType").description("계정연동"),
                                fieldWithPath("lastLoginAt").description("최근 로그인 일자")
                        )));
    }
}
