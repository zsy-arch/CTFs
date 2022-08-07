package com.yolanda.nohttp;

import com.mob.tools.network.HttpPatch;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;

/* loaded from: classes2.dex */
public enum RequestMethod {
    GET("GET"),
    POST("POST"),
    PUT(HttpPut.METHOD_NAME),
    DELETE(HttpDelete.METHOD_NAME),
    HEAD(HttpHead.METHOD_NAME),
    OPTIONS(HttpOptions.METHOD_NAME),
    TRACE(HttpTrace.METHOD_NAME),
    PATCH(HttpPatch.METHOD_NAME);
    
    private final String value;

    RequestMethod(String value) {
        this.value = value;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
