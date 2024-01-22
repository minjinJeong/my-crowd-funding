package com.flab.funding.infrastructure.adapters.output.persistence.converter;

import com.flab.funding.domain.model.MemberGender;
import com.flab.funding.domain.model.MemberStatus;
import jakarta.persistence.AttributeConverter;

public class MemberStatusAttributeConverter implements AttributeConverter<MemberStatus, String> {
    @Override
    public String convertToDatabaseColumn(MemberStatus attribute) {
        return attribute.getStatus();
    }

    @Override
    public MemberStatus convertToEntityAttribute(String dbData) {
        return MemberStatus.valueOf(dbData);
    }
}