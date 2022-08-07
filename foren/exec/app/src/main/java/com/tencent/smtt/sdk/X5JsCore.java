package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Looper;
import android.webkit.ValueCallback;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsVirtualMachine;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class X5JsCore {

    /* renamed from: a  reason: collision with root package name */
    public static a f1316a;

    /* renamed from: b  reason: collision with root package name */
    public static a f1317b;

    /* renamed from: c  reason: collision with root package name */
    public static a f1318c;

    /* renamed from: d  reason: collision with root package name */
    public final Context f1319d;

    /* renamed from: e  reason: collision with root package name */
    public Object f1320e;
    public WebView f;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum a {
        UNINITIALIZED,
        UNAVAILABLE,
        AVAILABLE
    }

    static {
        a aVar = a.UNINITIALIZED;
        f1316a = aVar;
        f1317b = aVar;
        f1318c = aVar;
    }

    @Deprecated
    public X5JsCore(Context context) {
        Object a2;
        this.f1320e = null;
        this.f = null;
        this.f1319d = context;
        if (!canUseX5JsCore(context) || (a2 = a("createX5JavaBridge", new Class[]{Context.class}, context)) == null) {
            this.f = new WebView(context, null, 0, false);
            this.f.getSettings().setJavaScriptEnabled(true);
            return;
        }
        this.f1320e = a2;
    }

    public static IX5JsVirtualMachine a(Context context, Looper looper) {
        Object a2;
        if (!canUseX5JsCore(context) || (a2 = a("createX5JsVirtualMachine", new Class[]{Context.class, Looper.class}, context, looper)) == null) {
            return null;
        }
        return (IX5JsVirtualMachine) a2;
    }

    public static Object a() {
        return a("currentContextData", new Class[0], new Object[0]);
    }

    public static Object a(String str, Class<?>[] clsArr, Object... objArr) {
        try {
            u a2 = u.a();
            if (a2 != null && a2.b()) {
                return a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", str, clsArr, objArr);
            }
            String str2 = "X5Jscore#" + str + " - x5CoreEngine is null or is not x5core.";
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean canUseX5JsCore(Context context) {
        if (f1316a != a.UNINITIALIZED) {
            return f1316a == a.AVAILABLE;
        }
        f1316a = a.UNAVAILABLE;
        Object a2 = a("canUseX5JsCore", new Class[]{Context.class}, context);
        if (a2 == null || !(a2 instanceof Boolean) || !((Boolean) a2).booleanValue()) {
            return false;
        }
        a("setJsValueFactory", new Class[]{Object.class}, JsValue.a());
        f1316a = a.AVAILABLE;
        return true;
    }

    public static boolean canUseX5JsCoreNewAPI(Context context) {
        if (f1318c != a.UNINITIALIZED) {
            return f1318c == a.AVAILABLE;
        }
        f1318c = a.UNAVAILABLE;
        Object a2 = a("canUseX5JsCoreNewAPI", new Class[]{Context.class}, context);
        if (a2 == null || !(a2 instanceof Boolean) || !((Boolean) a2).booleanValue()) {
            return false;
        }
        f1318c = a.AVAILABLE;
        return true;
    }

    public static boolean canX5JsCoreUseNativeBuffer(Context context) {
        Object a2;
        if (f1317b != a.UNINITIALIZED) {
            return f1317b == a.AVAILABLE;
        }
        f1317b = a.UNAVAILABLE;
        if (!canUseX5JsCore(context) || (a2 = a("canX5JsCoreUseBuffer", new Class[]{Context.class}, context)) == null || !(a2 instanceof Boolean) || !((Boolean) a2).booleanValue()) {
            return false;
        }
        f1317b = a.AVAILABLE;
        return true;
    }

    @Deprecated
    public void addJavascriptInterface(Object obj, String str) {
        Object obj2 = this.f1320e;
        if (obj2 != null) {
            a("addJavascriptInterface", new Class[]{Object.class, String.class, Object.class}, obj, str, obj2);
            return;
        }
        WebView webView = this.f;
        if (webView != null) {
            webView.addJavascriptInterface(obj, str);
            this.f.loadUrl("about:blank");
        }
    }

    @Deprecated
    public void destroy() {
        Object obj = this.f1320e;
        if (obj != null) {
            a("destroyX5JsCore", new Class[]{Object.class}, obj);
            this.f1320e = null;
            return;
        }
        WebView webView = this.f;
        if (webView != null) {
            webView.clearHistory();
            this.f.clearCache(true);
            this.f.loadUrl("about:blank");
            this.f.freeMemory();
            this.f.pauseTimers();
            this.f.destroy();
            this.f = null;
        }
    }

    @Deprecated
    public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        Object obj = this.f1320e;
        if (obj != null) {
            a("evaluateJavascript", new Class[]{String.class, ValueCallback.class, Object.class}, str, valueCallback, obj);
            return;
        }
        WebView webView = this.f;
        if (webView != null) {
            webView.evaluateJavascript(str, valueCallback);
        }
    }

    @Deprecated
    public ByteBuffer getNativeBuffer(int i) {
        Object a2;
        if (this.f1320e == null || !canX5JsCoreUseNativeBuffer(this.f1319d) || (a2 = a("getNativeBuffer", new Class[]{Object.class, Integer.TYPE}, this.f1320e, Integer.valueOf(i))) == null || !(a2 instanceof ByteBuffer)) {
            return null;
        }
        return (ByteBuffer) a2;
    }

    @Deprecated
    public int getNativeBufferId() {
        Object a2;
        if (this.f1320e == null || !canX5JsCoreUseNativeBuffer(this.f1319d) || (a2 = a("getNativeBufferId", new Class[]{Object.class}, this.f1320e)) == null || !(a2 instanceof Integer)) {
            return -1;
        }
        return ((Integer) a2).intValue();
    }

    @Deprecated
    public void pause() {
        Object obj = this.f1320e;
        if (obj != null) {
            a("pause", new Class[]{Object.class}, obj);
        }
    }

    @Deprecated
    public void pauseTimers() {
        Object obj = this.f1320e;
        if (obj != null) {
            a("pauseTimers", new Class[]{Object.class}, obj);
        }
    }

    @Deprecated
    public void removeJavascriptInterface(String str) {
        Object obj = this.f1320e;
        if (obj != null) {
            a("removeJavascriptInterface", new Class[]{String.class, Object.class}, str, obj);
            return;
        }
        WebView webView = this.f;
        if (webView != null) {
            webView.removeJavascriptInterface(str);
        }
    }

    @Deprecated
    public void resume() {
        Object obj = this.f1320e;
        if (obj != null) {
            a("resume", new Class[]{Object.class}, obj);
        }
    }

    @Deprecated
    public void resumeTimers() {
        Object obj = this.f1320e;
        if (obj != null) {
            a("resumeTimers", new Class[]{Object.class}, obj);
        }
    }

    @Deprecated
    public void setNativeBuffer(int i, ByteBuffer byteBuffer) {
        if (this.f1320e != null && canX5JsCoreUseNativeBuffer(this.f1319d)) {
            a("setNativeBuffer", new Class[]{Object.class, Integer.TYPE, ByteBuffer.class}, this.f1320e, Integer.valueOf(i), byteBuffer);
        }
    }
}
