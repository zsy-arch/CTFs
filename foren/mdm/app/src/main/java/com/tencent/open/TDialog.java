package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.connect.auth.AuthConstants;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.open.a;
import com.tencent.open.a.f;
import com.tencent.open.b.g;
import com.tencent.open.c.b;
import com.tencent.open.utils.ServerSetting;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class TDialog extends b {
    static final FrameLayout.LayoutParams a = new FrameLayout.LayoutParams(-1, -1);
    static Toast b = null;
    private static WeakReference<ProgressDialog> d;
    private WeakReference<Context> c;
    private String e;
    private OnTimeListener f;
    private IUiListener g;
    private FrameLayout h;
    private b i;
    private Handler j;
    private boolean k = false;
    private QQToken l;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public class THandler extends Handler {
        private OnTimeListener mL;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public THandler(OnTimeListener onTimeListener, Looper looper) {
            super(looper);
            TDialog.this = r1;
            this.mL = onTimeListener;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            f.b("openSDK_LOG.TDialog", "--handleMessage--msg.WHAT = " + message.what);
            switch (message.what) {
                case 1:
                    this.mL.onComplete((String) message.obj);
                    return;
                case 2:
                    this.mL.onCancel();
                    return;
                case 3:
                    if (TDialog.this.c != null && TDialog.this.c.get() != null) {
                        TDialog.c((Context) TDialog.this.c.get(), (String) message.obj);
                        return;
                    }
                    return;
                case 4:
                default:
                    return;
                case 5:
                    if (TDialog.this.c != null && TDialog.this.c.get() != null) {
                        TDialog.d((Context) TDialog.this.c.get(), (String) message.obj);
                        return;
                    }
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public static class OnTimeListener implements IUiListener {
        private String mAction;
        String mAppid;
        String mUrl;
        private WeakReference<Context> mWeakCtx;
        private IUiListener mWeakL;

        public OnTimeListener(Context context, String str, String str2, String str3, IUiListener iUiListener) {
            this.mWeakCtx = new WeakReference<>(context);
            this.mAction = str;
            this.mUrl = str2;
            this.mAppid = str3;
            this.mWeakL = iUiListener;
        }

        public void onComplete(String str) {
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
            g.a().a(this.mAction + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, jSONObject.optInt("ret", -6), this.mUrl, false);
            if (this.mWeakL != null) {
                this.mWeakL.onComplete(jSONObject);
                this.mWeakL = null;
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            g.a().a(this.mAction + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, uiError.errorCode, uiError.errorMessage != null ? uiError.errorMessage + this.mUrl : this.mUrl, false);
            if (this.mWeakL != null) {
                this.mWeakL.onError(uiError);
                this.mWeakL = null;
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
            if (this.mWeakL != null) {
                this.mWeakL.onCancel();
                this.mWeakL = null;
            }
        }
    }

    public TDialog(Context context, String str, String str2, IUiListener iUiListener, QQToken qQToken) {
        super(context, 16973840);
        this.l = null;
        this.c = new WeakReference<>(context);
        this.e = str2;
        this.f = new OnTimeListener(context, str, str2, qQToken.getAppId(), iUiListener);
        this.j = new THandler(this.f, context.getMainLooper());
        this.g = iUiListener;
        this.l = qQToken;
    }

    @Override // com.tencent.open.b, android.app.Dialog
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        a();
        b();
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        if (this.f != null) {
            this.f.onCancel();
        }
        super.onBackPressed();
    }

    private void a() {
        new TextView(this.c.get()).setText("test");
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.i = new b(this.c.get());
        this.i.setLayoutParams(layoutParams);
        this.h = new FrameLayout(this.c.get());
        layoutParams.gravity = 17;
        this.h.setLayoutParams(layoutParams);
        this.h.addView(this.i);
        setContentView(this.h);
    }

    @Override // com.tencent.open.b
    protected void onConsoleMessage(String str) {
        f.b("openSDK_LOG.TDialog", "--onConsoleMessage--");
        try {
            this.jsBridge.a(this.i, str);
        } catch (Exception e) {
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void b() {
        this.i.setVerticalScrollBarEnabled(false);
        this.i.setHorizontalScrollBarEnabled(false);
        this.i.setWebViewClient(new FbWebViewClient());
        this.i.setWebChromeClient(this.mChromeClient);
        this.i.clearFormData();
        WebSettings settings = this.i.getSettings();
        if (settings != null) {
            settings.setSavePassword(false);
            settings.setSaveFormData(false);
            settings.setCacheMode(-1);
            settings.setNeedInitialFocus(false);
            settings.setBuiltInZoomControls(true);
            settings.setSupportZoom(true);
            settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
            settings.setJavaScriptEnabled(true);
            if (!(this.c == null || this.c.get() == null)) {
                settings.setDatabaseEnabled(true);
                settings.setDatabasePath(this.c.get().getApplicationContext().getDir("databases", 0).getPath());
            }
            settings.setDomStorageEnabled(true);
            this.jsBridge.a(new JsListener(), "sdk_js_if");
            this.i.loadUrl(this.e);
            this.i.setLayoutParams(a);
            this.i.setVisibility(4);
            this.i.getSettings().setSavePassword(false);
        }
    }

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public class JsListener extends a.b {
        private JsListener() {
            TDialog.this = r1;
        }

        public void onAddShare(String str) {
            f.b("openSDK_LOG.TDialog", "JsListener onAddShare");
            onComplete(str);
        }

        public void onInvite(String str) {
            onComplete(str);
        }

        public void onCancelAddShare(String str) {
            f.e("openSDK_LOG.TDialog", "JsListener onCancelAddShare" + str);
            onCancel(com.umeng.update.net.f.c);
        }

        public void onCancelLogin() {
            onCancel("");
        }

        public void onCancelInvite() {
            f.e("openSDK_LOG.TDialog", "JsListener onCancelInvite");
            onCancel("");
        }

        public void onComplete(String str) {
            TDialog.this.j.obtainMessage(1, str).sendToTarget();
            f.e("openSDK_LOG.TDialog", "JsListener onComplete" + str);
            TDialog.this.dismiss();
        }

        public void onCancel(String str) {
            f.e("openSDK_LOG.TDialog", "JsListener onCancel --msg = " + str);
            TDialog.this.j.obtainMessage(2, str).sendToTarget();
            TDialog.this.dismiss();
        }

        public void showMsg(String str) {
            TDialog.this.j.obtainMessage(3, str).sendToTarget();
        }

        public void onLoad(String str) {
            TDialog.this.j.obtainMessage(4, str).sendToTarget();
        }
    }

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public class FbWebViewClient extends WebViewClient {
        private FbWebViewClient() {
            TDialog.this = r1;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            f.a("openSDK_LOG.TDialog", "Redirect URL: " + str);
            if (str.startsWith(ServerSetting.getInstance().getEnvUrl((Context) TDialog.this.c.get(), ServerSetting.DEFAULT_REDIRECT_URI))) {
                TDialog.this.f.onComplete(Util.parseUrlToJson(str));
                if (TDialog.this.isShowing()) {
                    TDialog.this.dismiss();
                }
                return true;
            } else if (str.startsWith("auth://cancel")) {
                TDialog.this.f.onCancel();
                if (TDialog.this.isShowing()) {
                    TDialog.this.dismiss();
                }
                return true;
            } else if (str.startsWith("auth://close")) {
                if (TDialog.this.isShowing()) {
                    TDialog.this.dismiss();
                }
                return true;
            } else if (str.startsWith("download://")) {
                try {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(Uri.decode(str.substring("download://".length()))));
                    intent.addFlags(268435456);
                    if (!(TDialog.this.c == null || TDialog.this.c.get() == null)) {
                        ((Context) TDialog.this.c.get()).startActivity(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            } else if (str.startsWith(AuthConstants.PROGRESS_URI)) {
                return true;
            } else {
                return false;
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            TDialog.this.f.onError(new UiError(i, str, str2));
            if (!(TDialog.this.c == null || TDialog.this.c.get() == null)) {
                Toast.makeText((Context) TDialog.this.c.get(), "网络连接异常或系统错误", 0).show();
            }
            TDialog.this.dismiss();
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            f.a("openSDK_LOG.TDialog", "Webview loading URL: " + str);
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            TDialog.this.i.setVisibility(0);
        }
    }

    public static void c(Context context, String str) {
        try {
            JSONObject parseJson = Util.parseJson(str);
            int i = parseJson.getInt("type");
            String string = parseJson.getString("msg");
            if (i == 0) {
                if (b == null) {
                    b = Toast.makeText(context, string, 0);
                } else {
                    b.setView(b.getView());
                    b.setText(string);
                    b.setDuration(0);
                }
                b.show();
            } else if (i == 1) {
                if (b == null) {
                    b = Toast.makeText(context, string, 1);
                } else {
                    b.setView(b.getView());
                    b.setText(string);
                    b.setDuration(1);
                }
                b.show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void d(Context context, String str) {
        if (context != null && str != null) {
            try {
                JSONObject parseJson = Util.parseJson(str);
                int i = parseJson.getInt("action");
                String string = parseJson.getString("msg");
                if (i == 1) {
                    if (d == null || d.get() == null) {
                        ProgressDialog progressDialog = new ProgressDialog(context);
                        progressDialog.setMessage(string);
                        d = new WeakReference<>(progressDialog);
                        progressDialog.show();
                    } else {
                        d.get().setMessage(string);
                        if (!d.get().isShowing()) {
                            d.get().show();
                        }
                    }
                } else if (i == 0 && d != null && d.get() != null && d.get().isShowing()) {
                    d.get().dismiss();
                    d = null;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
