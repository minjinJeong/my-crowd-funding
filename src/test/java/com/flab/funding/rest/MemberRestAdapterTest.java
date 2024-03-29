package com.flab.funding.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.MemberGender;
import com.flab.funding.domain.model.MemberLinkType;
import com.flab.funding.domain.model.MemberStatus;
import com.flab.funding.domain.service.MemberService;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.rest.MemberRestAdapter;
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

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(MemberRestAdapter.class)
@AutoConfigureRestDocs
public class MemberRestAdapterTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("회원가입")
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

        Member response = Member.builder()
                .userKey("MM-0001")
                .status(MemberStatus.ACTIVATE)
                .build();

        given(memberService.registerMember(any(request.toMember().getClass()))).willReturn(response);

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
    @DisplayName("회원탈퇴")
    void deregisterMember() throws Exception {
        //given
        String request = "MM-0001";

        Member response = Member.builder()
                .userKey("MM-0001")
                .status(MemberStatus.WITHDRAW)
                .email("Test@gmail.com")
                .nickName("테스터")
                .phoneNumber("010-1111-2222")
                .linkType(MemberLinkType.NONE)
                .lastLoginAt(LocalDateTime.of(2024, 3, 15, 10, 9))
                .build();


        given(memberService.deregisterMember(eq(request))).willReturn(response);

        //when

        //then
        this.mockMvc.perform(delete("/members/{userKey}", request))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        pathParameters(
                                parameterWithName("userKey").description("회원번호(외부용)")
                        ),
                        responseFields(
                                fieldWithPath("userKey").description("회원번호(외부용)"),
                                fieldWithPath("status").description("회원상태"),
                                fieldWithPath("email").description("이메일"),
                                fieldWithPath("nickName").description("닉네임"),
                                fieldWithPath("phoneNumber").description("핸드폰 번호"),
                                fieldWithPath("linkType").description("계정연동"),
                                fieldWithPath("lastLoginAt").description("최근 로그인 일자")
                        )));
    }

    @Test
    @DisplayName("회원조회")
    void getMember() throws Exception {
        //given
        String request = "MM-0001";

        Member response = Member.builder()
                .userKey("MM-0001")
                .status(MemberStatus.ACTIVATE)
                .email("Test@gmail.com")
                .nickName("테스터")
                .phoneNumber("010-1111-2222")
                .linkType(MemberLinkType.NONE)
                .lastLoginAt(LocalDateTime.of(2024, 3, 15, 10, 9))
                .build();


        given(memberService.getMemberByUserKey(eq(request))).willReturn(response);

        //when

        //then
        this.mockMvc.perform(get("/members/{userKey}", request))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        pathParameters(
                                parameterWithName("userKey").description("회원번호(외부용)")
                        ),
                        responseFields(
                                fieldWithPath("userKey").description("회원번호(외부용)"),
                                fieldWithPath("status").description("회원상태"),
                                fieldWithPath("email").description("이메일"),
                                fieldWithPath("nickName").description("닉네임"),
                                fieldWithPath("phoneNumber").description("핸드폰 번호"),
                                fieldWithPath("linkType").description("계정연동"),
                                fieldWithPath("lastLoginAt").description("최근 로그인 일자")
                        )));
    }
}
