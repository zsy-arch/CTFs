package com.parse.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public abstract class ParseHttpBody {
    private final long contentLength;
    private final String contentType;

    public abstract InputStream getContent() throws IOException;

    public abstract void writeTo(OutputStream outputStream) throws IOException;

    public ParseHttpBody(String contentType, long contentLength) {
        this.contentType = contentType;
        this.contentLength = contentLength;
    }

    public long getContentLength() {
        return this.contentLength;
    }

    public String getContentType() {
        return this.contentType;
    }
}
