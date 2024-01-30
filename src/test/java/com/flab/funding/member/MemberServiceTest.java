package com.flab.funding.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.funding.domain.model.MemberGender;
import com.flab.funding.domain.model.MemberLinkType;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberCreateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
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
public class MemberServiceTest {

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
        MemberCreateRequest request = MemberCreateRequest.builder()
                .linkType(MemberLinkType.NONE)
                .email("Test@gmail.com")
                .userName("홍길순")
                .nickName("테스터")
                .phoneNum("010-1111-2222")
                .gender(MemberGender.FEMALE)
                .birthday(LocalDate.of(1998,1,30))
                .password("")
                .build();

        // when
        // then
        this.mockMvc.perform(post("/member")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("index",
                        requestFields(
                                fieldWithPath("linkType").description("회원연동"),
                                fieldWithPath("email").description("이메일"),
                                fieldWithPath("userName").description("이름"),
                                fieldWithPath("nickName").description("닉네임"),
                                fieldWithPath("phoneNum").description("핸드폰 번호"),
                                fieldWithPath("gender").description("성별"),
                                fieldWithPath("birthday").description("생년월일"),
                                fieldWithPath("password").description("비밀번호")
                        ),
                        responseFields(
                                fieldWithPath("userKey").description("회원번호(외부용)"),
                                fieldWithPath("statusCode").description("회원상태")
                        )));
    }
}
