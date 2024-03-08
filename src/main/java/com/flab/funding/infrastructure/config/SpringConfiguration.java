package com.flab.funding.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.MemberDeliveryAddressRepository;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.MemberPaymentMethodRepository;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.MemoryMemberDeliveryAddressRepository;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.MemoryMemberPaymentMethodRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@ComponentScan(
        basePackages = "com.flab.funding",
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = UseCase.class)
)
@EnableJpaAuditing
public class SpringConfiguration {

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }

    // TODO : JPA 연동 후 MemoryXXXRepository 삭제
    @Bean
    MemberDeliveryAddressRepository memberDeliveryAddressRepository() {
        return new MemoryMemberDeliveryAddressRepository();
    }
    @Bean
    MemberPaymentMethodRepository memberPaymentMethodRepository() {
        return new MemoryMemberPaymentMethodRepository();
    }
}
