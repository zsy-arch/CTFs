package com.ta.utdid2.android.utils;

/* loaded from: classes2.dex */
public class IntUtils {
    public static byte[] getBytes(int i) {
        byte[] bInt = {(byte) ((value >> 8) % 256), (byte) (value % 256), (byte) (value % 256), (byte) (i % 256)};
        int value = i >> 8;
        int value2 = value >> 8;
        return bInt;
    }

    public static byte[] getBytes(byte[] buffer, int i) {
        if (buffer.length != 4) {
            return null;
        }
        buffer[3] = (byte) (i % 256);
        int value = i >> 8;
        buffer[2] = (byte) (value % 256);
        int value2 = value >> 8;
        buffer[1] = (byte) (value2 % 256);
        buffer[0] = (byte) ((value2 >> 8) % 256);
        return buffer;
    }
}
