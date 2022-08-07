package com.lidroid.xutils.http.client.multipart.content;

import com.lidroid.xutils.util.IOUtils;
import com.umeng.update.net.f;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public class FileBody extends AbstractContentBody {
    private final String charset;
    private final File file;
    private final String filename;

    public FileBody(File file, String filename, String mimeType, String charset) {
        super(mimeType);
        if (file == null) {
            throw new IllegalArgumentException("File may not be null");
        }
        this.file = file;
        if (filename != null) {
            this.filename = filename;
        } else {
            this.filename = file.getName();
        }
        this.charset = charset;
    }

    public FileBody(File file, String mimeType, String charset) {
        this(file, null, mimeType, charset);
    }

    public FileBody(File file, String mimeType) {
        this(file, null, mimeType, null);
    }

    public FileBody(File file) {
        this(file, null, "application/octet-stream", null);
    }

    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentBody
    public void writeTo(OutputStream out) throws IOException {
        BufferedInputStream in;
        if (out == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        BufferedInputStream in2 = null;
        try {
            in = new BufferedInputStream(new FileInputStream(this.file));
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] tmp = new byte[4096];
            do {
                int l = in.read(tmp);
                if (l != -1) {
                    out.write(tmp, 0, l);
                    this.callBackInfo.pos += l;
                } else {
                    out.flush();
                    IOUtils.closeQuietly(in);
                    return;
                }
            } while (this.callBackInfo.doCallBack(false));
            throw new InterruptedIOException(f.c);
        } catch (Throwable th2) {
            th = th2;
            in2 = in;
            IOUtils.closeQuietly(in2);
            throw th;
        }
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public String getTransferEncoding() {
        return "binary";
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public String getCharset() {
        return this.charset;
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public long getContentLength() {
        return this.file.length();
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentBody
    public String getFilename() {
        return this.filename;
    }

    public File getFile() {
        return this.file;
    }
}
