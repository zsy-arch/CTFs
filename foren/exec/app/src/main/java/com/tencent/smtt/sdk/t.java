package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;
import com.tencent.smtt.export.external.DexLoader;
import dalvik.system.DexClassLoader;

/* loaded from: classes.dex */
public class t {

    /* renamed from: a  reason: collision with root package name */
    public DexLoader f1416a;

    public t(DexLoader dexLoader) {
        this.f1416a = null;
        this.f1416a = dexLoader;
    }

    public Object a(Context context) {
        DexLoader dexLoader = this.f1416a;
        return dexLoader.newInstance("com.tencent.tbs.player.TbsPlayerProxy", new Class[]{Context.class, DexClassLoader.class}, context, dexLoader.getClassLoader());
    }

    public void a(Object obj) {
        this.f1416a.invokeMethod(obj, "com.tencent.tbs.player.TbsPlayerProxy", "onUserStateChanged", new Class[0], new Object[0]);
    }

    public void a(Object obj, Activity activity, int i) {
        this.f1416a.invokeMethod(obj, "com.tencent.tbs.player.TbsPlayerProxy", "onActivity", new Class[]{Activity.class, Integer.TYPE}, activity, Integer.valueOf(i));
    }

    public boolean a(Object obj, Bundle bundle, FrameLayout frameLayout, Object obj2) {
        Object[] objArr;
        Class<?>[] clsArr;
        DexLoader dexLoader;
        if (obj2 != null) {
            dexLoader = this.f1416a;
            clsArr = new Class[]{Bundle.class, FrameLayout.class, Object.class};
            objArr = new Object[]{bundle, frameLayout, obj2};
        } else {
            dexLoader = this.f1416a;
            clsArr = new Class[]{Bundle.class, FrameLayout.class};
            objArr = new Object[]{bundle, frameLayout};
        }
        Object invokeMethod = dexLoader.invokeMethod(obj, "com.tencent.tbs.player.TbsPlayerProxy", "play", clsArr, objArr);
        if (invokeMethod instanceof Boolean) {
            return ((Boolean) invokeMethod).booleanValue();
        }
        return false;
    }
}
