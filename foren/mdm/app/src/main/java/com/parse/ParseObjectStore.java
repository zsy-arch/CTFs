package com.parse;

import bolts.Task;
import com.parse.ParseObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public interface ParseObjectStore<T extends ParseObject> {
    Task<Void> deleteAsync();

    Task<Boolean> existsAsync();

    Task<T> getAsync();

    Task<Void> setAsync(T t);
}
