package com.parse;

import com.parse.ParseObject;

/* loaded from: classes2.dex */
public interface GetCallback<T extends ParseObject> extends ParseCallback2<T, ParseException> {
    void done(T t, ParseException parseException);
}
