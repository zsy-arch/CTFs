package com.hyphenate.cloud;

import android.support.annotation.NonNull;
import internal.org.apache.http.entity.mime.HttpMultipartMode;
import internal.org.apache.http.entity.mime.MultipartEntity;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

/* loaded from: classes2.dex */
public class CustomMultiPartEntity extends MultipartEntity {
    private ProgressListener listener;

    /* loaded from: classes2.dex */
    public static class CountingOutputStream extends FilterOutputStream {
        private final ProgressListener listener;
        private long transferred = 0;

        public CountingOutputStream(OutputStream outputStream, ProgressListener progressListener) {
            super(outputStream);
            this.listener = progressListener;
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(int i) throws IOException {
            this.out.write(i);
            this.transferred++;
            this.listener.transferred(this.transferred);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(@NonNull byte[] bArr, int i, int i2) throws IOException {
            this.out.write(bArr, i, i2);
            this.transferred += i2;
            this.listener.transferred(this.transferred);
        }
    }

    /* loaded from: classes2.dex */
    public interface ProgressListener {
        void transferred(long j);
    }

    public CustomMultiPartEntity(ProgressListener progressListener) {
        this.listener = progressListener;
    }

    public CustomMultiPartEntity(HttpMultipartMode httpMultipartMode, ProgressListener progressListener) {
        super(httpMultipartMode);
        this.listener = progressListener;
    }

    public CustomMultiPartEntity(HttpMultipartMode httpMultipartMode, String str, Charset charset, ProgressListener progressListener) {
        super(httpMultipartMode, str, charset);
        this.listener = progressListener;
    }

    public void setListener(ProgressListener progressListener) {
        this.listener = progressListener;
    }

    @Override // internal.org.apache.http.entity.mime.MultipartEntity, org.apache.http.HttpEntity
    public void writeTo(OutputStream outputStream) throws IOException {
        super.writeTo(new CountingOutputStream(outputStream, this.listener));
    }
}
