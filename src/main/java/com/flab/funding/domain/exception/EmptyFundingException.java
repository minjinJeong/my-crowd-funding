package com.flab.funding.domain.exception;

public class EmptyFundingException extends FundingException {

    public EmptyFundingException() {
        super("404", "FUNDING_NOT_EXIST");
    }
}
