package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.domain.model.BankCode;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.BankEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.MemberPaymentMethodEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// TODO : JPA 연동 후 삭제
public class MemoryMemberPaymentMethodRepository implements MemberPaymentMethodRepository {
    private static final Map<Long, MemberPaymentMethodEntity> paymentMethodMap = new HashMap<>();
    private static Long id = 1L;

    @Override
    public MemberPaymentMethodEntity save(MemberPaymentMethodEntity paymentMethodEntity) {
        MemberPaymentMethodEntity paymentMethod = BankEntity.builder()
                .id(id)
                .paymentMethodKey(id.toString())
                .member(paymentMethodEntity.getMember())
                .isDefault(paymentMethodEntity.isDefault())
                .paymentNum(paymentMethodEntity.getPaymentNum())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .bankCode(BankCode.KOOKMIN)
                .accountBirthday(LocalDate.of(1999, 1, 30))
                .accountHolder("홍길동")
                .build();

        paymentMethodMap.put(id++, paymentMethod);
        return paymentMethod;
    }
}
