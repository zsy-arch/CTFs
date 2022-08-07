package com.tencent.open.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.view.MotionEvent;
import com.tencent.connect.common.Constants;
import com.tencent.open.a.f;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.security.MessageDigest;

/* compiled from: ProGuard */
/* loaded from: classes.dex */
public class SystemUtils {
    public static final String ACTION_LOGIN = "action_login";
    public static final String ACTION_REQUEST_API = "action_request";
    public static final String ACTION_SHARE = "action_share";
    public static final String H5_SHARE_DATA = "h5_share_data";
    public static final String IS_LOGIN = "is_login";
    public static final String IS_QQ_MOBILE_SHARE = "is_qq_mobile_share";
    public static final String QQDATALINE_CALLBACK_ACTION = "sendToMyComputer";
    public static final String QQFAVORITES_CALLBACK_ACTION = "addToQQFavorites";
    public static final String QQ_SHARE_CALLBACK_ACTION = "shareToQQ";
    public static final String QQ_VERSION_NAME_4_2_0 = "4.2.0";
    public static final String QQ_VERSION_NAME_4_3_0 = "4.3.0";
    public static final String QQ_VERSION_NAME_4_5_0 = "4.5.0";
    public static final String QQ_VERSION_NAME_4_6_0 = "4.6.0";
    public static final String QQ_VERSION_NAME_5_0_0 = "5.0.0";
    public static final String QQ_VERSION_NAME_5_1_0 = "5.1.0";
    public static final String QQ_VERSION_NAME_5_2_0 = "5.2.0";
    public static final String QQ_VERSION_NAME_5_3_0 = "5.3.0";
    public static final String QQ_VERSION_NAME_5_9_5 = "5.9.5";
    public static final String QZONE_SHARE_CALLBACK_ACTION = "shareToQzone";
    public static final String TROOPBAR_CALLBACK_ACTION = "shareToTroopBar";

