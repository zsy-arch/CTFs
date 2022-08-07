package com.alipay.sdk.auth;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.webkit.ConsoleMessage;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.alipay.sdk.authjs.a;
import com.alipay.sdk.authjs.c;
import com.alipay.sdk.util.l;
import java.lang.reflect.Method;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled", "DefaultLocale"})
/* loaded from: classes.dex */
public class AuthActivity extends Activity {
    static final String a = "params";
    static final String b = "redirectUri";
    private WebView c;
    private String d;
    private com.alipay.sdk.widget.a e;
    private Handler f;
    private boolean g;
    private boolean h;
    private Runnable i = new d(this);

    public static /* synthetic */ void a(AuthActivity authActivity, com.alipay.sdk.authjs.a aVar) {
        if (authActivity.c != null && aVar != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(com.alipay.sdk.authjs.a.e, aVar.i);
                jSONObject.put(com.alipay.sdk.authjs.a.g, aVar.k);
                jSONObject.put("param", aVar.m);
                jSONObject.put(com.alipay.sdk.authjs.a.h, aVar.l);
                authActivity.runOnUiThread(new c(authActivity, String.format("AlipayJSBridge._invokeJS(%s)", jSONObject.toString())));
            } catch (JSONException e) {
            }
        }
    }

    static /* synthetic */ boolean a(AuthActivity authActivity, String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("http://") || str.startsWith("https://")) {
            return false;
        }
        if (!"SDKLite://h5quit".equalsIgnoreCase(str)) {
            if (TextUtils.equals(str, authActivity.d)) {
                str = str + "?resultCode=150";
            }
            h.a(authActivity, str);
        }
        authActivity.finish();
        return true;
    }

    static /* synthetic */ void b(AuthActivity authActivity, String str) {
        String str2;
        c cVar = new c(authActivity.getApplicationContext(), new b(authActivity));
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(com.alipay.sdk.authjs.a.e);
            try {
                if (!TextUtils.isEmpty(string)) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("param");
                    JSONObject jSONObject3 = jSONObject2 instanceof JSONObject ? jSONObject2 : null;
                    String string2 = jSONObject.getString(com.alipay.sdk.authjs.a.g);
                    String string3 = jSONObject.getString(com.alipay.sdk.authjs.a.d);
                    com.alipay.sdk.authjs.a aVar = new com.alipay.sdk.authjs.a("call");
                    aVar.j = string3;
                    aVar.k = string2;
                    aVar.m = jSONObject3;
                    aVar.i = string;
                    cVar.a(aVar);
                }
            } catch (Exception e) {
                str2 = string;
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        cVar.a(str2, a.EnumC0009a.d);
                    } catch (JSONException e2) {
                    }
                }
            }
        } catch (Exception e3) {
            str2 = null;
        }
    }

    static /* synthetic */ void d(AuthActivity authActivity) {
        try {
            if (authActivity.e == null) {
                authActivity.e = new com.alipay.sdk.widget.a(authActivity, com.alipay.sdk.widget.a.a);
            }
            authActivity.e.a();
        } catch (Exception e) {
            authActivity.e = null;
        }
    }

    public static /* synthetic */ void g(AuthActivity authActivity) {
        if (authActivity.e != null) {
            authActivity.e.b();
        }
        authActivity.e = null;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                finish();
                return;
            }
            try {
                this.d = extras.getString(b);
                String string = extras.getString("params");
                if (!l.b(string)) {
                    finish();
                    return;
                }
                super.requestWindowFeature(1);
                this.f = new Handler(getMainLooper());
                LinearLayout linearLayout = new LinearLayout(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
                linearLayout.setOrientation(1);
                setContentView(linearLayout, layoutParams);
                this.c = new WebView(this);
                layoutParams.weight = 1.0f;
                this.c.setVisibility(0);
                linearLayout.addView(this.c, layoutParams);
                WebSettings settings = this.c.getSettings();
                settings.setUserAgentString(settings.getUserAgentString() + l.e(getApplicationContext()));
                settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
                settings.setSupportMultipleWindows(true);
                settings.setJavaScriptEnabled(true);
                settings.setSavePassword(false);
                settings.setJavaScriptCanOpenWindowsAutomatically(true);
                settings.setMinimumFontSize(settings.getMinimumFontSize() + 8);
                settings.setAllowFileAccess(false);
                settings.setTextSize(WebSettings.TextSize.NORMAL);
                this.c.setVerticalScrollbarOverlay(true);
                this.c.setWebViewClient(new b(this, (byte) 0));
                this.c.setWebChromeClient(new a(this, (byte) 0));
                this.c.setDownloadListener(new a(this));
                this.c.loadUrl(string);
                if (Build.VERSION.SDK_INT >= 7) {
                    try {
                        Method method = this.c.getSettings().getClass().getMethod("setDomStorageEnabled", Boolean.TYPE);
                        if (method != null) {
                            method.invoke(this.c.getSettings(), true);
                        }
                    } catch (Exception e) {
                    }
                }
                try {
                    this.c.removeJavascriptInterface("searchBoxJavaBridge_");
                    this.c.removeJavascriptInterface("accessibility");
                    this.c.removeJavascriptInterface("accessibilityTraversal");
                } catch (Throwable th) {
                    try {
                        Method method2 = this.c.getClass().getMethod("removeJavascriptInterface", new Class[0]);
                        if (method2 != null) {
                            method2.invoke(this.c, "searchBoxJavaBridge_");
                            method2.invoke(this.c, "accessibility");
                            method2.invoke(this.c, "accessibilityTraversal");
                        }
                    } catch (Throwable th2) {
                    }
                }
                if (Build.VERSION.SDK_INT >= 19) {
                    this.c.getSettings().setCacheMode(1);
                }
            } catch (Exception e2) {
                finish();
            }
        } catch (Exception e3) {
            finish();
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (!this.c.canGoBack()) {
            h.a(this, this.d + "?resultCode=150");
            finish();
        } else if (this.h) {
            h.a(this, this.d + "?resultCode=150");
            finish();
        }
    }

    /* loaded from: classes.dex */
    public class b extends WebViewClient {
        private b() {
            AuthActivity.this = r1;
        }

        /* synthetic */ b(AuthActivity authActivity, byte b) {
            this();
        }

        @Override // android.webkit.WebViewClient
        public final void onReceivedError(WebView webView, int i, String str, String str2) {
            AuthActivity.this.h = true;
            super.onReceivedError(webView, i, str, str2);
        }

        @Override // android.webkit.WebViewClient
        public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (AuthActivity.this.g) {
                sslErrorHandler.proceed();
                AuthActivity.this.g = false;
                return;
            }
            AuthActivity.this.runOnUiThread(new e(this, sslErrorHandler));
        }

        @Override // android.webkit.WebViewClient
        public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str.toLowerCase().startsWith(com.alipay.sdk.cons.a.i.toLowerCase()) || str.toLowerCase().startsWith(com.alipay.sdk.cons.a.j.toLowerCase())) {
                try {
                    l.a a = l.a(AuthActivity.this);
                    if (a == null || a.a()) {
                        return true;
                    }
                    if (str.startsWith("intent://platformapi/startapp")) {
                        str = str.replaceFirst(com.alipay.sdk.cons.a.j, com.alipay.sdk.cons.a.i);
                    }
                    AuthActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    return true;
                } catch (Throwable th) {
                    return true;
                }
            } else if (!AuthActivity.a(AuthActivity.this, str)) {
                return super.shouldOverrideUrlLoading(webView, str);
            } else {
                webView.stopLoading();
                return true;
            }
        }

        @Override // android.webkit.WebViewClient
        public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            AuthActivity.d(AuthActivity.this);
            AuthActivity.this.f.postDelayed(AuthActivity.this.i, 30000L);
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public final void onPageFinished(WebView webView, String str) {
            AuthActivity.g(AuthActivity.this);
            AuthActivity.this.f.removeCallbacks(AuthActivity.this.i);
        }
    }

    /* loaded from: classes.dex */
    private class a extends WebChromeClient {
        private a() {
            AuthActivity.this = r1;
        }

        /* synthetic */ a(AuthActivity authActivity, byte b) {
            this();
        }

        @Override // android.webkit.WebChromeClient
        public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            String message = consoleMessage.message();
            if (TextUtils.isEmpty(message)) {
                return super.onConsoleMessage(consoleMessage);
            }
            String str = null;
            if (message.startsWith("h5container.message: ")) {
                str = message.replaceFirst("h5container.message: ", "");
            }
            if (TextUtils.isEmpty(str)) {
                return super.onConsoleMessage(consoleMessage);
            }
            AuthActivity.b(AuthActivity.this, str);
            return super.onConsoleMessage(consoleMessage);
        }
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("http://") || str.startsWith("https://")) {
            return false;
        }
        if (!"SDKLite://h5quit".equalsIgnoreCase(str)) {
            if (TextUtils.equals(str, this.d)) {
                str = str + "?resultCode=150";
            }
            h.a(this, str);
        }
        finish();
        return true;
    }

    private void b(String str) {
        String str2;
        c cVar = new c(getApplicationContext(), new b(this));
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(com.alipay.sdk.authjs.a.e);
            try {
                if (!TextUtils.isEmpty(string)) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("param");
                    JSONObject jSONObject3 = jSONObject2 instanceof JSONObject ? jSONObject2 : null;
                    String string2 = jSONObject.getString(com.alipay.sdk.authjs.a.g);
                    String string3 = jSONObject.getString(com.alipay.sdk.authjs.a.d);
                    com.alipay.sdk.authjs.a aVar = new com.alipay.sdk.authjs.a("call");
                    aVar.j = string3;
                    aVar.k = string2;
                    aVar.m = jSONObject3;
                    aVar.i = string;
                    cVar.a(aVar);
                }
            } catch (Exception e) {
                str2 = string;
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        cVar.a(str2, a.EnumC0009a.d);
                    } catch (JSONException e2) {
                    }
                }
            }
        } catch (Exception e3) {
            str2 = null;
        }
    }

    private void a(com.alipay.sdk.authjs.a aVar) {
        if (this.c != null && aVar != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(com.alipay.sdk.authjs.a.e, aVar.i);
                jSONObject.put(com.alipay.sdk.authjs.a.g, aVar.k);
                jSONObject.put("param", aVar.m);
                jSONObject.put(com.alipay.sdk.authjs.a.h, aVar.l);
                runOnUiThread(new c(this, String.format("AlipayJSBridge._invokeJS(%s)", jSONObject.toString())));
            } catch (JSONException e) {
            }
        }
    }

    private void a() {
        try {
            if (this.e == null) {
                this.e = new com.alipay.sdk.widget.a(this, com.alipay.sdk.widget.a.a);
            }
            this.e.a();
        } catch (Exception e) {
            this.e = null;
        }
    }

    private void b() {
        if (this.e != null) {
            this.e.b();
        }
        this.e = null;
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.c != null) {
            this.c.removeAllViews();
            try {
                this.c.destroy();
            } catch (Throwable th) {
            }
            this.c = null;
        }
    }
}
