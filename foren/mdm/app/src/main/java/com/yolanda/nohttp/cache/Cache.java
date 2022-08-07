package com.yolanda.nohttp.cache;

/* loaded from: classes2.dex */
public interface Cache<T> {
    boolean clear();

    T get(String str);

    boolean remove(String str);

    T replace(String str, T t);
}
