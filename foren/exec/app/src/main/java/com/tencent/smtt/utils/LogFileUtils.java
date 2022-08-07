package com.tencent.smtt.utils;

import e.a.a.a.a;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class LogFileUtils {

    /* renamed from: a  reason: collision with root package name */
    public static OutputStream f1486a;

    public static void closeOutputStream(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    public static byte[] createHeaderText(String str, String str2) {
        try {
            byte[] encryptKey = encryptKey(str, str2);
            String format = String.format("%03d", Integer.valueOf(encryptKey.length));
            byte[] bArr = new byte[encryptKey.length + 3];
            bArr[0] = (byte) format.charAt(0);
            bArr[1] = (byte) format.charAt(1);
            bArr[2] = (byte) format.charAt(2);
            System.arraycopy(encryptKey, 0, bArr, 3, encryptKey.length);
            return bArr;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String createKey() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static byte[] encrypt(String str, String str2) {
        try {
            byte[] bytes = str2.getBytes("UTF-8");
            Cipher instance = Cipher.getInstance("RC4");
            instance.init(1, new SecretKeySpec(str.getBytes("UTF-8"), "RC4"));
            return instance.update(bytes);
        } catch (Throwable th) {
            StringBuilder a2 = a.a("encrypt exception:");
            a2.append(th.getMessage());
            a2.toString();
            return null;
        }
    }

    public static byte[] encryptKey(String str, String str2) {
        try {
            byte[] bytes = str2.getBytes("UTF-8");
            Cipher instance = Cipher.getInstance("RC4");
            instance.init(1, new SecretKeySpec(str.getBytes("UTF-8"), "RC4"));
            return instance.update(bytes);
        } catch (Throwable th) {
            StringBuilder a2 = a.a("encrypt exception:");
            a2.append(th.getMessage());
            a2.toString();
            return null;
        }
    }

    public static synchronized void writeDataToStorage(File file, String str, byte[] bArr, String str2, boolean z) {
        OutputStream outputStream;
        OutputStream outputStream2;
        byte[] bArr2;
        synchronized (LogFileUtils.class) {
            byte[] encrypt = encrypt(str, str2);
            if (encrypt != null) {
                str2 = null;
            } else {
                encrypt = null;
            }
            try {
                try {
                    file.getParentFile().mkdirs();
                    if (file.isFile() && file.exists() && file.length() > 2097152) {
                        file.delete();
                        file.createNewFile();
                    }
                    if (f1486a == null) {
                        f1486a = new BufferedOutputStream(new FileOutputStream(file, z));
                    }
                    if (str2 != null) {
                        outputStream2 = f1486a;
                        bArr2 = str2.getBytes();
                    } else {
                        f1486a.write(bArr);
                        f1486a.write(encrypt);
                        outputStream2 = f1486a;
                        bArr2 = new byte[]{10, 10};
                    }
                    outputStream2.write(bArr2);
                } catch (Throwable unused) {
                }
            } catch (Throwable th) {
                if (f1486a != null) {
                    try {
                        f1486a.flush();
                    } catch (Throwable unused2) {
                    }
                }
                throw th;
            }
            if (f1486a != null) {
                outputStream = f1486a;
                outputStream.flush();
            }
        }
    }
}
