package com.cdlinglu.utils.pay;

import com.parse.ParseException;
import u.aly.dc;

/* loaded from: classes.dex */
public final class Base64 {
    private static final int BASELENGTH = 128;
    private static final int EIGHTBIT = 8;
    private static final int FOURBYTE = 4;
    private static final int LOOKUPLENGTH = 64;
    private static final int SIGN = -128;
    private static final int SIXTEENBIT = 16;
    private static final int TWENTYFOURBITGROUP = 24;
    private static char PAD = '=';
    private static byte[] base64Alphabet = new byte[128];
    private static char[] lookUpBase64Alphabet = new char[64];

    static {
        for (int i = 0; i < 128; i++) {
            base64Alphabet[i] = -1;
        }
        for (int i2 = 90; i2 >= 65; i2--) {
            base64Alphabet[i2] = (byte) (i2 - 65);
        }
        for (int i3 = 122; i3 >= 97; i3--) {
            base64Alphabet[i3] = (byte) ((i3 - 97) + 26);
        }
        for (int i4 = 57; i4 >= 48; i4--) {
            base64Alphabet[i4] = (byte) ((i4 - 48) + 52);
        }
        base64Alphabet[43] = 62;
        base64Alphabet[47] = 63;
        for (int i5 = 0; i5 <= 25; i5++) {
            lookUpBase64Alphabet[i5] = (char) (i5 + 65);
        }
        int i6 = 26;
        int j = 0;
        while (i6 <= 51) {
            lookUpBase64Alphabet[i6] = (char) (j + 97);
            i6++;
            j++;
        }
        int i7 = 52;
        int j2 = 0;
        while (i7 <= 61) {
            lookUpBase64Alphabet[i7] = (char) (j2 + 48);
            i7++;
            j2++;
        }
        lookUpBase64Alphabet[62] = '+';
        lookUpBase64Alphabet[63] = '/';
    }

    private static boolean isWhiteSpace(char octect) {
        return octect == ' ' || octect == '\r' || octect == '\n' || octect == '\t';
    }

    private static boolean isPad(char octect) {
        return octect == PAD;
    }

    private static boolean isData(char octect) {
        return octect < 128 && base64Alphabet[octect] != -1;
    }

    public static String encode(byte[] binaryData) {
        if (binaryData == null) {
            return null;
        }
        int lengthDataBits = binaryData.length * 8;
        if (lengthDataBits == 0) {
            return "";
        }
        int fewerThan24bits = lengthDataBits % 24;
        int numberTriplets = lengthDataBits / 24;
        char[] encodedData = new char[(fewerThan24bits != 0 ? numberTriplets + 1 : numberTriplets) * 4];
        int i = 0;
        int dataIndex = 0;
        int encodedIndex = 0;
        while (i < numberTriplets) {
            int dataIndex2 = dataIndex + 1;
            byte b1 = binaryData[dataIndex];
            int dataIndex3 = dataIndex2 + 1;
            byte b2 = binaryData[dataIndex2];
            int dataIndex4 = dataIndex3 + 1;
            byte b3 = binaryData[dataIndex3];
            byte l = (byte) (b2 & dc.m);
            byte k = (byte) (b1 & 3);
            byte val1 = (b1 & Byte.MIN_VALUE) == 0 ? (byte) (b1 >> 2) : (byte) ((b1 >> 2) ^ 192);
            byte val2 = (b2 & Byte.MIN_VALUE) == 0 ? (byte) (b2 >> 4) : (byte) ((b2 >> 4) ^ 240);
            int i2 = (b3 & Byte.MIN_VALUE) == 0 ? b3 >> 6 : (b3 >> 6) ^ ParseException.UNSUPPORTED_SERVICE;
            int encodedIndex2 = encodedIndex + 1;
            encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
            int encodedIndex3 = encodedIndex2 + 1;
            encodedData[encodedIndex2] = lookUpBase64Alphabet[(k << 4) | val2];
            int encodedIndex4 = encodedIndex3 + 1;
            encodedData[encodedIndex3] = lookUpBase64Alphabet[(l << 2) | ((byte) i2)];
            encodedIndex = encodedIndex4 + 1;
            encodedData[encodedIndex4] = lookUpBase64Alphabet[b3 & 63];
            i++;
            dataIndex = dataIndex4;
        }
        if (fewerThan24bits == 8) {
            byte b12 = binaryData[dataIndex];
            byte k2 = (byte) (b12 & 3);
            int encodedIndex5 = encodedIndex + 1;
            encodedData[encodedIndex] = lookUpBase64Alphabet[(b12 & Byte.MIN_VALUE) == 0 ? (byte) (b12 >> 2) : (byte) ((b12 >> 2) ^ 192)];
            int encodedIndex6 = encodedIndex5 + 1;
            encodedData[encodedIndex5] = lookUpBase64Alphabet[k2 << 4];
            int encodedIndex7 = encodedIndex6 + 1;
            encodedData[encodedIndex6] = PAD;
            int i3 = encodedIndex7 + 1;
            encodedData[encodedIndex7] = PAD;
        } else if (fewerThan24bits == 16) {
            byte b13 = binaryData[dataIndex];
            byte b22 = binaryData[dataIndex + 1];
            byte l2 = (byte) (b22 & dc.m);
            byte k3 = (byte) (b13 & 3);
            byte val12 = (b13 & Byte.MIN_VALUE) == 0 ? (byte) (b13 >> 2) : (byte) ((b13 >> 2) ^ 192);
            byte val22 = (b22 & Byte.MIN_VALUE) == 0 ? (byte) (b22 >> 4) : (byte) ((b22 >> 4) ^ 240);
            int encodedIndex8 = encodedIndex + 1;
            encodedData[encodedIndex] = lookUpBase64Alphabet[val12];
            int encodedIndex9 = encodedIndex8 + 1;
            encodedData[encodedIndex8] = lookUpBase64Alphabet[(k3 << 4) | val22];
            int encodedIndex10 = encodedIndex9 + 1;
            encodedData[encodedIndex9] = lookUpBase64Alphabet[l2 << 2];
            encodedIndex = encodedIndex10 + 1;
            encodedData[encodedIndex10] = PAD;
        }
        return new String(encodedData);
    }

