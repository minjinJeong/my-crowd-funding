package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, MemberEntity> store = new HashMap<>();
    private static Long id = 1L;

    @Override
    public MemberEntity save(MemberEntity member) {
        store.put(id++, member);
        return member;
    }

    @Override
    public Optional<MemberEntity> findByUserKey(String userKey) {
        return Optional.empty();
    }
}
