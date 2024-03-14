package com.flab.funding.infrastructure.adapters.output.persistence.converter;

import com.flab.funding.domain.model.SupportPaymentStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SupportPaymentStatusAttributeConverter implements AttributeConverter<SupportPaymentStatus, String> {

    @Override
    public String convertToDatabaseColumn(SupportPaymentStatus attribute) {
        return attribute.getCode();
    }

    @Override
    public SupportPaymentStatus convertToEntityAttribute(String dbData) {
        return SupportPaymentStatus.valueOfCode(dbData);
    }
}
