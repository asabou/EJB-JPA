package com.tpjad.ejbjpa.groceries.utils;

public class ServiceUtils {
    public static boolean isStringNullOrEmpty(final String string) {
        return isObjectNull(string) || string.equals(Constants.EMPTY_STRING);
    }

    public static boolean isObjectNull(Object obj) {
        return obj == null;
    }

    public static Long convertStringToLong(final String string) { return Long.parseLong(string); }
}
