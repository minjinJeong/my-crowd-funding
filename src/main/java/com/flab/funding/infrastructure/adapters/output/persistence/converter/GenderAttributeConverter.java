package com.flab.funding.infrastructure.adapters.output.persistence.converter;

import com.flab.funding.domain.model.MemberGender;
import jakarta.persistence.AttributeConverter;

public class GenderAttributeConverter implements AttributeConverter<MemberGender, String> {
    @Override
    public String convertToDatabaseColumn(MemberGender attribute) {
        return attribute.getGender();
    }

    @Override
    public MemberGender convertToEntityAttribute(String dbData) {
        return MemberGender.valueOf(dbData);
    }
}
