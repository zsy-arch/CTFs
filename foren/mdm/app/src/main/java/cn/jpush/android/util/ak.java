package cn.jpush.android.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ak {
    private static final String a;
    private static final String[] z;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
        if (r5 != 0) goto L_0x002c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0028, code lost:
        r5 = r1;
        r8 = r6;
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002c, code lost:
        r5 = r5;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002e, code lost:
        if (r5 > r6) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0030, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0039, code lost:
        switch(r0) {
            case 0: goto L_0x0044;
            case 1: goto L_0x004c;
            case 2: goto L_0x0054;
            case 3: goto L_0x005c;
            case 4: goto L_0x0064;
            case 5: goto L_0x006c;
            case 6: goto L_0x0075;
            default: goto L_0x003c;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u0011ND\u0012^-LD\fH";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u0001GW\u0017D\u0011Gz\rD\u0001V";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u0013RU>C\u0013O@";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0002IB>C\u0013O@";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005c, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u001eKS\u0004r\u0001GF\u000eC\u0016Q";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "'vcL\u0015";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
        r3[r2] = r1;
        r2 = 7;
        r1 = "x^(\u001d x^/lQ{";
        r0 = 6;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0075, code lost:
        r3[r2] = r1;
        cn.jpush.android.util.ak.z = r3;
        cn.jpush.android.util.ak.a = cn.jpush.android.util.ak.class.getSimpleName();
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0081, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0082, code lost:
        r9 = 'r';
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0085, code lost:
        r9 = '\"';
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0088, code lost:
        r9 = '%';
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x008b, code lost:
        r9 = 'a';
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (r5 <= 1) goto L_0x0012;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0012, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x0082;
            case 1: goto L_0x0085;
            case 2: goto L_0x0088;
            case 3: goto L_0x008b;
            default: goto L_0x001e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        r9 = '-';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            r0 = 8
            java.lang.String[] r3 = new java.lang.String[r0]
            r2 = 0
            java.lang.String r1 = "\u0013AQ\b[\u001bV\\"
            r0 = -1
            r4 = r3
        L_0x0009:
            char[] r1 = r1.toCharArray()
            int r5 = r1.length
            r6 = 0
            r7 = 1
            if (r5 > r7) goto L_0x002e
        L_0x0012:
            r7 = r1
            r8 = r6
            r11 = r5
            r5 = r1
            r1 = r11
        L_0x0017:
            char r10 = r5[r6]
            int r9 = r8 % 5
            switch(r9) {
                case 0: goto L_0x0082;
                case 1: goto L_0x0085;
                case 2: goto L_0x0088;
                case 3: goto L_0x008b;
                default: goto L_0x001e;
            }
        L_0x001e:
            r9 = 45
        L_0x0020:
            r9 = r9 ^ r10
            char r9 = (char) r9
            r5[r6] = r9
            int r6 = r8 + 1
            if (r1 != 0) goto L_0x002c
            r5 = r7
            r8 = r6
            r6 = r1
            goto L_0x0017
        L_0x002c:
            r5 = r1
            r1 = r7
        L_0x002e:
            if (r5 > r6) goto L_0x0012
            java.lang.String r5 = new java.lang.String
            r5.<init>(r1)
            java.lang.String r1 = r5.intern()
            switch(r0) {
                case 0: goto L_0x0044;
                case 1: goto L_0x004c;
                case 2: goto L_0x0054;
                case 3: goto L_0x005c;
                case 4: goto L_0x0064;
                case 5: goto L_0x006c;
                case 6: goto L_0x0075;
                default: goto L_0x003c;
            }
        L_0x003c:
            r3[r2] = r1
            r2 = 1
            java.lang.String r1 = "\u0011ND\u0012^-LD\fH"
            r0 = 0
            r3 = r4
            goto L_0x0009
        L_0x0044:
            r3[r2] = r1
            r2 = 2
            java.lang.String r1 = "\u0001GW\u0017D\u0011Gz\rD\u0001V"
            r0 = 1
            r3 = r4
            goto L_0x0009
        L_0x004c:
            r3[r2] = r1
            r2 = 3
            java.lang.String r1 = "\u0013RU>C\u0013O@"
            r0 = 2
            r3 = r4
            goto L_0x0009
        L_0x0054:
            r3[r2] = r1
            r2 = 4
            java.lang.String r1 = "\u0002IB>C\u0013O@"
            r0 = 3
            r3 = r4
            goto L_0x0009
        L_0x005c:
            r3[r2] = r1
            r2 = 5
            java.lang.String r1 = "\u001eKS\u0004r\u0001GF\u000eC\u0016Q"
            r0 = 4
            r3 = r4
            goto L_0x0009
        L_0x0064:
            r3[r2] = r1
            r2 = 6
            java.lang.String r1 = "'vcL\u0015"
            r0 = 5
            r3 = r4
            goto L_0x0009
        L_0x006c:
            r3[r2] = r1
            r2 = 7
            java.lang.String r1 = "x^(\u001d x^/lQ{"
            r0 = 6
            r3 = r4
            goto L_0x0009
        L_0x0075:
            r3[r2] = r1
            cn.jpush.android.util.ak.z = r4
            java.lang.Class<cn.jpush.android.util.ak> r0 = cn.jpush.android.util.ak.class
            java.lang.String r0 = r0.getSimpleName()
            cn.jpush.android.util.ak.a = r0
            return
        L_0x0082:
            r9 = 114(0x72, float:1.6E-43)
            goto L_0x0020
        L_0x0085:
            r9 = 34
            goto L_0x0020
        L_0x0088:
            r9 = 37
            goto L_0x0020
        L_0x008b:
            r9 = 97
            goto L_0x0020
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.ak.<clinit>():void");
    }

    private static String a(String str, int i) {
        if (str == null) {
            return str;
        }
        String replaceAll = Pattern.compile(z[7]).matcher(str).replaceAll("");
        try {
            byte[] bytes = replaceAll.getBytes();
            return bytes.length > 30 ? replaceAll.substring(0, new String(bytes, 0, 30, z[6]).length()) : replaceAll;
        } catch (UnsupportedEncodingException e) {
            e.getMessage();
            ac.e();
            return replaceAll;
        }
    }

    private static Set<String> a(ActivityManager activityManager) {
        HashSet hashSet = new HashSet();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
            String[] strArr = runningAppProcessInfo.pkgList;
            for (String str : strArr) {
                hashSet.add(str);
            }
        }
        return hashSet;
    }

    private static JSONArray a(ActivityManager activityManager, PackageManager packageManager) {
        JSONArray jSONArray = new JSONArray();
        Set<String> a2 = a(activityManager);
        List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(8192);
        List<ActivityManager.RunningServiceInfo> runningServices = activityManager.getRunningServices(200);
        Collections.sort(installedApplications, new ApplicationInfo.DisplayNameComparator(packageManager));
        long elapsedRealtime = SystemClock.elapsedRealtime();
        for (ApplicationInfo applicationInfo : installedApplications) {
            Object a3 = a(applicationInfo.loadLabel(packageManager).toString(), 30);
            if (a2.contains(applicationInfo.packageName)) {
                JSONObject jSONObject = new JSONObject();
                JSONArray jSONArray2 = new JSONArray();
                for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
                    if (runningServiceInfo.service.getPackageName().equals(applicationInfo.packageName)) {
                        JSONObject jSONObject2 = new JSONObject();
                        long round = Math.round((float) ((elapsedRealtime - runningServiceInfo.activeSince) / 1000));
                        try {
                            jSONObject2.put(z[1], runningServiceInfo.service.getShortClassName());
                            jSONObject2.put(z[5], round);
                            jSONArray2.put(jSONObject2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    jSONObject.put(z[3], a3);
                    jSONObject.put(z[4], applicationInfo.packageName);
                    jSONObject.put(z[2], jSONArray2);
                    jSONArray.put(jSONObject);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return jSONArray;
    }

    public static void a(Context context) {
        ac.b();
        JSONArray a2 = a((ActivityManager) context.getSystemService(z[0]), context.getPackageManager());
        if (a2 != null && a2.length() != 0) {
            ah.a(context, a2);
        }
    }
}
