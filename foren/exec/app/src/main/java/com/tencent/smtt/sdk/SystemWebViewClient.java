package com.tencent.smtt.sdk;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslCertificate;
import android.os.Build;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebResourceError;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.tencent.smtt.export.external.interfaces.ClientCertRequest;
import com.tencent.smtt.export.external.interfaces.HttpAuthHandler;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebViewClient;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.i;
import com.tencent.smtt.utils.n;
import java.io.InputStream;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Map;

@SuppressLint({"NewApi", "Override"})
/* loaded from: classes.dex */
public class SystemWebViewClient extends WebViewClient {

    /* renamed from: c  reason: collision with root package name */
    public static String f1181c;

    /* renamed from: a  reason: collision with root package name */
    public WebViewClient f1182a;

    /* renamed from: b  reason: collision with root package name */
    public WebView f1183b;

    /* loaded from: classes.dex */
    private static class a extends ClientCertRequest {

        /* renamed from: a  reason: collision with root package name */
        public android.webkit.ClientCertRequest f1189a;

        public a(android.webkit.ClientCertRequest clientCertRequest) {
            this.f1189a = clientCertRequest;
        }

        @Override // com.tencent.smtt.export.external.interfaces.ClientCertRequest
        public void cancel() {
            this.f1189a.cancel();
        }

        @Override // com.tencent.smtt.export.external.interfaces.ClientCertRequest
        public String getHost() {
            return this.f1189a.getHost();
        }

        @Override // com.tencent.smtt.export.external.interfaces.ClientCertRequest
        public String[] getKeyTypes() {
            return this.f1189a.getKeyTypes();
        }

        @Override // com.tencent.smtt.export.external.interfaces.ClientCertRequest
        public int getPort() {
            return this.f1189a.getPort();
        }

        @Override // com.tencent.smtt.export.external.interfaces.ClientCertRequest
        public Principal[] getPrincipals() {
            return this.f1189a.getPrincipals();
        }

        @Override // com.tencent.smtt.export.external.interfaces.ClientCertRequest
        public void ignore() {
            this.f1189a.ignore();
        }

        @Override // com.tencent.smtt.export.external.interfaces.ClientCertRequest
        public void proceed(PrivateKey privateKey, X509Certificate[] x509CertificateArr) {
            this.f1189a.proceed(privateKey, x509CertificateArr);
        }
    }

    /* loaded from: classes.dex */
    private static class b implements HttpAuthHandler {

        /* renamed from: a  reason: collision with root package name */
        public android.webkit.HttpAuthHandler f1190a;

        public b(android.webkit.HttpAuthHandler httpAuthHandler) {
            this.f1190a = httpAuthHandler;
        }

        @Override // com.tencent.smtt.export.external.interfaces.HttpAuthHandler
        public void cancel() {
            this.f1190a.cancel();
        }

        @Override // com.tencent.smtt.export.external.interfaces.HttpAuthHandler
        public void proceed(String str, String str2) {
            this.f1190a.proceed(str, str2);
        }

        @Override // com.tencent.smtt.export.external.interfaces.HttpAuthHandler
        public boolean useHttpAuthUsernamePassword() {
            return this.f1190a.useHttpAuthUsernamePassword();
        }
    }

    /* loaded from: classes.dex */
    private static class c implements SslErrorHandler {

        /* renamed from: a  reason: collision with root package name */
        public android.webkit.SslErrorHandler f1191a;

        public c(android.webkit.SslErrorHandler sslErrorHandler) {
            this.f1191a = sslErrorHandler;
        }

        @Override // com.tencent.smtt.export.external.interfaces.SslErrorHandler
        public void cancel() {
            this.f1191a.cancel();
        }

        @Override // com.tencent.smtt.export.external.interfaces.SslErrorHandler
        public void proceed() {
            this.f1191a.proceed();
        }
    }

    /* loaded from: classes.dex */
    private static class d implements SslError {

        /* renamed from: a  reason: collision with root package name */
        public android.net.http.SslError f1192a;

