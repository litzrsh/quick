package me.litzrsh.thirdparty.jpa.converters;

import jakarta.persistence.AttributeConverter;

public class BooleanToStringAttributeConverter implements AttributeConverter<Boolean, String> {

    public static final String TRUE = "Y";
    public static final String FALSE = "N";

    @Override
    public String convertToDatabaseColumn(Boolean value) {
        return value ? TRUE : FALSE;
    }

    @Override
    public Boolean convertToEntityAttribute(String str) {
        return TRUE.equalsIgnoreCase(str);
    }
}
