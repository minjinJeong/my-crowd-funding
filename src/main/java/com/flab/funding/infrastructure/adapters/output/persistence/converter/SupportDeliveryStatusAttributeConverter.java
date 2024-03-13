package com.flab.funding.infrastructure.adapters.output.persistence.converter;

import com.flab.funding.domain.model.SupportDeliveryStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SupportDeliveryStatusAttributeConverter implements AttributeConverter<SupportDeliveryStatus, String> {
    @Override
    public String convertToDatabaseColumn(SupportDeliveryStatus attribute) {
        return attribute.getCode();
    }

    @Override
    public SupportDeliveryStatus convertToEntityAttribute(String dbData) {
        return SupportDeliveryStatus.valueOfCode(dbData);
    }
}
