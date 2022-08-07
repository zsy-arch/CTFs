package com.http.base;

import com.google.gson.internal.C$Gson$Types;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes2.dex */
public abstract class BaseCallBack<T> {
    public Type mType = getSuperclassTypeParameter(getClass());

    public abstract void OnRequestBefore(Request request);

    public abstract void inProgress(int i, long j, int i2);

    public abstract void onEror(Call call, int i, Exception exc);

    public abstract void onFailure(Call call, IOException iOException);

    public abstract void onResponse(Response response);

    public abstract void onSuccess(Call call, Response response, T t);

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (!(superclass instanceof Class)) {
            return C$Gson$Types.canonicalize(((ParameterizedType) superclass).getActualTypeArguments()[0]);
        }
        throw new RuntimeException("Missing type parameter.");
    }
}
