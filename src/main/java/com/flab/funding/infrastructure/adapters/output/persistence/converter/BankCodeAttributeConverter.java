package com.flab.funding.infrastructure.adapters.output.persistence.converter;

import com.flab.funding.domain.model.BankCode;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BankCodeAttributeConverter implements AttributeConverter<BankCode, String> {
    @Override
    public String convertToDatabaseColumn(BankCode attribute) {
        return attribute.getCode();
    }

    @Override
    public BankCode convertToEntityAttribute(String dbData) {
        return BankCode.valueOfLabel(dbData);
    }
}
