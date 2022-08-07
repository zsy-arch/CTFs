package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Message;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;
import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.WebStorage;
import com.tencent.smtt.sdk.WebView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class SystemWebChromeClient extends WebChromeClient {
    private WebView a;
    private WebChromeClient b;

    /* loaded from: classes2.dex */
    private static class a implements ConsoleMessage {
        private ConsoleMessage.MessageLevel a;
        private String b;
        private String c;
        private int d;

        a(android.webkit.ConsoleMessage consoleMessage) {
            this.a = ConsoleMessage.MessageLevel.valueOf(consoleMessage.messageLevel().name());
            this.b = consoleMessage.message();
            this.c = consoleMessage.sourceId();
            this.d = consoleMessage.lineNumber();
        }

        a(String str, String str2, int i) {
            this.a = ConsoleMessage.MessageLevel.LOG;
            this.b = str;
            this.c = str2;
            this.d = i;
        }

        @Override // com.tencent.smtt.export.external.interfaces.ConsoleMessage
        public int lineNumber() {
            return this.d;
        }

        @Override // com.tencent.smtt.export.external.interfaces.ConsoleMessage
        public String message() {
            return this.b;
        }

        @Override // com.tencent.smtt.export.external.interfaces.ConsoleMessage
        public ConsoleMessage.MessageLevel messageLevel() {
            return this.a;
        }

        @Override // com.tencent.smtt.export.external.interfaces.ConsoleMessage
        public String sourceId() {
            return this.c;
        }
    }

    /* loaded from: classes2.dex */
    class b implements IX5WebChromeClient.CustomViewCallback {
        WebChromeClient.CustomViewCallback a;

        b(WebChromeClient.CustomViewCallback customViewCallback) {
            SystemWebChromeClient.this = r1;
            this.a = customViewCallback;
        }

        @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient.CustomViewCallback
        public void onCustomViewHidden() {
            this.a.onCustomViewHidden();
        }
    }

    /* loaded from: classes2.dex */
    class c implements GeolocationPermissionsCallback {
        GeolocationPermissions.Callback a;

        c(GeolocationPermissions.Callback callback) {
            SystemWebChromeClient.this = r1;
            this.a = callback;
        }

        @Override // com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback
        public void invoke(String str, boolean z, boolean z2) {
            this.a.invoke(str, z, z2);
        }
    }

    /* loaded from: classes2.dex */
    private class d implements JsPromptResult {
        android.webkit.JsPromptResult a;

        d(android.webkit.JsPromptResult jsPromptResult) {
            SystemWebChromeClient.this = r1;
            this.a = jsPromptResult;
        }

        @Override // com.tencent.smtt.export.external.interfaces.JsResult
        public void cancel() {
            this.a.cancel();
        }

        @Override // com.tencent.smtt.export.external.interfaces.JsResult
        public void confirm() {
            this.a.confirm();
        }

        @Override // com.tencent.smtt.export.external.interfaces.JsPromptResult
        public void confirm(String str) {
            this.a.confirm(str);
        }
    }

    /* loaded from: classes2.dex */
    private class e implements JsResult {
        android.webkit.JsResult a;

        e(android.webkit.JsResult jsResult) {
            SystemWebChromeClient.this = r1;
            this.a = jsResult;
        }

        @Override // com.tencent.smtt.export.external.interfaces.JsResult
        public void cancel() {
            this.a.cancel();
        }

        @Override // com.tencent.smtt.export.external.interfaces.JsResult
        public void confirm() {
            this.a.confirm();
        }
    }

    /* loaded from: classes2.dex */
    class f implements WebStorage.QuotaUpdater {
        WebStorage.QuotaUpdater a;

        f(WebStorage.QuotaUpdater quotaUpdater) {
            SystemWebChromeClient.this = r1;
            this.a = quotaUpdater;
        }

        @Override // com.tencent.smtt.sdk.WebStorage.QuotaUpdater
        public void updateQuota(long j) {
            this.a.updateQuota(j);
        }
    }

    public SystemWebChromeClient(WebView webView, WebChromeClient webChromeClient) {
        this.a = webView;
        this.b = webChromeClient;
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(7)
    public Bitmap getDefaultVideoPoster() {
        return this.b.getDefaultVideoPoster();
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(7)
    public View getVideoLoadingProgressView() {
        return this.b.getVideoLoadingProgressView();
    }

    @Override // android.webkit.WebChromeClient
    public void getVisitedHistory(ValueCallback<String[]> valueCallback) {
        this.b.getVisitedHistory(new u(this, valueCallback));
    }

    @Override // android.webkit.WebChromeClient
    public void onCloseWindow(WebView webView) {
        this.a.a(webView);
        this.b.onCloseWindow(this.a);
    }

    @Override // android.webkit.WebChromeClient
    public void onConsoleMessage(String str, int i, String str2) {
        this.b.onConsoleMessage(new a(str, str2, i));
    }

    @Override // android.webkit.WebChromeClient
    public boolean onConsoleMessage(android.webkit.ConsoleMessage consoleMessage) {
        return this.b.onConsoleMessage(new a(consoleMessage));
    }

    @Override // android.webkit.WebChromeClient
    public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
        WebView webView2 = this.a;
        webView2.getClass();
        WebView.WebViewTransport webViewTransport = new WebView.WebViewTransport();
        Message obtain = Message.obtain(message.getTarget(), new v(this, webViewTransport, message));
        obtain.obj = webViewTransport;
        return this.b.onCreateWindow(this.a, z, z2, obtain);
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(5)
    @Deprecated
    public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
        this.b.onExceededDatabaseQuota(str, str2, j, j2, j3, new f(quotaUpdater));
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(5)
    public void onGeolocationPermissionsHidePrompt() {
        this.b.onGeolocationPermissionsHidePrompt();
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(5)
    public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
        this.b.onGeolocationPermissionsShowPrompt(str, new c(callback));
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(7)
    public void onHideCustomView() {
        this.b.onHideCustomView();
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsAlert(android.webkit.WebView webView, String str, String str2, android.webkit.JsResult jsResult) {
        this.a.a(webView);
        return this.b.onJsAlert(this.a, str, str2, new e(jsResult));
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsBeforeUnload(android.webkit.WebView webView, String str, String str2, android.webkit.JsResult jsResult) {
        this.a.a(webView);
        return this.b.onJsBeforeUnload(this.a, str, str2, new e(jsResult));
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsConfirm(android.webkit.WebView webView, String str, String str2, android.webkit.JsResult jsResult) {
        this.a.a(webView);
        return this.b.onJsConfirm(this.a, str, str2, new e(jsResult));
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsPrompt(android.webkit.WebView webView, String str, String str2, String str3, android.webkit.JsPromptResult jsPromptResult) {
        this.a.a(webView);
        return this.b.onJsPrompt(this.a, str, str2, str3, new d(jsPromptResult));
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(7)
    public boolean onJsTimeout() {
        return this.b.onJsTimeout();
    }

    @Override // android.webkit.WebChromeClient
    public void onProgressChanged(android.webkit.WebView webView, int i) {
        this.a.a(webView);
        this.b.onProgressChanged(this.a, i);
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(7)
    @Deprecated
    public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
        this.b.onReachedMaxAppCacheSize(j, j2, new f(quotaUpdater));
    }

    @Override // android.webkit.WebChromeClient
    public void onReceivedIcon(android.webkit.WebView webView, Bitmap bitmap) {
        this.a.a(webView);
        this.b.onReceivedIcon(this.a, bitmap);
    }

    @Override // android.webkit.WebChromeClient
    public void onReceivedTitle(android.webkit.WebView webView, String str) {
        this.a.a(webView);
        this.b.onReceivedTitle(this.a, str);
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(7)
    public void onReceivedTouchIconUrl(android.webkit.WebView webView, String str, boolean z) {
        this.a.a(webView);
        this.b.onReceivedTouchIconUrl(this.a, str, z);
    }

    @Override // android.webkit.WebChromeClient
    public void onRequestFocus(android.webkit.WebView webView) {
        this.a.a(webView);
        this.b.onRequestFocus(this.a);
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(14)
    @Deprecated
    public void onShowCustomView(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
        this.b.onShowCustomView(view, i, new b(customViewCallback));
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(7)
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        this.b.onShowCustomView(view, new b(customViewCallback));
    }

    @Override // android.webkit.WebChromeClient
    public boolean onShowFileChooser(android.webkit.WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        x xVar = new x(this, valueCallback);
        y yVar = new y(this, fileChooserParams);
        this.a.a(webView);
        return this.b.onShowFileChooser(this.a, xVar, yVar);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback) {
        openFileChooser(valueCallback, null, null);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback, String str) {
        openFileChooser(valueCallback, str, null);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
        this.b.openFileChooser(new w(this, valueCallback), str, str2);
    }

    public void setupAutoFill(Message message) {
    }
}
