package com.parse.http;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class ParseHttpResponse {
    private final InputStream content;
    private final String contentType;
    private final Map<String, String> headers;
    private final String reasonPhrase;
    private final int statusCode;
    private final long totalSize;

    /* loaded from: classes2.dex */
    public static final class Builder {
        private InputStream content;
        private String contentType;
        private Map<String, String> headers;
        private String reasonPhrase;
        private int statusCode;
        private long totalSize;

        public Builder() {
            this.totalSize = -1L;
            this.headers = new HashMap();
        }

        public Builder(ParseHttpResponse response) {
            setStatusCode(response.getStatusCode());
            setContent(response.getContent());
            setTotalSize(response.getTotalSize());
            setContentType(response.getContentType());
            setHeaders(response.getAllHeaders());
            setReasonPhrase(response.getReasonPhrase());
        }

        public Builder setStatusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder setContent(InputStream content) {
            this.content = content;
            return this;
        }

        public Builder setTotalSize(long totalSize) {
            this.totalSize = totalSize;
            return this;
        }

        public Builder setReasonPhrase(String reasonPhrase) {
            this.reasonPhrase = reasonPhrase;
            return this;
        }

        public Builder setHeaders(Map<String, String> headers) {
            this.headers = new HashMap(headers);
            return this;
        }

        public Builder addHeaders(Map<String, String> headers) {
            this.headers.putAll(headers);
            return this;
        }

        public Builder addHeader(String name, String value) {
            this.headers.put(name, value);
            return this;
        }

        public Builder setContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public ParseHttpResponse build() {
            return new ParseHttpResponse(this);
        }
    }

    private ParseHttpResponse(Builder builder) {
        this.statusCode = builder.statusCode;
        this.content = builder.content;
        this.totalSize = builder.totalSize;
        this.reasonPhrase = builder.reasonPhrase;
        this.headers = Collections.unmodifiableMap(new HashMap(builder.headers));
        this.contentType = builder.contentType;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public InputStream getContent() {
        return this.content;
    }

    public long getTotalSize() {
        return this.totalSize;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getHeader(String name) {
        return this.headers.get(name);
    }

    public Map<String, String> getAllHeaders() {
        return this.headers;
    }
}
