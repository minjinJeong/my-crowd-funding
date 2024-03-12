package com.flab.funding.domain.service;

import com.flab.funding.application.ports.input.DeregisterMemberUseCase;
import com.flab.funding.application.ports.input.LoginUseCase;
import com.flab.funding.application.ports.input.RegisterMemberUseCase;
import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.exception.DuplicateMemberException;
import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.config.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class MemberService implements RegisterMemberUseCase, DeregisterMemberUseCase, LoginUseCase {
    private final MemberPort memberPort;

    @Override
    @Transactional
    public Member registMember(Member member) {
        validateDuplicateMember(member);
        return memberPort.saveMember(member.activate());
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberPort.getMemberByEmail(member.getEmail());
        if(!findMembers.isEmpty()) {
            throw new DuplicateMemberException();
        }
    }

    @Override
    @Transactional
    public Member deregistMember(Member member) {
        Member findmember = memberPort.getMemberByUserKey(member.getUserKey());
        return memberPort.saveMember(findmember.deactivate());
    }

    @Override
    public Member getMemberByUserKey(String userKey) {
        return memberPort.getMemberByUserKey(userKey);
    }

    // TODO : Login 기능 구현
    @Override
    public Member login(Member member) {
        return null;
    }
}