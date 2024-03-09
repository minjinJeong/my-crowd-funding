package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// TODO : JPA 연동 후 삭제
public class MemoryMemberRepository implements MemberRepository {

    private static final Map<Long, MemberEntity> store = new HashMap<>();
    private static Long id = 1L;

    @Override
    public MemberEntity save(MemberEntity member) {
        MemberEntity savedMember = MemberEntity.builder()
                .id(id)
                .userKey(id.toString())
                .status(member.getStatus())
                .linkType(member.getLinkType())
                .email(member.getEmail())
                .userName(member.getUserName())
                .nickName(member.getNickName())
                .phoneNumber(member.getPhoneNumber())
                .gender(member.getGender())
                .birthday(member.getBirthday())
                .password(member.getPassword())
                .lastLoginAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        store.put(id++, savedMember);
        return savedMember;
    }

    @Override
    public Optional<MemberEntity> findByUserKey(String userKey) {
        return Optional.ofNullable(store.get(Long.valueOf(userKey)));
    }

    @Override
    public List<MemberEntity> findByEmail(String email) {
        return null;
    }
}
