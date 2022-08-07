package com.tencent.connect.auth;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.alipay.sdk.sys.a;
import com.tencent.connect.auth.AuthMap;
import com.tencent.connect.common.Constants;
import com.tencent.open.a.f;
import com.tencent.open.b.g;
import com.tencent.open.c.c;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.ServerSetting;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.Util;
import com.tencent.open.web.security.JniInterface;
import com.tencent.open.web.security.SecureJsInterface;
import com.tencent.open.web.security.b;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class AuthDialog extends Dialog {
    private String a;
    private OnTimeListener b;
    private IUiListener c;
    private Handler d;
    private FrameLayout e;
    private LinearLayout f;
    private FrameLayout g;
    private ProgressBar h;
    private String i;
    private c j;
    private Context k;
    private int n;
    private String o;
    private String p;
    private HashMap<String, Runnable> s;
    private boolean m = false;
    private long q = 0;
    private long r = 30000;
    private b l = new b();

    static /* synthetic */ String a(AuthDialog authDialog, Object obj) {
        String str = authDialog.a + obj;
        authDialog.a = str;
        return str;
    }

    static /* synthetic */ int m(AuthDialog authDialog) {
        int i = authDialog.n;
        authDialog.n = i + 1;
        return i;
    }

    static {
        try {
            Context context = Global.getContext();
            if (context == null) {
                f.c("openSDK_LOG.AuthDialog", "-->load lib fail, because context is null:" + AuthAgent.SECURE_LIB_NAME);
            } else if (new File(context.getFilesDir().toString() + "/" + AuthAgent.SECURE_LIB_NAME).exists()) {
                System.load(context.getFilesDir().toString() + "/" + AuthAgent.SECURE_LIB_NAME);
                f.c("openSDK_LOG.AuthDialog", "-->load lib success:" + AuthAgent.SECURE_LIB_NAME);
            } else {
                f.c("openSDK_LOG.AuthDialog", "-->fail, because so is not exists:" + AuthAgent.SECURE_LIB_NAME);
            }
        } catch (Exception e) {
            f.b("openSDK_LOG.AuthDialog", "-->load lib error:" + AuthAgent.SECURE_LIB_NAME, e);
        }
    }

    public AuthDialog(Context context, String str, String str2, IUiListener iUiListener, QQToken qQToken) {
        super(context, 16973840);
        this.k = context;
        this.a = str2;
        this.b = new OnTimeListener(str, str2, qQToken.getAppId(), iUiListener);
        this.d = new THandler(this.b, context.getMainLooper());
        this.c = iUiListener;
        this.i = str;
        getWindow().setSoftInputMode(32);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        b();
        d();
        this.s = new HashMap<>();
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        if (!this.m) {
            this.b.onCancel();
        }
        super.onBackPressed();
    }

    @Override // android.app.Dialog
    protected void onStop() {
        super.onStop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public class THandler extends Handler {
        private OnTimeListener b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public THandler(OnTimeListener onTimeListener, Looper looper) {
            super(looper);
            AuthDialog.this = r1;
            this.b = onTimeListener;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.b.a((String) message.obj);
                    return;
                case 2:
                    this.b.onCancel();
                    return;
                case 3:
                    AuthDialog.b(AuthDialog.this.k, (String) message.obj);
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public class OnTimeListener implements IUiListener {
        String a;
        String b;
        private String d;
        private IUiListener e;

        public OnTimeListener(String str, String str2, String str3, IUiListener iUiListener) {
            AuthDialog.this = r1;
            this.d = str;
            this.a = str2;
            this.b = str3;
            this.e = iUiListener;
        }

        public void a(String str) {
            try {
                onComplete(Util.parseJson(str));
            } catch (JSONException e) {
                e.printStackTrace();
                onError(new UiError(-4, Constants.MSG_JSON_ERROR, str));
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            JSONObject jSONObject = (JSONObject) obj;
            g.a().a(this.d + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, jSONObject.optInt("ret", -6), this.a, false);
            if (this.e != null) {
                this.e.onComplete(jSONObject);
                this.e = null;
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            String str = uiError.errorMessage != null ? uiError.errorMessage + this.a : this.a;
            g.a().a(this.d + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, uiError.errorCode, str, false);
            AuthDialog.this.a(str);
            if (this.e != null) {
                this.e.onError(uiError);
                this.e = null;
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
            if (this.e != null) {
                this.e.onCancel();
                this.e = null;
            }
        }
    }

    public String a(String str) {
        StringBuilder sb = new StringBuilder(str);
        if (!TextUtils.isEmpty(this.p) && this.p.length() >= 4) {
            sb.append("_u_").append(this.p.substring(this.p.length() - 4));
        }
        return sb.toString();
    }

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public class LoginWebViewClient extends WebViewClient {
        private LoginWebViewClient() {
            AuthDialog.this = r1;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            f.a("openSDK_LOG.AuthDialog", "-->Redirect URL: " + str);
            if (str.startsWith(AuthConstants.REDIRECT_BROWSER_URI)) {
                JSONObject parseUrlToJson = Util.parseUrlToJson(str);
                AuthDialog.this.m = AuthDialog.this.e();
                if (!AuthDialog.this.m) {
                    if (parseUrlToJson.optString("fail_cb", null) != null) {
                        AuthDialog.this.callJs(parseUrlToJson.optString("fail_cb"), "");
                    } else if (parseUrlToJson.optInt("fall_to_wv") == 1) {
                        AuthDialog.a(AuthDialog.this, (Object) (AuthDialog.this.a.indexOf("?") > -1 ? a.b : "?"));
                        AuthDialog.a(AuthDialog.this, (Object) "browser_error=1");
                        AuthDialog.this.j.loadUrl(AuthDialog.this.a);
                    } else {
                        String optString = parseUrlToJson.optString("redir", null);
                        if (optString != null) {
                            AuthDialog.this.j.loadUrl(optString);
                        }
                    }
                }
                return true;
            } else if (str.startsWith(ServerSetting.DEFAULT_REDIRECT_URI)) {
                AuthDialog.this.b.onComplete(Util.parseUrlToJson(str));
                AuthDialog.this.dismiss();
                return true;
            } else if (str.startsWith("auth://cancel")) {
                AuthDialog.this.b.onCancel();
                AuthDialog.this.dismiss();
                return true;
            } else if (str.startsWith("auth://close")) {
                AuthDialog.this.dismiss();
                return true;
            } else if (str.startsWith("download://")) {
                try {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(Uri.decode(str.substring("download://".length()))));
                    intent.addFlags(268435456);
                    AuthDialog.this.k.startActivity(intent);
                } catch (Exception e) {
                    f.b("openSDK_LOG.AuthDialog", "-->start download activity exception, e: ", e);
                }
                return true;
            } else if (str.startsWith(AuthConstants.PROGRESS_URI)) {
                try {
                    List<String> pathSegments = Uri.parse(str).getPathSegments();
                    if (pathSegments.isEmpty()) {
                        return true;
                    }
                    int intValue = Integer.valueOf(pathSegments.get(0)).intValue();
                    if (intValue == 0) {
                        AuthDialog.this.g.setVisibility(8);
                        AuthDialog.this.j.setVisibility(0);
                    } else if (intValue == 1) {
                        AuthDialog.this.g.setVisibility(0);
                    }
                    return true;
                } catch (Exception e2) {
                    return true;
                }
            } else if (str.startsWith(AuthConstants.ON_LOGIN_URI)) {
                try {
                    List<String> pathSegments2 = Uri.parse(str).getPathSegments();
                    if (!pathSegments2.isEmpty()) {
                        AuthDialog.this.p = pathSegments2.get(0);
                    }
                } catch (Exception e3) {
                }
                return true;
            } else if (AuthDialog.this.l.a(AuthDialog.this.j, str)) {
                return true;
            } else {
                f.c("openSDK_LOG.AuthDialog", "-->Redirect URL: return false");
                return false;
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            f.c("openSDK_LOG.AuthDialog", "-->onReceivedError, errorCode: " + i + " | description: " + str);
            if (!Util.checkNetWork(AuthDialog.this.k)) {
                AuthDialog.this.b.onError(new UiError(9001, "当前网络不可用，请稍后重试！", str2));
                AuthDialog.this.dismiss();
            } else if (!AuthDialog.this.o.startsWith(ServerSetting.DOWNLOAD_QQ_URL)) {
                long elapsedRealtime = SystemClock.elapsedRealtime() - AuthDialog.this.q;
                if (AuthDialog.this.n >= 1 || elapsedRealtime >= AuthDialog.this.r) {
                    AuthDialog.this.j.loadUrl(AuthDialog.this.a());
                    return;
                }
                AuthDialog.m(AuthDialog.this);
                AuthDialog.this.d.postDelayed(new Runnable() { // from class: com.tencent.connect.auth.AuthDialog.LoginWebViewClient.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AuthDialog.this.j.loadUrl(AuthDialog.this.o);
                    }
                }, 500L);
            } else {
                AuthDialog.this.b.onError(new UiError(i, str, str2));
                AuthDialog.this.dismiss();
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            f.a("openSDK_LOG.AuthDialog", "-->onPageStarted, url: " + str);
            super.onPageStarted(webView, str, bitmap);
            AuthDialog.this.g.setVisibility(0);
            AuthDialog.this.q = SystemClock.elapsedRealtime();
            if (!TextUtils.isEmpty(AuthDialog.this.o)) {
                AuthDialog.this.d.removeCallbacks((Runnable) AuthDialog.this.s.remove(AuthDialog.this.o));
            }
            AuthDialog.this.o = str;
            TimeOutRunable timeOutRunable = new TimeOutRunable(AuthDialog.this.o);
            AuthDialog.this.s.put(str, timeOutRunable);
            AuthDialog.this.d.postDelayed(timeOutRunable, 120000L);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            f.a("openSDK_LOG.AuthDialog", "-->onPageFinished, url: " + str);
            AuthDialog.this.g.setVisibility(8);
            if (AuthDialog.this.j != null) {
                AuthDialog.this.j.setVisibility(0);
            }
            if (!TextUtils.isEmpty(str)) {
                AuthDialog.this.d.removeCallbacks((Runnable) AuthDialog.this.s.remove(str));
            }
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(8)
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.cancel();
            AuthDialog.this.b.onError(new UiError(sslError.getPrimaryError(), "请求不合法，请检查手机安全设置，如系统时间、代理等。", "ssl error"));
            AuthDialog.this.dismiss();
        }
    }

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    class TimeOutRunable implements Runnable {
        String a;

        public TimeOutRunable(String str) {
            AuthDialog.this = r2;
            this.a = "";
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            f.a("openSDK_LOG.AuthDialog", "-->timeoutUrl: " + this.a + " | mRetryUrl: " + AuthDialog.this.o);
            if (this.a.equals(AuthDialog.this.o)) {
                AuthDialog.this.b.onError(new UiError(9002, "请求页面超时，请稍后重试！", AuthDialog.this.o));
                AuthDialog.this.dismiss();
            }
        }
    }

    public String a() {
        String str = ServerSetting.DOWNLOAD_QQ_URL + this.a.substring(this.a.indexOf("?") + 1);
        f.c("openSDK_LOG.AuthDialog", "-->generateDownloadUrl, url: http://qzs.qq.com/open/mobile/login/qzsjump.html?");
        return str;
    }

    private void b() {
        c();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.j = new c(this.k);
        if (Build.VERSION.SDK_INT >= 11) {
            this.j.setLayerType(1, null);
        }
        this.j.setLayoutParams(layoutParams);
        this.e = new FrameLayout(this.k);
        layoutParams.gravity = 17;
        this.e.setLayoutParams(layoutParams);
        this.e.addView(this.j);
        this.e.addView(this.g);
        setContentView(this.e);
    }

    private void c() {
        this.h = new ProgressBar(this.k);
        this.h.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        this.f = new LinearLayout(this.k);
        TextView textView = null;
        if (this.i.equals(SystemUtils.ACTION_LOGIN)) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 16;
            layoutParams.leftMargin = 5;
            textView = new TextView(this.k);
            if (Locale.getDefault().getLanguage().equals("zh")) {
                textView.setText("登录中...");
            } else {
                textView.setText("Logging in...");
            }
            textView.setTextColor(Color.rgb(255, 255, 255));
            textView.setTextSize(18.0f);
            textView.setLayoutParams(layoutParams);
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 17;
        this.f.setLayoutParams(layoutParams2);
        this.f.addView(this.h);
        if (textView != null) {
            this.f.addView(textView);
        }
        this.g = new FrameLayout(this.k);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-1, -2);
        layoutParams3.leftMargin = 80;
        layoutParams3.rightMargin = 80;
        layoutParams3.topMargin = 40;
        layoutParams3.bottomMargin = 40;
        layoutParams3.gravity = 17;
        this.g.setLayoutParams(layoutParams3);
        this.g.setBackgroundResource(17301504);
        this.g.addView(this.f);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void d() {
        this.j.setVerticalScrollBarEnabled(false);
        this.j.setHorizontalScrollBarEnabled(false);
        this.j.setWebViewClient(new LoginWebViewClient());
        this.j.setWebChromeClient(new WebChromeClient());
        this.j.clearFormData();
        this.j.clearSslPreferences();
        this.j.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.tencent.connect.auth.AuthDialog.1
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                return true;
            }
        });
        this.j.setOnTouchListener(new View.OnTouchListener() { // from class: com.tencent.connect.auth.AuthDialog.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                    case 1:
                        if (view.hasFocus()) {
                            return false;
                        }
                        view.requestFocus();
                        return false;
                    default:
                        return false;
                }
            }
        });
        WebSettings settings = this.j.getSettings();
        settings.setSavePassword(false);
        settings.setSaveFormData(false);
        settings.setCacheMode(-1);
        settings.setNeedInitialFocus(false);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(this.k.getDir("databases", 0).getPath());
        settings.setDomStorageEnabled(true);
        f.a("openSDK_LOG.AuthDialog", "-->mUrl : " + this.a);
        this.o = this.a;
        this.j.loadUrl(this.a);
        this.j.setVisibility(4);
        this.j.getSettings().setSavePassword(false);
        this.l.a(new SecureJsInterface(), "SecureJsInterface");
        SecureJsInterface.isPWDEdit = false;
        super.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.tencent.connect.auth.AuthDialog.3
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                try {
                    JniInterface.clearAllPWD();
                } catch (Exception e) {
                }
            }
        });
    }

    public boolean e() {
        AuthMap instance = AuthMap.getInstance();
        String makeKey = instance.makeKey();
        AuthMap.Auth auth = new AuthMap.Auth();
        auth.listener = this.c;
        auth.dialog = this;
        auth.key = makeKey;
        String str = instance.set(auth);
        String substring = this.a.substring(0, this.a.indexOf("?"));
        Bundle parseUrl = Util.parseUrl(this.a);
        parseUrl.putString("token_key", makeKey);
        parseUrl.putString("serial", str);
        parseUrl.putString("browser", "1");
        this.a = substring + "?" + HttpUtils.encodeUrl(parseUrl);
        return Util.openBrowser(this.k, this.a);
    }

    public static void b(Context context, String str) {
        try {
            JSONObject parseJson = Util.parseJson(str);
            int i = parseJson.getInt("type");
            Toast.makeText(context.getApplicationContext(), parseJson.getString("msg"), i).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void callJs(String str, String str2) {
        this.j.loadUrl("javascript:" + str + "(" + str2 + ");void(" + System.currentTimeMillis() + ");");
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        this.s.clear();
        this.d.removeCallbacksAndMessages(null);
        if (isShowing()) {
            super.dismiss();
        }
        if (this.j != null) {
            this.j.destroy();
            this.j = null;
        }
    }
}
