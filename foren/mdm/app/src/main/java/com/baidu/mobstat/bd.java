package com.baidu.mobstat;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bd extends WebViewClient {
    private WeakReference<Context> a;
    private WebViewClient b;

    public bd(Context context, WebViewClient webViewClient) {
        this.a = new WeakReference<>(context);
        this.b = webViewClient;
    }

    private void a(String str) {
        JSONObject jSONObject = new JSONObject(str);
        String string = jSONObject.getString("action");
        JSONObject jSONObject2 = jSONObject.getJSONObject("obj");
        Context context = this.a.get();
        if (context != null) {
            if ("onPageStart".equals(string)) {
                StatService.onPageStart(context, jSONObject2.getString("page"));
            } else if ("onPageEnd".equals(string)) {
                StatService.onPageEnd(context, jSONObject2.getString("page"));
            } else if ("onEvent".equals(string)) {
                StatService.onEvent(context, jSONObject2.getString("event_id"), jSONObject2.getString("label"), jSONObject2.getInt("acc"));
            } else if ("onEventStart".equals(string)) {
                StatService.onEventStart(context, jSONObject2.getString("event_id"), jSONObject2.getString("label"));
            } else if ("onEventEnd".equals(string)) {
                StatService.onEventEnd(context, jSONObject2.getString("event_id"), jSONObject2.getString("label"));
            } else if ("onEventDuration".equals(string)) {
                StatService.onEventDuration(context, jSONObject2.getString("event_id"), jSONObject2.getString("label"), jSONObject2.getLong("duration"));
            }
        }
    }

    @Override // android.webkit.WebViewClient
    public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
        if (this.b != null) {
            this.b.doUpdateVisitedHistory(webView, str, z);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onFormResubmission(WebView webView, Message message, Message message2) {
        if (this.b != null) {
            this.b.onFormResubmission(webView, message, message2);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onLoadResource(WebView webView, String str) {
        if (this.b != null) {
            this.b.onLoadResource(webView, str);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        if (this.b != null) {
            this.b.onPageFinished(webView, str);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.b != null) {
            this.b.onPageStarted(webView, str, bitmap);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        if (this.b != null) {
            this.b.onReceivedError(webView, i, str, str2);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
        if (this.b != null) {
            this.b.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
        if (this.b != null) {
            this.b.onReceivedLoginRequest(webView, str, str2, str3);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (this.b != null) {
            this.b.onReceivedSslError(webView, sslErrorHandler, sslError);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onScaleChanged(WebView webView, float f, float f2) {
        if (this.b != null) {
            this.b.onScaleChanged(webView, f, f2);
        }
    }

    @Override // android.webkit.WebViewClient
    @Deprecated
    public void onTooManyRedirects(WebView webView, Message message, Message message2) {
        if (this.b != null) {
            this.b.onTooManyRedirects(webView, message, message2);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
        if (this.b != null) {
            this.b.onUnhandledKeyEvent(webView, keyEvent);
        }
    }

    @Override // android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        if (this.b != null) {
            return this.b.shouldInterceptRequest(webView, str);
        }
        return null;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        if (this.b != null) {
            return this.b.shouldOverrideKeyEvent(webView, keyEvent);
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003d A[RETURN, SYNTHETIC] */
    @Override // android.webkit.WebViewClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean shouldOverrideUrlLoading(android.webkit.WebView r3, java.lang.String r4) {
        /*
            r2 = this;
            java.lang.String r0 = "BaiduStatJSInterface"
            java.lang.String r1 = "shouldOverrideUrlLoading"
            android.util.Log.d(r0, r1)
            java.lang.String r0 = "UTF-8"
            java.lang.String r0 = java.net.URLDecoder.decode(r4, r0)     // Catch: UnsupportedEncodingException -> 0x0025, JSONException -> 0x0036
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch: UnsupportedEncodingException -> 0x0041, JSONException -> 0x003f
            if (r1 != 0) goto L_0x002b
            java.lang.String r1 = "bmtj:"
            boolean r1 = r0.startsWith(r1)     // Catch: UnsupportedEncodingException -> 0x0041, JSONException -> 0x003f
            if (r1 == 0) goto L_0x002b
            r1 = 5
            java.lang.String r1 = r0.substring(r1)     // Catch: UnsupportedEncodingException -> 0x0041, JSONException -> 0x003f
            r2.a(r1)     // Catch: UnsupportedEncodingException -> 0x0041, JSONException -> 0x003f
            r0 = 1
        L_0x0024:
            return r0
        L_0x0025:
            r0 = move-exception
            r1 = r0
            r0 = r4
        L_0x0028:
            com.baidu.mobstat.cr.b(r1)
        L_0x002b:
            android.webkit.WebViewClient r1 = r2.b
            if (r1 == 0) goto L_0x003d
            android.webkit.WebViewClient r1 = r2.b
            boolean r0 = r1.shouldOverrideUrlLoading(r3, r0)
            goto L_0x0024
        L_0x0036:
            r0 = move-exception
            r1 = r0
            r0 = r4
        L_0x0039:
            com.baidu.mobstat.cr.b(r1)
            goto L_0x002b
        L_0x003d:
            r0 = 0
            goto L_0x0024
        L_0x003f:
            r1 = move-exception
            goto L_0x0039
        L_0x0041:
            r1 = move-exception
            goto L_0x0028
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.bd.shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String):boolean");
    }
}
