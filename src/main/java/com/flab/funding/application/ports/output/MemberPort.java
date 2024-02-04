package com.flab.funding.application.ports.output;

import com.flab.funding.domain.model.Member;

import java.math.BigInteger;
import java.util.Optional;

public interface MemberPort {
    Member getMemberByUserKey(String userKey);
    Member saveMember(Member member);
    Member modifyMember(Member member);
    void deleteById(Member member);
}
