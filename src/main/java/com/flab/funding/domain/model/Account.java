package com.flab.funding.domain.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Account {

    private String email;

    private String password;

    public static Account from(Member member) {

        return Account.builder()
                .email(member.getEmail())
                .password(member.getPassword())
                .build();
    }
}
