package com.flab.funding.infrastructure.adapters.output.persistence;

import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class MemberPersistenceAdapter implements MemberPort {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberPersistenceAdapter(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Optional<Member> getMemberByKey(String userKey) {
        return Optional.empty();
    }

    @Override
    public Member saveMember(Member member) {
        return null;
    }

    @Override
    public Member modifyMember(Member member) {
        return null;
    }

    @Override
    public void deleteById(Member member) {

    }
}
