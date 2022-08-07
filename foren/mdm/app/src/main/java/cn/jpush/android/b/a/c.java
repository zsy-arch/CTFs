package cn.jpush.android.b.a;

import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/* loaded from: classes.dex */
public class c extends WebChromeClient {
    private static final String[] z;
    private final String a = z[0];
    private d b;
    private boolean c;

    /* JADX WARN: Removed duplicated region for block: B:12:0x0029 A[LOOP:1: B:7:0x0018->B:12:0x0029, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x002d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001f  */
    static {
        /*
            r4 = 1
            r1 = 0
            r0 = 2
            java.lang.String[] r3 = new java.lang.String[r0]
            java.lang.String r2 = "\u0017Q\r(&*Z\u0003\u000e-,P\n(\u00062V\u0002#1"
            r0 = -1
            r5 = r3
            r6 = r3
            r3 = r1
        L_0x000b:
            char[] r2 = r2.toCharArray()
            int r7 = r2.length
            if (r7 > r4) goto L_0x0058
            r8 = r1
        L_0x0013:
            r9 = r2
            r10 = r8
            r13 = r7
            r7 = r2
            r2 = r13
        L_0x0018:
            char r12 = r7[r8]
            int r11 = r10 % 5
            switch(r11) {
                case 0: goto L_0x004c;
                case 1: goto L_0x004f;
                case 2: goto L_0x0052;
                case 3: goto L_0x0055;
                default: goto L_0x001f;
            }
        L_0x001f:
            r11 = 69
        L_0x0021:
            r11 = r11 ^ r12
            char r11 = (char) r11
            r7[r8] = r11
            int r8 = r10 + 1
            if (r2 != 0) goto L_0x002d
            r7 = r9
            r10 = r8
            r8 = r2
            goto L_0x0018
        L_0x002d:
            r7 = r2
            r2 = r9
        L_0x002f:
            if (r7 > r8) goto L_0x0013
            java.lang.String r7 = new java.lang.String
            r7.<init>(r2)
            java.lang.String r2 = r7.intern()
            switch(r0) {
                case 0: goto L_0x0047;
                default: goto L_0x003d;
            }
        L_0x003d:
            r5[r3] = r2
            java.lang.String r0 = "~V\t' =KG'6~V\t9 ,Y\u0006. ~\\\b 52Z\u0013()'\u001f\b#e.M\b*7;L\u0014m"
            r2 = r0
            r3 = r4
            r5 = r6
            r0 = r1
            goto L_0x000b
        L_0x0047:
            r5[r3] = r2
            cn.jpush.android.b.a.c.z = r6
            return
        L_0x004c:
            r11 = 94
            goto L_0x0021
        L_0x004f:
            r11 = 63
            goto L_0x0021
        L_0x0052:
            r11 = 103(0x67, float:1.44E-43)
            goto L_0x0021
        L_0x0055:
            r11 = 77
            goto L_0x0021
        L_0x0058:
            r8 = r1
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.b.a.c.<clinit>():void");
    }

    public c(String str, Class cls) {
        this.b = new d(str, cls);
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        jsResult.confirm();
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        jsPromptResult.confirm(this.b.a(webView, str2));
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public void onProgressChanged(WebView webView, int i) {
        if (i <= 25) {
            this.c = false;
        } else if (!this.c) {
            webView.loadUrl(this.b.a());
            this.c = true;
            Log.d(z[0], z[1] + i);
        }
        super.onProgressChanged(webView, i);
    }
}
