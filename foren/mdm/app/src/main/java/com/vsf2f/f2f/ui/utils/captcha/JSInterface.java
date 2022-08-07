package com.vsf2f.f2f.ui.utils.captcha;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class JSInterface {
    private CaptchaDialog captchaDialog;
    private CaptchaListener captchaListener;
    private Context context;

    public JSInterface(Context context, CaptchaListener captchaListener, CaptchaDialog captchaDialog) {
        this.captchaListener = captchaListener;
        this.captchaDialog = captchaDialog;
        this.context = context;
    }

    @JavascriptInterface
    public void onValidate(String result, String validate, String message) {
        Log.i(Captcha.TAG, "result = " + result + ", validate = " + validate + ", message = " + message);
        if (validate != null && validate.length() > 0) {
            this.captchaDialog.dismiss();
        }
        if (this.captchaListener != null) {
            this.captchaListener.onValidate(result, validate, message);
        }
    }

    @JavascriptInterface
    public void closeWindow() {
        this.captchaDialog.dismiss();
        if (this.captchaListener != null) {
            this.captchaListener.closeWindow();
        }
        if (this.captchaDialog.getProgressDialog() != null) {
            this.captchaDialog.getProgressDialog().dismiss();
        }
    }

    @JavascriptInterface
    public void onReady() {
        ((Activity) this.context).runOnUiThread(new Runnable() { // from class: com.vsf2f.f2f.ui.utils.captcha.JSInterface.1
            @Override // java.lang.Runnable
            public void run() {
                if (!JSInterface.this.captchaDialog.isShowing()) {
                    new Handler().postDelayed(new Runnable() { // from class: com.vsf2f.f2f.ui.utils.captcha.JSInterface.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            JSInterface.this.captchaDialog.show();
                        }
                    }, 100L);
                }
            }
        });
        if (this.captchaListener != null) {
            this.captchaListener.onReady(true);
        }
        if (this.captchaDialog.getProgressDialog() != null) {
            this.captchaDialog.getProgressDialog().dismiss();
        }
    }

    @JavascriptInterface
    public void onError(String msg) {
        this.captchaDialog.dismiss();
        if (this.captchaListener != null) {
            this.captchaListener.onError(msg);
        }
        final CaptchaProgressDialog dialog = (CaptchaProgressDialog) this.captchaDialog.getProgressDialog();
        if (dialog != null && dialog.isCancelLoading) {
            ((Activity) this.context).runOnUiThread(new Runnable() { // from class: com.vsf2f.f2f.ui.utils.captcha.JSInterface.2
                @Override // java.lang.Runnable
                public void run() {
                    if (!dialog.isShowing()) {
                        dialog.show();
                        dialog.setProgressTips(R.string.tip_load_failed);
                        dialog.isCanClickDisappear = true;
                    }
                }
            });
        }
    }
}
