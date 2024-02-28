package com.flab.funding.infrastructure.adapters.input.rest;

import com.flab.funding.application.ports.input.RegisterPaymentMethodUseCase;
import com.flab.funding.domain.model.PaymentMethod;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberPaymentMethodRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberPaymentMethodRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberPaymentMethodRestAdapter {
    private final RegisterPaymentMethodUseCase registerPaymentMethodUseCase;

    @PostMapping("/paymentMethods")
    @ResponseBody
    public MemberPaymentMethodRegisterResponse registMemberPaymentMethod(@RequestBody MemberPaymentMethodRegisterRequest request) {
        PaymentMethod paymentMethod = registerPaymentMethodUseCase.registPaymentMethod(request.toPaymentMethod());
        return MemberPaymentMethodRegisterResponse.from(paymentMethod);
    }
}
