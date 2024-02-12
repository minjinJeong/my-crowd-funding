package com.flab.funding.infrastructure.adapters.output.persistence.converter;

import com.flab.funding.domain.model.FundingStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class FundingStatusAttributeConverter implements AttributeConverter<FundingStatus, String> {
    @Override
    public String convertToDatabaseColumn(FundingStatus attribute) {
        return attribute.getStatusCode();
    }

    @Override
    public FundingStatus convertToEntityAttribute(String dbData) {
        return FundingStatus.valueOf(dbData);
    }
}
