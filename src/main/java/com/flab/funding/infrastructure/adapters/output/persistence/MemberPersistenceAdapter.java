package com.flab.funding.infrastructure.adapters.output.persistence;

import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.model.Account;
import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberPersistenceAdapter implements MemberPort {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Member saveMember(Member member) {
        MemberEntity memberEntity = MemberEntity.from(encodePassword(member));
        MemberEntity savedMember = memberRepository.save(memberEntity);
        return savedMember.toMember();
    }

    // TODO : Util로 뺄 수 있을지 고민해보자
    private Member encodePassword(Member member) {

        return member.changePassword(
                passwordEncoder.encode(member.getPassword())
        );
    }

    @Override
    public Member getMemberByUserKey(String userKey) {

        MemberEntity findMemberEntity =
                memberRepository.findByUserKey(userKey).orElse(MemberEntity.builder().build());

        return findMemberEntity.toMember();
    }

    @Override
    public List<Member> getMembersByEmail(String email) {

        List<MemberEntity> findMembers = memberRepository.findAllByEmail(email);

        return findMembers.stream()
                .map(MemberEntity::toMember)
                .collect(Collectors.toList());
    }

    @Override
    public Member findMemberByAccount(Account account) {

        MemberEntity findMemberEntity =
                memberRepository.findByEmail(account.getEmail())
                        .orElse(MemberEntity.builder().build());

        if (!passwordEncoder.matches(account.getPassword(), findMemberEntity.getPassword())) {

            findMemberEntity = MemberEntity.builder().build();
        }

        return findMemberEntity.toMember();
    }
}
