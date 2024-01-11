package com.flab.funding.application.ports.input;

import com.flab.funding.domain.model.Member;

public interface UpdateMemberUseCase {
    Member updateMember(Member member);
}
