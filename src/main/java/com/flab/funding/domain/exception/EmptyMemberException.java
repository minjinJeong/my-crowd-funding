package com.flab.funding.domain.exception;

public class EmptyMemberException extends MemberException {

    public EmptyMemberException() {
        super("404", "MEMBER_NOT_EXIST");
    }
}
