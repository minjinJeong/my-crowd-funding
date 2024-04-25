package com.flab.funding.domain.service;

import com.flab.funding.application.ports.input.DeregisterMemberUseCase;
import com.flab.funding.application.ports.input.LoginUseCase;
import com.flab.funding.application.ports.input.RegisterMemberUseCase;
import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.exception.DuplicateMemberException;
import com.flab.funding.domain.exception.EmptyMemberException;
import com.flab.funding.domain.model.Account;
import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.config.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class MemberService implements RegisterMemberUseCase, DeregisterMemberUseCase, LoginUseCase {

    private final MemberPort memberPort;

    // TODO 이렇게 사용하면 클린 아키텍처가 깨지잖아. 암호화 로직을 분리해야 한다.
    @Override
    public Member registerMember(Member member) {
        validateDuplicateMember(member);
        return memberPort.saveMember(member.activate());
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberPort.getMembersByEmail(member.getEmail());
        if(!findMembers.isEmpty()) {
            throw new DuplicateMemberException();
        }
    }

    @Override
    public Member deregisterMember(String userKey) {
        Member findmember = memberPort.getMemberByUserKey(userKey);
        return memberPort.saveMember(findmember.deactivate());
    }

    @Override
    public Member getMemberByUserKey(String userKey) {
        return memberPort.getMemberByUserKey(userKey);
    }

    @Override
    public Member login(Member member) {

        Member findMember = memberPort.findMemberByAccount(Account.from(member));

        if (findMember.getId() == null) {

            throw new EmptyMemberException();
        }

        return findMember;
    }
}