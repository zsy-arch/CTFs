package com.tencent.smtt.export.external.proxy;

import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.webkit.ValueCallback;
import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.QuotaUpdater;

/* loaded from: classes2.dex */
public class ProxyWebChromeClient implements IX5WebChromeClient {
    protected IX5WebChromeClient mWebChromeClient;

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public Bitmap getDefaultVideoPoster() {
        return null;
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public void getVisitedHistory(ValueCallback<String[]> valueCallback) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.getVisitedHistory(valueCallback);
        }
    }

    public IX5WebChromeClient getmWebChromeClient() {
        return this.mWebChromeClient;
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public void onCloseWindow(IX5WebViewBase iX5WebViewBase) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onCloseWindow(iX5WebViewBase);
        }
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public void onConsoleMessage(String str, int i, String str2) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onConsoleMessage(str, i, str2);
        }
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        if (this.mWebChromeClient != null) {
            return this.mWebChromeClient.onConsoleMessage(consoleMessage);
        }
        return false;
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public boolean onCreateWindow(IX5WebViewBase iX5WebViewBase, boolean z, boolean z2, Message message) {
        if (this.mWebChromeClient != null) {
            return this.mWebChromeClient.onCreateWindow(iX5WebViewBase, z, z2, message);
        }
        return false;
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, QuotaUpdater quotaUpdater) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onExceededDatabaseQuota(str, str2, j, j2, j3, quotaUpdater);
        }
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public void onGeolocationPermissionsHidePrompt() {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onGeolocationPermissionsHidePrompt();
        }
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissionsCallback geolocationPermissionsCallback) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onGeolocationPermissionsShowPrompt(str, geolocationPermissionsCallback);
        }
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public void onGeolocationStartUpdating(ValueCallback<Location> valueCallback, ValueCallback<Bundle> valueCallback2) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onGeolocationStartUpdating(valueCallback, valueCallback2);
        }
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public void onGeolocationStopUpdating() {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onGeolocationStopUpdating();
        }
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public void onHideCustomView() {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onHideCustomView();
        }
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public boolean onJsAlert(IX5WebViewBase iX5WebViewBase, String str, String str2, JsResult jsResult) {
        if (this.mWebChromeClient != null) {
            return this.mWebChromeClient.onJsAlert(iX5WebViewBase, str, str2, jsResult);
        }
        return false;
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public boolean onJsBeforeUnload(IX5WebViewBase iX5WebViewBase, String str, String str2, JsResult jsResult) {
        if (this.mWebChromeClient != null) {
            return this.mWebChromeClient.onJsBeforeUnload(iX5WebViewBase, str, str2, jsResult);
        }
        return false;
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public boolean onJsConfirm(IX5WebViewBase iX5WebViewBase, String str, String str2, JsResult jsResult) {
        if (this.mWebChromeClient != null) {
            return this.mWebChromeClient.onJsConfirm(iX5WebViewBase, str, str2, jsResult);
        }
        return false;
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public boolean onJsPrompt(IX5WebViewBase iX5WebViewBase, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        if (this.mWebChromeClient != null) {
            return this.mWebChromeClient.onJsPrompt(iX5WebViewBase, str, str2, str3, jsPromptResult);
        }
        return false;
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public boolean onJsTimeout() {
        if (this.mWebChromeClient != null) {
            return this.mWebChromeClient.onJsTimeout();
        }
        return false;
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public void onProgressChanged(IX5WebViewBase iX5WebViewBase, int i) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onProgressChanged(iX5WebViewBase, i);
        }
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public void onReachedMaxAppCacheSize(long j, long j2, QuotaUpdater quotaUpdater) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onReachedMaxAppCacheSize(j, j2, quotaUpdater);
        }
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public void onReceivedIcon(IX5WebViewBase iX5WebViewBase, Bitmap bitmap) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onReceivedIcon(iX5WebViewBase, bitmap);
        }
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public void onReceivedTitle(IX5WebViewBase iX5WebViewBase, String str) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onReceivedTitle(iX5WebViewBase, str);
        }
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public void onReceivedTouchIconUrl(IX5WebViewBase iX5WebViewBase, String str, boolean z) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onReceivedTouchIconUrl(iX5WebViewBase, str, z);
        }
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public void onRequestFocus(IX5WebViewBase iX5WebViewBase) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onRequestFocus(iX5WebViewBase);
        }
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public void onShowCustomView(View view, int i, IX5WebChromeClient.CustomViewCallback customViewCallback) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onShowCustomView(view, customViewCallback);
        }
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onShowCustomView(view, customViewCallback);
        }
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public boolean onShowFileChooser(IX5WebViewBase iX5WebViewBase, ValueCallback<Uri[]> valueCallback, IX5WebChromeClient.FileChooserParams fileChooserParams) {
        return false;
    }

    @Override // com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
    public void openFileChooser(ValueCallback<Uri[]> valueCallback, String str, String str2, boolean z) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.openFileChooser(valueCallback, str, str2, z);
        }
    }

    public void setWebChromeClient(IX5WebChromeClient iX5WebChromeClient) {
        this.mWebChromeClient = iX5WebChromeClient;
    }
}