    public static byte[] decode(String encoded) {
        if (encoded == null) {
            return null;
        }
        char[] base64Data = encoded.toCharArray();
        int len = removeWhiteSpace(base64Data);
        if (len % 4 != 0) {
            return null;
        }
        int numberQuadruple = len / 4;
        if (numberQuadruple == 0) {
            return new byte[0];
        }
        int i = 0;
        int encodedIndex = 0;
        byte[] decodedData = new byte[numberQuadruple * 3];
        int dataIndex = 0;
        while (i < numberQuadruple - 1) {
            int dataIndex2 = dataIndex + 1;
            char d1 = base64Data[dataIndex];
            if (isData(d1)) {
                dataIndex = dataIndex2 + 1;
                char d2 = base64Data[dataIndex2];
                if (isData(d2)) {
                    int dataIndex3 = dataIndex + 1;
                    char d3 = base64Data[dataIndex];
                    if (isData(d3)) {
                        dataIndex = dataIndex3 + 1;
                        char d4 = base64Data[dataIndex3];
                        if (isData(d4)) {
                            byte b1 = base64Alphabet[d1];
                            byte b2 = base64Alphabet[d2];
                            byte b3 = base64Alphabet[d3];
                            byte b4 = base64Alphabet[d4];
                            int encodedIndex2 = encodedIndex + 1;
                            decodedData[encodedIndex] = (byte) ((b1 << 2) | (b2 >> 4));
                            int encodedIndex3 = encodedIndex2 + 1;
                            decodedData[encodedIndex2] = (byte) (((b2 & dc.m) << 4) | ((b3 >> 2) & 15));
                            encodedIndex = encodedIndex3 + 1;
                            decodedData[encodedIndex3] = (byte) ((b3 << 6) | b4);
                            i++;
                        }
                    }
                }
            }
            return null;
        }
        int dataIndex4 = dataIndex + 1;
        char d12 = base64Data[dataIndex];
        if (isData(d12)) {
            int dataIndex5 = dataIndex4 + 1;
            char d22 = base64Data[dataIndex4];
            if (isData(d22)) {
                byte b12 = base64Alphabet[d12];
                byte b22 = base64Alphabet[d22];
                int dataIndex6 = dataIndex5 + 1;
                char d32 = base64Data[dataIndex5];
                int i2 = dataIndex6 + 1;
                char d42 = base64Data[dataIndex6];
                if (isData(d32) && isData(d42)) {
                    byte b32 = base64Alphabet[d32];
                    byte b42 = base64Alphabet[d42];
                    int encodedIndex4 = encodedIndex + 1;
                    decodedData[encodedIndex] = (byte) ((b12 << 2) | (b22 >> 4));
                    int encodedIndex5 = encodedIndex4 + 1;
                    decodedData[encodedIndex4] = (byte) (((b22 & dc.m) << 4) | ((b32 >> 2) & 15));
                    int i3 = encodedIndex5 + 1;
                    decodedData[encodedIndex5] = (byte) ((b32 << 6) | b42);
                    return decodedData;
                } else if (!isPad(d32) || !isPad(d42)) {
                    if (isPad(d32) || !isPad(d42)) {
                        return null;
                    }
                    byte b33 = base64Alphabet[d32];
                    if ((b33 & 3) != 0) {
                        return null;
                    }
                    byte[] tmp = new byte[(i * 3) + 2];
                    System.arraycopy(decodedData, 0, tmp, 0, i * 3);
                    tmp[encodedIndex] = (byte) ((b12 << 2) | (b22 >> 4));
                    tmp[encodedIndex + 1] = (byte) (((b22 & dc.m) << 4) | ((b33 >> 2) & 15));
                    return tmp;
                } else if ((b22 & dc.m) != 0) {
                    return null;
                } else {
                    byte[] tmp2 = new byte[(i * 3) + 1];
                    System.arraycopy(decodedData, 0, tmp2, 0, i * 3);
                    tmp2[encodedIndex] = (byte) ((b12 << 2) | (b22 >> 4));
                    return tmp2;
                }
            }
        }
        return null;
    }

    private static int removeWhiteSpace(char[] data) {
        int newSize;
        if (data == null) {
            return 0;
        }
        int len = data.length;
        int i = 0;
        int newSize2 = 0;
        while (i < len) {
            if (!isWhiteSpace(data[i])) {
                newSize = newSize2 + 1;
                data[newSize2] = data[i];
            } else {
                newSize = newSize2;
            }
            i++;
            newSize2 = newSize;
        }
        return newSize2;
    }
}
