package com.flab.funding.infrastructure.adapters.output.persistence;

import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberPersistenceAdapter implements MemberPort {

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public Member saveMember(Member member) {
        MemberEntity memberEntity = MemberEntity.from(member);
        MemberEntity savedMember = memberRepository.save(memberEntity);
        return savedMember.toMember();
    }

    @Override
    public Member getMemberByUserKey(String userKey) {
        MemberEntity findMemberEntity = memberRepository.findByUserKey(userKey).orElse(MemberEntity.builder().build());
        return findMemberEntity.toMember();
    }

    @Override
    public List<Member> getMemberByEmail(String email) {
        List<MemberEntity> findMembers = memberRepository.findByEmail(email);
        return findMembers.stream().map(MemberEntity::toMember).collect(Collectors.toList());
    }
}
