package com.flab.funding.infrastructure.adapters.output.persistence.repository;

import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportEntity;

public interface SupportRepository {
    SupportEntity save(SupportEntity support);
}
