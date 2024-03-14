package com.flab.funding.infrastructure.adapters.input.rest;

import com.flab.funding.application.ports.input.DeregisterMemberUseCase;
import com.flab.funding.application.ports.input.LoginUseCase;
import com.flab.funding.application.ports.input.RegisterMemberUseCase;
import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberInfoRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberInfoResponse;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberRegisterResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberRestAdapter {

    private final RegisterMemberUseCase registerMemberUseCase;
    private final DeregisterMemberUseCase deregisterMemberUseCase;
    private final LoginUseCase loginUseCase;

    @PostMapping("/members")
    @ResponseBody
    public MemberRegisterResponse createMember(@RequestBody @Valid MemberRegisterRequest request) {
        Member member = registerMemberUseCase.registerMember(request.toMember());
        return MemberRegisterResponse.from(member);
    }

    @DeleteMapping("/members")
    @ResponseBody
    public MemberInfoResponse deleteMember(@RequestBody MemberInfoRequest request) {
        Member member = deregisterMemberUseCase.deregisterMember(request.toMember());
        return MemberInfoResponse.from(member);
    }

    @GetMapping("/members/{userKey}")
    @ResponseBody
    public MemberInfoResponse getMember(@PathVariable("userKey") String userKey) {
        Member member = loginUseCase.getMemberByUserKey(userKey);
        return MemberInfoResponse.from(member);
    }
}