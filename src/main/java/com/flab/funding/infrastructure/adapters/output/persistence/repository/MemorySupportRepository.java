package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.domain.model.SupportDeliveryStatus;
import com.flab.funding.domain.model.SupportPaymentStatus;
import com.flab.funding.domain.model.SupportStatus;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportDeliveryEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportPaymentEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MemorySupportRepository implements SupportRepository {

    private static final Map<Long, SupportEntity> store = new HashMap<>();
    private static final Map<Long, SupportDeliveryEntity> deliveries = new HashMap<>();
    private static final Map<Long, SupportPaymentEntity> payments = new HashMap<>();
    private static Long supportId = 1L;
    private static Long deliveryId = 1L;
    private static Long paymentId = 1L;

    @Override
    public SupportEntity save(SupportEntity support) {
        Long id = supportId++;

        SupportDeliveryEntity supportDeliveryEntity =
                savedSupportDeliveryEntity(id, support.getSupportDelivery());

        SupportPaymentEntity supportPaymentEntity =
                savedSupportPaymentEntity(id, support.getSupportPayment());

        SupportEntity savedSupport = SupportEntity.builder()
                .id(id)
                .member(support.getMember())
                .funding(support.getFunding())
                .reward(support.getReward())
                .supportKey(id.toString())
                .status(SupportStatus.RESERVATION)
                .supportDelivery(supportDeliveryEntity)
                .supportPayment(supportPaymentEntity)
                .createdAt(LocalDateTime.now())
                .createdBy("ADMIN")
                .updatedAt(LocalDateTime.now())
                .updatedBy("ADMIN")
                .build();

        store.put(id, savedSupport);
        return savedSupport;
    }

    private SupportDeliveryEntity savedSupportDeliveryEntity(Long supportId, SupportDeliveryEntity supportDelivery) {
        Long id = deliveryId++;

        SupportDeliveryEntity savedSupportDelivery = SupportDeliveryEntity.builder()
                .id(deliveryId)
                .support(SupportEntity.builder().id(supportId).build())
                .memberDeliveryAddress(supportDelivery.getMemberDeliveryAddress())
                .status(SupportDeliveryStatus.READY)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        deliveries.put(id, savedSupportDelivery);
        return savedSupportDelivery;
    }

    // TODO : 결제일 로직 변경
    private SupportPaymentEntity savedSupportPaymentEntity(Long supportId, SupportPaymentEntity supportPayment) {
        Long id = paymentId++;

        SupportPaymentEntity savedSupportPayment = SupportPaymentEntity.builder()
                .id(paymentId)
                .support(SupportEntity.builder().id(supportId).build())
                .memberPaymentMethod(supportPayment.getMemberPaymentMethod())
                .status(SupportPaymentStatus.READY)
                .amount(supportPayment.getAmount())
                .paymentAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        payments.put(id, savedSupportPayment);
        return savedSupportPayment;
    }
}
