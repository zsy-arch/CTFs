package com.cdlinglu.utils.x5WebView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

/* loaded from: classes.dex */
public class SystemWebView extends WebView {
    public SystemWebView(Context context) {
        super(context);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public SystemWebView(Context context, AttributeSet attr) {
        super(context, attr);
        setClickable(true);
        initSettings();
        setOnTouchListener(new View.OnTouchListener() { // from class: com.cdlinglu.utils.x5WebView.SystemWebView.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    private void initSettings() {
        WebSettings webSetting = getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
    }
}
