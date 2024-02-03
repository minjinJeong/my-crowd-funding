package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;

import java.util.Optional;

public interface MemberRepository {
    MemberEntity save(MemberEntity member);

    MemberEntity update(MemberEntity member);

    Optional<MemberEntity> findByUserKey(String userKey);
}
