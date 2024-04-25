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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static com.flab.funding.data.MemberTestData.getMember;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MemberPersistenceAdapterTest {

    private final MemberPort memberPort;

    @Autowired
    public MemberPersistenceAdapterTest(MemberRepository memberRepository,
                                        BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.memberPort = new MemberPersistenceAdapter(memberRepository, bCryptPasswordEncoder);
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
        assertNotNull(savedMember.getUserKey());
        assertEquals(MemberLinkType.NONE, savedMember.getLinkType());
        assertEquals("Test@gmail.com", savedMember.getEmail());
        assertEquals("홍길순", savedMember.getUserName());
        assertEquals("테스터", savedMember.getNickName());
        assertEquals("010-1111-2222", savedMember.getPhoneNumber());
        assertEquals(MemberGender.FEMALE, savedMember.getGender());
        assertEquals(LocalDate.of(1998, 1, 30), savedMember.getBirthday());
        assertEquals("", savedMember.getPassword());
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
        assertNotNull(savedMember.getUserKey());
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
        List<Member> findMember = memberPort.getMembersByEmail(savedMember.getEmail());

        //then
        assertNotNull(savedMember.getUserKey());
        assertEquals(1, findMember.size());
    }
}