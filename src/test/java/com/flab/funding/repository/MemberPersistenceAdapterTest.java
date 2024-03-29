package com.flab.funding.repository;

import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.MemberGender;
import com.flab.funding.domain.model.MemberLinkType;
import com.flab.funding.infrastructure.adapters.output.persistence.MemberPersistenceAdapter;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MemberPersistenceAdapterTest {

    private final MemberPort memberPort;

    @Autowired
    public MemberPersistenceAdapterTest(MemberRepository memberRepository) {
        this.memberPort = new MemberPersistenceAdapter(memberRepository);
    }

    @Test
    @DisplayName("회원가입")
    public void saveMember() {
        //given
        Member member = getMember().activate();

        //when
        Member savedMember = memberPort.saveMember(member);

        //then
        assertNotNull(savedMember.getId());
        assertEquals("Test@gmail.com", savedMember.getEmail());
        assertEquals("", savedMember.getPassword());
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
    @DisplayName("회원 키로 회원조회")
    public void getMemberByUserKey() {
        //given
        Member member = getMember().activate();
        Member savedMember = memberPort.saveMember(member);

        // when
        Member findMember = memberPort.getMemberByUserKey(savedMember.getUserKey());

        //then
        assertEquals(savedMember.getUserKey(), findMember.getUserKey());
        assertEquals(savedMember.getEmail(), findMember.getEmail());
    }

    @Test
    @DisplayName("이메일로 회원조회")
    public void getMemberByEmail() {
        //given
        Member member = getMember().activate();
        Member savedMember = memberPort.saveMember(member);

        // when
        List<Member> findMember = memberPort.getMemberByEmail(savedMember.getEmail());

        //then
        assertEquals(1, findMember.size());
    }
}