package me.litzrsh.commons.utils;

public abstract class StringUtils extends org.springframework.util.StringUtils {

    public static String trim(String value) {
        if (value == null) value = "";
        return value.trim();
    }
}
