package com.flab.funding.infrastructure.adapters.input.rest;

import com.flab.funding.application.ports.input.LoginUseCase;
import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.adapters.input.data.request.LoginRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.LoginResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginRestAdapter {

    private final LoginUseCase loginUseCase;

    @PostMapping("/accounts/authentication")
    @ResponseBody
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        Member member = loginUseCase.login(request.toMember());
        return LoginResponse.from(member);
    }
}
