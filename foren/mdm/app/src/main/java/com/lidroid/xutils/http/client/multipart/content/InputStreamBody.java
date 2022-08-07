package com.lidroid.xutils.http.client.multipart.content;

import com.lidroid.xutils.util.IOUtils;
import com.umeng.update.net.f;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public class InputStreamBody extends AbstractContentBody {
    private final String filename;
    private final InputStream in;
    private long length;

    public InputStreamBody(InputStream in, long length, String filename, String mimeType) {
        super(mimeType);
        if (in == null) {
            throw new IllegalArgumentException("Input stream may not be null");
        }
        this.in = in;
        this.filename = filename;
        this.length = length;
    }

    public InputStreamBody(InputStream in, long length, String filename) {
        this(in, length, filename, "application/octet-stream");
    }

    public InputStreamBody(InputStream in, long length) {
        this(in, length, "no_name", "application/octet-stream");
    }

    public InputStream getInputStream() {
        return this.in;
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentBody
    public void writeTo(OutputStream out) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        try {
            byte[] tmp = new byte[4096];
            do {
                int l = this.in.read(tmp);
                if (l != -1) {
                    out.write(tmp, 0, l);
                    this.callBackInfo.pos += l;
                } else {
                    out.flush();
                    return;
                }
            } while (this.callBackInfo.doCallBack(false));
            throw new InterruptedIOException(f.c);
        } finally {
            IOUtils.closeQuietly(this.in);
        }
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public String getTransferEncoding() {
        return "binary";
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public String getCharset() {
        return null;
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public long getContentLength() {
        return this.length;
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentBody
    public String getFilename() {
        return this.filename;
    }
}
