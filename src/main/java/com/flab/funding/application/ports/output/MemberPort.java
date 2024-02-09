package com.flab.funding.application.ports.output;

import com.flab.funding.domain.model.DeliveryAddress;
import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.PaymentMethod;

import java.math.BigInteger;
import java.util.Optional;

public interface MemberPort {
    Member getMemberByUserKey(String userKey);
    Member saveMember(Member member);
}
