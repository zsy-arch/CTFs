package com.amap.api.services.a;

import android.content.Context;

/* compiled from: PoiHandler.java */
/* loaded from: classes.dex */
abstract class u<T, V> extends b<T, V> {
    public u(Context context, T t) {
        super(context, t);
    }

    protected boolean d(String str) {
        return str == null || str.equals("") || str.equals("[]");
    }
}
