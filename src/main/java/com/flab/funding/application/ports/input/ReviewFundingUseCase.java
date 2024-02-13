package com.flab.funding.application.ports.input;

import com.flab.funding.domain.model.Funding;

public interface ReviewFundingUseCase {
    Funding waitForFundingReview(String fundingId);
    Funding completeFundingReview(String fundingId);
}
