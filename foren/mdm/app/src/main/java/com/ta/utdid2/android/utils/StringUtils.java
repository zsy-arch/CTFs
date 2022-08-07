package com.ta.utdid2.android.utils;

/* loaded from: classes2.dex */
public class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.length() <= 0;
    }

    public static String convertObjectToString(Object o) {
        if (o == null) {
            return "";
        }
        if (o instanceof String) {
            return ((String) o).toString();
        }
        if (o instanceof Integer) {
            return new StringBuilder().append(((Integer) o).intValue()).toString();
        }
        if (o instanceof Long) {
            return new StringBuilder().append(((Long) o).longValue()).toString();
        }
        if (o instanceof Double) {
            return new StringBuilder().append(((Double) o).doubleValue()).toString();
        }
        if (o instanceof Float) {
            return new StringBuilder().append(((Float) o).floatValue()).toString();
        }
        if (o instanceof Short) {
            return new StringBuilder().append((int) ((Short) o).shortValue()).toString();
        }
        if (o instanceof Byte) {
            return new StringBuilder().append((int) ((Byte) o).byteValue()).toString();
        }
        if (o instanceof Boolean) {
            return ((Boolean) o).toString();
        }
        if (o instanceof Character) {
            return ((Character) o).toString();
        }
        return o.toString();
    }

    public static int hashCode(String value) {
        int h = 0;
        if (0 == 0 && value.length() > 0) {
            for (char c : value.toCharArray()) {
                h = (h * 31) + c;
            }
        }
        return h;
    }
}
