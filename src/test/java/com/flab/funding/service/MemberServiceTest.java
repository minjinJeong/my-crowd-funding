package com.flab.funding.service;

import com.flab.funding.domain.exception.DuplicateMemberException;
import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.MemberGender;
import com.flab.funding.domain.model.MemberLinkType;
import com.flab.funding.domain.model.MemberStatus;
import com.flab.funding.domain.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = getMember();

        //when
        Member savedMember = memberService.registerMember(member);
        Member findMember = memberService.getMemberByUserKey(savedMember.getUserKey());

        //then
        assertEquals(savedMember.getId(), findMember.getId());
        assertEquals(savedMember.getId(), findMember.getId());
        assertEquals(savedMember.getUserKey(), findMember.getUserKey());
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
    public void 중복_회원_예외() throws Exception {
        //given
        Member member = getMember();
        
        //when
        memberService.registerMember(member);

        //then
        assertThrows(DuplicateMemberException.class, () -> memberService.registerMember(member));
    }

    @Test
    public void 회원탈퇴() throws Exception {
        //given
        Member member = memberService.registerMember(getMember());

        //when
        Member deregistMember = memberService.deregisterMember(member.getUserKey());
        Member findMember = memberService.getMemberByUserKey(deregistMember.getUserKey());

        //then
        assertEquals(deregistMember.getStatus(), MemberStatus.WITHDRAW);
        assertEquals(deregistMember.getStatus(), findMember.getStatus());

    }
}
