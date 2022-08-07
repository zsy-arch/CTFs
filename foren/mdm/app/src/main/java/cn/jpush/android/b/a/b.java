package cn.jpush.android.b.a;

import android.webkit.WebView;
import cn.jpush.android.api.p;
import cn.jpush.android.util.ac;

/* loaded from: classes.dex */
public class b {
    private static final String TAG;
    private static f mWebViewHelper;
    private static final String z;

    /* JADX WARN: Removed duplicated region for block: B:12:0x0024 A[LOOP:1: B:7:0x0014->B:12:0x0024, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0028 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001b  */
    static {
        /*
            r8 = 80
            r1 = 0
            java.lang.String r2 = "\faS\u0004)\u0011w\u00146\"\u000e\u007f\u0014'5\u0003(\u0014"
            r0 = -1
        L_0x0006:
            char[] r2 = r2.toCharArray()
            int r3 = r2.length
            r4 = 1
            if (r3 > r4) goto L_0x004d
            r4 = r1
        L_0x000f:
            r5 = r2
            r6 = r4
            r10 = r3
            r3 = r2
            r2 = r10
        L_0x0014:
            char r9 = r3[r4]
            int r7 = r6 % 5
            switch(r7) {
                case 0: goto L_0x0042;
                case 1: goto L_0x0045;
                case 2: goto L_0x0048;
                case 3: goto L_0x004b;
                default: goto L_0x001b;
            }
        L_0x001b:
            r7 = r8
        L_0x001c:
            r7 = r7 ^ r9
            char r7 = (char) r7
            r3[r4] = r7
            int r4 = r6 + 1
            if (r2 != 0) goto L_0x0028
            r3 = r5
            r6 = r4
            r4 = r2
            goto L_0x0014
        L_0x0028:
            r3 = r2
            r2 = r5
        L_0x002a:
            if (r3 > r4) goto L_0x000f
            java.lang.String r3 = new java.lang.String
            r3.<init>(r2)
            java.lang.String r2 = r3.intern()
            switch(r0) {
                case 0: goto L_0x003f;
                default: goto L_0x0038;
            }
        L_0x0038:
            cn.jpush.android.b.a.b.z = r2
            java.lang.String r0 = ")}G$\u001a\u0012AW? \u0004"
            r2 = r0
            r0 = r1
            goto L_0x0006
        L_0x003f:
            cn.jpush.android.b.a.b.TAG = r2
            return
        L_0x0042:
            r7 = 97
            goto L_0x001c
        L_0x0045:
            r7 = 18
            goto L_0x001c
        L_0x0048:
            r7 = 52
            goto L_0x001c
        L_0x004b:
            r7 = r8
            goto L_0x001c
        L_0x004d:
            r4 = r1
            goto L_0x002a
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.b.a.b.<clinit>():void");
    }

    public static void click(WebView webView, String str, String str2, String str3) {
        if (mWebViewHelper != null) {
            mWebViewHelper.b(str, str2, str3);
        }
    }

    public static void close(WebView webView) {
        if (mWebViewHelper != null) {
            mWebViewHelper.a();
        }
    }

    public static void createShortcut(WebView webView, String str, String str2, String str3) {
        if (mWebViewHelper != null) {
            mWebViewHelper.a(str, str2, str3);
        }
    }

    public static void download(WebView webView, String str) {
        if (mWebViewHelper != null) {
            mWebViewHelper.c(str);
        }
    }

    public static void download(WebView webView, String str, String str2) {
        if (mWebViewHelper != null) {
            mWebViewHelper.c(str, str2);
        }
    }

    public static void download(WebView webView, String str, String str2, String str3) {
        if (mWebViewHelper != null) {
            f fVar = mWebViewHelper;
            new StringBuilder(z).append(str3);
            ac.a();
            fVar.c(str, str2);
        }
    }

    public static void executeMsgMessage(WebView webView, String str) {
        if (mWebViewHelper != null) {
            mWebViewHelper.e(str);
        }
    }

    public static void setWebViewHelper(f fVar) {
        if (fVar != null) {
            mWebViewHelper = fVar;
        }
    }

    public static void showTitleBar(WebView webView) {
        if (mWebViewHelper != null) {
            mWebViewHelper.b();
        }
    }

    public static void showToast(WebView webView, String str) {
        if (mWebViewHelper != null) {
            mWebViewHelper.d(str);
        }
    }

    public static void startActivityByIntent(WebView webView, String str, String str2) {
        if (mWebViewHelper != null) {
            mWebViewHelper.b(str, str2);
        }
    }

    public static void startActivityByName(WebView webView, String str, String str2) {
        if (mWebViewHelper != null) {
            mWebViewHelper.a(str, str2);
        }
    }

    public static void startActivityByNameWithSystemAlert(WebView webView, String str, String str2) {
        if (p.a != null) {
            p.a.a(str, str2);
        }
    }

    public static void startMainActivity(WebView webView, String str) {
        if (mWebViewHelper != null) {
            mWebViewHelper.b(str);
        }
    }

    public static void startPushActivity(WebView webView, String str) {
        if (mWebViewHelper != null) {
            mWebViewHelper.f(str);
        }
    }

    public static void triggerNativeAction(WebView webView, String str) {
        if (mWebViewHelper != null) {
            mWebViewHelper.a(str);
        }
    }
}
