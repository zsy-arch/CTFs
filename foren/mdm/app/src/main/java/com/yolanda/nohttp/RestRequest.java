package com.yolanda.nohttp;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public abstract class RestRequest<T> extends BasicRequest<T> {
    protected Map<String, Object> mParamMap;

    public RestRequest(String url) {
        this(url, RequestMethod.GET);
    }

    public RestRequest(String url, RequestMethod requestMethod) {
        super(url, requestMethod);
        this.mParamMap = null;
        this.mParamMap = new LinkedHashMap();
    }

    @Override // com.yolanda.nohttp.Request
    public void add(String key, String value) {
        Map<String, Object> map = this.mParamMap;
        if (value == null) {
            value = "";
        }
        map.put(key, value);
    }

    @Override // com.yolanda.nohttp.Request
    public void add(String key, int value) {
        this.mParamMap.put(key, Integer.toString(value));
    }

    @Override // com.yolanda.nohttp.Request
    public void add(String key, long value) {
        this.mParamMap.put(key, Long.toString(value));
    }

    @Override // com.yolanda.nohttp.Request
    public void add(String key, boolean value) {
        this.mParamMap.put(key, String.valueOf(value));
    }

    @Override // com.yolanda.nohttp.Request
    public void add(String key, char value) {
        this.mParamMap.put(key, String.valueOf(value));
    }

    @Override // com.yolanda.nohttp.Request
    public void add(String key, double value) {
        this.mParamMap.put(key, Double.toString(value));
    }

    @Override // com.yolanda.nohttp.Request
    public void add(String key, float value) {
        this.mParamMap.put(key, Float.toString(value));
    }

    @Override // com.yolanda.nohttp.Request
    public void add(String key, short value) {
        this.mParamMap.put(key, Integer.toString(value));
    }

    @Override // com.yolanda.nohttp.Request
    public void add(String key, byte value) {
        this.mParamMap.put(key, Integer.toString(value));
    }

    @Override // com.yolanda.nohttp.Request
    public void add(String key, Binary binary) {
        this.mParamMap.put(key, binary);
    }

    @Override // com.yolanda.nohttp.Request
    public void add(Map<String, String> params) {
        if (params != null) {
            this.mParamMap.putAll(params);
        }
    }

    @Override // com.yolanda.nohttp.Request
    public void set(Map<String, String> params) {
        if (params != null) {
            this.mParamMap.clear();
            this.mParamMap.putAll(params);
        }
    }

    @Override // com.yolanda.nohttp.Request
    public Object remove(String key) {
        return this.mParamMap.remove(key);
    }

    @Override // com.yolanda.nohttp.Request
    public void removeAll() {
        this.mParamMap.clear();
    }

    @Override // com.yolanda.nohttp.BasicRequest
    public Set<String> keySet() {
        return this.mParamMap.keySet();
    }

    @Override // com.yolanda.nohttp.BasicRequest
    public Object value(String key) {
        return this.mParamMap.get(key);
    }
}
