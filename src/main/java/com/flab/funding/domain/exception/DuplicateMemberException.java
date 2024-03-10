package com.flab.funding.domain.exception;

public class DuplicateMemberException extends MemberException {
    public DuplicateMemberException() {
        super("409", "Duplicate Member");
    }
}
