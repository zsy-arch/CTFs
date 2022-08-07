package com.vsf2f.f2f.ui.utils;

import android.text.TextUtils;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.pay.Base64;
import com.hyphenate.util.HanziToPinyin;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/* loaded from: classes2.dex */
public class DesUtil {
    private static final String DES = "DES";

    public static String read(File file) throws FileNotFoundException, IOException {
        Throwable th;
        try {
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            FileReader reader = new FileReader(file);
            try {
                char[] chars = new char[(int) file.length()];
                reader.read(chars);
                String txt = String.valueOf(chars);
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return txt;
            } catch (FileNotFoundException e2) {
                throw e2;
            } catch (IOException e3) {
                throw e3;
            } catch (Throwable th3) {
                th = th3;
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException e5) {
            throw e5;
        } catch (IOException e6) {
            throw e6;
        }
    }

    public static String encrypt(String data) throws Exception {
        String signKey = ComUtil.getRandomString(8);
        int max = 8 * 2;
        if (data.length() < max) {
            for (int i = data.length(); i < max; i++) {
                data = data + HanziToPinyin.Token.SEPARATOR;
            }
        }
        String encrypt = encrypt(data, signKey).replaceAll("\r|\n", "");
        int j = 0;
        StringBuffer buffer = new StringBuffer();
        for (int i2 = 0; i2 < encrypt.length(); i2++) {
            buffer.append(encrypt.charAt(i2));
            if (i2 > 0 && i2 % 2 == 0 && j < 8) {
                buffer.append(signKey.charAt(j));
                j++;
            }
        }
        return buffer.toString();
    }

    public static String encrypt(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
        new Base64();
        return Base64.encode(bt);
    }

    public static String decrypt(String data) {
        int ll = 2 + 1;
        StringBuffer buffer = new StringBuffer();
        StringBuffer signKey = new StringBuffer();
        for (int i = 0; i < data.length(); i++) {
            if (i <= 0 || i % ll != 0 || signKey.length() >= 8) {
                buffer.append(data.charAt(i));
            } else {
                signKey.append(data.charAt(i));
            }
        }
        String decrypt = decrypt(buffer.toString(), signKey.toString());
        if (decrypt != null) {
            return decrypt.trim();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String decrypt(String data, boolean split) {
        if (TextUtils.isEmpty(data)) {
            return "";
        }
        if (!split) {
            return decrypt(data);
        }
        StringBuilder decryptBuffer = new StringBuilder();
        for (String encrypt : data.split("\n")) {
            String decrypt = decrypt(encrypt);
            if (decrypt != null) {
                decryptBuffer.append(decrypt.trim());
            }
        }
        return decryptBuffer.toString();
    }

    private static String decrypt(String data, String key) {
        String str = null;
        if (data == null) {
            return null;
        }
        try {
            str = new String(decrypt(Base64.decode(data), key.getBytes(), data));
        } catch (Exception e) {
            System.err.println("非法加密串:" + data);
        }
        return str;
    }

    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        SecretKey securekey = SecretKeyFactory.getInstance(DES).generateSecret(new DESKeySpec(key));
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(1, securekey, sr);
        return cipher.doFinal(data);
    }

    private static byte[] decrypt(byte[] data, byte[] key, String str) {
        try {
            SecureRandom sr = new SecureRandom();
            SecretKey securekey = SecretKeyFactory.getInstance(DES).generateSecret(new DESKeySpec(key));
            Cipher cipher = Cipher.getInstance(DES);
            cipher.init(2, securekey, sr);
            return cipher.doFinal(data);
        } catch (Exception e) {
            System.err.println("非法加密串:" + str);
            return null;
        }
    }
}
