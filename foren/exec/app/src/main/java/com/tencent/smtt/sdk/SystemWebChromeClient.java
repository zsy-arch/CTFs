package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;
import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebStorage;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes.dex */
public class SystemWebChromeClient extends WebChromeClient {

    /* renamed from: a  reason: collision with root package name */
    public WebView f1150a;

    /* renamed from: b  reason: collision with root package name */
    public WebChromeClient f1151b;

    /* loaded from: classes.dex */
    private static class a implements ConsoleMessage {

        /* renamed from: a  reason: collision with root package name */
        public ConsoleMessage.MessageLevel f1167a;

        /* renamed from: b  reason: collision with root package name */
        public String f1168b;

        /* renamed from: c  reason: collision with root package name */
        public String f1169c;

        /* renamed from: d  reason: collision with root package name */
        public int f1170d;

        public a(android.webkit.ConsoleMessage consoleMessage) {
            this.f1167a = ConsoleMessage.MessageLevel.valueOf(consoleMessage.messageLevel().name());
            this.f1168b = consoleMessage.message();
            this.f1169c = consoleMessage.sourceId();
            this.f1170d = consoleMessage.lineNumber();
        }

        public a(String str, String str2, int i) {
            this.f1167a = ConsoleMessage.MessageLevel.LOG;
            this.f1168b = str;
            this.f1169c = str2;
            this.f1170d = i;
        }

        @Override // com.tencent.smtt.export.external.interfaces.ConsoleMessage
        public int lineNumber() {
            return this.f1170d;
        }

        @Override // com.tencent.smtt.export.external.interfaces.ConsoleMessage
        public String message() {
            return this.f1168b;
        }

        @Override // com.tencent.smtt.export.external.interfaces.ConsoleMessage
        public ConsoleMessage.MessageLevel messageLevel() {
            return this.f1167a;
        }

        @Override // com.tencent.smtt.export.external.interfaces.ConsoleMessage
        public String sourceId() {
            return this.f1169c;
        }
    }

    /* loaded from: classes.dex */
    class b implements IX5WebChromeClient.CustomViewCallback {

        /* renamed from: a  reason: collision with root package name */
        public WebChromeClient.CustomViewCallback f1171a;

        public b(WebChromeClient.CustomViewCallback customViewCallback) {
            this.f1171a = customViewCallback;
        }

        @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient.CustomViewCallback
        public void onCustomViewHidden() {
            this.f1171a.onCustomViewHidden();
        }
    }

    /* loaded from: classes.dex */
    class c implements GeolocationPermissionsCallback {

        /* renamed from: a  reason: collision with root package name */
        public GeolocationPermissions.Callback f1173a;

        public c(GeolocationPermissions.Callback callback) {
            this.f1173a = callback;
        }

        @Override // com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback
        public void invoke(String str, boolean z, boolean z2) {
            this.f1173a.invoke(str, z, z2);
        }
    }

    /* loaded from: classes.dex */
    private class d implements JsPromptResult {

        /* renamed from: a  reason: collision with root package name */
        public android.webkit.JsPromptResult f1175a;

        public d(android.webkit.JsPromptResult jsPromptResult) {
            this.f1175a = jsPromptResult;
        }

        @Override // com.tencent.smtt.export.external.interfaces.JsResult
        public void cancel() {
            this.f1175a.cancel();
        }

        @Override // com.tencent.smtt.export.external.interfaces.JsResult
        public void confirm() {
            this.f1175a.confirm();
        }

        @Override // com.tencent.smtt.export.external.interfaces.JsPromptResult
        public void confirm(String str) {
            this.f1175a.confirm(str);
        }
    }

    /* loaded from: classes.dex */
    private class e implements JsResult {

        /* renamed from: a  reason: collision with root package name */
        public android.webkit.JsResult f1177a;

        public e(android.webkit.JsResult jsResult) {
            this.f1177a = jsResult;
        }

        @Override // com.tencent.smtt.export.external.interfaces.JsResult
        public void cancel() {
            this.f1177a.cancel();
        }

        @Override // com.tencent.smtt.export.external.interfaces.JsResult
        public void confirm() {
            this.f1177a.confirm();
        }
    }

    /* loaded from: classes.dex */
    class f implements WebStorage.QuotaUpdater {

        /* renamed from: a  reason: collision with root package name */
        public WebStorage.QuotaUpdater f1179a;

        public f(WebStorage.QuotaUpdater quotaUpdater) {
            this.f1179a = quotaUpdater;
        }

        @Override // com.tencent.smtt.sdk.WebStorage.QuotaUpdater
        public void updateQuota(long j) {
            this.f1179a.updateQuota(j);
        }
    }

