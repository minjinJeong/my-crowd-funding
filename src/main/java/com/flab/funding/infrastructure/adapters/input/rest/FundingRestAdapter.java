package com.flab.funding.infrastructure.adapters.input.rest;

import com.flab.funding.application.ports.input.CancelFundingUseCase;
import com.flab.funding.application.ports.input.RegisterFundingUseCase;
import com.flab.funding.application.ports.input.ReviewFundingUseCase;
import com.flab.funding.domain.model.Funding;
import com.flab.funding.domain.model.FundingCreator;
import com.flab.funding.domain.model.FundingItem;
import com.flab.funding.domain.model.FundingReward;
import com.flab.funding.infrastructure.adapters.input.data.request.FundingCreatorRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.FundingItemRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.FundingRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.FundingRewardRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.*;
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

    @PostMapping("/funding/creators")
    @ResponseBody
    public FundingCreatorRegisterResponse saveFundingCreators(@RequestBody FundingCreatorRegisterRequest request) {
        FundingCreator fundingCreator = registerFundingUseCase.registFundingCreator(request.toFundingCreator());
        return FundingCreatorRegisterResponse.from(fundingCreator);
    }

    @PostMapping("/funding/items")
    @ResponseBody
    public FundingItemRegisterResponse saveFundingItems(@RequestBody FundingItemRegisterRequest request) {
        FundingItem fundingItem = registerFundingUseCase.makeFundingItem(request.toFundingItem());
        return FundingItemRegisterResponse.from(fundingItem);
    }

    @PostMapping("/funding/rewards")
    @ResponseBody
    public FundingRewardRegisterResponse saveFundingRewards(@RequestBody FundingRewardRegisterRequest request) {
        FundingReward fundingReward = registerFundingUseCase.makeFundingReward(request.toFundingReward());
        return FundingRewardRegisterResponse.from(fundingReward);
    }

    @PatchMapping("/funding/{fundingKey}/wait")
    @ResponseBody
    public FundingInfoResponse waitForFundingReview(@PathVariable("fundingKey") String fundingId) {
        Funding funding = reviewFundingUseCase.waitForFundingReview(fundingId);
        return FundingInfoResponse.from(funding);
    }

    @PatchMapping("/funding/{fundingKey}/complete")
    @ResponseBody
    public FundingInfoResponse completeFundingReview(@PathVariable("fundingKey") String fundingId) {
        Funding funding = reviewFundingUseCase.completeFundingReview(fundingId);
        return FundingInfoResponse.from(funding);
    }

    @PatchMapping("/funding/{fundingKey}/cancel")
    @ResponseBody
    public FundingInfoResponse cancelFunding(@PathVariable("fundingKey") String fundingId) {
        Funding funding = cancelFundingUseCase.cancelFunding(fundingId);
        return FundingInfoResponse.from(funding);
    }
}