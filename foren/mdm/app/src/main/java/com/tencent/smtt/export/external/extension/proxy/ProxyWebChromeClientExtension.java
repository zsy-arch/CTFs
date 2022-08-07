package com.tencent.smtt.export.external.extension.proxy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.MediaAccessPermissionsCallback;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class ProxyWebChromeClientExtension implements IX5WebChromeClientExtension {
    private static boolean sCompatibleNewOnSavePassword = true;
    private static boolean sCompatibleOpenFileChooser = true;
    protected IX5WebChromeClientExtension mWebChromeClient;

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void acquireWakeLock() {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.acquireWakeLock();
        }
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void addFlashView(View view, ViewGroup.LayoutParams layoutParams) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.addFlashView(view, layoutParams);
        }
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void exitFullScreenFlash() {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.exitFullScreenFlash();
        }
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public Context getApplicationContex() {
        if (this.mWebChromeClient != null) {
            return this.mWebChromeClient.getApplicationContex();
        }
        return null;
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public View getVideoLoadingProgressView() {
        if (this.mWebChromeClient != null) {
            return this.mWebChromeClient.getVideoLoadingProgressView();
        }
        return null;
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public Object getX5WebChromeClientInstance() {
        return this.mWebChromeClient;
    }

    public IX5WebChromeClientExtension getmWebChromeClient() {
        return this.mWebChromeClient;
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void h5videoExitFullScreen(String str) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.h5videoExitFullScreen(str);
        }
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void h5videoRequestFullScreen(String str) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.h5videoRequestFullScreen(str);
        }
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void jsExitFullScreen() {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.jsExitFullScreen();
        }
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void jsRequestFullScreen() {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.jsRequestFullScreen();
        }
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public boolean onAddFavorite(IX5WebViewExtension iX5WebViewExtension, String str, String str2, JsResult jsResult) {
        if (this.mWebChromeClient != null) {
            return this.mWebChromeClient.onAddFavorite(iX5WebViewExtension, str, str2, jsResult);
        }
        return false;
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void onAllMetaDataFinished(IX5WebViewExtension iX5WebViewExtension, HashMap<String, String> hashMap) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onAllMetaDataFinished(iX5WebViewExtension, hashMap);
        }
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void onBackforwardFinished(int i) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onBackforwardFinished(i);
        }
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void onColorModeChanged(long j) {
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void onHitTestResultFinished(IX5WebViewExtension iX5WebViewExtension, IX5WebViewBase.HitTestResult hitTestResult) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onHitTestResultFinished(iX5WebViewExtension, hitTestResult);
        }
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void onHitTestResultForPluginFinished(IX5WebViewExtension iX5WebViewExtension, IX5WebViewBase.HitTestResult hitTestResult, Bundle bundle) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onHitTestResultForPluginFinished(iX5WebViewExtension, hitTestResult, bundle);
        }
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public Object onMiscCallBack(String str, Bundle bundle) {
        if (this.mWebChromeClient != null) {
            return this.mWebChromeClient.onMiscCallBack(str, bundle);
        }
        return null;
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public boolean onPageNotResponding(Runnable runnable) {
        if (this.mWebChromeClient != null) {
            return this.mWebChromeClient.onPageNotResponding(runnable);
        }
        return false;
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public boolean onPermissionRequest(String str, long j, MediaAccessPermissionsCallback mediaAccessPermissionsCallback) {
        return false;
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void onPrepareX5ReadPageDataFinished(IX5WebViewExtension iX5WebViewExtension, HashMap<String, String> hashMap) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onPrepareX5ReadPageDataFinished(iX5WebViewExtension, hashMap);
        }
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void onPrintPage() {
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void onPromptNotScalable(IX5WebViewExtension iX5WebViewExtension) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onPromptNotScalable(iX5WebViewExtension);
        }
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void onPromptScaleSaved(IX5WebViewExtension iX5WebViewExtension) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onPromptScaleSaved(iX5WebViewExtension);
        }
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public boolean onSavePassword(ValueCallback<String> valueCallback, String str, String str2, String str3, String str4, String str5, boolean z) {
        if (this.mWebChromeClient != null && sCompatibleNewOnSavePassword) {
            try {
                return this.mWebChromeClient.onSavePassword(valueCallback, str, str2, str3, str4, str5, z);
            } catch (NoSuchMethodError e) {
                if (e.getMessage() == null || !e.getMessage().contains("onSavePassword")) {
                    throw e;
                }
                sCompatibleNewOnSavePassword = false;
            }
        }
        return false;
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public boolean onSavePassword(String str, String str2, String str3, boolean z, Message message) {
        if (this.mWebChromeClient != null) {
            try {
                return this.mWebChromeClient.onSavePassword(str, str2, str3, z, message);
            } catch (NoSuchMethodError e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void onX5ReadModeAvailableChecked(HashMap<String, String> hashMap) {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.onX5ReadModeAvailableChecked(hashMap);
        }
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void openFileChooser(ValueCallback<Uri[]> valueCallback, String str, String str2) {
        if (this.mWebChromeClient != null && sCompatibleOpenFileChooser) {
            try {
                this.mWebChromeClient.openFileChooser(valueCallback, str, str2);
            } catch (NoSuchMethodError e) {
                if (e.getMessage() == null || !e.getMessage().contains("openFileChooser")) {
                    throw e;
                }
                sCompatibleOpenFileChooser = false;
            }
        }
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void releaseWakeLock() {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.releaseWakeLock();
        }
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void requestFullScreenFlash() {
        if (this.mWebChromeClient != null) {
            this.mWebChromeClient.requestFullScreenFlash();
        }
    }

    public void setWebChromeClientExtend(IX5WebChromeClientExtension iX5WebChromeClientExtension) {
        this.mWebChromeClient = iX5WebChromeClientExtension;
    }
}
