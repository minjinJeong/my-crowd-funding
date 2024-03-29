package com.flab.funding.application.ports.output;

import com.flab.funding.domain.model.Member;

import java.util.List;

public interface MemberPort {

    Member saveMember(Member member);

    Member getMemberByUserKey(String userKey);

    List<Member> getMemberByEmail(String email);

}
