package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.export.external.DexLoader;

/* loaded from: classes.dex */
public class q {

    /* renamed from: a  reason: collision with root package name */
    public DexLoader f1395a;

    public q(DexLoader dexLoader) {
        this.f1395a = null;
        this.f1395a = dexLoader;
    }

    public String a(Context context) {
        Object newInstance;
        Object invokeMethod;
        DexLoader dexLoader = this.f1395a;
        return (dexLoader == null || (newInstance = dexLoader.newInstance("com.tencent.tbs.utils.TbsVideoUtilsProxy", new Class[0], new Object[0])) == null || (invokeMethod = this.f1395a.invokeMethod(newInstance, "com.tencent.tbs.utils.TbsVideoUtilsProxy", "getCurWDPDecodeType", new Class[]{Context.class}, context)) == null) ? BuildConfig.FLAVOR : String.valueOf(invokeMethod);
    }

    public void a(Context context, String str) {
        Object newInstance;
        DexLoader dexLoader = this.f1395a;
        if (dexLoader != null && (newInstance = dexLoader.newInstance("com.tencent.tbs.utils.TbsVideoUtilsProxy", new Class[0], new Object[0])) != null) {
            this.f1395a.invokeMethod(newInstance, "com.tencent.tbs.utils.TbsVideoUtilsProxy", "deleteVideoCache", new Class[]{Context.class, String.class}, context, str);
        }
    }
}
