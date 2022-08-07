package com.tencent.open.yyb;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.tencent.connect.common.Constants;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class a {

    /* compiled from: ProGuard */
    /* renamed from: com.tencent.open.yyb.a$a */
    /* loaded from: classes2.dex */
    public static class C0089a {
        public String a;
        public String b;
        public long c;
    }

    public static void a(Context context, String str, String str2, String str3, String str4) {
        if (!TextUtils.isEmpty(str)) {
            CookieSyncManager.createInstance(context);
            CookieManager instance = CookieManager.getInstance();
            instance.setAcceptCookie(true);
            String str5 = null;
            if (Uri.parse(str).getHost().toLowerCase().endsWith(".qq.com")) {
                str5 = ".qq.com";
            }
            instance.setCookie(str, b("logintype", "MOBILEQ", str5));
            instance.setCookie(str, b("qopenid", str2, str5));
            instance.setCookie(str, b("qaccesstoken", str3, str5));
            instance.setCookie(str, b("openappid", str4, str5));
            CookieSyncManager.getInstance().sync();
        }
    }

    private static String b(String str, String str2, String str3) {
        String str4 = str + "=" + str2;
        if (str3 == null) {
            return str4;
        }
        return (str4 + "; path=/") + "; domain=" + str3;
    }

    public static Drawable a(String str, Context context) {
        return a(str, context, new Rect(0, 0, 0, 0));
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x00ac A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.drawable.Drawable a(java.lang.String r8, android.content.Context r9, android.graphics.Rect r10) {
        /*
            Method dump skipped, instructions count: 201
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.yyb.a.a(java.lang.String, android.content.Context, android.graphics.Rect):android.graphics.drawable.Drawable");
    }

    public static void a(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString("uin", Constants.DEFAULT_UIN);
        bundle.putString("action", str2);
        bundle.putString("appid", str);
        bundle.putString("via", str3);
        new b().execute(bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public static class b extends AsyncTask<Bundle, Void, Void> {
        private b() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:8:0x0017, code lost:
            if (android.text.TextUtils.isEmpty(r0) == false) goto L_0x0019;
         */
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Void doInBackground(android.os.Bundle... r6) {
            /*
                r5 = this;
                r4 = 0
                if (r6 != 0) goto L_0x0004
            L_0x0003:
                return r4
            L_0x0004:
                java.lang.String r1 = "http://analy.qq.com/cgi-bin/mapp_apptrace"
                int r0 = r6.length
                r2 = 2
                if (r0 != r2) goto L_0x0054
                r0 = 1
                r0 = r6[r0]
                java.lang.String r2 = "uri"
                java.lang.String r0 = r0.getString(r2)
                boolean r2 = android.text.TextUtils.isEmpty(r0)
                if (r2 != 0) goto L_0x0054
            L_0x0019:
                r1 = 0
                java.lang.String r2 = "GET"
                r3 = 0
                r3 = r6[r3]     // Catch: Exception -> 0x0048
                com.tencent.open.utils.Util$Statistic r0 = com.tencent.open.utils.HttpUtils.openUrl2(r1, r0, r2, r3)     // Catch: Exception -> 0x0048
                java.lang.String r0 = r0.response     // Catch: Exception -> 0x0048
                org.json.JSONObject r0 = com.tencent.open.utils.Util.parseJson(r0)     // Catch: Exception -> 0x0048
                java.lang.String r1 = "ret"
                int r0 = r0.getInt(r1)     // Catch: Exception -> 0x0048
                java.lang.String r1 = "openSDK_LOG.AppbarUtil"
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: Exception -> 0x0048
                r2.<init>()     // Catch: Exception -> 0x0048
                java.lang.String r3 = "-->(ViaAsyncTask)doInBackground : ret = "
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch: Exception -> 0x0048
                java.lang.StringBuilder r0 = r2.append(r0)     // Catch: Exception -> 0x0048
                java.lang.String r0 = r0.toString()     // Catch: Exception -> 0x0048
                com.tencent.open.a.f.b(r1, r0)     // Catch: Exception -> 0x0048
                goto L_0x0003
            L_0x0048:
                r0 = move-exception
                java.lang.String r1 = "openSDK_LOG.AppbarUtil"
                java.lang.String r2 = "-->(ViaAsyncTask)doInBackground : Exception = "
                com.tencent.open.a.f.b(r1, r2, r0)
                r0.printStackTrace()
                goto L_0x0003
            L_0x0054:
                r0 = r1
                goto L_0x0019
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.yyb.a.b.doInBackground(android.os.Bundle[]):java.lang.Void");
        }
    }
}
