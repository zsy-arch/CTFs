package com.tencent.smtt.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import com.tencent.smtt.sdk.TbsExtensionFunctionManager;
import com.tencent.smtt.sdk.TbsPVConfig;
import com.tencent.smtt.sdk.TbsShareManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class a {
    public static int a(Context context, File file) {
        try {
            int i = Build.VERSION.SDK_INT;
            return a(context, file, !TbsExtensionFunctionManager.getInstance().canUseFunction(context, TbsExtensionFunctionManager.DISABLE_GET_APK_VERSION_SWITCH_FILE_NAME));
        } catch (Exception unused) {
            TbsLog.i("ApkUtil", "getApkVersion Failed");
            return 0;
        }
    }

    public static int a(Context context, File file, boolean z) {
        if (file != null) {
            try {
                if (file.exists()) {
                    boolean contains = file.getName().contains("tbs.org");
                    boolean contains2 = file.getName().contains("x5.tbs.decouple");
                    if (contains || contains2) {
                        int a2 = a(contains2, file);
                        if (a2 > 0) {
                            return a2;
                        }
                        if (!TbsShareManager.isThirdPartyApp(context) && !file.getAbsolutePath().contains(context.getApplicationInfo().packageName)) {
                            return 0;
                        }
                    }
                    boolean z2 = (Build.VERSION.SDK_INT == 23 || Build.VERSION.SDK_INT == 25) && Build.MANUFACTURER.toLowerCase().contains("mi");
                    TbsPVConfig.releaseInstance();
                    int readApk = TbsPVConfig.getInstance(context).getReadApk();
                    if (readApk == 1) {
                        z = false;
                        z2 = false;
                    } else if (readApk == 2) {
                        return 0;
                    }
                    if (z || z2) {
                        int b2 = b(file);
                        if (b2 > 0) {
                            return b2;
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        if (file == null || !file.exists()) {
            return 0;
        }
        try {
            PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 1);
            if (packageArchiveInfo != null) {
                return packageArchiveInfo.versionCode;
            }
            return 0;
        } catch (Throwable th2) {
            th2.printStackTrace();
            return -1;
        }
    }

    public static int a(boolean z, File file) {
        try {
            File parentFile = file.getParentFile();
            if (parentFile == null) {
                return -1;
            }
            File[] listFiles = parentFile.listFiles();
            Pattern compile = Pattern.compile(a(z) + "(.*)");
            for (File file2 : listFiles) {
                if (compile.matcher(file2.getName()).find() && file2.isFile() && file2.exists()) {
                    return Integer.parseInt(file2.getName().substring(file2.getName().lastIndexOf(".") + 1));
                }
            }
            return -1;
        } catch (Exception unused) {
            return -1;
        }
    }

    public static String a(File file) {
        Throwable th;
        char[] cArr;
        char[] cArr2;
        FileInputStream fileInputStream;
        Exception e2;
        FileInputStream fileInputStream2;
        int i;
        try {
            cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            cArr2 = new char[32];
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
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
                    byte b2 = digest[i];
                    int i3 = i2 + 1;
                    cArr2[i2] = cArr[(b2 >>> 4) & 15];
                    i2 = i3 + 1;
                    cArr2[i3] = cArr[b2 & 15];
                }
                String str = new String(cArr2);
                try {
                    fileInputStream2.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                return str;
            } catch (Exception e4) {
                e2 = e4;
                e2.printStackTrace();
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                return null;
            }
        } catch (Exception e6) {
            e2 = e6;
            fileInputStream2 = null;
        } catch (Throwable th3) {
            th = th3;
            if (0 != 0) {
                try {
                    fileInputStream.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static final String a(boolean z) {
        return b.c() ? z ? "x5.64.decouple.backup" : "x5.64.backup" : z ? "x5.decouple.backup" : "x5.backup";
    }

    public static boolean a(Context context, File file, long j, int i) {
        if (file != null && file.exists()) {
            if (j > 0 && j != file.length()) {
                return false;
            }
            try {
                if (i != a(context, file)) {
                    return false;
                }
                return "3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a".equals(b.a(context, true, file));
            } catch (Exception unused) {
            }
        }
        return false;
    }

    /*  JADX ERROR: NullPointerException in pass: BlockProcessor
        java.lang.NullPointerException: Cannot read field "wordsInUse" because "<parameter1>" is null
        	at java.base/java.util.BitSet.or(Unknown Source)
        	at jadx.core.utils.BlockUtils.lambda$getPathCross$3(BlockUtils.java:704)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.utils.BlockUtils.getPathCross(BlockUtils.java:704)
        	at jadx.core.dex.visitors.blocks.BlockExceptionHandler.searchBottomBlock(BlockExceptionHandler.java:412)
        	at jadx.core.dex.visitors.blocks.BlockExceptionHandler.wrapBlocksWithTryCatch(BlockExceptionHandler.java:329)
        	at jadx.core.dex.visitors.blocks.BlockExceptionHandler.connectExcHandlers(BlockExceptionHandler.java:84)
        	at jadx.core.dex.visitors.blocks.BlockExceptionHandler.process(BlockExceptionHandler.java:59)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.independentBlockTreeMod(BlockProcessor.java:452)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:51)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    public static int b(java.io.File r6) {
        /*
            java.lang.Class<com.tencent.smtt.utils.a> r0 = com.tencent.smtt.utils.a.class
            monitor-enter(r0)
            r1 = 0
            java.util.jar.JarFile r2 = new java.util.jar.JarFile     // Catch: Exception -> 0x0060, all -> 0x005d
            r2.<init>(r6)     // Catch: Exception -> 0x0060, all -> 0x005d
            java.lang.String r6 = "assets/webkit/tbs.conf"
            java.util.jar.JarEntry r6 = r2.getJarEntry(r6)     // Catch: Exception -> 0x005b, all -> 0x0073
            java.io.InputStream r6 = r2.getInputStream(r6)     // Catch: Exception -> 0x005b, all -> 0x0073
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: Exception -> 0x005b, all -> 0x0073
            r3.<init>(r6)     // Catch: Exception -> 0x005b, all -> 0x0073
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch: Exception -> 0x005b, all -> 0x0073
            r6.<init>(r3)     // Catch: Exception -> 0x005b, all -> 0x0073
        L_0x001d:
            java.lang.String r1 = r6.readLine()     // Catch: Exception -> 0x0056, all -> 0x0051
            if (r1 == 0) goto L_0x004a
            java.lang.String r3 = "tbs_core_version"
            boolean r3 = r1.contains(r3)     // Catch: Exception -> 0x0056, all -> 0x0051
            if (r3 == 0) goto L_0x001d
            java.lang.String r3 = "="
            java.lang.String[] r1 = r1.split(r3)     // Catch: Exception -> 0x0056, all -> 0x0051
            if (r1 == 0) goto L_0x001d
            int r3 = r1.length     // Catch: Exception -> 0x0056, all -> 0x0051
            r4 = 2
            if (r3 != r4) goto L_0x001d
            r3 = 1
            r1 = r1[r3]     // Catch: Exception -> 0x0056, all -> 0x0051
            java.lang.String r1 = r1.trim()     // Catch: Exception -> 0x0056, all -> 0x0051
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: Exception -> 0x0056, all -> 0x0051
            r6.close()     // Catch: Exception -> 0x0048, all -> 0x006b
            r2.close()     // Catch: Exception -> 0x0048, all -> 0x006b
        L_0x0048:
            monitor-exit(r0)     // Catch: all -> 0x006b
            return r1
        L_0x004a:
            r6.close()     // Catch: Exception -> 0x0070, all -> 0x006b
        L_0x004d:
            r2.close()     // Catch: Exception -> 0x0070, all -> 0x006b
            goto L_0x0070
        L_0x0051:
            r1 = move-exception
            r5 = r1
            r1 = r6
            r6 = r5
            goto L_0x0074
        L_0x0056:
            r1 = move-exception
            r5 = r1
            r1 = r6
            r6 = r5
            goto L_0x0062
        L_0x005b:
            r6 = move-exception
            goto L_0x0062
        L_0x005d:
            r6 = move-exception
            r2 = r1
            goto L_0x0074
        L_0x0060:
            r6 = move-exception
            r2 = r1
        L_0x0062:
            r6.printStackTrace()     // Catch: all -> 0x0073
            if (r1 == 0) goto L_0x006d
            r1.close()     // Catch: Exception -> 0x0070, all -> 0x006b
            goto L_0x006d
        L_0x006b:
            r6 = move-exception
            goto L_0x007f
        L_0x006d:
            if (r2 == 0) goto L_0x0070
            goto L_0x004d
        L_0x0070:
            r6 = -1
            monitor-exit(r0)     // Catch: all -> 0x006b
            return r6
        L_0x0073:
            r6 = move-exception
        L_0x0074:
            if (r1 == 0) goto L_0x0079
            r1.close()     // Catch: Exception -> 0x007e, all -> 0x006b
        L_0x0079:
            if (r2 == 0) goto L_0x007e
            r2.close()     // Catch: Exception -> 0x007e, all -> 0x006b
        L_0x007e:
            throw r6     // Catch: all -> 0x006b
        L_0x007f:
            monitor-exit(r0)     // Catch: all -> 0x006b
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.a.b(java.io.File):int");
    }
}
