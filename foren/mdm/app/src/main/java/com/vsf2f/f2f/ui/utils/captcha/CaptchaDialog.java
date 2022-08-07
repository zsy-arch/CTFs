package com.vsf2f.f2f.ui.utils.captcha;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class CaptchaDialog extends Dialog {
    private float dScale;
    private int dWidth;
    private Context dcontext;
    private boolean isEnglishLangulage;
    private CaptchaWebView dwebview = null;
    private CaptchaListener dcaListener = null;
    private String dDeviceId = "";
    private String dCaptchaId = "";
    private String dTitle = "";
    private boolean debug = false;
    private boolean isShowing = false;
    private int mPositionX = -1;
    private int mPositionY = -1;
    private int mPositionW = -1;
    private int mPositionH = -1;
    private ProgressDialog progressDialog = null;

    public CaptchaDialog(Context context, boolean isEnglishLangulage) {
        super(context);
        this.dcontext = null;
        this.dcontext = context;
        this.isEnglishLangulage = isEnglishLangulage;
    }

    public CaptchaDialog(Context context, int themeResId) {
        super(context, R.style.DialogTheme);
        this.dcontext = null;
        this.dcontext = context;
    }

    public void setPosition(int left, int top, int w, int h) {
        this.mPositionX = left;
        this.mPositionY = top;
        this.mPositionW = w;
        this.mPositionH = h;
    }

    public CaptchaDialog setTitle(String title) {
        this.dTitle = title;
        return this;
    }

    public CaptchaDialog setCaListener(CaptchaListener caListener) {
        this.dcaListener = caListener;
        return this;
    }

    public CaptchaDialog setCaptchaId(String captchaId) {
        this.dCaptchaId = captchaId;
        return this;
    }

    public boolean isDebug() {
        return this.debug;
    }

    public CaptchaDialog setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

    private String getDeviceId() {
        TelephonyManager tel;
        try {
            if (this.dDeviceId.equals("") && (tel = (TelephonyManager) this.dcontext.getSystemService("phone")) != null) {
                this.dDeviceId = tel.getDeviceId();
            }
        } catch (Exception e) {
            Log.e(Captcha.TAG, "getImei failed");
        }
        return this.dDeviceId;
    }

    public CaptchaDialog setDeviceId(String deviceId) {
        this.dDeviceId = deviceId;
        return this;
    }

    public ProgressDialog getProgressDialog() {
        return this.progressDialog;
    }

    public CaptchaDialog setProgressDialog(ProgressDialog progressDialog) {
        if (this.progressDialog == null && progressDialog != null) {
            this.progressDialog = progressDialog;
        }
        return this;
    }

    @Override // android.app.Dialog
    public boolean isShowing() {
        return this.isShowing;
    }

    public void initDialog() {
        Log.d(Captcha.TAG, "start init dialog");
        getDialogWidth();
        setWebView();
    }

    private void getDialogWidth() {
        try {
            DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
            int width = metrics.widthPixels;
            int height = metrics.heightPixels;
            float scale = metrics.density;
            this.dScale = scale;
            if (this.mPositionW > 270) {
                this.dWidth = this.mPositionW;
                return;
            }
            if (height < width) {
                width = (height * 3) / 4;
            }
            int width2 = (width * 4) / 5;
            if (((int) (width2 / scale)) < 270) {
                width2 = (int) (270.0f * scale);
            }
            this.dWidth = width2;
        } catch (Exception e) {
            Log.e(Captcha.TAG, "getDialogWidth failed");
        }
    }

    private void setWebView() {
        if (this.dwebview == null) {
            this.dwebview = new CaptchaWebView(this.dcontext, this.dcaListener, this);
        }
        StringBuffer sburl = new StringBuffer();
        sburl.append(Captcha.baseURL);
        sburl.append("?captchaId=" + this.dCaptchaId);
        sburl.append("&deviceId=" + getDeviceId());
        sburl.append("&os=android");
        sburl.append("&osVer=" + Build.VERSION.RELEASE);
        sburl.append("&sdkVer=2.4.2");
        sburl.append("&title=" + this.dTitle);
        sburl.append("&debug=" + this.debug);
        sburl.append("&width=" + ((int) (this.dWidth / this.dScale)));
        if (this.isEnglishLangulage) {
            sburl.append("&lang=en");
        }
        String requrl = sburl.toString();
        Log.d(Captcha.TAG, "url: " + requrl);
        this.dwebview.addJavascriptInterface(new JSInterface(this.dcontext, this.dcaListener, this), "JSInterface");
        this.dwebview.loadUrl(requrl);
        this.dwebview.buildLayer();
        WindowManager.LayoutParams params = getWindow().getAttributes();
        if (this.mPositionX != -1) {
            params.gravity = 3;
            params.x = this.mPositionX;
        }
        if (this.mPositionY != -1) {
            params.gravity |= 48;
            params.y = this.mPositionY;
        }
        if (this.mPositionW > 0) {
            params.width = this.mPositionW;
        }
        if (this.mPositionH > 0) {
            params.height = this.mPositionH;
        }
        getWindow().setAttributes(params);
    }

    public void onPageFinished() {
        if (this.progressDialog != null) {
            this.progressDialog.dismiss();
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(this.dwebview);
        ViewGroup.LayoutParams layoutParams = this.dwebview.getLayoutParams();
        layoutParams.width = this.dWidth;
        layoutParams.height = (int) ((this.dWidth / 2.0d) + (52.0f * this.dScale) + 15.0d);
        this.dwebview.setLayoutParams(layoutParams);
    }

    @Override // android.app.Dialog
    public void show() {
        this.isShowing = true;
        try {
            if (this.dcontext != null && !((Activity) this.dcontext).isFinishing()) {
                super.show();
            }
        } catch (Exception e) {
            Log.e(Captcha.TAG, "Captcha Dialog show Error:" + e.toString());
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        this.isShowing = false;
        try {
            super.dismiss();
        } catch (Exception e) {
            Log.e(Captcha.TAG, "Captcha Dialog dismiss Error:" + e.toString());
        }
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
