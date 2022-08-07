package com.alipay.sdk.app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Handler;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alipay.sdk.app.statistic.c;
import com.alipay.sdk.util.l;
import com.alipay.sdk.widget.a;

/* loaded from: classes.dex */
public final class b extends WebViewClient {
    Activity a;
    Handler b;
    boolean c;
    private boolean d;
    private a e;
    private Runnable f = new f(this);

    public b(Activity activity) {
        this.a = activity;
        this.b = new Handler(this.a.getMainLooper());
    }

    @Override // android.webkit.WebViewClient
    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        this.c = true;
        super.onReceivedError(webView, i, str, str2);
    }

    @Override // android.webkit.WebViewClient
    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        com.alipay.sdk.app.statistic.a.a(c.a, c.q, "证书错误");
        if (this.d) {
            sslErrorHandler.proceed();
            this.d = false;
            return;
        }
        this.a.runOnUiThread(new c(this, sslErrorHandler));
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return l.a(webView, str, this.a);
    }

    @Override // android.webkit.WebViewClient
    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.e == null) {
            this.e = new a(this.a, a.a);
            this.e.e = true;
        }
        this.e.a();
        this.b.postDelayed(this.f, 30000L);
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(WebView webView, String str) {
        b();
        this.b.removeCallbacks(this.f);
    }

    private void a() {
        if (this.e == null) {
            this.e = new a(this.a, a.a);
            this.e.e = true;
        }
        this.e.a();
    }

    public void b() {
        if (this.e != null) {
            this.e.b();
        }
        this.e = null;
    }

    private void c() {
        this.b = null;
        this.a = null;
    }

    private boolean d() {
        return this.c;
    }
}
