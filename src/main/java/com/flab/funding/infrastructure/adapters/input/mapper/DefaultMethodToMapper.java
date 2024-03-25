package com.flab.funding.infrastructure.adapters.input.mapper;

import com.flab.funding.domain.model.Funding;
import com.flab.funding.domain.model.FundingReward;
import com.flab.funding.domain.model.Member;

public interface DefaultMethodToMapper {

    default Member toMember(String userKey) {
        if (userKey == null) {
            return null;
        }

        return Member.builder().userKey(userKey).build();
    }

    default Funding toFunding(String fundingKey) {
        if (fundingKey == null) {
            return null;
        }

        return Funding.builder().fundingKey(fundingKey).build();
    }

    default FundingReward toReward(Long rewardId) {
        if (rewardId == null) {
            return null;
        }

        return FundingReward.builder().id(rewardId).build();
    }
}
