package com.hy.frame.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ZoomButtonsController;
import com.hy.frame.util.MyLog;
import com.hyphenate.util.HanziToPinyin;

/* loaded from: classes2.dex */
public class MyWebView extends WebView {
    ZoomButtonsController zoomController;

    public MyWebView(Context context) {
        this(context, null);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        WebSettings wbs = getSettings();
        wbs.setJavaScriptEnabled(true);
        wbs.setJavaScriptCanOpenWindowsAutomatically(true);
        wbs.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wbs.setAppCacheEnabled(true);
        wbs.setBlockNetworkImage(false);
        wbs.setDatabaseEnabled(true);
        wbs.setSaveFormData(false);
        wbs.setAllowFileAccess(true);
        wbs.setDomStorageEnabled(true);
        wbs.setGeolocationEnabled(true);
        wbs.setSupportZoom(true);
        wbs.setBuiltInZoomControls(true);
        wbs.setUseWideViewPort(true);
        wbs.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wbs.setLoadWithOverviewMode(true);
        hideZoom();
        closeSoftWare();
        setVerticalFadingEdgeEnabled(true);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        setWebViewClient(new WebViewClient() { // from class: com.hy.frame.view.MyWebView.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                String address;
                if (url.startsWith(com.tencent.smtt.sdk.WebView.SCHEME_TEL)) {
                    try {
                        Intent intent = new Intent("android.intent.action.DIAL");
                        intent.setData(Uri.parse(url));
                        MyWebView.this.getContext().startActivity(intent);
                        return true;
                    } catch (ActivityNotFoundException e) {
                        MyLog.e("Error dialing " + url + ": " + e.toString());
                        return true;
                    }
                } else if (url.startsWith("geo:")) {
                    try {
                        Intent intent2 = new Intent("android.intent.action.VIEW");
                        intent2.setData(Uri.parse(url));
                        MyWebView.this.getContext().startActivity(intent2);
                        return true;
                    } catch (ActivityNotFoundException e2) {
                        MyLog.e("Error showing map " + url + ": " + e2.toString());
                        return true;
                    }
                } else if (url.startsWith(com.tencent.smtt.sdk.WebView.SCHEME_MAILTO)) {
                    try {
                        Intent intent3 = new Intent("android.intent.action.VIEW");
                        intent3.setData(Uri.parse(url));
                        MyWebView.this.getContext().startActivity(intent3);
                        return true;
                    } catch (ActivityNotFoundException e3) {
                        MyLog.e("Error sending email " + url + ": " + e3.toString());
                        return true;
                    }
                } else if (url.startsWith("sms:")) {
                    try {
                        Intent intent4 = new Intent("android.intent.action.VIEW");
                        int parmIndex = url.indexOf(63);
                        if (parmIndex == -1) {
                            address = url.substring(4);
                        } else {
                            address = url.substring(4, parmIndex);
                            String query = Uri.parse(url).getQuery();
                            if (query != null && query.startsWith("body=")) {
                                intent4.putExtra("sms_body", query.substring(5));
                            }
                        }
                        intent4.setData(Uri.parse("sms:" + address));
                        intent4.putExtra("address", address);
                        intent4.setType("vnd.android-dir/mms-sms");
                        MyWebView.this.getContext().startActivity(intent4);
                        return true;
                    } catch (ActivityNotFoundException e4) {
                        MyLog.e("Error sending sms " + url + ":" + e4.toString());
                        return true;
                    }
                } else if (url.startsWith("market:")) {
                    try {
                        Intent intent5 = new Intent("android.intent.action.VIEW");
                        intent5.setData(Uri.parse(url));
                        MyWebView.this.getContext().startActivity(intent5);
                        return true;
                    } catch (ActivityNotFoundException e5) {
                        MyLog.e("Error loading Google Play Store: " + url + HanziToPinyin.Token.SEPARATOR + e5.toString());
                        return true;
                    }
                } else if (url.endsWith(".apk")) {
                    try {
                        Intent intent6 = new Intent("android.intent.action.VIEW");
                        intent6.setData(Uri.parse(url));
                        MyWebView.this.getContext().startActivity(intent6);
                        return true;
                    } catch (ActivityNotFoundException e6) {
                        MyLog.e("Error dialing " + url + ": " + e6.toString());
                        return true;
                    }
                } else {
                    MyLog.d("url-----> " + url);
                    view.loadUrl(url);
                    return true;
                }
            }
        });
    }

    @SuppressLint({"NewApi"})
    private void hideZoom() {
        if (Build.VERSION.SDK_INT >= 11) {
            getSettings().setDisplayZoomControls(false);
        } else {
            setZoomControlHide(this);
        }
    }

    public void setZoomControlHide(View view) {
        try {
            this.zoomController = (ZoomButtonsController) Class.forName("android.webkit.WebView").getMethod("getZoomButtonsController", new Class[0]).invoke(this, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @TargetApi(11)
    private void closeSoftWare() {
        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
    }
}