    public static String getAppVersionName(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static int compareVersion(String str, String str2) {
        if (str == null && str2 == null) {
            return 0;
        }
        if (str != null && str2 == null) {
            return 1;
        }
        if (str == null && str2 != null) {
            return -1;
        }
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int i = 0;
        while (i < split.length && i < split2.length) {
            try {
                int parseInt = Integer.parseInt(split[i]);
                int parseInt2 = Integer.parseInt(split2[i]);
                if (parseInt < parseInt2) {
                    return -1;
                }
                if (parseInt > parseInt2) {
                    return 1;
                }
                i++;
            } catch (NumberFormatException e) {
                return str.compareTo(str2);
            }
        }
        if (split.length > i) {
            return 1;
        }
        return split2.length > i ? -1 : 0;
    }

    public static boolean isAppSignatureValid(Context context, String str, String str2) {
        f.a("openSDK_LOG.SystemUtils", "OpenUi, validateAppSignatureForPackage");
        try {
            for (Signature signature : context.getPackageManager().getPackageInfo(str, 64).signatures) {
                if (Util.encrypt(signature.toCharsString()).equals(str2)) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static String getAppSignatureMD5(Context context, String str) {
        Exception e;
        String str2;
        MessageDigest instance;
        f.a("openSDK_LOG.SystemUtils", "OpenUi, getSignValidString");
        try {
            String packageName = context.getPackageName();
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(packageName, 64).signatures;
            instance = MessageDigest.getInstance("MD5");
            instance.update(signatureArr[0].toByteArray());
            String hexString = Util.toHexString(instance.digest());
            instance.reset();
            f.a("openSDK_LOG.SystemUtils", "-->sign: " + hexString);
            instance.update(Util.getBytesUTF8(packageName + "_" + hexString + "_" + str + ""));
            str2 = Util.toHexString(instance.digest());
        } catch (Exception e2) {
            str2 = "";
            e = e2;
        }
        try {
            instance.reset();
            f.a("openSDK_LOG.SystemUtils", "-->signEncryped: " + str2);
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            f.b("openSDK_LOG.SystemUtils", "OpenUi, getSignValidString error", e);
            return str2;
        }
        return str2;
    }

    public static boolean isActivityExist(Context context, Intent intent) {
        return (context == null || intent == null || context.getPackageManager().queryIntentActivities(intent, 0).size() == 0) ? false : true;
    }

    public static String getAppName(Context context) {
        return context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
    }

    public static int compareQQVersion(Context context, String str) {
        return compareVersion(getAppVersionName(context, "com.tencent.mobileqq"), str);
    }

    public static int compareTimVersion(Context context, String str) {
        return compareVersion(getAppVersionName(context, Constants.PACKAGE_TIM), str);
    }

    @Deprecated
    public static boolean checkMobileQQ(Context context) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo("com.tencent.mobileqq", 0);
        } catch (PackageManager.NameNotFoundException e) {
            f.b("openSDK_LOG.SystemUtils", "checkMobileQQ NameNotFoundException", e);
            e.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo == null) {
            return false;
        }
        String str = packageInfo.versionName;
        try {
            f.b("MobileQQ verson", str);
            String[] split = str.split("\\.");
            int parseInt = Integer.parseInt(split[0]);
            return parseInt > 4 || (parseInt == 4 && Integer.parseInt(split[1]) >= 1);
        } catch (Exception e2) {
            f.b("openSDK_LOG.SystemUtils", "checkMobileQQ Exception", e2);
            e2.printStackTrace();
            return false;
        }
    }

    public static int getAndroidSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static boolean isSupportMultiTouch() {
        Method[] declaredMethods = MotionEvent.class.getDeclaredMethods();
        boolean z = false;
        boolean z2 = false;
        for (Method method : declaredMethods) {
            if (method.getName().equals("getPointerCount")) {
                z2 = true;
            }
            if (method.getName().equals("getPointerId")) {
                z = true;
            }
        }
        if (getAndroidSDKVersion() < 7) {
            return z2 && z;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00c7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00c2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Unknown variable types count: 1 */
    @android.annotation.SuppressLint({"SdCardPath"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean extractSecureLib(java.lang.String r9, java.lang.String r10, int r11) {
        /*
            r2 = 0
            r1 = 1
            r0 = 0
            java.lang.String r3 = "openSDK_LOG.SystemUtils"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "-->extractSecureLib, libName: "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r9)
            java.lang.String r4 = r4.toString()
            com.tencent.open.a.f.c(r3, r4)
            android.content.Context r4 = com.tencent.open.utils.Global.getContext()
            if (r4 != 0) goto L_0x0029
            java.lang.String r1 = "openSDK_LOG.SystemUtils"
            java.lang.String r2 = "-->extractSecureLib, global context is null. "
            com.tencent.open.a.f.c(r1, r2)
        L_0x0028:
            return r0
        L_0x0029:
            java.lang.String r3 = "secure_lib"
            android.content.SharedPreferences r5 = r4.getSharedPreferences(r3, r0)
            java.io.File r3 = new java.io.File
            java.io.File r6 = r4.getFilesDir()
            r3.<init>(r6, r10)
            boolean r6 = r3.exists()
            if (r6 != 0) goto L_0x007a
            java.io.File r6 = r3.getParentFile()
            if (r6 == 0) goto L_0x004d
            boolean r6 = r6.mkdirs()
            if (r6 == 0) goto L_0x004d
            r3.createNewFile()     // Catch: IOException -> 0x0075
        L_0x004d:
            android.content.res.AssetManager r3 = r4.getAssets()     // Catch: Exception -> 0x00a6, all -> 0x00be
            java.io.InputStream r3 = r3.open(r9)     // Catch: Exception -> 0x00a6, all -> 0x00be
            r6 = 0
            java.io.FileOutputStream r2 = r4.openFileOutput(r10, r6)     // Catch: Exception -> 0x00d7, all -> 0x00d5
            a(r3, r2)     // Catch: Exception -> 0x00d7, all -> 0x00d5
            android.content.SharedPreferences$Editor r4 = r5.edit()     // Catch: Exception -> 0x00d7, all -> 0x00d5
            java.lang.String r5 = "version"
            r4.putInt(r5, r11)     // Catch: Exception -> 0x00d7, all -> 0x00d5
            r4.commit()     // Catch: Exception -> 0x00d7, all -> 0x00d5
            if (r3 == 0) goto L_0x006e
            r3.close()     // Catch: IOException -> 0x00cb
        L_0x006e:
            if (r2 == 0) goto L_0x0073
            r2.close()     // Catch: IOException -> 0x00cd
        L_0x0073:
            r0 = r1
            goto L_0x0028
        L_0x0075:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x004d
        L_0x007a:
            java.lang.String r3 = "version"
            int r3 = r5.getInt(r3, r0)
            java.lang.String r6 = "openSDK_LOG.SystemUtils"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "-->extractSecureLib, libVersion: "
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.StringBuilder r7 = r7.append(r11)
            java.lang.String r8 = " | oldVersion: "
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.StringBuilder r7 = r7.append(r3)
            java.lang.String r7 = r7.toString()
            com.tencent.open.a.f.c(r6, r7)
            if (r11 != r3) goto L_0x004d
            r0 = r1
            goto L_0x0028
        L_0x00a6:
            r1 = move-exception
            r3 = r2
        L_0x00a8:
            java.lang.String r4 = "openSDK_LOG.SystemUtils"
            java.lang.String r5 = "-->extractSecureLib, when copy lib execption."
            com.tencent.open.a.f.b(r4, r5, r1)     // Catch: all -> 0x00d5
            if (r3 == 0) goto L_0x00b4
            r3.close()     // Catch: IOException -> 0x00cf
        L_0x00b4:
            if (r2 == 0) goto L_0x0028
            r2.close()     // Catch: IOException -> 0x00bb
            goto L_0x0028
        L_0x00bb:
            r1 = move-exception
            goto L_0x0028
        L_0x00be:
            r0 = move-exception
            r3 = r2
        L_0x00c0:
            if (r3 == 0) goto L_0x00c5
            r3.close()     // Catch: IOException -> 0x00d1
        L_0x00c5:
            if (r2 == 0) goto L_0x00ca
            r2.close()     // Catch: IOException -> 0x00d3
        L_0x00ca:
            throw r0
        L_0x00cb:
            r0 = move-exception
            goto L_0x006e
        L_0x00cd:
            r0 = move-exception
            goto L_0x0073
        L_0x00cf:
            r1 = move-exception
            goto L_0x00b4
        L_0x00d1:
            r1 = move-exception
            goto L_0x00c5
        L_0x00d3:
            r1 = move-exception
            goto L_0x00ca
        L_0x00d5:
            r0 = move-exception
            goto L_0x00c0
        L_0x00d7:
            r1 = move-exception
            goto L_0x00a8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.utils.SystemUtils.extractSecureLib(java.lang.String, java.lang.String, int):boolean");
    }

    @SuppressLint({"SdCardPath"})
    public static boolean isLibExtracted(String str, int i) {
        Context context = Global.getContext();
        if (context == null) {
            f.c("openSDK_LOG.SystemUtils", "-->isSecureLibExtracted, global context is null. ");
            return false;
        }
        File file = new File(context.getFilesDir(), str);
        SharedPreferences sharedPreferences = context.getSharedPreferences("secure_lib", 0);
        if (!file.exists()) {
            return false;
        }
        int i2 = sharedPreferences.getInt("version", 0);
        f.c("openSDK_LOG.SystemUtils", "-->extractSecureLib, libVersion: " + i + " | oldVersion: " + i2);
        if (i == i2) {
            return true;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt("version", i);
        edit.commit();
        return false;
    }

    private static long a(InputStream inputStream, OutputStream outputStream) throws IOException {
        long j = 0;
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
                j += read;
            } else {
                f.c("openSDK_LOG.SystemUtils", "-->copy, copyed size is: " + j);
                return j;
            }
        }
    }

    public static int getRequestCodeFromCallback(String str) {
        if (QQ_SHARE_CALLBACK_ACTION.equals(str)) {
            return Constants.REQUEST_QQ_SHARE;
        }
        if (QZONE_SHARE_CALLBACK_ACTION.equals(str)) {
            return Constants.REQUEST_QZONE_SHARE;
        }
        if (QQFAVORITES_CALLBACK_ACTION.equals(str)) {
            return Constants.REQUEST_QQ_FAVORITES;
        }
        if (QQDATALINE_CALLBACK_ACTION.equals(str)) {
            return Constants.REQUEST_SEND_TO_MY_COMPUTER;
        }
        if (TROOPBAR_CALLBACK_ACTION.equals(str)) {
            return Constants.REQUEST_SHARE_TO_TROOP_BAR;
        }
        if (ACTION_LOGIN.equals(str)) {
            return Constants.REQUEST_LOGIN;
        }
        if (ACTION_REQUEST_API.equals(str)) {
            return Constants.REQUEST_API;
        }
        return -1;
    }

    public static String getActionFromRequestcode(int i) {
        if (i == 10103) {
            return QQ_SHARE_CALLBACK_ACTION;
        }
        if (i == 10104) {
            return QZONE_SHARE_CALLBACK_ACTION;
        }
        if (i == 10105) {
            return QQFAVORITES_CALLBACK_ACTION;
        }
        if (i == 10106) {
            return QQDATALINE_CALLBACK_ACTION;
        }
        if (i == 10107) {
            return TROOPBAR_CALLBACK_ACTION;
        }
        if (i == 11101) {
            return ACTION_LOGIN;
        }
        if (i == 10100) {
            return ACTION_REQUEST_API;
        }
        return null;
    }
}
