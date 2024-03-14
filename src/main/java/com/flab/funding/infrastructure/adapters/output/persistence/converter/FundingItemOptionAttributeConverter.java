package com.flab.funding.infrastructure.adapters.output.persistence.converter;

import com.flab.funding.domain.model.FundingItemOptionType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class FundingItemOptionAttributeConverter implements AttributeConverter<FundingItemOptionType, String> {

    @Override
    public String convertToDatabaseColumn(FundingItemOptionType attribute) {
        return attribute.getCode();
    }

    @Override
    public FundingItemOptionType convertToEntityAttribute(String dbData) {
        return FundingItemOptionType.valueOfCode(dbData);
    }
}
