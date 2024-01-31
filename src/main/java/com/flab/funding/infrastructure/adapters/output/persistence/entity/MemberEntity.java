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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@Getter
@Table(name = "user")
public class MemberEntity {

    private static MemberPersistenceMapper mapper;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String userKey;
    @Convert(converter = MemberStatusAttributeConverter.class)
    private MemberStatus statusCode;
    @Convert(converter = MemberLinkTypeAttributeConverter.class)
    private MemberLinkType linkType;
    private String email;
    private String userName;
    private String nickName;
    private String phoneNum;
    @Convert(converter = GenderAttributeConverter.class)
    private MemberGender gender;
    private LocalDate birthday;
    private String password;
    private LocalDateTime lastLoginAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private MemberEntity() {
        mapper = Mappers.getMapper(MemberPersistenceMapper.class);
    }

    public static MemberEntity from(Member member) {
        return mapper.toMemberEntity(member);
    }

    public Member toMember() {
        return mapper.toMember(this);
    }

}
