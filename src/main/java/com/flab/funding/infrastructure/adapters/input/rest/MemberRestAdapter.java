package com.flab.funding.infrastructure.adapters.input.rest;

import com.flab.funding.application.ports.input.RegisterMemberUseCase;
import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberCreateRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberRestAdapter {
    private final RegisterMemberUseCase memberService;

    @PostMapping("/member")
    @ResponseBody
    MemberCreateResponse createMember(@RequestBody MemberCreateRequest request) {
        Member member = memberService.registMember(request.toMember());
        return MemberCreateResponse.from(member);
    }
}