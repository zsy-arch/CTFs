package com.lidroid.xutils.http.client.entity;

import com.lidroid.xutils.http.callback.RequestCallBackHandler;
import com.lidroid.xutils.util.IOUtils;
import com.umeng.update.net.f;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import org.apache.http.entity.FileEntity;

/* loaded from: classes2.dex */
public class FileUploadEntity extends FileEntity implements UploadEntity {
    private long fileSize;
    private long uploadedSize = 0;
    private RequestCallBackHandler callBackHandler = null;

    public FileUploadEntity(File file, String contentType) {
        super(file, contentType);
        this.fileSize = file.length();
    }

    @Override // org.apache.http.entity.FileEntity, org.apache.http.HttpEntity
    public void writeTo(OutputStream outStream) throws IOException {
        BufferedInputStream inStream;
        if (outStream == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        BufferedInputStream inStream2 = null;
        try {
            inStream = new BufferedInputStream(new FileInputStream(this.file));
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] tmp = new byte[4096];
            while (true) {
                int len = inStream.read(tmp);
                if (len != -1) {
                    outStream.write(tmp, 0, len);
                    this.uploadedSize += len;
                    if (this.callBackHandler != null && !this.callBackHandler.updateProgress(this.fileSize, this.uploadedSize, false)) {
                        throw new InterruptedIOException(f.c);
                    }
                } else {
                    outStream.flush();
                    if (this.callBackHandler != null) {
                        this.callBackHandler.updateProgress(this.fileSize, this.uploadedSize, true);
                    }
                    IOUtils.closeQuietly(inStream);
                    return;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            inStream2 = inStream;
            IOUtils.closeQuietly(inStream2);
            throw th;
        }
    }

    @Override // com.lidroid.xutils.http.client.entity.UploadEntity
    public void setCallBackHandler(RequestCallBackHandler callBackHandler) {
        this.callBackHandler = callBackHandler;
    }
}