    public SystemWebChromeClient(WebView webView, WebChromeClient webChromeClient) {
        this.f1150a = webView;
        this.f1151b = webChromeClient;
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(7)
    public Bitmap getDefaultVideoPoster() {
        Bitmap defaultVideoPoster = this.f1151b.getDefaultVideoPoster();
        if (defaultVideoPoster != null) {
            return defaultVideoPoster;
        }
        try {
            return Build.VERSION.SDK_INT >= 24 ? BitmapFactory.decodeResource(this.f1150a.getResources(), 17301540) : defaultVideoPoster;
        } catch (Exception unused) {
            return defaultVideoPoster;
        }
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(7)
    public View getVideoLoadingProgressView() {
        return this.f1151b.getVideoLoadingProgressView();
    }

    @Override // android.webkit.WebChromeClient
    public void getVisitedHistory(final ValueCallback<String[]> valueCallback) {
        this.f1151b.getVisitedHistory(new ValueCallback<String[]>() { // from class: com.tencent.smtt.sdk.SystemWebChromeClient.1
            /* renamed from: a */
            public void onReceiveValue(String[] strArr) {
                valueCallback.onReceiveValue(strArr);
            }
        });
    }

    @Override // android.webkit.WebChromeClient
    public void onCloseWindow(WebView webView) {
        this.f1150a.a(webView);
        this.f1151b.onCloseWindow(this.f1150a);
    }

    @Override // android.webkit.WebChromeClient
    public void onConsoleMessage(String str, int i, String str2) {
        this.f1151b.onConsoleMessage(new a(str, str2, i));
    }

    @Override // android.webkit.WebChromeClient
    public boolean onConsoleMessage(android.webkit.ConsoleMessage consoleMessage) {
        return this.f1151b.onConsoleMessage(new a(consoleMessage));
    }

    @Override // android.webkit.WebChromeClient
    public boolean onCreateWindow(WebView webView, boolean z, boolean z2, final Message message) {
        WebView webView2 = this.f1150a;
        webView2.getClass();
        final WebView.WebViewTransport webViewTransport = new WebView.WebViewTransport();
        Message obtain = Message.obtain(message.getTarget(), new Runnable() { // from class: com.tencent.smtt.sdk.SystemWebChromeClient.2
            @Override // java.lang.Runnable
            public void run() {
                WebView webView3 = webViewTransport.getWebView();
                if (webView3 != null) {
                    ((WebView.WebViewTransport) message.obj).setWebView(webView3.b());
                }
                message.sendToTarget();
            }
        });
        obtain.obj = webViewTransport;
        return this.f1151b.onCreateWindow(this.f1150a, z, z2, obtain);
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(5)
    @Deprecated
    public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
        this.f1151b.onExceededDatabaseQuota(str, str2, j, j2, j3, new f(quotaUpdater));
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(5)
    public void onGeolocationPermissionsHidePrompt() {
        this.f1151b.onGeolocationPermissionsHidePrompt();
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(5)
    public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
        this.f1151b.onGeolocationPermissionsShowPrompt(str, new c(callback));
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(7)
    public void onHideCustomView() {
        this.f1151b.onHideCustomView();
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsAlert(android.webkit.WebView webView, String str, String str2, android.webkit.JsResult jsResult) {
        this.f1150a.a(webView);
        return this.f1151b.onJsAlert(this.f1150a, str, str2, new e(jsResult));
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsBeforeUnload(android.webkit.WebView webView, String str, String str2, android.webkit.JsResult jsResult) {
        this.f1150a.a(webView);
        return this.f1151b.onJsBeforeUnload(this.f1150a, str, str2, new e(jsResult));
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsConfirm(android.webkit.WebView webView, String str, String str2, android.webkit.JsResult jsResult) {
        this.f1150a.a(webView);
        return this.f1151b.onJsConfirm(this.f1150a, str, str2, new e(jsResult));
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsPrompt(android.webkit.WebView webView, String str, String str2, String str3, android.webkit.JsPromptResult jsPromptResult) {
        this.f1150a.a(webView);
        return this.f1151b.onJsPrompt(this.f1150a, str, str2, str3, new d(jsPromptResult));
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(7)
    public boolean onJsTimeout() {
        return this.f1151b.onJsTimeout();
    }

    @Override // android.webkit.WebChromeClient
    public void onPermissionRequest(final PermissionRequest permissionRequest) {
        this.f1151b.onPermissionRequest(new com.tencent.smtt.export.external.interfaces.PermissionRequest() { // from class: com.tencent.smtt.sdk.SystemWebChromeClient.6
            @Override // com.tencent.smtt.export.external.interfaces.PermissionRequest
            public void deny() {
                int i = Build.VERSION.SDK_INT;
                permissionRequest.deny();
            }

            @Override // com.tencent.smtt.export.external.interfaces.PermissionRequest
            public Uri getOrigin() {
                int i = Build.VERSION.SDK_INT;
                return permissionRequest.getOrigin();
            }

            @Override // com.tencent.smtt.export.external.interfaces.PermissionRequest
            public String[] getResources() {
                int i = Build.VERSION.SDK_INT;
                return permissionRequest.getResources();
            }

            @Override // com.tencent.smtt.export.external.interfaces.PermissionRequest
            public void grant(String[] strArr) {
                int i = Build.VERSION.SDK_INT;
                permissionRequest.grant(strArr);
            }
        });
    }

    @Override // android.webkit.WebChromeClient
    public void onPermissionRequestCanceled(final PermissionRequest permissionRequest) {
        this.f1151b.onPermissionRequestCanceled(new com.tencent.smtt.export.external.interfaces.PermissionRequest() { // from class: com.tencent.smtt.sdk.SystemWebChromeClient.7
            @Override // com.tencent.smtt.export.external.interfaces.PermissionRequest
            public void deny() {
                int i = Build.VERSION.SDK_INT;
                permissionRequest.deny();
            }

            @Override // com.tencent.smtt.export.external.interfaces.PermissionRequest
            public Uri getOrigin() {
                int i = Build.VERSION.SDK_INT;
                return permissionRequest.getOrigin();
            }

            @Override // com.tencent.smtt.export.external.interfaces.PermissionRequest
            public String[] getResources() {
                int i = Build.VERSION.SDK_INT;
                return permissionRequest.getResources();
            }

            @Override // com.tencent.smtt.export.external.interfaces.PermissionRequest
            public void grant(String[] strArr) {
                int i = Build.VERSION.SDK_INT;
                permissionRequest.grant(strArr);
            }
        });
    }

    @Override // android.webkit.WebChromeClient
    public void onProgressChanged(android.webkit.WebView webView, int i) {
        this.f1150a.a(webView);
        this.f1151b.onProgressChanged(this.f1150a, i);
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(7)
    @Deprecated
    public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
        this.f1151b.onReachedMaxAppCacheSize(j, j2, new f(quotaUpdater));
    }

    @Override // android.webkit.WebChromeClient
    public void onReceivedIcon(android.webkit.WebView webView, Bitmap bitmap) {
        this.f1150a.a(webView);
        this.f1151b.onReceivedIcon(this.f1150a, bitmap);
    }

    @Override // android.webkit.WebChromeClient
    public void onReceivedTitle(android.webkit.WebView webView, String str) {
        this.f1150a.a(webView);
        this.f1151b.onReceivedTitle(this.f1150a, str);
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(7)
    public void onReceivedTouchIconUrl(android.webkit.WebView webView, String str, boolean z) {
        this.f1150a.a(webView);
        this.f1151b.onReceivedTouchIconUrl(this.f1150a, str, z);
    }

    @Override // android.webkit.WebChromeClient
    public void onRequestFocus(android.webkit.WebView webView) {
        this.f1150a.a(webView);
        this.f1151b.onRequestFocus(this.f1150a);
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(14)
    @Deprecated
    public void onShowCustomView(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
        this.f1151b.onShowCustomView(view, i, new b(customViewCallback));
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(7)
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        this.f1151b.onShowCustomView(view, new b(customViewCallback));
    }

    @Override // android.webkit.WebChromeClient
    public boolean onShowFileChooser(android.webkit.WebView webView, final ValueCallback<Uri[]> valueCallback, final WebChromeClient.FileChooserParams fileChooserParams) {
        ValueCallback<Uri[]> valueCallback2 = new ValueCallback<Uri[]>() { // from class: com.tencent.smtt.sdk.SystemWebChromeClient.4
            /* renamed from: a */
            public void onReceiveValue(Uri[] uriArr) {
                valueCallback.onReceiveValue(uriArr);
            }
        };
        WebChromeClient.FileChooserParams fileChooserParams2 = new WebChromeClient.FileChooserParams() { // from class: com.tencent.smtt.sdk.SystemWebChromeClient.5
            @Override // com.tencent.smtt.sdk.WebChromeClient.FileChooserParams
            public Intent createIntent() {
                return fileChooserParams.createIntent();
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient.FileChooserParams
            public String[] getAcceptTypes() {
                return fileChooserParams.getAcceptTypes();
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient.FileChooserParams
            public String getFilenameHint() {
                return fileChooserParams.getFilenameHint();
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient.FileChooserParams
            public int getMode() {
                return fileChooserParams.getMode();
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient.FileChooserParams
            public CharSequence getTitle() {
                return fileChooserParams.getTitle();
            }

            @Override // com.tencent.smtt.sdk.WebChromeClient.FileChooserParams
            public boolean isCaptureEnabled() {
                return fileChooserParams.isCaptureEnabled();
            }
        };
        this.f1150a.a(webView);
        return this.f1151b.onShowFileChooser(this.f1150a, valueCallback2, fileChooserParams2);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback) {
        openFileChooser(valueCallback, null, null);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback, String str) {
        openFileChooser(valueCallback, str, null);
    }

    public void openFileChooser(final ValueCallback<Uri> valueCallback, String str, String str2) {
        this.f1151b.openFileChooser(new ValueCallback<Uri>() { // from class: com.tencent.smtt.sdk.SystemWebChromeClient.3
            /* renamed from: a */
            public void onReceiveValue(Uri uri) {
                valueCallback.onReceiveValue(uri);
            }
        }, str, str2);
    }

    public void setupAutoFill(Message message) {
    }
}