        public d(android.net.http.SslError sslError) {
            this.f1192a = sslError;
        }

        @Override // com.tencent.smtt.export.external.interfaces.SslError
        public boolean addError(int i) {
            return this.f1192a.addError(i);
        }

        @Override // com.tencent.smtt.export.external.interfaces.SslError
        public SslCertificate getCertificate() {
            return this.f1192a.getCertificate();
        }

        @Override // com.tencent.smtt.export.external.interfaces.SslError
        public int getPrimaryError() {
            return this.f1192a.getPrimaryError();
        }

        @Override // com.tencent.smtt.export.external.interfaces.SslError
        public String getUrl() {
            return this.f1192a.getUrl();
        }

        @Override // com.tencent.smtt.export.external.interfaces.SslError
        public boolean hasError(int i) {
            return this.f1192a.hasError(i);
        }
    }

    /* loaded from: classes.dex */
    static class e implements WebResourceRequest {

        /* renamed from: a  reason: collision with root package name */
        public String f1193a;

        /* renamed from: b  reason: collision with root package name */
        public boolean f1194b;

        /* renamed from: c  reason: collision with root package name */
        public boolean f1195c;

        /* renamed from: d  reason: collision with root package name */
        public boolean f1196d;

        /* renamed from: e  reason: collision with root package name */
        public String f1197e;
        public Map<String, String> f;

