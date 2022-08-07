package com.lidroid.xutils.http.client.multipart.content;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public class ByteArrayBody extends AbstractContentBody {
    private final byte[] data;
    private final String filename;

    public ByteArrayBody(byte[] data, String mimeType, String filename) {
        super(mimeType);
        if (data == null) {
            throw new IllegalArgumentException("byte[] may not be null");
        }
        this.data = data;
        this.filename = filename;
    }

    public ByteArrayBody(byte[] data, String filename) {
        this(data, "application/octet-stream", filename);
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentBody
    public String getFilename() {
        return this.filename;
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentBody
    public void writeTo(OutputStream out) throws IOException {
        out.write(this.data);
        this.callBackInfo.pos += this.data.length;
        this.callBackInfo.doCallBack(false);
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public String getCharset() {
        return null;
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public String getTransferEncoding() {
        return "binary";
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public long getContentLength() {
        return this.data.length;
    }
}
