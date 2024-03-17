package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberInfoRequest;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberRegisterRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberInfoResponse;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberRegisterResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-17T20:12:32+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member toMember(MemberRegisterRequest memberRegisterRequest) {
        if ( memberRegisterRequest == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.linkType( memberRegisterRequest.getLinkType() );
        member.email( memberRegisterRequest.getEmail() );
        member.userName( memberRegisterRequest.getUserName() );
        member.nickName( memberRegisterRequest.getNickName() );
        member.phoneNumber( memberRegisterRequest.getPhoneNumber() );
        member.gender( memberRegisterRequest.getGender() );
        member.birthday( memberRegisterRequest.getBirthday() );
        member.password( memberRegisterRequest.getPassword() );

        return member.build();
    }

    @Override
    public MemberRegisterResponse toMemberRegisterResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberRegisterResponse.MemberRegisterResponseBuilder memberRegisterResponse = MemberRegisterResponse.builder();

        memberRegisterResponse.userKey( member.getUserKey() );
        memberRegisterResponse.status( member.getStatus() );

        return memberRegisterResponse.build();
    }

    @Override
    public Member toMember(MemberInfoRequest memberInfoRequest) {
        if ( memberInfoRequest == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.userKey( memberInfoRequest.getUserKey() );

        return member.build();
    }

    @Override
    public MemberInfoResponse toMemberInfoResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberInfoResponse.MemberInfoResponseBuilder memberInfoResponse = MemberInfoResponse.builder();

        memberInfoResponse.userKey( member.getUserKey() );
        memberInfoResponse.status( member.getStatus() );
        memberInfoResponse.email( member.getEmail() );
        memberInfoResponse.nickName( member.getNickName() );
        memberInfoResponse.phoneNumber( member.getPhoneNumber() );
        memberInfoResponse.linkType( member.getLinkType() );
        memberInfoResponse.lastLoginAt( member.getLastLoginAt() );

        return memberInfoResponse.build();
    }
}
