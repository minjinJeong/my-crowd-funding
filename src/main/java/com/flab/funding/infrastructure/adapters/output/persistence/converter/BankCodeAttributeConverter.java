package com.flab.funding.infrastructure.adapters.output.persistence.converter;

import com.flab.funding.domain.model.BankCode;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BankCodeAttributeConverter implements AttributeConverter<BankCode, String> {

    @Override
    public String convertToDatabaseColumn(BankCode attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public BankCode convertToEntityAttribute(String dbData) {
        return dbData != null ? BankCode.valueOfCode(dbData) : null;
    }
}
