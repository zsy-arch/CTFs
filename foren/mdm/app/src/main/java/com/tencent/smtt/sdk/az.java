package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;
import com.tencent.smtt.export.external.DexLoader;
import dalvik.system.DexClassLoader;

/* loaded from: classes2.dex */
class az {
    protected DexLoader a;

    public az(DexLoader dexLoader) {
        this.a = null;
        this.a = dexLoader;
    }

    public Object a(Context context) {
        return this.a.newInstance("com.tencent.tbs.player.TbsPlayerProxy", new Class[]{Context.class, DexClassLoader.class}, context, this.a.getClassLoader());
    }

    public void a(Object obj) {
        this.a.invokeMethod(obj, "com.tencent.tbs.player.TbsPlayerProxy", "onUserStateChanged", new Class[0], new Object[0]);
    }

    public void a(Object obj, Activity activity, int i) {
        this.a.invokeMethod(obj, "com.tencent.tbs.player.TbsPlayerProxy", "onActivity", new Class[]{Activity.class, Integer.TYPE}, activity, Integer.valueOf(i));
    }

    public boolean a(Object obj, Bundle bundle, FrameLayout frameLayout, Object obj2) {
        Object invokeMethod = obj2 != null ? this.a.invokeMethod(obj, "com.tencent.tbs.player.TbsPlayerProxy", "play", new Class[]{Bundle.class, FrameLayout.class, Object.class}, bundle, frameLayout, obj2) : this.a.invokeMethod(obj, "com.tencent.tbs.player.TbsPlayerProxy", "play", new Class[]{Bundle.class, FrameLayout.class}, bundle, frameLayout);
        if (invokeMethod instanceof Boolean) {
            return ((Boolean) invokeMethod).booleanValue();
        }
        return false;
    }
}
