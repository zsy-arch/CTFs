package com.parse;

/* loaded from: classes2.dex */
public interface FunctionCallback<T> extends ParseCallback2<T, ParseException> {
    void done(T t, ParseException parseException);
}
