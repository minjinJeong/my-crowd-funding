package com.flab.funding.infrastructure.adapters.output.persistence.converter;

import com.flab.funding.domain.model.FundingCategory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class FundingCategoryAttributeConverter implements AttributeConverter<FundingCategory, String> {
    @Override
    public String convertToDatabaseColumn(FundingCategory attribute) {
        return attribute.getCode();
    }

    @Override
    public FundingCategory convertToEntityAttribute(String dbData) {
        return FundingCategory.valueOf(dbData);
    }
}
