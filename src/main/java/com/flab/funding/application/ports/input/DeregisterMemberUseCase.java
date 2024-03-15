package com.flab.funding.application.ports.input;

import com.flab.funding.domain.model.Member;

public interface DeregisterMemberUseCase {

    Member deregisterMember(String userKey);
}
