package com.tencent.smtt.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.a.d;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class s {
    private static s a = null;
    private Handler b;

    private s() {
        this.b = null;
        this.b = new t(this, Looper.getMainLooper());
    }

    private int a(Context context) {
        if (a(TbsConfig.APP_QB, context, 128) != null) {
            return 2;
        }
        return !TextUtils.isEmpty(e(context)) ? 1 : 0;
    }

    public static s a() {
        if (a == null) {
            a = new s();
        }
        return a;
    }

    private Map<String, String> a(String str) {
        HashMap hashMap;
        String[] split;
        if (str == null || str.length() <= 0) {
            return null;
        }
        try {
            hashMap = new HashMap();
            try {
                String[] split2 = str.split("\n");
                for (String str2 : split2) {
                    if (str2 != null && str2.length() > 0 && (split = str2.trim().split("=", 2)) != null && split.length >= 2) {
                        String str3 = split[0];
                        String str4 = split[1];
                        if (str3 != null && str3.length() > 0) {
                            hashMap.put(str3, str4);
                        }
                    }
                }
                return hashMap;
            } catch (Throwable th) {
                th = th;
                th.printStackTrace();
                return hashMap;
            }
        } catch (Throwable th2) {
            th = th2;
            hashMap = null;
        }
    }

    private void b(Context context) {
        Message message = new Message();
        message.what = 1;
        message.obj = new Object[]{context};
        this.b.sendMessage(message);
    }

    public static void b(Context context, String str) {
        if (context != null && str != null && str.length() > 0) {
            d.a(context, str, null, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Context context) {
        try {
            String e = e(context);
            if (!TextUtils.isEmpty(e)) {
                File file = new File(e);
                if (file.exists()) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.addFlags(268435456);
                    intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                    context.startActivity(intent);
                    u.a(context).a(context.getApplicationInfo().processName);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void c(Context context, String str) {
        Message message = new Message();
        message.what = 0;
        message.obj = new Object[]{context, str};
        this.b.sendMessage(message);
    }

    private Map<String, String> d(Context context) {
        Throwable th;
        IOException e;
        Throwable th2;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        String str = "";
        try {
            try {
                File file = new File("/data/data/com.tencent.mm/app_tbs/share/QQBrowserDownloadInfo.ini");
                if (file == null || !file.exists()) {
                    file = new File("/data/data/com.tencent.mobileqq/app_tbs/share/QQBrowserDownloadInfo.ini");
                }
                if (file == null || !file.exists()) {
                    file = new File("/data/data/com.qzone/app_tbs/share/QQBrowserDownloadInfo.ini");
                }
                if (file == null || !file.exists()) {
                    fileInputStream = null;
                } else {
                    fileInputStream = new FileInputStream(file);
                    try {
                        byte[] b = k.b(fileInputStream);
                        if (b != null) {
                            str = new String(b, "utf-8");
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        fileInputStream2 = fileInputStream;
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        e = e3;
                        e.printStackTrace();
                        return a(str);
                    }
                }
            } catch (Throwable th4) {
                th2 = th4;
            }
            return a(str);
        } catch (Throwable th5) {
            th = th5;
        }
    }

    private void d(Context context, String str) {
        Message message = new Message();
        message.what = 2;
        message.obj = new Object[]{context, str};
        this.b.sendMessage(message);
    }

    private String e(Context context) {
        Map<String, String> d = d(context);
        if (d != null && d.size() > 0) {
            String str = d.get("FileDownloadPath");
            String str2 = d.get("FileDownloadVerifyInfo");
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                return "";
            }
            File file = new File(str);
            if (file.exists() && TextUtils.equals(o.a(file.lastModified() + ""), str2)) {
                return str;
            }
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Context context, String str) {
        if (context != null && str != null && str.length() > 0) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.addFlags(268435456);
            context.startActivity(intent);
        }
    }

    public PackageInfo a(String str, Context context, int i) {
        if (context == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, i);
        } catch (Throwable th) {
            return null;
        }
    }

    public boolean a(Context context, String str) {
        String str2;
        String str3 = null;
        if (str != null) {
            try {
                if (str.startsWith("tbsqbdownload://")) {
                    String[] split = str.substring("tbsqbdownload://".length()).split(",");
                    if (split.length > 1) {
                        String[] split2 = split[0].split("=");
                        str2 = (split2.length <= 1 || !"url".equalsIgnoreCase(split2[0])) ? null : split[0].substring("url".length() + 1);
                        String[] split3 = split[1].split("=");
                        if (split3.length > 1 && "downloadurl".equalsIgnoreCase(split3[0])) {
                            str3 = split[1].substring("downloadurl".length() + 1);
                        }
                    } else {
                        str2 = null;
                    }
                    if (str2 == null || str3 == null) {
                        return false;
                    }
                    int a2 = a(context);
                    if (a2 == 2) {
                        c(context, str2);
                        return true;
                    } else if (a2 == 1) {
                        b(context);
                        return true;
                    } else if (a2 != 0) {
                        return true;
                    } else {
                        d(context, str3);
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
