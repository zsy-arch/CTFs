package com.hyphenate.util;

import android.util.Base64;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import u.aly.dc;

/* loaded from: classes2.dex */
public class CryptoUtils {
    public static final int ALGORIGHM_AES = 1;
    public static final int ALGORIGHM_DES = 0;
    static final String HEXES = "0123456789ABCDEF";
    Cipher cipher = null;
    Cipher decipher = null;
    byte[] keyBytes = {74, 111, 104, 110, 115, 111, 110, 77, 97, 74, 105, 70, 97, 110, 103, 74, 101, 114, 118, 105, 115, 76, 105, 117, 76, 105, 117, 83, 104, 97, 111, 90};
    String key = "TongliforniaJohnson";

    public static byte[] fromHexString(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public static String getHex(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append(HEXES.charAt((b & 240) >> 4)).append(HEXES.charAt(b & dc.m));
        }
        return sb.toString();
    }

    public byte[] decrypt(byte[] bArr) throws Exception {
        return this.decipher.doFinal(bArr);
    }

    public String decryptBase64String(String str) throws Exception {
        return new String(decrypt(Base64.decode(str, 0)), "UTF-8");
    }

    public byte[] encrypt(String str) throws Exception {
        return this.cipher.doFinal(str.getBytes("UTF-8"));
    }

    public byte[] encrypt(byte[] bArr) throws Exception {
        return this.cipher.doFinal(bArr);
    }

    public String encryptBase64String(String str) throws Exception {
        return new String(Base64.encode(encrypt(str), 0));
    }

    public void init(int i) {
        if (i == 0) {
            initDES();
        } else {
            initAES();
        }
    }

    public void initAES() {
        try {
            this.cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(this.keyBytes, "AES");
            this.cipher.init(1, secretKeySpec);
            this.decipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            this.decipher.init(2, secretKeySpec);
            EMLog.d("encrypt", "initital for AES");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initDES() {
        try {
            this.keyBytes = Arrays.copyOf(MessageDigest.getInstance("md5").digest(this.key.getBytes("utf-8")), 24);
            int i = 0;
            int i2 = 16;
            while (i < 8) {
                i2++;
                i++;
                this.keyBytes[i2] = this.keyBytes[i];
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(this.keyBytes, "DESede");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[8]);
            this.cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            this.cipher.init(1, secretKeySpec, ivParameterSpec);
            this.decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            this.decipher.init(2, secretKeySpec, ivParameterSpec);
            EMLog.d("encrypt", "initital for DES");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
