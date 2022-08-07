package com.tencent.smtt.sdk;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import com.tencent.smtt.export.external.interfaces.ClientCertRequest;
import com.tencent.smtt.export.external.interfaces.HttpAuthHandler;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.export.external.interfaces.IX5WebViewClient;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.export.external.proxy.X5ProxyWebViewClient;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.n;
import e.a.a.a.a;

/* loaded from: classes.dex */
public class g extends X5ProxyWebViewClient {

    /* renamed from: c  reason: collision with root package name */
    public static String f1352c;

    /* renamed from: a  reason: collision with root package name */
    public WebViewClient f1353a;

    /* renamed from: b  reason: collision with root package name */
    public WebView f1354b;

    public g(IX5WebViewClient iX5WebViewClient, WebView webView, WebViewClient webViewClient) {
        super(iX5WebViewClient);
        this.f1354b = webView;
        this.f1353a = webViewClient;
        this.f1353a.f1313a = this;
    }

    public void a(WebView webView, String str, Bitmap bitmap) {
        this.f1354b.c();
    }

    public void a(String str) {
        Intent intent = new Intent("android.intent.action.DIAL", Uri.parse(str));
        intent.addFlags(268435456);
        try {
            if (this.f1354b.getContext() != null) {
                this.f1354b.getContext().startActivity(intent);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient
    public void countPVContentCacheCallBack(String str) {
        this.f1354b.f1294a++;
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void doUpdateVisitedHistory(IX5WebViewBase iX5WebViewBase, String str, boolean z) {
        this.f1354b.a(iX5WebViewBase);
        this.f1353a.doUpdateVisitedHistory(this.f1354b, str, z);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onDetectedBlankScreen(IX5WebViewBase iX5WebViewBase, String str, int i) {
        this.f1354b.a(iX5WebViewBase);
        this.f1353a.onDetectedBlankScreen(str, i);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onFormResubmission(IX5WebViewBase iX5WebViewBase, Message message, Message message2) {
        this.f1354b.a(iX5WebViewBase);
        this.f1353a.onFormResubmission(this.f1354b, message, message2);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onLoadResource(IX5WebViewBase iX5WebViewBase, String str) {
        this.f1354b.a(iX5WebViewBase);
        this.f1353a.onLoadResource(this.f1354b, str);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onPageCommitVisible(IX5WebViewBase iX5WebViewBase, String str) {
        this.f1354b.a(iX5WebViewBase);
        this.f1353a.onPageCommitVisible(this.f1354b, str);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onPageFinished(IX5WebViewBase iX5WebViewBase, int i, int i2, String str) {
        n a2;
        if (f1352c == null && (a2 = n.a()) != null) {
            a2.a(false);
            f1352c = Boolean.toString(false);
        }
        this.f1354b.a(iX5WebViewBase);
        WebView webView = this.f1354b;
        webView.f1294a++;
        this.f1353a.onPageFinished(webView, str);
        if (TbsConfig.APP_QZONE.equals(iX5WebViewBase.getView().getContext().getApplicationInfo().packageName)) {
            this.f1354b.a(iX5WebViewBase.getView().getContext());
        }
        TbsLog.app_extra("SmttWebViewClient", iX5WebViewBase.getView().getContext());
        WebView.d();
        if (!TbsShareManager.mHasQueryed && this.f1354b.getContext() != null && TbsShareManager.isThirdPartyApp(this.f1354b.getContext())) {
            TbsShareManager.mHasQueryed = true;
            new Thread(new Runnable() { // from class: com.tencent.smtt.sdk.g.1
                @Override // java.lang.Runnable
                public void run() {
                    if (!TbsShareManager.forceLoadX5FromTBSDemo(g.this.f1354b.getContext()) && TbsDownloader.needDownload(g.this.f1354b.getContext(), false)) {
                        TbsDownloader.startDownload(g.this.f1354b.getContext(), false);
                    }
                }
            }).start();
        }
        if (this.f1354b.getContext() != null && !TbsLogReport.getInstance(this.f1354b.getContext()).getShouldUploadEventReport()) {
            TbsLogReport.getInstance(this.f1354b.getContext()).setShouldUploadEventReport(true);
            TbsLogReport.getInstance(this.f1354b.getContext()).dailyReport();
        }
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onPageFinished(IX5WebViewBase iX5WebViewBase, String str) {
        onPageFinished(iX5WebViewBase, 0, 0, str);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onPageStarted(IX5WebViewBase iX5WebViewBase, int i, int i2, String str, Bitmap bitmap) {
        this.f1354b.a(iX5WebViewBase);
        this.f1353a.onPageStarted(this.f1354b, str, bitmap);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onPageStarted(IX5WebViewBase iX5WebViewBase, String str, Bitmap bitmap) {
        onPageStarted(iX5WebViewBase, 0, 0, str, bitmap);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onReceivedClientCertRequest(IX5WebViewBase iX5WebViewBase, ClientCertRequest clientCertRequest) {
        this.f1354b.a(iX5WebViewBase);
        this.f1353a.onReceivedClientCertRequest(this.f1354b, clientCertRequest);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onReceivedError(IX5WebViewBase iX5WebViewBase, int i, String str, String str2) {
        if (i < -15) {
            if (i == -17) {
                i = -1;
            } else {
                return;
            }
        }
        this.f1354b.a(iX5WebViewBase);
        this.f1353a.onReceivedError(this.f1354b, i, str, str2);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onReceivedError(IX5WebViewBase iX5WebViewBase, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        this.f1354b.a(iX5WebViewBase);
        this.f1353a.onReceivedError(this.f1354b, webResourceRequest, webResourceError);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onReceivedHttpAuthRequest(IX5WebViewBase iX5WebViewBase, HttpAuthHandler httpAuthHandler, String str, String str2) {
        this.f1354b.a(iX5WebViewBase);
        this.f1353a.onReceivedHttpAuthRequest(this.f1354b, httpAuthHandler, str, str2);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onReceivedHttpError(IX5WebViewBase iX5WebViewBase, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
        this.f1354b.a(iX5WebViewBase);
        this.f1353a.onReceivedHttpError(this.f1354b, webResourceRequest, webResourceResponse);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onReceivedLoginRequest(IX5WebViewBase iX5WebViewBase, String str, String str2, String str3) {
        this.f1354b.a(iX5WebViewBase);
        this.f1353a.onReceivedLoginRequest(this.f1354b, str, str2, str3);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onReceivedSslError(IX5WebViewBase iX5WebViewBase, SslErrorHandler sslErrorHandler, SslError sslError) {
        this.f1354b.a(iX5WebViewBase);
        this.f1353a.onReceivedSslError(this.f1354b, sslErrorHandler, sslError);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onScaleChanged(IX5WebViewBase iX5WebViewBase, float f, float f2) {
        this.f1354b.a(iX5WebViewBase);
        this.f1353a.onScaleChanged(this.f1354b, f, f2);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onTooManyRedirects(IX5WebViewBase iX5WebViewBase, Message message, Message message2) {
        this.f1354b.a(iX5WebViewBase);
        this.f1353a.onTooManyRedirects(this.f1354b, message, message2);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public void onUnhandledKeyEvent(IX5WebViewBase iX5WebViewBase, KeyEvent keyEvent) {
        this.f1354b.a(iX5WebViewBase);
        this.f1353a.onUnhandledKeyEvent(this.f1354b, keyEvent);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public WebResourceResponse shouldInterceptRequest(IX5WebViewBase iX5WebViewBase, WebResourceRequest webResourceRequest) {
        this.f1354b.a(iX5WebViewBase);
        return this.f1353a.shouldInterceptRequest(this.f1354b, webResourceRequest);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public WebResourceResponse shouldInterceptRequest(IX5WebViewBase iX5WebViewBase, WebResourceRequest webResourceRequest, Bundle bundle) {
        this.f1354b.a(iX5WebViewBase);
        return this.f1353a.shouldInterceptRequest(this.f1354b, webResourceRequest, bundle);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public WebResourceResponse shouldInterceptRequest(IX5WebViewBase iX5WebViewBase, String str) {
        this.f1354b.a(iX5WebViewBase);
        return this.f1353a.shouldInterceptRequest(this.f1354b, str);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public boolean shouldOverrideKeyEvent(IX5WebViewBase iX5WebViewBase, KeyEvent keyEvent) {
        this.f1354b.a(iX5WebViewBase);
        return this.f1353a.shouldOverrideKeyEvent(this.f1354b, keyEvent);
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public boolean shouldOverrideUrlLoading(IX5WebViewBase iX5WebViewBase, WebResourceRequest webResourceRequest) {
        String uri = (webResourceRequest == null || webResourceRequest.getUrl() == null) ? null : webResourceRequest.getUrl().toString();
        if (uri == null || this.f1354b.showDebugView(uri)) {
            return true;
        }
        this.f1354b.a(iX5WebViewBase);
        boolean shouldOverrideUrlLoading = this.f1353a.shouldOverrideUrlLoading(this.f1354b, webResourceRequest);
        if (!shouldOverrideUrlLoading) {
            if (uri.startsWith("wtai://wp/mc;")) {
                StringBuilder a2 = a.a(WebView.SCHEME_TEL);
                a2.append(uri.substring(13));
                this.f1354b.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(a2.toString())));
                return true;
            } else if (uri.startsWith(WebView.SCHEME_TEL)) {
                a(uri);
                return true;
            }
        }
        return shouldOverrideUrlLoading;
    }

    @Override // com.tencent.smtt.export.external.proxy.ProxyWebViewClient, com.tencent.smtt.export.external.interfaces.IX5WebViewClient
    public boolean shouldOverrideUrlLoading(IX5WebViewBase iX5WebViewBase, String str) {
        if (str == null || this.f1354b.showDebugView(str)) {
            return true;
        }
        this.f1354b.a(iX5WebViewBase);
        boolean shouldOverrideUrlLoading = this.f1353a.shouldOverrideUrlLoading(this.f1354b, str);
        if (!shouldOverrideUrlLoading) {
            if (str.startsWith("wtai://wp/mc;")) {
                StringBuilder a2 = a.a(WebView.SCHEME_TEL);
                a2.append(str.substring(13));
                this.f1354b.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(a2.toString())));
                return true;
            } else if (str.startsWith(WebView.SCHEME_TEL)) {
                a(str);
                return true;
            }
        }
        return shouldOverrideUrlLoading;
    }
}
