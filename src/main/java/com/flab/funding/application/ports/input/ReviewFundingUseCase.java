package com.flab.funding.application.ports.input;

import com.flab.funding.domain.model.Funding;

public interface ReviewFundingUseCase {
    Funding waitForFundingReview(String fundingKey);
    Funding completeFundingReview(String fundingKey);
}
