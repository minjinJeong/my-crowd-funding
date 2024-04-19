package com.flab.funding.infrastructure.adapters.output.persistence.converter;

import com.flab.funding.domain.model.MemberLinkType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MemberLinkTypeAttributeConverter implements AttributeConverter<MemberLinkType, String> {

    @Override
    public String convertToDatabaseColumn(MemberLinkType attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public MemberLinkType convertToEntityAttribute(String dbData) {
        return dbData != null ? MemberLinkType.valueOfCode(dbData) : null;
    }
}
