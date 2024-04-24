package com.flab.funding.domain.service;

import com.flab.funding.application.ports.input.DeregisterMemberUseCase;
import com.flab.funding.application.ports.input.LoginUseCase;
import com.flab.funding.application.ports.input.RegisterMemberUseCase;
import com.flab.funding.application.ports.output.MemberPort;
import com.flab.funding.domain.exception.DuplicateMemberException;
import com.flab.funding.domain.exception.EmptyMemberException;
import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.config.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class MemberService implements RegisterMemberUseCase, DeregisterMemberUseCase, LoginUseCase {

    private final BCryptPasswordEncoder passwordEncoder;

    private final MemberPort memberPort;

    @Override
    public Member registerMember(Member member) {
        validateDuplicateMember(member);
        String encodePassword = passwordEncoder.encode(member.getPassword());
        return memberPort.saveMember(member.encode(encodePassword).activate());
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

        Member findMember = memberPort.getMemberByEmail(member.getEmail());

        if (findMember.getId() == null) {

            throw new EmptyMemberException();
        }

        if (passwordEncoder.matches(member.getPassword(), findMember.getPassword())) {

            return findMember;
        }

        throw new EmptyMemberException();
    }
}