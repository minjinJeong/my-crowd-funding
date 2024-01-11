package com.flab.funding.infrastructure.adapters.input.rest;

import com.flab.funding.application.ports.input.CreateMemberUseCase;
import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberCreateRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberCreateResponse;
import com.flab.funding.infrastructure.adapters.input.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberRestAdapter {
    private final CreateMemberUseCase memberService;
    private final MemberMapper memberMapper;

    @Autowired
    public MemberRestAdapter(CreateMemberUseCase memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    @PostMapping("/member")
    ResponseEntity<MemberCreateResponse> createMember(@RequestBody MemberCreateRequest request) {
         Member member = memberService.createMember(memberMapper.toMember(request));
         return new ResponseEntity<>(memberMapper.toMemberCreateResponse(member), HttpStatus.CREATED);
    }
}