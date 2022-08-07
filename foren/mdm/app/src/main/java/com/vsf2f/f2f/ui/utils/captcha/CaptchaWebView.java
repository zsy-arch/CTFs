package com.vsf2f.f2f.ui.utils.captcha;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class CaptchaWebView extends WebView {
    private CaptchaDialog captchaDialog;
    private CaptchaListener captchaListener;
    private Context context;
    private boolean debug;
    private WebChromeClient mWebChromeClient;
    private WebViewClientBase mWebViewClientBase;
    private WebView webView = this;
    private int mTimeout = 10000;
    private ScheduledExecutorService scheduledExecutorService = null;

    public CaptchaWebView(Context context, CaptchaListener captchaListener, CaptchaDialog captchaDialog) {
        super(context);
        this.debug = false;
        this.mWebViewClientBase = null;
        this.mWebChromeClient = null;
        this.context = context;
        this.captchaDialog = captchaDialog;
        this.captchaListener = captchaListener;
        this.debug = captchaDialog.isDebug();
        this.mWebViewClientBase = new WebViewClientBase();
        this.mWebChromeClient = new WebChromeClientBase();
        setWebView();
    }

    private void setWebView() {
        WebSettings webSettings = getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setUseWideViewPort(true);
        setOverScrollMode(2);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        setWebViewClient(this.mWebViewClientBase);
        setWebChromeClient(this.mWebChromeClient);
        onResume();
    }

    /* loaded from: classes2.dex */
    private class WebChromeClientBase extends WebChromeClient {
        private WebChromeClientBase() {
        }

        @Override // android.webkit.WebChromeClient
        public void onCloseWindow(WebView window) {
            CaptchaWebView.this.captchaDialog.cancel();
            super.onCloseWindow(window);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class WebViewClientBase extends WebViewClient {
        private WebViewClientBase() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(url));
                CaptchaWebView.this.context.startActivity(intent);
                return true;
            } catch (Exception e) {
                Log.e(Captcha.TAG, "shouldOverrideUrlLoading intent error:" + e.toString());
                return true;
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.i(Captcha.TAG, "webview did start");
            super.onPageStarted(view, url, favicon);
            TimerTask timerTask = new TimerTask() { // from class: com.vsf2f.f2f.ui.utils.captcha.CaptchaWebView.WebViewClientBase.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    ((Activity) CaptchaWebView.this.context).runOnUiThread(new Runnable() { // from class: com.vsf2f.f2f.ui.utils.captcha.CaptchaWebView.WebViewClientBase.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (CaptchaWebView.this.webView.getProgress() < 100) {
                                CaptchaWebView.this.webView.stopLoading();
                                if (CaptchaWebView.this.captchaListener != null) {
                                    Log.d(Captcha.TAG, "time out 2");
                                    CaptchaWebView.this.captchaListener.onReady(false);
                                }
                            }
                        }
                    });
                }
            };
            if (CaptchaWebView.this.scheduledExecutorService == null) {
                CaptchaWebView.this.scheduledExecutorService = Executors.newScheduledThreadPool(2);
            }
            CaptchaWebView.this.scheduledExecutorService.schedule(timerTask, CaptchaWebView.this.mTimeout, TimeUnit.MILLISECONDS);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView view, String url) {
            if (CaptchaWebView.this.captchaDialog.isShowing()) {
                if (CaptchaWebView.this.scheduledExecutorService != null && !CaptchaWebView.this.scheduledExecutorService.isShutdown()) {
                    CaptchaWebView.this.scheduledExecutorService.shutdown();
                }
                Log.i(Captcha.TAG, "webview did Finished");
            }
            super.onPageFinished(view, url);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            if (errorCode == -2 || errorCode == -14) {
                CaptchaWebView.this.captchaListener.onError("errorERROR_FILE_NOT_FOUND" + errorCode);
            }
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(23)
        public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
            CaptchaWebView.this.captchaListener.onError(req.toString() + rerr.toString());
            super.onReceivedError(view, req, rerr);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            Log.d(Captcha.TAG, request.toString() + errorResponse.toString());
            if (CaptchaWebView.this.captchaListener != null) {
                Log.d(Captcha.TAG, "onReceivedHttpError ");
            }
            super.onReceivedHttpError(view, request, errorResponse);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            if (CaptchaWebView.this.captchaListener != null) {
                Log.d(Captcha.TAG, "onReceivedHttpError ");
            }
            handler.cancel();
        }
    }
}
