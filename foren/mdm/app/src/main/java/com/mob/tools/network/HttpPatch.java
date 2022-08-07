package com.mob.tools.network;

import java.net.URI;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

/* loaded from: classes2.dex */
public class HttpPatch extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "PATCH";

    public HttpPatch() {
    }

    public HttpPatch(String uri) {
        setURI(URI.create(uri));
    }

    public HttpPatch(URI uri) {
        setURI(uri);
    }

    @Override // org.apache.http.client.methods.HttpRequestBase, org.apache.http.client.methods.HttpUriRequest
    public String getMethod() {
        return METHOD_NAME;
    }
}
