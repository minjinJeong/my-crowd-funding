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

    // TODO : modify method after make api docs
    @Override
    public Optional<Member> getMemberByUserKey(String userKey) {
        return Optional.empty();
    }

    // TODO : modify method after make api docs
    @Override
    public Member saveMember(Member member) {
        throw new NullPointerException();
    }

    // TODO : modify method after make api docs
    @Override
    public Member modifyMember(Member member) {
        throw new NullPointerException();
    }

    // TODO : modify method after make api docs
    @Override
    public void deleteById(Member member) {
        throw new NullPointerException();
    }
}
