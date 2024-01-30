package com.flab.funding.infrastructure.adapters.output.persistence;

import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.mapper.MemberPersistenceMapper;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements MemberPort {
    private final MemberRepository memberRepository;
    private final MemberPersistenceMapper memberPersistenceMapper;

    // TODO : modify method after make api docs
    @Override
    public Optional<Member> getMemberByUserKey(String userKey) {
        return Optional.empty();
    }

    // TODO : modify method after make api docs
    @Override
    public Member saveMember(Member member) {
        MemberEntity memberEntity = memberPersistenceMapper.toMemberEntity(member);
        MemberEntity savedMember = memberRepository.save(memberEntity);
        return memberPersistenceMapper.toMember(savedMember);
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
