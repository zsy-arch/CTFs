package com.hyphenate.chat;

import android.util.Base64;
import com.hyphenate.util.EMLog;
import com.hyphenate.util.EasyUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

/* loaded from: classes2.dex */
public class EMEncryptUtils {
    private static final String TAG = "encrypt";

    public static void decryptFile(String str, String str2) {
        try {
            EMLog.d(TAG, "decrypt file:" + str);
            RandomAccessFile randomAccessFile = new RandomAccessFile(str, "r");
            int length = (int) randomAccessFile.length();
            byte[] bArr = new byte[length];
            int read = randomAccessFile.read(bArr);
            if (read != length) {
                EMLog.e(TAG, "error read file, file len:" + length + " readLen:" + read);
            } else {
                randomAccessFile.close();
                byte[] decrypt = EMClient.getInstance().getEncryptProvider().decrypt(bArr, str2);
                FileOutputStream fileOutputStream = new FileOutputStream(str, false);
                fileOutputStream.write(decrypt);
                fileOutputStream.close();
                EMLog.d(TAG, "decrypted file:" + str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String decryptMessage(String str, String str2) {
        try {
            EMLog.d(TAG, "encrypted str:" + str);
            byte[] decode = Base64.decode(str, 0);
            EMLog.d(TAG, "base64 decode bytes:" + EasyUtils.convertByteArrayToString(decode));
            byte[] decrypt = EMClient.getInstance().getEncryptProvider().decrypt(decode, str2);
            EMLog.d(TAG, "decrypt bytes:" + EasyUtils.convertByteArrayToString(decrypt));
            String str3 = new String(decrypt, "UTF-8");
            EMLog.d(TAG, "descripted str:" + str3);
            return str3;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String encryptFile(String str, String str2) {
        try {
            EMLog.d(TAG, "try to encrypt file:" + str);
            RandomAccessFile randomAccessFile = new RandomAccessFile(str, "r");
            int length = (int) randomAccessFile.length();
            EMLog.d(TAG, "try to encrypt file:" + str + " original len:" + length);
            byte[] bArr = new byte[length];
            int read = randomAccessFile.read(bArr);
            if (read != length) {
                EMLog.e(TAG, "error read file, file len:" + length + " readLen:" + read);
                randomAccessFile.close();
                return str;
            }
            randomAccessFile.close();
            byte[] encrypt = EMClient.getInstance().getEncryptProvider().encrypt(bArr, str2);
            String str3 = null;
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf >= 0) {
                str3 = str.substring(lastIndexOf);
            }
            File createTempFile = File.createTempFile("encrypted", str3);
            FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
            fileOutputStream.write(encrypt);
            fileOutputStream.close();
            String absolutePath = createTempFile.getAbsolutePath();
            EMLog.d(TAG, "generated encrypted file:" + absolutePath);
            return absolutePath;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    static String encryptMessage(String str, String str2) {
        try {
            EMEncryptProvider encryptProvider = EMClient.getInstance().getEncryptProvider();
            byte[] bytes = str.getBytes("UTF-8");
            EMLog.d(TAG, "utf-8 bytes:" + EasyUtils.convertByteArrayToString(bytes));
            byte[] encrypt = encryptProvider.encrypt(bytes, str2);
            EMLog.d(TAG, "encrypted bytes:" + EasyUtils.convertByteArrayToString(encrypt));
            byte[] encode = Base64.encode(encrypt, 0);
            EMLog.d(TAG, "base64 bytes:" + EasyUtils.convertByteArrayToString(encode));
            String str3 = new String(encode);
            EMLog.d(TAG, "encrypted str:" + str3);
            return str3;
        } catch (Exception e) {
            e.printStackTrace();
            EMLog.e(TAG, "encryption error, send plain msg");
            return str;
        }
    }
}
