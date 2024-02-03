package com.flab.funding.infrastructure.adapters.input.rest;

import com.flab.funding.application.ports.input.DeregisterMemberUseCase;
import com.flab.funding.application.ports.input.RegisterMemberUseCase;
import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberCreateRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberCreateResponse;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberRestAdapter {

    private final RegisterMemberUseCase registerMemberUseCase;
    private final DeregisterMemberUseCase deregisterMemberUseCase;

    @PostMapping("/member")
    @ResponseBody
    public MemberCreateResponse createMember(@RequestBody MemberCreateRequest request) {
        Member member = registerMemberUseCase.registMember(request.toMember());
        return MemberCreateResponse.from(member);
    }

    @DeleteMapping("/member")
    @ResponseBody
    public MemberResponse deleteMember(@RequestBody MemberRequest request) {
        Member member = deregisterMemberUseCase.deregistMember(request.toMember());
        return MemberResponse.from(member);
    }
}