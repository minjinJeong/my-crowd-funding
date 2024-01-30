package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.Member;
import com.flab.funding.infrastructure.adapters.input.data.request.MemberCreateRequest;
import com.flab.funding.infrastructure.adapters.input.data.response.MemberCreateResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-30T22:22:52+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member toMember(MemberCreateRequest memberCreateRequest) {
        if ( memberCreateRequest == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.linkType( memberCreateRequest.getLinkType() );
        member.email( memberCreateRequest.getEmail() );
        member.userName( memberCreateRequest.getUserName() );
        member.nickName( memberCreateRequest.getNickName() );
        member.phoneNum( memberCreateRequest.getPhoneNum() );
        member.gender( memberCreateRequest.getGender() );
        member.birthday( memberCreateRequest.getBirthday() );
        member.password( memberCreateRequest.getPassword() );

        return member.build();
    }

    @Override
    public MemberCreateResponse toMemberCreateResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberCreateResponse.MemberCreateResponseBuilder memberCreateResponse = MemberCreateResponse.builder();

        memberCreateResponse.userKey( member.getUserKey() );
        memberCreateResponse.statusCode( member.getStatusCode() );

        return memberCreateResponse.build();
    }
}
