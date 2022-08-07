package com.tencent.smtt.sdk;

import com.tencent.smtt.export.external.jscore.interfaces.IX5JsError;

/* loaded from: classes.dex */
public class JsError {

    /* renamed from: a  reason: collision with root package name */
    public final IX5JsError f1115a;

    public JsError(IX5JsError iX5JsError) {
        this.f1115a = iX5JsError;
    }

    public String getMessage() {
        return this.f1115a.getMessage();
    }

    public String getStack() {
        return this.f1115a.getStack();
    }
}
