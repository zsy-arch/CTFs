package com.vsf2f.f2f.ui.utils;

import java.io.UnsupportedEncodingException;

/* loaded from: classes2.dex */
public class Base64Helper {
    private static final String BASE64_CODE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private static final int[] BASE64_DECODE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    private static byte[] zeroPad(int length, byte[] bytes) {
        byte[] padded = new byte[length];
        System.arraycopy(bytes, 0, padded, 0, bytes.length);
        return padded;
    }

    public static synchronized String encode(byte[] buff) {
        String sb;
        synchronized (Base64Helper.class) {
            if (buff == null) {
                sb = null;
            } else {
                StringBuilder strBuilder = new StringBuilder("");
                int paddingCount = (3 - (buff.length % 3)) % 3;
                byte[] stringArray = zeroPad(buff.length + paddingCount, buff);
                for (int i = 0; i < stringArray.length; i += 3) {
                    int j = ((stringArray[i] & 255) << 16) + ((stringArray[i + 1] & 255) << 8) + (stringArray[i + 2] & 255);
                    strBuilder.append(BASE64_CODE.charAt((j >> 18) & 63));
                    strBuilder.append(BASE64_CODE.charAt((j >> 12) & 63));
                    strBuilder.append(BASE64_CODE.charAt((j >> 6) & 63));
                    strBuilder.append(BASE64_CODE.charAt(j & 63));
                }
                int intPos = strBuilder.length();
                for (int i2 = paddingCount; i2 > 0; i2--) {
                    strBuilder.setCharAt(intPos - i2, '=');
                }
                sb = strBuilder.toString();
            }
        }
        return sb;
    }

    public static synchronized String encode(String string, String encoding) throws UnsupportedEncodingException {
        String str;
        synchronized (Base64Helper.class) {
            if (string == null || encoding == null) {
                str = null;
            } else {
                str = encode(string.getBytes(encoding));
            }
        }
        return str;
    }

    public static synchronized String decode(String string, String encoding) throws UnsupportedEncodingException {
        String str;
        Throwable th;
        int decodeLen;
        synchronized (Base64Helper.class) {
            if (string == null || encoding == null) {
                str = null;
            } else {
                try {
                    if (string.endsWith("==")) {
                        decodeLen = string.length() - 2;
                    } else {
                        decodeLen = string.endsWith("=") ? string.length() - 1 : string.length();
                    }
                    byte[] buff = new byte[(decodeLen * 3) / 4];
                    int count4 = decodeLen - (decodeLen % 4);
                    int i = 0;
                    int posIndex = 0;
                    while (i < count4) {
                        try {
                            int c0 = BASE64_DECODE[string.charAt(i)];
                            int c1 = BASE64_DECODE[string.charAt(i + 1)];
                            int c2 = BASE64_DECODE[string.charAt(i + 2)];
                            int c3 = BASE64_DECODE[string.charAt(i + 3)];
                            int posIndex2 = posIndex + 1;
                            buff[posIndex] = (byte) (((c0 << 2) | (c1 >> 4)) & 255);
                            int posIndex3 = posIndex2 + 1;
                            buff[posIndex2] = (byte) ((((c1 & 15) << 4) | (c2 >> 2)) & 255);
                            int posIndex4 = posIndex3 + 1;
                            buff[posIndex3] = (byte) ((((c2 & 3) << 6) | c3) & 255);
                            i += 4;
                            posIndex = posIndex4;
                        } catch (Throwable th2) {
                            th = th2;
                            throw th;
                        }
                    }
                    if (2 <= decodeLen % 4) {
                        int c02 = BASE64_DECODE[string.charAt(count4)];
                        int c12 = BASE64_DECODE[string.charAt(count4 + 1)];
                        int posIndex5 = posIndex + 1;
                        buff[posIndex] = (byte) (((c02 << 2) | (c12 >> 4)) & 255);
                        if (3 == decodeLen % 4) {
                            posIndex = posIndex5 + 1;
                            buff[posIndex5] = (byte) ((((c12 & 15) << 4) | (BASE64_DECODE[string.charAt(count4 + 2)] >> 2)) & 255);
                        }
                        str = new String(buff, encoding);
                    }
                    str = new String(buff, encoding);
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            return str;
        }
    }
}
