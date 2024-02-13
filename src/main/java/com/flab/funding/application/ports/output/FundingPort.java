package com.flab.funding.application.ports.output;

import com.flab.funding.domain.model.Funding;

public interface FundingPort {
    Funding getFundingByFundingId(String fundingId);
    Funding saveFunding(Funding funding);
}

