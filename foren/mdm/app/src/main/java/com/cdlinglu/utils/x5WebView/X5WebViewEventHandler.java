package com.cdlinglu.utils.x5WebView;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.MediaAccessPermissionsCallback;
import com.tencent.smtt.sdk.WebViewCallbackClient;
import java.util.HashMap;

/* loaded from: classes.dex */
public class X5WebViewEventHandler extends ProxyWebViewClientExtension implements IX5WebChromeClientExtension {
    private WebViewCallbackClient callbackClient = new WebViewCallbackClient() { // from class: com.cdlinglu.utils.x5WebView.X5WebViewEventHandler.1
        @Override // com.tencent.smtt.sdk.WebViewCallbackClient
        public boolean onTouchEvent(MotionEvent event, View view) {
            Log.i("X5WebView", "tbs_onTouchEvent view is " + view.getClass().toString());
            return X5WebViewEventHandler.this.webView.tbs_onTouchEvent(event, view);
        }

        @Override // com.tencent.smtt.sdk.WebViewCallbackClient
        public boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent, View view) {
            return X5WebViewEventHandler.this.webView.tbs_overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent, view);
        }

        @Override // com.tencent.smtt.sdk.WebViewCallbackClient
        public void computeScroll(View view) {
            X5WebViewEventHandler.this.webView.tbs_computeScroll(view);
        }

        @Override // com.tencent.smtt.sdk.WebViewCallbackClient
        public void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY, View view) {
            X5WebViewEventHandler.this.webView.tbs_onOverScrolled(scrollX, scrollY, clampedX, clampedY, view);
        }

        @Override // com.tencent.smtt.sdk.WebViewCallbackClient
        public void onScrollChanged(int l, int t, int oldl, int oldt, View view) {
            X5WebViewEventHandler.this.webView.tbs_onScrollChanged(l, t, oldl, oldt, view);
        }

        @Override // com.tencent.smtt.sdk.WebViewCallbackClient
        public boolean dispatchTouchEvent(MotionEvent ev, View view) {
            return X5WebViewEventHandler.this.webView.tbs_dispatchTouchEvent(ev, view);
        }

        @Override // com.tencent.smtt.sdk.WebViewCallbackClient
        public boolean onInterceptTouchEvent(MotionEvent ev, View view) {
            return X5WebViewEventHandler.this.webView.tbs_onInterceptTouchEvent(ev, view);
        }
    };
    private X5WebView webView;

    public X5WebViewEventHandler(X5WebView webView) {
        this.webView = webView;
        this.webView.setWebViewCallbackClient(this.callbackClient);
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void acquireWakeLock() {
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void addFlashView(View arg0, ViewGroup.LayoutParams arg1) {
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void exitFullScreenFlash() {
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public Context getApplicationContex() {
        return null;
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public View getVideoLoadingProgressView() {
        return null;
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public Object getX5WebChromeClientInstance() {
        return null;
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void h5videoExitFullScreen(String arg0) {
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void h5videoRequestFullScreen(String arg0) {
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public boolean onAddFavorite(IX5WebViewExtension arg0, String arg1, String arg2, JsResult arg3) {
        return false;
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void onAllMetaDataFinished(IX5WebViewExtension arg0, HashMap<String, String> arg1) {
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void onBackforwardFinished(int arg0) {
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void onHitTestResultFinished(IX5WebViewExtension arg0, IX5WebViewBase.HitTestResult arg1) {
        Log.i("yuanhaizhou", "onHitTestResultFinished");
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void onHitTestResultForPluginFinished(IX5WebViewExtension arg0, IX5WebViewBase.HitTestResult arg1, Bundle arg2) {
        arg1.getData();
        Log.i("yuanhaizhou", "onHitTestResultForPluginFinished");
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public boolean onPageNotResponding(Runnable arg0) {
        return false;
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void onPrepareX5ReadPageDataFinished(IX5WebViewExtension arg0, HashMap<String, String> arg1) {
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void onPromptNotScalable(IX5WebViewExtension arg0) {
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void onPromptScaleSaved(IX5WebViewExtension arg0) {
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public boolean onSavePassword(String arg0, String arg1, String arg2, boolean arg3, Message arg4) {
        return false;
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void onX5ReadModeAvailableChecked(HashMap<String, String> arg0) {
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void releaseWakeLock() {
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void requestFullScreenFlash() {
    }

    @Override // com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public Object onMiscCallBack(String method, Bundle bundle) {
        return null;
    }

    @Override // com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension
    public boolean onTouchEvent(MotionEvent event, View view) {
        return this.callbackClient.onTouchEvent(event, view);
    }

    @Override // com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension
    public boolean onInterceptTouchEvent(MotionEvent ev, View view) {
        return this.callbackClient.onInterceptTouchEvent(ev, view);
    }

    @Override // com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension
    public boolean dispatchTouchEvent(MotionEvent ev, View view) {
        return this.callbackClient.dispatchTouchEvent(ev, view);
    }

    @Override // com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension
    public boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent, View view) {
        return this.callbackClient.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent, view);
    }

    @Override // com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension
    public void onScrollChanged(int l, int t, int oldl, int oldt, View view) {
        this.callbackClient.onScrollChanged(l, t, oldl, oldt, view);
    }

    @Override // com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension
    public void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY, View view) {
        this.callbackClient.onOverScrolled(scrollX, scrollY, clampedX, clampedY, view);
    }

    @Override // com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension, com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension
    public void computeScroll(View view) {
        this.callbackClient.computeScroll(view);
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void onPrintPage() {
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public boolean onSavePassword(ValueCallback<String> arg0, String arg1, String arg2, String arg3, String arg4, String arg5, boolean arg6) {
        return false;
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void openFileChooser(ValueCallback<Uri[]> arg0, String arg1, String arg2) {
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void onColorModeChanged(long arg0) {
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void jsExitFullScreen() {
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public void jsRequestFullScreen() {
    }

    @Override // com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension
    public boolean onPermissionRequest(String arg0, long arg1, MediaAccessPermissionsCallback arg2) {
        return false;
    }
}
