package com.flab.funding.domain.exception;

public class MemberException extends RuntimeException {
    private String code;
    private String message;

    public MemberException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
