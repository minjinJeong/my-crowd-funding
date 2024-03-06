package com.flab.funding.infrastructure.adapters.output.persistence.entity;

import com.flab.funding.domain.model.Member;
import com.flab.funding.domain.model.MemberGender;
import com.flab.funding.domain.model.MemberLinkType;
import com.flab.funding.domain.model.MemberStatus;
import com.flab.funding.infrastructure.adapters.output.persistence.converter.GenderAttributeConverter;
import com.flab.funding.infrastructure.adapters.output.persistence.converter.MemberLinkTypeAttributeConverter;
import com.flab.funding.infrastructure.adapters.output.persistence.converter.MemberStatusAttributeConverter;
import com.flab.funding.infrastructure.adapters.output.persistence.mapper.MemberPersistenceMapper;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String userKey;

    @Convert(converter = MemberStatusAttributeConverter.class)
    @Column(name = "status_code")
    private MemberStatus status;

    @Convert(converter = MemberLinkTypeAttributeConverter.class)
    private MemberLinkType linkType;

    private String email;

    private String userName;

    private String nickName;

    private String phoneNumber;

    @Convert(converter = GenderAttributeConverter.class)
    private MemberGender gender;

    private LocalDate birthday;

    private String password;

    private LocalDateTime lastLoginAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static MemberEntity from(Member member) {
        return MemberPersistenceMapper.INSTANCE.toMemberEntity(member);
    }

    public Member toMember() {
        return MemberPersistenceMapper.INSTANCE.toMember(this);
    }

}
