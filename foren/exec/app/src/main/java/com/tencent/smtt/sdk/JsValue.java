package com.tencent.smtt.sdk;

import com.tencent.smtt.export.external.jscore.interfaces.IX5JsValue;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class JsValue {

    /* renamed from: a  reason: collision with root package name */
    public final JsContext f1116a;

    /* renamed from: b  reason: collision with root package name */
    public final IX5JsValue f1117b;

    /* loaded from: classes.dex */
    private static class a implements IX5JsValue.JsValueFactory {
        public a() {
        }

        public /* synthetic */ a(AnonymousClass1 r1) {
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsValue.JsValueFactory
        public String getJsValueClassName() {
            return JsValue.class.getName();
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsValue.JsValueFactory
        public IX5JsValue unwrap(Object obj) {
            if (obj == null || !(obj instanceof JsValue)) {
                return null;
            }
            return ((JsValue) obj).f1117b;
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsValue.JsValueFactory
        public Object wrap(IX5JsValue iX5JsValue) {
            JsContext current;
            if (iX5JsValue == null || (current = JsContext.current()) == null) {
                return null;
            }
            return new JsValue(current, iX5JsValue);
        }
    }

    public JsValue(JsContext jsContext, IX5JsValue iX5JsValue) {
        this.f1116a = jsContext;
        this.f1117b = iX5JsValue;
    }

    public static IX5JsValue.JsValueFactory a() {
        return new a(null);
    }

    private JsValue a(IX5JsValue iX5JsValue) {
        if (iX5JsValue == null) {
            return null;
        }
        return new JsValue(this.f1116a, iX5JsValue);
    }

    public JsValue call(Object... objArr) {
        return a(this.f1117b.call(objArr));
    }

    public JsValue construct(Object... objArr) {
        return a(this.f1117b.construct(objArr));
    }

    public JsContext context() {
        return this.f1116a;
    }

    public boolean isArray() {
        return this.f1117b.isArray();
    }

    public boolean isArrayBufferOrArrayBufferView() {
        return this.f1117b.isArrayBufferOrArrayBufferView();
    }

    public boolean isBoolean() {
        return this.f1117b.isBoolean();
    }

    public boolean isFunction() {
        return this.f1117b.isFunction();
    }

    public boolean isInteger() {
        return this.f1117b.isInteger();
    }

    public boolean isJavascriptInterface() {
        return this.f1117b.isJavascriptInterface();
    }

    public boolean isNull() {
        return this.f1117b.isNull();
    }

    public boolean isNumber() {
        return this.f1117b.isNumber();
    }

    public boolean isObject() {
        return this.f1117b.isObject();
    }

    public boolean isPromise() {
        return this.f1117b.isPromise();
    }

    public boolean isString() {
        return this.f1117b.isString();
    }

    public boolean isUndefined() {
        return this.f1117b.isUndefined();
    }

    public void reject(Object obj) {
        this.f1117b.resolveOrReject(obj, false);
    }

    public void resolve(Object obj) {
        this.f1117b.resolveOrReject(obj, true);
    }

    public boolean toBoolean() {
        return this.f1117b.toBoolean();
    }

    public ByteBuffer toByteBuffer() {
        return this.f1117b.toByteBuffer();
    }

    public int toInteger() {
        return this.f1117b.toInteger();
    }

    public Object toJavascriptInterface() {
        return this.f1117b.toJavascriptInterface();
    }

    public Number toNumber() {
        return this.f1117b.toNumber();
    }

    public <T> T toObject(Class<T> cls) {
        return (T) this.f1117b.toObject(cls);
    }

    public String toString() {
        return this.f1117b.toString();
    }
}
