package com.flab.funding.application.ports.input;

import com.flab.funding.domain.model.Funding;

public interface RegisterFundingUseCase {
    Funding registFunding(Funding funding);
}
