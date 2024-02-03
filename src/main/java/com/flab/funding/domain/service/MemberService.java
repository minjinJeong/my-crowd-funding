package com.flab.funding.domain.service;

import com.flab.funding.application.ports.input.DeregisterMemberUseCase;
import com.flab.funding.application.ports.input.LoginUseCase;
import com.flab.funding.application.ports.input.RegisterMemberUseCase;
import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.config.UseCase;

import java.util.Optional;

@UseCase
public class MemberService implements RegisterMemberUseCase, DeregisterMemberUseCase, LoginUseCase {
    private final MemberPort memberPort;

    public MemberService(MemberPort memberPort) {
        this.memberPort = memberPort;
    }

    @Override
    public Member registMember(Member member) {
        return memberPort.saveMember(member.activate());
    }

    @Override
    public Member deregistMember(Member member) {
        return memberPort.modifyMember(member.deactivate());
    }

    @Override
    public Optional<Member> getMemberByUserKey(String userKey) {
        return memberPort.getMemberByUserKey(userKey);
    }
}