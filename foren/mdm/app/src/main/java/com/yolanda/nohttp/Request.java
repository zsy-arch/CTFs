package com.yolanda.nohttp;

import java.util.Map;

/* loaded from: classes2.dex */
public interface Request<T> extends ImplClientRequest, ImplServerRequest {
    void add(String str, byte b);

    void add(String str, char c);

    void add(String str, double d);

    void add(String str, float f);

    void add(String str, int i);

    void add(String str, long j);

    void add(String str, Binary binary);

    void add(String str, String str2);

    void add(String str, short s);

    void add(String str, boolean z);

    void add(Map<String, String> map);

    T parseResponse(String str, Headers headers, byte[] bArr);

    Object remove(String str);

    void removeAll();

    void set(Map<String, String> map);
}
