package com.lidroid.xutils.http.client.entity;

import com.lidroid.xutils.http.callback.RequestCallBackHandler;
import com.lidroid.xutils.util.IOUtils;
import com.umeng.update.net.f;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class DecompressingEntity extends HttpEntityWrapper implements UploadEntity {
    private InputStream content;
    private long uncompressedLength;
    private long uploadedSize = 0;
    private RequestCallBackHandler callBackHandler = null;

    abstract InputStream decorate(InputStream inputStream) throws IOException;

    public DecompressingEntity(HttpEntity wrapped) {
        super(wrapped);
        this.uncompressedLength = wrapped.getContentLength();
    }

    private InputStream getDecompressingStream() throws IOException {
        InputStream in = null;
        try {
            in = this.wrappedEntity.getContent();
            return decorate(in);
        } catch (IOException ex) {
            IOUtils.closeQuietly(in);
            throw ex;
        }
    }

    @Override // org.apache.http.entity.HttpEntityWrapper, org.apache.http.HttpEntity
    public InputStream getContent() throws IOException {
        if (!this.wrappedEntity.isStreaming()) {
            return getDecompressingStream();
        }
        if (this.content == null) {
            this.content = getDecompressingStream();
        }
        return this.content;
    }

    @Override // org.apache.http.entity.HttpEntityWrapper, org.apache.http.HttpEntity
    public long getContentLength() {
        return -1L;
    }

    @Override // org.apache.http.entity.HttpEntityWrapper, org.apache.http.HttpEntity
    public void writeTo(OutputStream outStream) throws IOException {
        if (outStream == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        InputStream inStream = null;
        try {
            inStream = getContent();
            byte[] tmp = new byte[4096];
            while (true) {
                int len = inStream.read(tmp);
                if (len != -1) {
                    outStream.write(tmp, 0, len);
                    this.uploadedSize += len;
                    if (this.callBackHandler != null && !this.callBackHandler.updateProgress(this.uncompressedLength, this.uploadedSize, false)) {
                        throw new InterruptedIOException(f.c);
                    }
                } else {
                    outStream.flush();
                    if (this.callBackHandler != null) {
                        this.callBackHandler.updateProgress(this.uncompressedLength, this.uploadedSize, true);
                    }
                    return;
                }
            }
        } finally {
            IOUtils.closeQuietly(inStream);
        }
    }

    @Override // com.lidroid.xutils.http.client.entity.UploadEntity
    public void setCallBackHandler(RequestCallBackHandler callBackHandler) {
        this.callBackHandler = callBackHandler;
    }
}
