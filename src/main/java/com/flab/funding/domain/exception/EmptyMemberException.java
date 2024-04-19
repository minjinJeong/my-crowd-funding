package com.flab.funding.domain.exception;

public class EmptyMemberException extends MemberException {

    public EmptyMemberException() {
        super("404", "FUNDING_NOT_EXIST");
    }
}
