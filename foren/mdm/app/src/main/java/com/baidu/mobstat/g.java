package com.baidu.mobstat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.update.UpdateConfig;
import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.crypto.Cipher;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class g {
    private static final String a;
    private static j e;
    private final Context b;
    private int c = 0;
    private PublicKey d;

    static {
        String str = new String(b.a(new byte[]{77, 122, 65, 121, 77, 84, 73, 120, 77, 68, 73, 61}));
        a = str + new String(b.a(new byte[]{90, 71, 108, 106, 100, 87, 82, 112, 89, 87, 73, 61}));
    }

    private g(Context context) {
        this.b = context.getApplicationContext();
        a();
    }

    public static String a(Context context) {
        return c(context).b();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.FileReader] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    private static String a(File file) {
        String str;
        FileReader fileReader;
        try {
            str = 0;
            str = 0;
            str = 0;
        } catch (Throwable th) {
            th = th;
        }
        try {
            fileReader = new FileReader(file);
            try {
                char[] cArr = new char[8192];
                CharArrayWriter charArrayWriter = new CharArrayWriter();
                while (true) {
                    int read = fileReader.read(cArr);
                    if (read <= 0) {
                        break;
                    }
                    charArrayWriter.write(cArr, 0, read);
                }
                String charArrayWriter2 = charArrayWriter.toString();
                str = charArrayWriter2;
                if (fileReader != null) {
                    try {
                        fileReader.close();
                        str = charArrayWriter2;
                    } catch (Exception e2) {
                        b(e2);
                        str = charArrayWriter2;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                b(e);
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (Exception e4) {
                        b(e4);
                    }
                }
                return str;
            }
        } catch (Exception e5) {
            e = e5;
            fileReader = null;
        } catch (Throwable th2) {
            th = th2;
            if (0 != 0) {
                try {
                    str.close();
                } catch (Exception e6) {
                    b(e6);
                }
            }
            throw th;
        }
        return str;
    }

    private static String a(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
        }
        String str = "";
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            str = hexString.length() == 1 ? str + "0" + hexString : str + hexString;
        }
        return str.toLowerCase();
    }

    private List<i> a(Intent intent, boolean z) {
        boolean z2 = true;
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = this.b.getPackageManager();
        List<ResolveInfo> queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0);
        if (queryBroadcastReceivers != null) {
            for (ResolveInfo resolveInfo : queryBroadcastReceivers) {
                if (!(resolveInfo.activityInfo == null || resolveInfo.activityInfo.applicationInfo == null)) {
                    try {
                        Bundle bundle = packageManager.getReceiverInfo(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name), 128).metaData;
                        if (bundle != null) {
                            String string = bundle.getString("galaxy_data");
                            if (!TextUtils.isEmpty(string)) {
                                byte[] a2 = b.a(string.getBytes("utf-8"));
                                JSONObject jSONObject = new JSONObject(new String(a2));
                                i iVar = new i(null);
                                iVar.b = jSONObject.getInt(LogFactory.PRIORITY_KEY);
                                iVar.a = resolveInfo.activityInfo.applicationInfo;
                                if (this.b.getPackageName().equals(resolveInfo.activityInfo.applicationInfo.packageName)) {
                                    iVar.d = true;
                                }
                                if (z) {
                                    String string2 = bundle.getString("galaxy_sf");
                                    if (!TextUtils.isEmpty(string2)) {
                                        PackageInfo packageInfo = packageManager.getPackageInfo(resolveInfo.activityInfo.applicationInfo.packageName, 64);
                                        JSONArray jSONArray = jSONObject.getJSONArray("sigs");
                                        String[] strArr = new String[jSONArray.length()];
                                        for (int i = 0; i < strArr.length; i++) {
                                            strArr[i] = jSONArray.getString(i);
                                        }
                                        if (a(strArr, a(packageInfo.signatures))) {
                                            byte[] a3 = a(b.a(string2.getBytes()), this.d);
                                            byte[] a4 = d.a(a2);
                                            if (a3 == null || !Arrays.equals(a3, a4)) {
                                                z2 = false;
                                            }
                                            if (z2) {
                                                iVar.c = true;
                                            }
                                        }
                                    }
                                }
                                arrayList.add(iVar);
                            }
                        }
                    } catch (Exception e2) {
                    }
                }
            }
        }
        Collections.sort(arrayList, new h(this));
        return arrayList;
    }

    private void a() {
        Throwable th;
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayInputStream byteArrayInputStream2 = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(f.a());
        } catch (Exception e2) {
            byteArrayInputStream = null;
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            this.d = CertificateFactory.getInstance("X.509").generateCertificate(byteArrayInputStream).getPublicKey();
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (Exception e3) {
                    b(e3);
                }
            }
        } catch (Exception e4) {
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (Exception e5) {
                    b(e5);
                }
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayInputStream2 = byteArrayInputStream;
            if (byteArrayInputStream2 != null) {
                try {
                    byteArrayInputStream2.close();
                } catch (Exception e6) {
                    b(e6);
                }
            }
            throw th;
        }
    }

    private boolean a(String str) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = null;
            try {
                fileOutputStream = this.b.openFileOutput("libcuid.so", 1);
                fileOutputStream.write(str.getBytes());
                fileOutputStream.flush();
                if (fileOutputStream == null) {
                    return true;
                }
                try {
                    fileOutputStream.close();
                    return true;
                } catch (Exception e2) {
                    b(e2);
                    return true;
                }
            } catch (Exception e3) {
                b(e3);
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e4) {
                        b(e4);
                    }
                }
                return false;
            }
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e5) {
                    b(e5);
                }
            }
            throw th;
        }
    }

    private boolean a(String str, String str2) {
        try {
            return Settings.System.putString(this.b.getContentResolver(), str, str2);
        } catch (Exception e2) {
            b(e2);
            return false;
        }
    }

    private boolean a(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null || strArr.length != strArr2.length) {
            return false;
        }
        HashSet hashSet = new HashSet();
        for (String str : strArr) {
            hashSet.add(str);
        }
        HashSet hashSet2 = new HashSet();
        for (String str2 : strArr2) {
            hashSet2.add(str2);
        }
        return hashSet.equals(hashSet2);
    }

    private static byte[] a(byte[] bArr, PublicKey publicKey) {
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(2, publicKey);
        return instance.doFinal(bArr);
    }

    private String[] a(Signature[] signatureArr) {
        String[] strArr = new String[signatureArr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = a(d.a(signatureArr[i].toByteArray()));
        }
        return strArr;
    }

    private j b() {
        boolean z;
        j jVar;
        String str;
        j jVar2;
        String str2;
        String str3 = null;
        boolean z2 = false;
        List<i> a2 = a(new Intent("com.baidu.intent.action.GALAXY").setPackage(this.b.getPackageName()), true);
        if (a2 == null || a2.size() == 0) {
            for (int i = 0; i < 3; i++) {
                Log.w("DeviceId", "galaxy lib host missing meta-data,make sure you know the right way to integrate galaxy");
            }
            z = false;
        } else {
            i iVar = a2.get(0);
            z = iVar.c;
            if (!iVar.c) {
                for (int i2 = 0; i2 < 3; i2++) {
                    Log.w("DeviceId", "galaxy config err, In the release version of the signature should be matched");
                }
            }
        }
        File file = new File(this.b.getFilesDir(), "libcuid.so");
        j a3 = file.exists() ? j.a(f(a(file))) : null;
        if (a3 == null) {
            this.c |= 16;
            List<i> a4 = a(new Intent("com.baidu.intent.action.GALAXY"), z);
            if (a4 != null) {
                File filesDir = this.b.getFilesDir();
                if (!"files".equals(filesDir.getName())) {
                    Log.e("DeviceId", "fetal error:: app files dir name is unexpectedly :: " + filesDir.getAbsolutePath());
                    str2 = filesDir.getName();
                } else {
                    str2 = "files";
                }
                for (i iVar2 : a4) {
                    if (!iVar2.d) {
                        File file2 = new File(new File(iVar2.a.dataDir, str2), "libcuid.so");
                        if (file2.exists()) {
                            a3 = j.a(f(a(file2)));
                            if (a3 != null) {
                                break;
                            }
                        } else {
                            a3 = a3;
                        }
                    }
                }
            }
        }
        if (a3 == null) {
            a3 = j.a(f(b("com.baidu.deviceid.v2")));
        }
        boolean c = c("android.permission.READ_EXTERNAL_STORAGE");
        if (a3 != null || !c) {
            jVar = a3;
        } else {
            this.c |= 2;
            jVar = e();
        }
        if (jVar == null) {
            this.c |= 8;
            jVar = d();
        }
        if (jVar != null || !c) {
            str = null;
        } else {
            this.c |= 1;
            str = h("");
            jVar = d(str);
            z2 = true;
        }
        if (jVar == null) {
            this.c |= 4;
            if (!z2) {
                str = h("");
            }
            j jVar3 = new j(null);
            String b = b(this.b);
            jVar3.a = c.a((Build.VERSION.SDK_INT < 23 ? str + b + UUID.randomUUID().toString() : "com.baidu" + b).getBytes(), true);
            jVar3.b = str;
            jVar2 = jVar3;
        } else {
            jVar2 = jVar;
        }
        File file3 = new File(this.b.getFilesDir(), "libcuid.so");
        if ((this.c & 16) != 0 || !file3.exists()) {
            String e2 = TextUtils.isEmpty(null) ? e(jVar2.a()) : null;
            a(e2);
            str3 = e2;
        }
        boolean c2 = c();
        if (c2 && ((this.c & 2) != 0 || TextUtils.isEmpty(b("com.baidu.deviceid.v2")))) {
            if (TextUtils.isEmpty(str3)) {
                str3 = e(jVar2.a());
            }
            a("com.baidu.deviceid.v2", str3);
        }
        if (c(UpdateConfig.f)) {
            File file4 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2");
            if ((this.c & 8) != 0 || !file4.exists()) {
                if (TextUtils.isEmpty(str3)) {
                    str3 = e(jVar2.a());
                }
                g(str3);
            }
        }
        if (c2 && ((this.c & 1) != 0 || TextUtils.isEmpty(b("com.baidu.deviceid")))) {
            a("com.baidu.deviceid", jVar2.a);
            a("bd_setting_i", jVar2.b);
        }
        if (c2 && !TextUtils.isEmpty(jVar2.b)) {
            File file5 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid");
            if ((this.c & 2) != 0 || !file5.exists()) {
                b(jVar2.b, jVar2.a);
            }
        }
        return jVar2;
    }

    public static String b(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        return TextUtils.isEmpty(string) ? "" : string;
    }

    private String b(String str) {
        try {
            return Settings.System.getString(this.b.getContentResolver(), str);
        } catch (Exception e2) {
            b(e2);
            return null;
        }
    }

    private static void b(String str, String str2) {
        File file;
        if (!TextUtils.isEmpty(str)) {
            File file2 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig");
            File file3 = new File(file2, ".cuid");
            try {
                if (file2.exists() && !file2.isDirectory()) {
                    Random random = new Random();
                    File parentFile = file2.getParentFile();
                    String name = file2.getName();
                    do {
                        file = new File(parentFile, name + random.nextInt() + ".tmp");
                    } while (file.exists());
                    file2.renameTo(file);
                    file.delete();
                }
                file2.mkdirs();
                FileWriter fileWriter = new FileWriter(file3, false);
                fileWriter.write(b.a(a.a(a, a, (str + "=" + str2).getBytes()), "utf-8"));
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e2) {
            } catch (Exception e3) {
            }
        }
    }

    public static void b(Throwable th) {
    }

    private static j c(Context context) {
        if (e == null) {
            synchronized (j.class) {
                if (e == null) {
                    SystemClock.uptimeMillis();
                    e = new g(context).b();
                    SystemClock.uptimeMillis();
                }
            }
        }
        return e;
    }

    private boolean c() {
        return c("android.permission.WRITE_SETTINGS");
    }

    private boolean c(String str) {
        return this.b.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    private j d() {
        String b = b("com.baidu.deviceid");
        String b2 = b("bd_setting_i");
        if (TextUtils.isEmpty(b2)) {
            b2 = h("");
            if (!TextUtils.isEmpty(b2)) {
                a("bd_setting_i", b2);
            }
        }
        if (TextUtils.isEmpty(b)) {
            b = b(c.a(("com.baidu" + b2 + b(this.b)).getBytes(), true));
        }
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        j jVar = new j(null);
        jVar.a = b;
        jVar.b = b2;
        return jVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00a0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.baidu.mobstat.j d(java.lang.String r9) {
        /*
            r8 = this;
            r2 = 0
            r3 = 0
            r4 = 1
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 23
            if (r0 >= r1) goto L_0x0014
            r5 = r4
        L_0x000a:
            if (r5 == 0) goto L_0x0016
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            if (r0 == 0) goto L_0x0016
            r0 = r2
        L_0x0013:
            return r0
        L_0x0014:
            r5 = r3
            goto L_0x000a
        L_0x0016:
            java.lang.String r0 = ""
            java.io.File r1 = new java.io.File
            java.io.File r6 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r7 = "baidu/.cuid"
            r1.<init>(r6, r7)
            boolean r6 = r1.exists()
            if (r6 == 0) goto L_0x005a
        L_0x0029:
            java.io.FileReader r4 = new java.io.FileReader     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
            r4.<init>(r1)     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
            r1.<init>(r4)     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
            r4.<init>()     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
        L_0x0038:
            java.lang.String r6 = r1.readLine()     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
            if (r6 == 0) goto L_0x0067
            r4.append(r6)     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
            java.lang.String r6 = "\r\n"
            r4.append(r6)     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
            goto L_0x0038
        L_0x0047:
            r1 = move-exception
            r1 = r9
        L_0x0049:
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 != 0) goto L_0x00b6
            com.baidu.mobstat.j r3 = new com.baidu.mobstat.j
            r3.<init>(r2)
            r3.a = r0
            r3.b = r1
            r0 = r3
            goto L_0x0013
        L_0x005a:
            java.io.File r1 = new java.io.File
            java.io.File r3 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r6 = "backups/.SystemConfig/.cuid"
            r1.<init>(r3, r6)
            r3 = r4
            goto L_0x0029
        L_0x0067:
            r1.close()     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
            java.lang.String r1 = new java.lang.String     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
            java.lang.String r6 = com.baidu.mobstat.g.a     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
            java.lang.String r7 = com.baidu.mobstat.g.a     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
            java.lang.String r4 = r4.toString()     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
            byte[] r4 = r4.getBytes()     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
            byte[] r4 = com.baidu.mobstat.b.a(r4)     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
            byte[] r4 = com.baidu.mobstat.a.b(r6, r7, r4)     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
            r1.<init>(r4)     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
            java.lang.String r4 = "="
            java.lang.String[] r1 = r1.split(r4)     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
            if (r1 == 0) goto L_0x00c3
            int r4 = r1.length     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
            r6 = 2
            if (r4 != r6) goto L_0x00c3
            if (r5 == 0) goto L_0x00a6
            r4 = 0
            r4 = r1[r4]     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
            boolean r4 = r9.equals(r4)     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
            if (r4 == 0) goto L_0x00a6
            r4 = 1
            r0 = r1[r4]     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
            r1 = r9
        L_0x009e:
            if (r3 != 0) goto L_0x0049
            b(r1, r0)     // Catch: FileNotFoundException -> 0x00a4, IOException -> 0x00c1, Exception -> 0x00bc
            goto L_0x0049
        L_0x00a4:
            r3 = move-exception
            goto L_0x0049
        L_0x00a6:
            if (r5 != 0) goto L_0x00c3
            boolean r4 = android.text.TextUtils.isEmpty(r9)     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
            if (r4 == 0) goto L_0x00b1
            r4 = 1
            r9 = r1[r4]     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
        L_0x00b1:
            r4 = 1
            r0 = r1[r4]     // Catch: FileNotFoundException -> 0x0047, IOException -> 0x00be, Exception -> 0x00b9
            r1 = r9
            goto L_0x009e
        L_0x00b6:
            r0 = r2
            goto L_0x0013
        L_0x00b9:
            r1 = move-exception
            r1 = r9
            goto L_0x0049
        L_0x00bc:
            r3 = move-exception
            goto L_0x0049
        L_0x00be:
            r1 = move-exception
            r1 = r9
            goto L_0x0049
        L_0x00c1:
            r3 = move-exception
            goto L_0x0049
        L_0x00c3:
            r1 = r9
            goto L_0x009e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.g.d(java.lang.String):com.baidu.mobstat.j");
    }

    private j e() {
        File file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2");
        if (file.exists()) {
            String a2 = a(file);
            if (!TextUtils.isEmpty(a2)) {
                try {
                    return j.a(new String(a.b(a, a, b.a(a2.getBytes()))));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        return null;
    }

    private static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return b.a(a.a(a, a, str.getBytes()), "utf-8");
        } catch (UnsupportedEncodingException e2) {
            b(e2);
            return "";
        } catch (Exception e3) {
            b(e3);
            return "";
        }
    }

    private static String f(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new String(a.b(a, a, b.a(str.getBytes())));
        } catch (Exception e2) {
            b(e2);
            return "";
        }
    }

    private static void g(String str) {
        File file;
        File file2 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig");
        File file3 = new File(file2, ".cuid2");
        try {
            if (file2.exists() && !file2.isDirectory()) {
                Random random = new Random();
                File parentFile = file2.getParentFile();
                String name = file2.getName();
                do {
                    file = new File(parentFile, name + random.nextInt() + ".tmp");
                } while (file.exists());
                file2.renameTo(file);
                file.delete();
            }
            file2.mkdirs();
            FileWriter fileWriter = new FileWriter(file3, false);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e2) {
        } catch (Exception e3) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001b A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String h(java.lang.String r5) {
        /*
            r4 = this;
            r1 = 0
            android.content.Context r0 = r4.b     // Catch: Exception -> 0x001c
            java.lang.String r2 = "phone"
            java.lang.Object r0 = r0.getSystemService(r2)     // Catch: Exception -> 0x001c
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0     // Catch: Exception -> 0x001c
            if (r0 == 0) goto L_0x0024
            java.lang.String r0 = r0.getDeviceId()     // Catch: Exception -> 0x001c
        L_0x0011:
            java.lang.String r0 = i(r0)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x0026
        L_0x001b:
            return r5
        L_0x001c:
            r0 = move-exception
            java.lang.String r2 = "DeviceId"
            java.lang.String r3 = "Read IMEI failed"
            android.util.Log.e(r2, r3, r0)
        L_0x0024:
            r0 = r1
            goto L_0x0011
        L_0x0026:
            r5 = r0
            goto L_0x001b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.g.h(java.lang.String):java.lang.String");
    }

    private static String i(String str) {
        return (str == null || !str.contains(":")) ? str : "";
    }
}
