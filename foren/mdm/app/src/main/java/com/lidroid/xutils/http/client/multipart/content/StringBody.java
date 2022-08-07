package com.lidroid.xutils.http.client.multipart.content;

import com.umeng.update.net.f;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* loaded from: classes2.dex */
public class StringBody extends AbstractContentBody {
    private final Charset charset;
    private final byte[] content;

    public static StringBody create(String text, String mimeType, Charset charset) throws IllegalArgumentException {
        try {
            return new StringBody(text, mimeType, charset);
        } catch (UnsupportedEncodingException ex) {
            throw new IllegalArgumentException("Charset " + charset + " is not supported", ex);
        }
    }

    public static StringBody create(String text, Charset charset) throws IllegalArgumentException {
        return create(text, null, charset);
    }

    public static StringBody create(String text) throws IllegalArgumentException {
        return create(text, null, null);
    }

    public StringBody(String text, String mimeType, Charset charset) throws UnsupportedEncodingException {
        super(mimeType);
        if (text == null) {
            throw new IllegalArgumentException("Text may not be null");
        }
        charset = charset == null ? Charset.forName("UTF-8") : charset;
        this.content = text.getBytes(charset.name());
        this.charset = charset;
    }

    public StringBody(String text, Charset charset) throws UnsupportedEncodingException {
        this(text, "text/plain", charset);
    }

    public StringBody(String text) throws UnsupportedEncodingException {
        this(text, "text/plain", null);
    }

    public Reader getReader() {
        return new InputStreamReader(new ByteArrayInputStream(this.content), this.charset);
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentBody
    public void writeTo(OutputStream out) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        InputStream in = new ByteArrayInputStream(this.content);
        byte[] tmp = new byte[4096];
        do {
            int l = in.read(tmp);
            if (l != -1) {
                out.write(tmp, 0, l);
                this.callBackInfo.pos += l;
            } else {
                out.flush();
                return;
            }
        } while (this.callBackInfo.doCallBack(false));
        throw new InterruptedIOException(f.c);
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public String getTransferEncoding() {
        return "8bit";
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public String getCharset() {
        return this.charset.name();
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public long getContentLength() {
        return this.content.length;
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentBody
    public String getFilename() {
        return null;
    }
}
