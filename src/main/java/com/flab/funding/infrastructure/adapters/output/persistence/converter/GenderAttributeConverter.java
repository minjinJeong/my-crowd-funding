package com.flab.funding.infrastructure.adapters.output.persistence.converter;

import com.flab.funding.domain.model.MemberGender;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class GenderAttributeConverter implements AttributeConverter<MemberGender, String> {

    @Override
    public String convertToDatabaseColumn(MemberGender attribute) {
        return attribute != null ? attribute.getGender() : null;
    }

    @Override
    public MemberGender convertToEntityAttribute(String dbData) {
        return dbData != null ? MemberGender.valueOfGender(dbData) : null;
    }
}
