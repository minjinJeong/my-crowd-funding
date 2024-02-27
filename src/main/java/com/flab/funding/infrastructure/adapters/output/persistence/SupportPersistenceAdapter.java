package com.flab.funding.infrastructure.adapters.output.persistence;

import com.flab.funding.application.ports.output.SupportPort;
import com.flab.funding.domain.model.Support;
import com.flab.funding.infrastructure.adapters.output.persistence.entity.SupportEntity;
import com.flab.funding.infrastructure.adapters.output.persistence.repository.SupportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupportPersistenceAdapter implements SupportPort {

    private final SupportRepository supportRepository;

    @Override
    public Support saveSupport(Support support) {
        SupportEntity supportEntity = SupportEntity.from(support);
        SupportEntity savedSupport = supportRepository.save(supportEntity);
        return savedSupport.toSupport();
    }
}
