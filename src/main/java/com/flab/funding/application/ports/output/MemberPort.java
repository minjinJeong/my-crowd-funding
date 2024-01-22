package com.flab.funding.application.ports.output;

import com.flab.funding.domain.model.Member;

import java.math.BigInteger;
import java.util.Optional;

public interface MemberPort {
    Optional<Member> getMemberByUserKey(String userKey) throws Exception;
    Member saveMember(Member member) throws Exception;
    Member modifyMember(Member member) throws Exception;
    void deleteById(Member member) throws Exception;
}
