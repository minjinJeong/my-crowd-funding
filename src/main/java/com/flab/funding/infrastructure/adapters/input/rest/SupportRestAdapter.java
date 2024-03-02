package com.flab.funding.infrastructure.adapters.input.rest;

import com.flab.funding.application.ports.input.RegisterSupportUseCase;
import com.flab.funding.application.ports.input.SupportDeliveryUseCase;
import com.flab.funding.application.ports.input.SupportPaymentUseCase;
import com.flab.funding.domain.model.Support;
import com.flab.funding.domain.model.SupportDelivery;
import com.flab.funding.infrastructure.adapters.input.data.request.SupportRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.SupportDeliveryInfoResponse;
import com.flab.funding.infrastructure.adapters.input.data.response.SupportRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SupportRestAdapter {

    private final RegisterSupportUseCase registerSupportUseCase;
    private final SupportDeliveryUseCase supportDeliveryUseCase;
    private final SupportPaymentUseCase supportPaymentUseCase;

    @PostMapping("/supports")
    @ResponseBody
    public SupportRegisterResponse createSupport(@RequestBody SupportRegisterRequest request) {
        Support support = registerSupportUseCase.registSupport(request.toSupport());
        return SupportRegisterResponse.from(support);
    }

    @PatchMapping("/supports/{supportKey}/shipped-out")
    public SupportDeliveryInfoResponse shippedOut(@PathVariable("supportKey") String supportKey) {
        SupportDelivery supportDelivery = supportDeliveryUseCase.shippedOut(supportKey);
        return SupportDeliveryInfoResponse.from(supportDelivery);
    }
}