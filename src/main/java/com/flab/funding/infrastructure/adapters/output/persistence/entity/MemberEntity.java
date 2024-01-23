package com.flab.funding.infrastructure.adapters.output.persistence.entity;

import com.flab.funding.domain.model.MemberGender;
import com.flab.funding.domain.model.MemberStatus;
import com.flab.funding.infrastructure.adapters.output.persistence.converter.GenderAttributeConverter;
import com.flab.funding.infrastructure.adapters.output.persistence.converter.MemberStatusAttributeConverter;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "user")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_key")
    private String userKey;
    @Convert(converter = MemberStatusAttributeConverter.class)
    @Column(name = "status_code")
    private MemberStatus statusCode;
    @Column(name = "link_type")
    private String linkType;
    @Column(name = "email")
    private String email;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "phone_num")
    private String phoneNum;
    @Convert(converter = GenderAttributeConverter.class)
    @Column(name = "gender")
    private MemberGender gender;
    @Column(name = "birthday")
    private LocalDate birthDay;
    @Column(name = "password")
    private String password;
    @Column(name = "last_login_at")
    private Timestamp lastLoginAt;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
