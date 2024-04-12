package com.flab.funding.infrastructure.adapters.output.persistence.converter;

import com.flab.funding.domain.model.FundingStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class FundingStatusAttributeConverter implements AttributeConverter<FundingStatus, String> {

    @Override
    public String convertToDatabaseColumn(FundingStatus attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public FundingStatus convertToEntityAttribute(String dbData) {
        return dbData != null ? FundingStatus.valueOfCode(dbData) : null;
    }
}
