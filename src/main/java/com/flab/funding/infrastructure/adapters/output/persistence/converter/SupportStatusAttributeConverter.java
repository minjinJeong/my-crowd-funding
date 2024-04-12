package com.flab.funding.infrastructure.adapters.output.persistence.converter;

import com.flab.funding.domain.model.SupportStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SupportStatusAttributeConverter implements AttributeConverter<SupportStatus, String> {

    @Override
    public String convertToDatabaseColumn(SupportStatus attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public SupportStatus convertToEntityAttribute(String dbData) {
        return dbData != null ? SupportStatus.valueOfCode(dbData) : null;
    }
}
