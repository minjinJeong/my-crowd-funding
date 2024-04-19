package com.flab.funding.domain.exception;

public class FundingException extends RuntimeException {

    private String code;

    private String message;

    public FundingException(String message, String code) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
