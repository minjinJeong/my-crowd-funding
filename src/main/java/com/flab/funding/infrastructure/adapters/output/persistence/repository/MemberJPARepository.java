package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberJPARepository implements MemberRepository {
    private final EntityManager em;

    @Override
    public MemberEntity save(MemberEntity member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<MemberEntity> findByUserKey(String userKey) {
        List<MemberEntity> findMember = em.createQuery(
                        "select m from MemberEntity m" +
                                " where m.userKey = :userKey", MemberEntity.class
                )
                .setParameter("userKey", userKey)
                .getResultList();
        return findMember.stream().findAny();
    }
}
