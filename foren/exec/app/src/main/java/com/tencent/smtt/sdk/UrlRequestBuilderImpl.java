package com.tencent.smtt.sdk;

import android.util.Pair;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.export.external.interfaces.UrlRequest;
import java.util.ArrayList;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class UrlRequestBuilderImpl extends UrlRequest.Builder {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1274a = "UrlRequestBuilderImpl";

    /* renamed from: b  reason: collision with root package name */
    public final String f1275b;

    /* renamed from: c  reason: collision with root package name */
    public final UrlRequest.Callback f1276c;

    /* renamed from: d  reason: collision with root package name */
    public final Executor f1277d;

    /* renamed from: e  reason: collision with root package name */
    public String f1278e;
    public boolean g;
    public String i;
    public byte[] j;
    public String k;
    public String l;
    public final ArrayList<Pair<String, String>> f = new ArrayList<>();
    public int h = 3;

    public UrlRequestBuilderImpl(String str, UrlRequest.Callback callback, Executor executor) {
        if (str == null) {
            throw new NullPointerException("URL is required.");
        } else if (callback == null) {
            throw new NullPointerException("Callback is required.");
        } else if (executor != null) {
            this.f1275b = str;
            this.f1276c = callback;
            this.f1277d = executor;
        } else {
            throw new NullPointerException("Executor is required.");
        }
    }

    @Override // com.tencent.smtt.export.external.interfaces.UrlRequest.Builder
    public UrlRequestBuilderImpl addHeader(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("Invalid header name.");
        } else if (str2 == null) {
            throw new NullPointerException("Invalid header value.");
        } else if ("Accept-Encoding".equalsIgnoreCase(str)) {
            return this;
        } else {
            this.f.add(Pair.create(str, str2));
            return this;
        }
    }

    @Override // com.tencent.smtt.export.external.interfaces.UrlRequest.Builder
    public UrlRequest build() {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            return null;
        }
        DexLoader b2 = a2.c().b();
        UrlRequest urlRequest = (UrlRequest) b2.invokeStaticMethod("com.tencent.smtt.net.X5UrlRequestProvider", "GetX5UrlRequestProvider", new Class[]{String.class, Integer.TYPE, UrlRequest.Callback.class, Executor.class, Boolean.TYPE, String.class, ArrayList.class, String.class, byte[].class, String.class, String.class}, this.f1275b, Integer.valueOf(this.h), this.f1276c, this.f1277d, Boolean.valueOf(this.g), this.f1278e, this.f, this.i, this.j, this.k, this.l);
        if (urlRequest == null) {
            urlRequest = (UrlRequest) b2.invokeStaticMethod("com.tencent.smtt.net.X5UrlRequestProvider", "GetX5UrlRequestProvider", new Class[]{String.class, Integer.TYPE, UrlRequest.Callback.class, Executor.class, Boolean.TYPE, String.class, ArrayList.class, String.class}, this.f1275b, Integer.valueOf(this.h), this.f1276c, this.f1277d, Boolean.valueOf(this.g), this.f1278e, this.f, this.i);
        }
        if (urlRequest == null) {
            urlRequest = (UrlRequest) b2.invokeStaticMethod("com.tencent.smtt.net.X5UrlRequestProvider", "GetX5UrlRequestProvider", new Class[]{String.class, Integer.TYPE, UrlRequest.Callback.class, Executor.class, Boolean.TYPE, String.class, ArrayList.class}, this.f1275b, Integer.valueOf(this.h), this.f1276c, this.f1277d, Boolean.valueOf(this.g), this.f1278e, this.f);
        }
        if (urlRequest == null) {
            urlRequest = (UrlRequest) b2.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "UrlRequest_getX5UrlRequestProvider", new Class[]{String.class, Integer.TYPE, UrlRequest.Callback.class, Executor.class, Boolean.TYPE, String.class, ArrayList.class, String.class, byte[].class, String.class, String.class}, this.f1275b, Integer.valueOf(this.h), this.f1276c, this.f1277d, Boolean.valueOf(this.g), this.f1278e, this.f, this.i, this.j, this.k, this.l);
        }
        if (urlRequest != null) {
            return urlRequest;
        }
        throw new NullPointerException("UrlRequest build fail");
    }

    @Override // com.tencent.smtt.export.external.interfaces.UrlRequest.Builder
    public UrlRequestBuilderImpl disableCache() {
        this.g = true;
        return this;
    }

    @Override // com.tencent.smtt.export.external.interfaces.UrlRequest.Builder
    public UrlRequestBuilderImpl setDns(String str, String str2) {
        if (str == null || str2 == null) {
            throw new NullPointerException("host and address are required.");
        }
        this.k = str;
        this.l = str2;
        return this;
    }

    @Override // com.tencent.smtt.export.external.interfaces.UrlRequest.Builder
    public UrlRequest.Builder setHttpMethod(String str) {
        if (str != null) {
            this.f1278e = str;
            return this;
        }
        throw new NullPointerException("Method is required.");
    }

    @Override // com.tencent.smtt.export.external.interfaces.UrlRequest.Builder
    public UrlRequestBuilderImpl setPriority(int i) {
        this.h = i;
        return this;
    }

    @Override // com.tencent.smtt.export.external.interfaces.UrlRequest.Builder
    public UrlRequest.Builder setRequestBody(String str) {
        if (str != null) {
            this.i = str;
            return this;
        }
        throw new NullPointerException("Body is required.");
    }

    @Override // com.tencent.smtt.export.external.interfaces.UrlRequest.Builder
    public UrlRequest.Builder setRequestBodyBytes(byte[] bArr) {
        if (bArr != null) {
            this.j = bArr;
            return this;
        }
        throw new NullPointerException("Body is required.");
    }
}