        public e(String str, boolean z, boolean z2, boolean z3, String str2, Map<String, String> map) {
            this.f1193a = str;
            this.f1194b = z;
            this.f1195c = z2;
            this.f1196d = z3;
            this.f1197e = str2;
            this.f = map;
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceRequest
        public String getMethod() {
            return this.f1197e;
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceRequest
        public Map<String, String> getRequestHeaders() {
            return this.f;
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceRequest
        public Uri getUrl() {
            return Uri.parse(this.f1193a);
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceRequest
        public boolean hasGesture() {
            return this.f1196d;
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceRequest
        public boolean isForMainFrame() {
            return this.f1194b;
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceRequest
        public boolean isRedirect() {
            return this.f1195c;
        }
    }

    /* loaded from: classes.dex */
    private static class f implements WebResourceRequest {

        /* renamed from: a  reason: collision with root package name */
        public android.webkit.WebResourceRequest f1198a;

        public f(android.webkit.WebResourceRequest webResourceRequest) {
            this.f1198a = webResourceRequest;
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceRequest
        public String getMethod() {
            return this.f1198a.getMethod();
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceRequest
        public Map<String, String> getRequestHeaders() {
            return this.f1198a.getRequestHeaders();
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceRequest
        public Uri getUrl() {
            return this.f1198a.getUrl();
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceRequest
        public boolean hasGesture() {
            return this.f1198a.hasGesture();
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceRequest
        public boolean isForMainFrame() {
            return this.f1198a.isForMainFrame();
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceRequest
        public boolean isRedirect() {
            if (Build.VERSION.SDK_INT >= 24) {
                Object a2 = i.a(this.f1198a, "isRedirect");
                if (a2 instanceof Boolean) {
                    return ((Boolean) a2).booleanValue();
                }
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    private static class g extends WebResourceResponse {

        /* renamed from: a  reason: collision with root package name */
        public android.webkit.WebResourceResponse f1199a;

        public g(android.webkit.WebResourceResponse webResourceResponse) {
            this.f1199a = webResourceResponse;
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceResponse
        public InputStream getData() {
            return this.f1199a.getData();
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceResponse
        public String getEncoding() {
            return this.f1199a.getEncoding();
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceResponse
        public String getMimeType() {
            return this.f1199a.getMimeType();
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceResponse
        public String getReasonPhrase() {
            return this.f1199a.getReasonPhrase();
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceResponse
        public Map<String, String> getResponseHeaders() {
            return this.f1199a.getResponseHeaders();
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceResponse
        public int getStatusCode() {
            return this.f1199a.getStatusCode();
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceResponse
        public void setData(InputStream inputStream) {
            this.f1199a.setData(inputStream);
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceResponse
        public void setEncoding(String str) {
            this.f1199a.setEncoding(str);
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceResponse
        public void setMimeType(String str) {
            this.f1199a.setMimeType(str);
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceResponse
        public void setResponseHeaders(Map<String, String> map) {
            this.f1199a.setResponseHeaders(map);
        }

        @Override // com.tencent.smtt.export.external.interfaces.WebResourceResponse
        public void setStatusCodeAndReasonPhrase(int i, String str) {
            this.f1199a.setStatusCodeAndReasonPhrase(i, str);
        }
    }

    public SystemWebViewClient(WebView webView, WebViewClient webViewClient) {
        this.f1183b = webView;
        this.f1182a = webViewClient;
    }

    @Override // android.webkit.WebViewClient
    public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
        this.f1183b.a(webView);
        this.f1182a.doUpdateVisitedHistory(this.f1183b, str, z);
    }

    @Override // android.webkit.WebViewClient
    public void onFormResubmission(WebView webView, Message message, Message message2) {
        this.f1183b.a(webView);
        this.f1182a.onFormResubmission(this.f1183b, message, message2);
    }

    @Override // android.webkit.WebViewClient
    public void onLoadResource(WebView webView, String str) {
        this.f1183b.a(webView);
        this.f1182a.onLoadResource(this.f1183b, str);
    }

    @Override // android.webkit.WebViewClient
    public void onPageCommitVisible(WebView webView, String str) {
        this.f1183b.a(webView);
        this.f1182a.onPageCommitVisible(this.f1183b, str);
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        n a2;
        if (f1181c == null && (a2 = n.a()) != null) {
            a2.a(true);
            f1181c = Boolean.toString(true);
        }
        this.f1183b.a(webView);
        WebView webView2 = this.f1183b;
        webView2.f1294a++;
        this.f1182a.onPageFinished(webView2, str);
        if (TbsConfig.APP_QZONE.equals(webView.getContext().getApplicationInfo().packageName)) {
            this.f1183b.a(webView.getContext());
        }
        TbsLog.app_extra("SystemWebViewClient", webView.getContext());
        WebView.d();
        if (!TbsShareManager.mHasQueryed && this.f1183b.getContext() != null && TbsShareManager.isThirdPartyApp(this.f1183b.getContext())) {
            TbsShareManager.mHasQueryed = true;
            new Thread(new Runnable() { // from class: com.tencent.smtt.sdk.SystemWebViewClient.1
                @Override // java.lang.Runnable
                public void run() {
                    if (!TbsShareManager.forceLoadX5FromTBSDemo(SystemWebViewClient.this.f1183b.getContext()) && TbsDownloader.needDownload(SystemWebViewClient.this.f1183b.getContext(), false)) {
                        TbsDownloader.startDownload(SystemWebViewClient.this.f1183b.getContext(), false);
                    }
                }
            }).start();
        }
        if (this.f1183b.getContext() != null && !TbsLogReport.getInstance(this.f1183b.getContext()).getShouldUploadEventReport()) {
            TbsLogReport.getInstance(this.f1183b.getContext()).setShouldUploadEventReport(true);
            TbsLogReport.getInstance(this.f1183b.getContext()).dailyReport();
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        this.f1183b.a(webView);
        this.f1182a.onPageStarted(this.f1183b, str, bitmap);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedClientCertRequest(WebView webView, android.webkit.ClientCertRequest clientCertRequest) {
        int i = Build.VERSION.SDK_INT;
        this.f1183b.a(webView);
        this.f1182a.onReceivedClientCertRequest(this.f1183b, new a(clientCertRequest));
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        this.f1183b.a(webView);
        this.f1182a.onReceivedError(this.f1183b, i, str, str2);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, android.webkit.WebResourceRequest webResourceRequest, final WebResourceError webResourceError) {
        this.f1183b.a(webView);
        com.tencent.smtt.export.external.interfaces.WebResourceError webResourceError2 = null;
        f fVar = webResourceRequest != null ? new f(webResourceRequest) : null;
        if (webResourceError != null) {
            webResourceError2 = new com.tencent.smtt.export.external.interfaces.WebResourceError() { // from class: com.tencent.smtt.sdk.SystemWebViewClient.2
                @Override // com.tencent.smtt.export.external.interfaces.WebResourceError
                public CharSequence getDescription() {
                    return webResourceError.getDescription();
                }

                @Override // com.tencent.smtt.export.external.interfaces.WebResourceError
                public int getErrorCode() {
                    return webResourceError.getErrorCode();
                }
            };
        }
        this.f1182a.onReceivedError(this.f1183b, fVar, webResourceError2);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedHttpAuthRequest(WebView webView, android.webkit.HttpAuthHandler httpAuthHandler, String str, String str2) {
        this.f1183b.a(webView);
        this.f1182a.onReceivedHttpAuthRequest(this.f1183b, new b(httpAuthHandler), str, str2);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedHttpError(WebView webView, android.webkit.WebResourceRequest webResourceRequest, android.webkit.WebResourceResponse webResourceResponse) {
        this.f1183b.a(webView);
        this.f1182a.onReceivedHttpError(this.f1183b, new f(webResourceRequest), new g(webResourceResponse));
    }

    @Override // android.webkit.WebViewClient
    @TargetApi(12)
    public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
        int i = Build.VERSION.SDK_INT;
        this.f1183b.a(webView);
        this.f1182a.onReceivedLoginRequest(this.f1183b, str, str2, str3);
    }

    @Override // android.webkit.WebViewClient
    @TargetApi(8)
    public void onReceivedSslError(WebView webView, android.webkit.SslErrorHandler sslErrorHandler, android.net.http.SslError sslError) {
        int i = Build.VERSION.SDK_INT;
        this.f1183b.a(webView);
        this.f1182a.onReceivedSslError(this.f1183b, new c(sslErrorHandler), new d(sslError));
    }

    @Override // android.webkit.WebViewClient
    public boolean onRenderProcessGone(WebView webView, final RenderProcessGoneDetail renderProcessGoneDetail) {
        this.f1183b.a(webView);
        return this.f1182a.onRenderProcessGone(this.f1183b, new WebViewClient.a() { // from class: com.tencent.smtt.sdk.SystemWebViewClient.3
        });
    }

    @Override // android.webkit.WebViewClient
    public void onScaleChanged(WebView webView, float f2, float f3) {
        this.f1183b.a(webView);
        this.f1182a.onScaleChanged(this.f1183b, f2, f3);
    }

    @Override // android.webkit.WebViewClient
    public void onTooManyRedirects(WebView webView, Message message, Message message2) {
        this.f1183b.a(webView);
        this.f1182a.onTooManyRedirects(this.f1183b, message, message2);
    }

    @Override // android.webkit.WebViewClient
    public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
        this.f1183b.a(webView);
        this.f1182a.onUnhandledKeyEvent(this.f1183b, keyEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0046 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0047  */
    @Override // android.webkit.WebViewClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.webkit.WebResourceResponse shouldInterceptRequest(android.webkit.WebView r10, android.webkit.WebResourceRequest r11) {
        /*
            r9 = this;
            int r10 = android.os.Build.VERSION.SDK_INT
            r0 = 0
            if (r11 != 0) goto L_0x0006
            return r0
        L_0x0006:
            r1 = 0
            r2 = 24
            if (r10 < r2) goto L_0x001d
            java.lang.String r10 = "isRedirect"
            java.lang.Object r10 = com.tencent.smtt.utils.i.a(r11, r10)
            boolean r2 = r10 instanceof java.lang.Boolean
            if (r2 == 0) goto L_0x001d
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r1 = r10.booleanValue()
            r5 = r1
            goto L_0x001e
        L_0x001d:
            r5 = 0
        L_0x001e:
            com.tencent.smtt.sdk.SystemWebViewClient$e r10 = new com.tencent.smtt.sdk.SystemWebViewClient$e
            android.net.Uri r1 = r11.getUrl()
            java.lang.String r3 = r1.toString()
            boolean r4 = r11.isForMainFrame()
            boolean r6 = r11.hasGesture()
            java.lang.String r7 = r11.getMethod()
            java.util.Map r8 = r11.getRequestHeaders()
            r2 = r10
            r2.<init>(r3, r4, r5, r6, r7, r8)
            com.tencent.smtt.sdk.WebViewClient r11 = r9.f1182a
            com.tencent.smtt.sdk.WebView r1 = r9.f1183b
            com.tencent.smtt.export.external.interfaces.WebResourceResponse r10 = r11.shouldInterceptRequest(r1, r10)
            if (r10 != 0) goto L_0x0047
            return r0
        L_0x0047:
            android.webkit.WebResourceResponse r11 = new android.webkit.WebResourceResponse
            java.lang.String r0 = r10.getMimeType()
            java.lang.String r1 = r10.getEncoding()
            java.io.InputStream r2 = r10.getData()
            r11.<init>(r0, r1, r2)
            java.util.Map r0 = r10.getResponseHeaders()
            r11.setResponseHeaders(r0)
            int r0 = r10.getStatusCode()
            java.lang.String r10 = r10.getReasonPhrase()
            int r1 = r11.getStatusCode()
            if (r0 != r1) goto L_0x0079
            if (r10 == 0) goto L_0x007c
            java.lang.String r1 = r11.getReasonPhrase()
            boolean r1 = r10.equals(r1)
            if (r1 != 0) goto L_0x007c
        L_0x0079:
            r11.setStatusCodeAndReasonPhrase(r0, r10)
        L_0x007c:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.SystemWebViewClient.shouldInterceptRequest(android.webkit.WebView, android.webkit.WebResourceRequest):android.webkit.WebResourceResponse");
    }

    @Override // android.webkit.WebViewClient
    @TargetApi(11)
    public android.webkit.WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        int i = Build.VERSION.SDK_INT;
        WebResourceResponse shouldInterceptRequest = this.f1182a.shouldInterceptRequest(this.f1183b, str);
        if (shouldInterceptRequest == null) {
            return null;
        }
        return new android.webkit.WebResourceResponse(shouldInterceptRequest.getMimeType(), shouldInterceptRequest.getEncoding(), shouldInterceptRequest.getData());
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        this.f1183b.a(webView);
        return this.f1182a.shouldOverrideKeyEvent(this.f1183b, keyEvent);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, android.webkit.WebResourceRequest webResourceRequest) {
        boolean z;
        String uri = (webResourceRequest == null || webResourceRequest.getUrl() == null) ? null : webResourceRequest.getUrl().toString();
        if (uri == null || this.f1183b.showDebugView(uri)) {
            return true;
        }
        this.f1183b.a(webView);
        if (Build.VERSION.SDK_INT >= 24) {
            Object a2 = i.a(webResourceRequest, "isRedirect");
            if (a2 instanceof Boolean) {
                z = ((Boolean) a2).booleanValue();
                return this.f1182a.shouldOverrideUrlLoading(this.f1183b, new e(webResourceRequest.getUrl().toString(), webResourceRequest.isForMainFrame(), z, webResourceRequest.hasGesture(), webResourceRequest.getMethod(), webResourceRequest.getRequestHeaders()));
            }
        }
        z = false;
        return this.f1182a.shouldOverrideUrlLoading(this.f1183b, new e(webResourceRequest.getUrl().toString(), webResourceRequest.isForMainFrame(), z, webResourceRequest.hasGesture(), webResourceRequest.getMethod(), webResourceRequest.getRequestHeaders()));
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str == null || this.f1183b.showDebugView(str)) {
            return true;
        }
        this.f1183b.a(webView);
        return this.f1182a.shouldOverrideUrlLoading(this.f1183b, str);
    }
}
