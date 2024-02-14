package com.flab.funding.infrastructure.adapters.input.rest;

import com.flab.funding.application.ports.input.CancelFundingUseCase;
import com.flab.funding.application.ports.input.RegisterFundingUseCase;
import com.flab.funding.application.ports.input.ReviewFundingUseCase;
import com.flab.funding.domain.model.Funding;
import com.flab.funding.infrastructure.adapters.input.data.request.FundingRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.FundingInfoResponse;
import com.flab.funding.infrastructure.adapters.input.data.response.FundingRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FundingRestAdapter {

    private final RegisterFundingUseCase registerFundingUseCase;
    private final ReviewFundingUseCase reviewFundingUseCase;
    private final CancelFundingUseCase cancelFundingUseCase;

    @PostMapping("/funding")
    @ResponseBody
    public FundingRegisterResponse saveFunding(@RequestBody FundingRegisterRequest request) {
        Funding funding = registerFundingUseCase.registFunding(request.toFunding());
        return FundingRegisterResponse.from(funding);
    }

    @PutMapping("/funding/{fundingId}/wait")
    @ResponseBody
    public FundingInfoResponse waitForFundingReview(@PathVariable("fundingId") String fundingId) {
        Funding funding = reviewFundingUseCase.waitForFundingReview(fundingId);
        return FundingInfoResponse.from(funding);
    }

    @PutMapping("/funding/{fundingId}/complete")
    @ResponseBody
    public FundingInfoResponse completeFundingReview(@PathVariable("fundingId") String fundingId) {
        Funding funding = reviewFundingUseCase.completeFundingReview(fundingId);
        return FundingInfoResponse.from(funding);
    }

    @PutMapping("/funding/{fundingId}/cancel")
    @ResponseBody
    public FundingInfoResponse cancelFunding(@PathVariable("fundingId") String fundingId) {
        Funding funding = cancelFundingUseCase.cancelFunding(fundingId);
        return FundingInfoResponse.from(funding);
    }
}