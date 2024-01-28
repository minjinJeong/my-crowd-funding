package com.flab.funding.infrastructure.config;

import com.flab.funding.infrastructure.adapters.output.persistence.repository.MemberRepository;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.flab.funding",
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = UseCase.class)
)
public class SpringConfiguration {

    // TODO : JPA 연동 후 MemoryMemberRepository 삭제
    @Bean
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
