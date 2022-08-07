package com.vsf2f.f2f.ui.utils.captcha;

/* loaded from: classes2.dex */
public interface CaptchaListener {
    void closeWindow();

    void onCancel();

    void onError(String str);

    void onReady(boolean z);

    void onValidate(String str, String str2, String str3);
}
