package com.flab.funding.service;

import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.exception.DuplicateMemberException;
import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.MemberStatus;
import com.flab.funding.domain.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.flab.funding.data.MemberTestData.getMember;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith({MockitoExtension.class})
public class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberPort memberPort;

    @Test
    @DisplayName("회원가입")
    public void join() {
        //given
        Member member = getMember();

        given(memberPort.saveMember(any(Member.class)))
                .willAnswer(invocation -> invocation.getArguments()[0]);

        given(memberPort.getMemberByEmail(member.getEmail()))
                .willReturn(List.of());

        //when
        Member savedMember = memberService.registerMember(member);

        //then
        assertNotNull(savedMember.getUserKey());
        assertEquals(getMember().getEmail(), savedMember.getEmail());
        assertEquals(MemberStatus.ACTIVATE, savedMember.getStatus());
    }

    @Test
    @DisplayName("중복 회원 예외")
    public void duplicateMember() {
        //given
        Member member = getMember();

        given(memberPort.getMemberByEmail(member.getEmail()))
                .willReturn(List.of(member));
        
        //when
        //then
        assertThrows(DuplicateMemberException.class, () -> memberService.registerMember(member));
    }

    @Test
    @DisplayName("회원탈퇴")
    public void withdraw() {
        //given
        Member member = getMember();

        given(memberPort.getMemberByUserKey(any()))
                .willReturn(member);

        given(memberPort.saveMember(any(Member.class)))
                .willAnswer(invocation -> invocation.getArguments()[0]);

        //when
        Member deregistMember = memberService.deregisterMember(member.getUserKey());

        //then
        assertEquals(MemberStatus.WITHDRAW, deregistMember.getStatus());

    }
}
