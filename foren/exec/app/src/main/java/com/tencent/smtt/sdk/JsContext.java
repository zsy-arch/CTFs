package com.tencent.smtt.sdk;

import android.content.Context;
import android.webkit.ValueCallback;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsError;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsValue;
import java.net.URL;

/* loaded from: classes.dex */
public final class JsContext {

    /* renamed from: a */
    public final JsVirtualMachine f1108a;

    /* renamed from: b */
    public final IX5JsContext f1109b;

    /* renamed from: c */
    public ExceptionHandler f1110c;

    /* renamed from: d */
    public String f1111d;

    /* loaded from: classes.dex */
    public interface ExceptionHandler {
        void handleException(JsContext jsContext, JsError jsError);
    }

    public JsContext(Context context) {
        this(new JsVirtualMachine(context, null));
    }

    public JsContext(JsVirtualMachine jsVirtualMachine) {
        if (jsVirtualMachine != null) {
            this.f1108a = jsVirtualMachine;
            this.f1109b = this.f1108a.a();
            try {
                this.f1109b.setPerContextData(this);
            } catch (AbstractMethodError unused) {
            }
        } else {
            throw new IllegalArgumentException("The virtualMachine value can not be null");
        }
    }

    public static JsContext current() {
        return (JsContext) X5JsCore.a();
    }

    public void addJavascriptInterface(Object obj, String str) {
        this.f1109b.addJavascriptInterface(obj, str);
    }

    public void destroy() {
        this.f1109b.destroy();
    }

    public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        evaluateJavascript(str, valueCallback, null);
    }

    public void evaluateJavascript(String str, ValueCallback<String> valueCallback, URL url) {
        this.f1109b.evaluateJavascript(str, valueCallback, url);
    }

    public JsValue evaluateScript(String str) {
        return evaluateScript(str, null);
    }

    public JsValue evaluateScript(String str, URL url) {
        IX5JsValue evaluateScript = this.f1109b.evaluateScript(str, url);
        if (evaluateScript == null) {
            return null;
        }
        return new JsValue(this, evaluateScript);
    }

    public void evaluateScriptAsync(String str, final ValueCallback<JsValue> valueCallback, URL url) {
        this.f1109b.evaluateScriptAsync(str, valueCallback == null ? null : new ValueCallback<IX5JsValue>() { // from class: com.tencent.smtt.sdk.JsContext.1
            /* renamed from: a */
            public void onReceiveValue(IX5JsValue iX5JsValue) {
                valueCallback.onReceiveValue(iX5JsValue == null ? null : new JsValue(JsContext.this, iX5JsValue));
            }
        }, url);
    }

    public ExceptionHandler exceptionHandler() {
        return this.f1110c;
    }

    public byte[] getNativeBuffer(int i) {
        return this.f1109b.getNativeBuffer(i);
    }

    public int getNativeBufferId() {
        return this.f1109b.getNativeBufferId();
    }

    public String name() {
        return this.f1111d;
    }

    public void removeJavascriptInterface(String str) {
        this.f1109b.removeJavascriptInterface(str);
    }

    public void setExceptionHandler(ExceptionHandler exceptionHandler) {
        IX5JsContext iX5JsContext;
        ValueCallback<IX5JsError> valueCallback;
        this.f1110c = exceptionHandler;
        if (exceptionHandler == null) {
            iX5JsContext = this.f1109b;
            valueCallback = null;
        } else {
            iX5JsContext = this.f1109b;
            valueCallback = new ValueCallback<IX5JsError>() { // from class: com.tencent.smtt.sdk.JsContext.2
                /* renamed from: a */
                public void onReceiveValue(IX5JsError iX5JsError) {
                    JsContext jsContext = JsContext.this;
                    jsContext.f1110c.handleException(jsContext, new JsError(iX5JsError));
                }
            };
        }
        iX5JsContext.setExceptionHandler(valueCallback);
    }

    public void setName(String str) {
        this.f1111d = str;
        this.f1109b.setName(str);
    }

    public int setNativeBuffer(int i, byte[] bArr) {
        return this.f1109b.setNativeBuffer(i, bArr);
    }

    public void stealValueFromOtherCtx(String str, JsContext jsContext, String str2) {
        this.f1109b.stealValueFromOtherCtx(str, jsContext.f1109b, str2);
    }

    public JsVirtualMachine virtualMachine() {
        return this.f1108a;
    }
}
