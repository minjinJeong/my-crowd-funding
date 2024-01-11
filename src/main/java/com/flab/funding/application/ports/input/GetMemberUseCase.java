package com.flab.funding.application.ports.input;

import com.flab.funding.domain.model.Member;

import java.util.Optional;

public interface GetMemberUseCase {
    Optional<Member> getMemberByKey(String userKey);
}
