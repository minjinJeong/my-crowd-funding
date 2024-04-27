package com.flab.funding.infrastructure.adapters.input.rest;

import com.flab.funding.application.ports.input.DeregisterMemberUseCase;
import com.flab.funding.application.ports.input.RegisterMemberUseCase;
import com.flab.funding.domain.model.Member;
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

    @PostMapping("/members")
    @ResponseBody
    public MemberRegisterResponse createMember(@RequestBody @Valid MemberRegisterRequest request) {
        Member member = registerMemberUseCase.registerMember(request.toMember());
        return MemberRegisterResponse.from(member);
    }

    @DeleteMapping("/members/{userKey}")
    @ResponseBody
    public MemberInfoResponse deleteMember(@PathVariable("userKey") String userKey) {
        Member member = deregisterMemberUseCase.deregisterMember(userKey);
        return MemberInfoResponse.from(member);
    }

    @GetMapping("/members/{userKey}")
    @ResponseBody
    public MemberInfoResponse getMember(@PathVariable("userKey") String userKey) {
        Member member = registerMemberUseCase.getMemberByUserKey(userKey);
        return MemberInfoResponse.from(member);
    }

    // TODO : 비밀번호 찾기 API 추가
}