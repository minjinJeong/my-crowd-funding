package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.FundingEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// TODO : JPA 연동 후 삭제
@Repository
public class MemoryFundingRepository implements FundingRepository {

    private static final Map<Long, FundingEntity> store = new HashMap<>();
    private static Long id = 1L;

    // TODO : API 문서 작성을 위해 로직 수정 필요
    @Override
    public FundingEntity save(FundingEntity funding) {
        FundingEntity savedFunding = FundingEntity.builder()
                .id(id)
                .fundingKey(id.toString())
                .member(funding.getMember())
                .isAdult(funding.isAdult())
                .pricePlan(funding.getPricePlan())
                .categoryCode(funding.getCategoryCode())
                .expectAmount(funding.getExpectAmount())
                .status(funding.getStatus())
                .title(funding.getTitle())
                .fundingDesc(funding.getFundingDesc())
                .fundingIntroduce(funding.getFundingIntroduce())
                .budgetDesc(funding.getBudgetDesc())
                .scheduleDesc(funding.getScheduleDesc())
                .teamDesc(funding.getTeamDesc())
                .rewardDesc(funding.getRewardDesc())
                .startAt(funding.getStartAt())
                .endAt(funding.getEndAt())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        store.put(id++, savedFunding);
        return savedFunding;
    }

    @Override
    public Optional<FundingEntity> findByFundingId(String fundingId) {
        return Optional.ofNullable(store.get(Long.valueOf(fundingId)));
    }
}
