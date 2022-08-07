package com.tencent.smtt.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import u.aly.dc;

/* loaded from: classes2.dex */
public class a {
    public static int a(Context context, File file) {
        if (file == null || !file.exists()) {
            return 0;
        }
        try {
            PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 1);
            if (packageArchiveInfo != null) {
                return packageArchiveInfo.versionCode;
            }
            return 0;
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    public static String a(File file) {
        FileInputStream fileInputStream;
        int i;
        char[] cArr;
        char[] cArr2;
        FileInputStream fileInputStream2;
        try {
            fileInputStream = null;
            cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            cArr2 = new char[32];
        } catch (Throwable th) {
            th = th;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            fileInputStream2 = new FileInputStream(file);
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = fileInputStream2.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    instance.update(bArr, 0, read);
                }
                byte[] digest = instance.digest();
                int i2 = 0;
                for (i = 0; i < 16; i++) {
                    byte b = digest[i];
                    int i3 = i2 + 1;
                    cArr2[i2] = cArr[(b >>> 4) & 15];
                    i2 = i3 + 1;
                    cArr2[i3] = cArr[b & dc.m];
                }
                String str = new String(cArr2);
                if (fileInputStream2 == null) {
                    return str;
                }
                try {
                    fileInputStream2.close();
                    return str;
                } catch (IOException e) {
                    e.printStackTrace();
                    return str;
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                return null;
            }
        } catch (Exception e4) {
            e = e4;
            fileInputStream2 = null;
        } catch (Throwable th2) {
            th = th2;
            if (0 != 0) {
                try {
                    fileInputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static boolean a(Context context, File file, long j, int i) {
        if (file == null || !file.exists()) {
            return false;
        }
        if (j > 0 && j != file.length()) {
            return false;
        }
        try {
            if (i == a(context, file)) {
                return "3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a".equals(b.a(context, file));
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
