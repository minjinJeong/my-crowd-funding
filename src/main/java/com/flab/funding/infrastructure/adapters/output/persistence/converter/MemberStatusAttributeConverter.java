package com.flab.funding.infrastructure.adapters.output.persistence.converter;

import com.flab.funding.domain.model.MemberStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MemberStatusAttributeConverter implements AttributeConverter<MemberStatus, String> {

    @Override
    public String convertToDatabaseColumn(MemberStatus attribute) {
        return attribute.getCode();
    }

    @Override
    public MemberStatus convertToEntityAttribute(String dbData) {
        return MemberStatus.valueOfCode(dbData);
    }
}
