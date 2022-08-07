package com.parse;

import com.parse.ParseObject;
import java.util.List;

/* loaded from: classes2.dex */
public interface FindCallback<T extends ParseObject> extends ParseCallback2<List<T>, ParseException> {
    void done(List<T> list, ParseException parseException);
}
