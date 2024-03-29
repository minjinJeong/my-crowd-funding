package com.flab.funding.service;

import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.exception.DuplicateMemberException;
import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.MemberGender;
import com.flab.funding.domain.model.MemberLinkType;
import com.flab.funding.domain.model.MemberStatus;
import com.flab.funding.domain.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

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
                .willReturn(member);

        given(memberPort.getMemberByEmail(member.getEmail()))
                .willReturn(List.of());

        given(memberPort.getMemberByUserKey(any()))
                .willReturn(member);

        //when
        Member savedMember = memberService.registerMember(member);
        Member findMember = memberService.getMemberByUserKey(savedMember.getUserKey());

        //then
        assertNotNull(savedMember.getUserKey());
        assertEquals(savedMember.getId(), findMember.getId());
        assertEquals(savedMember.getUserKey(), findMember.getUserKey());
        assertEquals(savedMember.getStatus(), MemberStatus.ACTIVATE);
        assertEquals(savedMember.getStatus(), findMember.getStatus());
        assertEquals(savedMember.getLinkType(), findMember.getLinkType());
        assertEquals(savedMember.getEmail(), findMember.getEmail());
        assertEquals(savedMember.getUserName(), findMember.getUserName());
        assertEquals(savedMember.getNickName(), findMember.getNickName());
        assertEquals(savedMember.getPhoneNumber(), findMember.getPhoneNumber());
        assertEquals(savedMember.getGender(), findMember.getGender());
        assertEquals(savedMember.getBirthday(), findMember.getBirthday());
        assertEquals(savedMember.getPassword(), findMember.getPassword());
    }

    private Member getMember() {
        return Member.builder()
                .linkType(MemberLinkType.NONE)
                .email("Test@gmail.com")
                .userName("홍길순")
                .nickName("테스터")
                .phoneNumber("010-1111-2222")
                .gender(MemberGender.FEMALE)
                .birthday(LocalDate.of(1998, 1, 30))
                .password("")
                .build();
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
                .willReturn(member);

        //when
        Member deregistMember = memberService.deregisterMember(member.getUserKey());

        //then
        assertEquals(deregistMember.getStatus(), MemberStatus.WITHDRAW);

    }
}
