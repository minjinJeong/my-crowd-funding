package com.flab.funding.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.MemberStatus;
import com.flab.funding.domain.service.MemberService;
import com.flab.funding.infrastructure.adapters.input.data.request.LoginRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.LogoutRequest;
import com.flab.funding.infrastructure.adapters.input.rest.LoginRestAdapter;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(LoginRestAdapter.class)
@AutoConfigureRestDocs
public class LoginRestAdapterTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("로그인")
    public void login() throws Exception {

        //given
        LoginRequest request = LoginRequest.builder()
                .email("Test@gamil.com")
                .password("1234")
                .build();

        Member response = Member.builder()
                .userKey("MM-0001")
                .status(MemberStatus.ACTIVATE)
                .build();

        given(memberService.login(any(request.toMember().getClass())))
                .willReturn(response);

        //when
        //then
        this.mockMvc.perform(post("/login")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        requestFields(
                                fieldWithPath("email").description("이메일"),
                                fieldWithPath("password").description("비밀번호")
                        ),
                        responseFields(
                                fieldWithPath("userKey").description("회원번호(외부용)"),
                                fieldWithPath("status").description("회원상태")
                        )));

    }

    @Test
    @DisplayName("로그아웃")
    public void logout() throws Exception {
        //given
        LogoutRequest request = LogoutRequest.builder()
                .userKey("MM-0001")
                .build();

        Member response = Member.builder()
                .userKey("MM-0001")
                .status(MemberStatus.ACTIVATE)
                .build();

        given(memberService.logout(any(request.toMember().getClass())))
                .willReturn(response);

        //when
        //then
        this.mockMvc.perform(post("/logout")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        requestFields(
                                fieldWithPath("userKey").description("회원번호(외부용)")
                        ),
                        responseFields(
                                fieldWithPath("userKey").description("회원번호(외부용)"),
                                fieldWithPath("status").description("회원상태")
                        )));

    }
}
