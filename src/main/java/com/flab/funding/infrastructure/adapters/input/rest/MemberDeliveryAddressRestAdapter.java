package com.flab.funding.infrastructure.adapters.input.rest;

import com.flab.funding.application.ports.input.RegisterDeliveryAddressUseCase;
import com.flab.funding.domain.model.MemberDeliveryAddress;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberDeliveryAddressRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberDeliveryAddressRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberDeliveryAddressRestAdapter {

    private final RegisterDeliveryAddressUseCase registerDeliveryAddressUseCase;

    @PostMapping("/members/deliveryAddresses")
    @ResponseBody
    public MemberDeliveryAddressRegisterResponse registerMemberDeliveryAddress(@RequestBody MemberDeliveryAddressRegisterRequest request) {
        MemberDeliveryAddress memberDeliveryAddress = registerDeliveryAddressUseCase.registerDeliveryAddress(request.toDeliveryAddress());
        return MemberDeliveryAddressRegisterResponse.from(memberDeliveryAddress);
    }

}
