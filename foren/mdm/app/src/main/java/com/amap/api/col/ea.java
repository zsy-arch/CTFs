package com.amap.api.col;

import com.amap.api.col.ea;

/* compiled from: Inlist.java */
/* loaded from: classes.dex */
public class ea<T extends ea<T>> {
    public T f;

    public static <T extends ea<?>> T a(T t, T t2) {
        if (t2.f != null) {
            throw new IllegalArgumentException("'item' is a list");
        }
        t2.f = t;
        return t2;
    }
}
