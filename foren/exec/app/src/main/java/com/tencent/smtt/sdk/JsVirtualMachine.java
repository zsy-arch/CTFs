package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Looper;
import android.webkit.ValueCallback;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsError;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsValue;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsVirtualMachine;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class JsVirtualMachine {

    /* renamed from: a  reason: collision with root package name */
    public final Context f1118a;

    /* renamed from: b  reason: collision with root package name */
    public final IX5JsVirtualMachine f1119b;

    /* renamed from: c  reason: collision with root package name */
    public final HashSet<WeakReference<a>> f1120c;

    /* loaded from: classes.dex */
    private static class a implements IX5JsContext {

        /* renamed from: a  reason: collision with root package name */
        public WebView f1121a;

        public a(Context context) {
            this.f1121a = new WebView(context, null, 0, false);
            this.f1121a.getSettings().setJavaScriptEnabled(true);
        }

        public void a() {
            WebView webView = this.f1121a;
            if (webView != null) {
                webView.onPause();
            }
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void addJavascriptInterface(Object obj, String str) {
            WebView webView = this.f1121a;
            if (webView != null) {
                webView.addJavascriptInterface(obj, str);
                this.f1121a.loadUrl("about:blank");
            }
        }

        public void b() {
            WebView webView = this.f1121a;
            if (webView != null) {
                webView.onResume();
            }
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void destroy() {
            WebView webView = this.f1121a;
            if (webView != null) {
                webView.clearHistory();
                this.f1121a.clearCache(true);
                this.f1121a.loadUrl("about:blank");
                this.f1121a.freeMemory();
                this.f1121a.pauseTimers();
                this.f1121a.destroy();
                this.f1121a = null;
            }
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void evaluateJavascript(String str, final ValueCallback<String> valueCallback, URL url) {
            WebView webView = this.f1121a;
            if (webView != null) {
                webView.evaluateJavascript(str, valueCallback == null ? null : new ValueCallback<String>() { // from class: com.tencent.smtt.sdk.JsVirtualMachine.a.1
                    /* renamed from: a */
                    public void onReceiveValue(String str2) {
                        valueCallback.onReceiveValue(str2);
                    }
                });
            }
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public IX5JsValue evaluateScript(String str, URL url) {
            WebView webView = this.f1121a;
            if (webView == null) {
                return null;
            }
            webView.evaluateJavascript(str, null);
            return null;
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void evaluateScriptAsync(String str, final ValueCallback<IX5JsValue> valueCallback, URL url) {
            WebView webView = this.f1121a;
            if (webView != null) {
                webView.evaluateJavascript(str, valueCallback == null ? null : new ValueCallback<String>() { // from class: com.tencent.smtt.sdk.JsVirtualMachine.a.2
                    /* renamed from: a */
                    public void onReceiveValue(String str2) {
                        valueCallback.onReceiveValue(null);
                    }
                });
            }
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public byte[] getNativeBuffer(int i) {
            return null;
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public int getNativeBufferId() {
            return -1;
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void removeJavascriptInterface(String str) {
            WebView webView = this.f1121a;
            if (webView != null) {
                webView.removeJavascriptInterface(str);
            }
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void setExceptionHandler(ValueCallback<IX5JsError> valueCallback) {
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void setName(String str) {
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public int setNativeBuffer(int i, byte[] bArr) {
            return -1;
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void setPerContextData(Object obj) {
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void stealValueFromOtherCtx(String str, IX5JsContext iX5JsContext, String str2) {
        }
    }

    public JsVirtualMachine(Context context) {
        this(context, null);
    }

    public JsVirtualMachine(Context context, Looper looper) {
        this.f1120c = new HashSet<>();
        this.f1118a = context;
        this.f1119b = X5JsCore.a(context, looper);
    }

    public IX5JsContext a() {
        IX5JsVirtualMachine iX5JsVirtualMachine = this.f1119b;
        if (iX5JsVirtualMachine != null) {
            return iX5JsVirtualMachine.createJsContext();
        }
        a aVar = new a(this.f1118a);
        this.f1120c.add(new WeakReference<>(aVar));
        return aVar;
    }

    public void destroy() {
        IX5JsVirtualMachine iX5JsVirtualMachine = this.f1119b;
        if (iX5JsVirtualMachine != null) {
            iX5JsVirtualMachine.destroy();
            return;
        }
        Iterator<WeakReference<a>> it = this.f1120c.iterator();
        while (it.hasNext()) {
            WeakReference<a> next = it.next();
            if (next.get() != null) {
                next.get().destroy();
            }
        }
    }

    public Looper getLooper() {
        IX5JsVirtualMachine iX5JsVirtualMachine = this.f1119b;
        return iX5JsVirtualMachine != null ? iX5JsVirtualMachine.getLooper() : Looper.myLooper();
    }

    public boolean isFallback() {
        return this.f1119b == null;
    }

    public void onPause() {
        IX5JsVirtualMachine iX5JsVirtualMachine = this.f1119b;
        if (iX5JsVirtualMachine != null) {
            iX5JsVirtualMachine.onPause();
            return;
        }
        Iterator<WeakReference<a>> it = this.f1120c.iterator();
        while (it.hasNext()) {
            WeakReference<a> next = it.next();
            if (next.get() != null) {
                next.get().a();
            }
        }
    }

    public void onResume() {
        IX5JsVirtualMachine iX5JsVirtualMachine = this.f1119b;
        if (iX5JsVirtualMachine != null) {
            iX5JsVirtualMachine.onResume();
            return;
        }
        Iterator<WeakReference<a>> it = this.f1120c.iterator();
        while (it.hasNext()) {
            WeakReference<a> next = it.next();
            if (next.get() != null) {
                next.get().b();
            }
        }
    }
}
