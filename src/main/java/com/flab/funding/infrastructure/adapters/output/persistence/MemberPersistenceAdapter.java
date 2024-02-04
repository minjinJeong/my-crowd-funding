package com.flab.funding.infrastructure.adapters.output.persistence;

import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements MemberPort {
    private final MemberRepository memberRepository;

    @Override
    public Member getMemberByUserKey(String userKey) {
        MemberEntity findMemberEntity = memberRepository.findByUserKey(userKey).orElse(MemberEntity.builder().build());
        return findMemberEntity.toMember();
    }

    @Override
    public Member saveMember(Member member) {
        MemberEntity memberEntity = MemberEntity.from(member);
        MemberEntity savedMember = memberRepository.save(memberEntity);
        return savedMember.toMember();
    }

    @Override
    public Member modifyMember(Member member) {
        MemberEntity memberEntity = MemberEntity.from(member);
        MemberEntity modifiedMember = memberRepository.update(memberEntity);
        return modifiedMember.toMember();
    }

    // TODO : modify method after make api docs
    @Override
    public void deleteById(Member member) {
        throw new NullPointerException();
    }
}
