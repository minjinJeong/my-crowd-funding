package com.flab.funding.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import java.time.LocalDate;

@Configuration
@ComponentScan(
        basePackages = "com.flab.funding",
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = UseCase.class)
)
public class SpringConfiguration {

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }

    // TODO : JPA 연동 후 MemoryMemberRepository 삭제
    @Bean
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    MemberDeliveryAddressRepository memberDeliveryAddressRepository() {
        return new MemoryMemberDeliveryAddressRepository();
    }
    @Bean
    MemberPaymentMethodRepository memberPaymentMethodRepository() {
        return new MemoryMemberPaymentMethodRepository();
    }
}
