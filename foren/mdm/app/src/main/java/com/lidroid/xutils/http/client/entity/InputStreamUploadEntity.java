package com.lidroid.xutils.http.client.entity;

import android.support.v4.media.session.PlaybackStateCompat;
import com.lidroid.xutils.http.callback.RequestCallBackHandler;
import com.lidroid.xutils.util.IOUtils;
import com.umeng.update.net.f;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import org.apache.http.entity.AbstractHttpEntity;

/* loaded from: classes2.dex */
public class InputStreamUploadEntity extends AbstractHttpEntity implements UploadEntity {
    private static final int BUFFER_SIZE = 2048;
    private final InputStream content;
    private final long length;
    private long uploadedSize = 0;
    private RequestCallBackHandler callBackHandler = null;

    public InputStreamUploadEntity(InputStream inputStream, long length) {
        if (inputStream == null) {
            throw new IllegalArgumentException("Source input stream may not be null");
        }
        this.content = inputStream;
        this.length = length;
    }

    @Override // org.apache.http.HttpEntity
    public boolean isRepeatable() {
        return false;
    }

    @Override // org.apache.http.HttpEntity
    public long getContentLength() {
        return this.length;
    }

    @Override // org.apache.http.HttpEntity
    public InputStream getContent() throws IOException {
        return this.content;
    }

    @Override // org.apache.http.HttpEntity
    public void writeTo(OutputStream outStream) throws IOException {
        int l;
        if (outStream == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        InputStream inStream = this.content;
        try {
            byte[] buffer = new byte[2048];
            if (this.length < 0) {
                while (true) {
                    int l2 = inStream.read(buffer);
                    if (l2 == -1) {
                        break;
                    }
                    outStream.write(buffer, 0, l2);
                    this.uploadedSize += l2;
                    if (this.callBackHandler != null && !this.callBackHandler.updateProgress(this.uploadedSize + 1, this.uploadedSize, false)) {
                        throw new InterruptedIOException(f.c);
                    }
                }
            } else {
                long remaining = this.length;
                while (remaining > 0 && (l = inStream.read(buffer, 0, (int) Math.min((long) PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH, remaining))) != -1) {
                    outStream.write(buffer, 0, l);
                    remaining -= l;
                    this.uploadedSize += l;
                    if (this.callBackHandler != null && !this.callBackHandler.updateProgress(this.length, this.uploadedSize, false)) {
                        throw new InterruptedIOException(f.c);
                    }
                }
            }
            outStream.flush();
            if (this.callBackHandler != null) {
                this.callBackHandler.updateProgress(this.length, this.uploadedSize, true);
            }
        } finally {
            IOUtils.closeQuietly(inStream);
        }
    }

    @Override // org.apache.http.HttpEntity
    public boolean isStreaming() {
        return true;
    }

    @Override // org.apache.http.entity.AbstractHttpEntity, org.apache.http.HttpEntity
    public void consumeContent() throws IOException {
        this.content.close();
    }

    @Override // com.lidroid.xutils.http.client.entity.UploadEntity
    public void setCallBackHandler(RequestCallBackHandler callBackHandler) {
        this.callBackHandler = callBackHandler;
    }
}
