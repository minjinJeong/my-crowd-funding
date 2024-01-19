package com.flab.funding.domain.service;

import com.flab.funding.application.ports.input.CreateMemberUseCase;
import com.flab.funding.application.ports.input.GetMemberUseCase;
import com.flab.funding.application.ports.input.UpdateMemberUseCase;
import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.config.UseCase;

import java.util.Optional;

@UseCase
public class MemberService implements CreateMemberUseCase, UpdateMemberUseCase, GetMemberUseCase {
    private final MemberPort memberPort;

    public MemberService(MemberPort memberPort) {
        this.memberPort = memberPort;
    }

    @Override
    public Member createMember(Member member) {
        return memberPort.saveMember(member.activate());
    }

    @Override
    public Member updateMember(Member member) {
        return memberPort.modifyMember(member);
    }

    @Override
    public Optional<Member> getMemberByUserKey(String userKey) {
        return memberPort.getMemberByUserKey(userKey);
    }
}