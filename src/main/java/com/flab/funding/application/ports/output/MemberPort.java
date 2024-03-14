package com.flab.funding.application.ports.output;

import com.flab.funding.domain.model.DeliveryAddress;
import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.PaymentMethod;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface MemberPort {

    Member saveMember(Member member);

    Member getMemberByUserKey(String userKey);

    List<Member> getMemberByEmail(String email);

}
