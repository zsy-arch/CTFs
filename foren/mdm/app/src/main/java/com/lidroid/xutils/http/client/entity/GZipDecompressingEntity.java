package com.lidroid.xutils.http.client.entity;

import com.lidroid.xutils.http.callback.RequestCallBackHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

/* loaded from: classes2.dex */
public class GZipDecompressingEntity extends DecompressingEntity {
    @Override // com.lidroid.xutils.http.client.entity.DecompressingEntity, org.apache.http.entity.HttpEntityWrapper, org.apache.http.HttpEntity
    public /* bridge */ /* synthetic */ InputStream getContent() throws IOException {
        return super.getContent();
    }

    @Override // com.lidroid.xutils.http.client.entity.DecompressingEntity, org.apache.http.entity.HttpEntityWrapper, org.apache.http.HttpEntity
    public /* bridge */ /* synthetic */ long getContentLength() {
        return super.getContentLength();
    }

    @Override // com.lidroid.xutils.http.client.entity.DecompressingEntity, com.lidroid.xutils.http.client.entity.UploadEntity
    public /* bridge */ /* synthetic */ void setCallBackHandler(RequestCallBackHandler requestCallBackHandler) {
        super.setCallBackHandler(requestCallBackHandler);
    }

    @Override // com.lidroid.xutils.http.client.entity.DecompressingEntity, org.apache.http.entity.HttpEntityWrapper, org.apache.http.HttpEntity
    public /* bridge */ /* synthetic */ void writeTo(OutputStream outputStream) throws IOException {
        super.writeTo(outputStream);
    }

    public GZipDecompressingEntity(HttpEntity entity) {
        super(entity);
    }

    @Override // com.lidroid.xutils.http.client.entity.DecompressingEntity
    InputStream decorate(InputStream wrapped) throws IOException {
        return new GZIPInputStream(wrapped);
    }

    @Override // org.apache.http.entity.HttpEntityWrapper, org.apache.http.HttpEntity
    public Header getContentEncoding() {
        return null;
    }
}
