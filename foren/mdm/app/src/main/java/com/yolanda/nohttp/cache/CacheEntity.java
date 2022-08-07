package com.yolanda.nohttp.cache;

import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.HttpHeaders;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.db.DBId;
import com.yolanda.nohttp.db.Field;
import java.io.Serializable;
import org.json.JSONException;

/* loaded from: classes2.dex */
public class CacheEntity implements DBId, Field, Serializable {
    private static final long serialVersionUID = 1234857234793L;
    private byte[] data;
    private long id;
    private String key;
    private long localExpire;
    private Headers responseHeaders;

    public CacheEntity() {
        this.responseHeaders = new HttpHeaders();
        this.data = new byte[0];
    }

    public CacheEntity(long id, String key, Headers responseHeaders, byte[] data, long localExpire) {
        this.responseHeaders = new HttpHeaders();
        this.data = new byte[0];
        this.id = id;
        this.key = key;
        this.responseHeaders = responseHeaders;
        this.data = data;
        this.localExpire = localExpire;
    }

    @Override // com.yolanda.nohttp.db.DBId
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Headers getResponseHeaders() {
        return this.responseHeaders;
    }

    public void setResponseHeaders(Headers responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public void setResponseHeadersJson(String jsonString) {
        try {
            this.responseHeaders.setJSONString(jsonString);
        } catch (JSONException e) {
            Logger.e(e);
        }
    }

    public String getResponseHeadersJson() {
        return this.responseHeaders.toJSONString();
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public long getLocalExpire() {
        return this.localExpire;
    }

    public void setLocalExpire(long localExpire) {
        this.localExpire = localExpire;
    }
}
