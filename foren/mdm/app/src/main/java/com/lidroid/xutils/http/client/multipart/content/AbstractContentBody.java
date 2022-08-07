package com.lidroid.xutils.http.client.multipart.content;

import com.lidroid.xutils.http.client.multipart.MultipartEntity;

/* loaded from: classes2.dex */
public abstract class AbstractContentBody implements ContentBody {
    protected MultipartEntity.CallBackInfo callBackInfo = MultipartEntity.CallBackInfo.DEFAULT;
    private final String mediaType;
    private final String mimeType;
    private final String subType;

    public AbstractContentBody(String mimeType) {
        if (mimeType == null) {
            throw new IllegalArgumentException("MIME type may not be null");
        }
        this.mimeType = mimeType;
        int i = mimeType.indexOf(47);
        if (i != -1) {
            this.mediaType = mimeType.substring(0, i);
            this.subType = mimeType.substring(i + 1);
            return;
        }
        this.mediaType = mimeType;
        this.subType = null;
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public String getMimeType() {
        return this.mimeType;
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public String getMediaType() {
        return this.mediaType;
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentDescriptor
    public String getSubType() {
        return this.subType;
    }

    @Override // com.lidroid.xutils.http.client.multipart.content.ContentBody
    public void setCallBackInfo(MultipartEntity.CallBackInfo callBackInfo) {
        this.callBackInfo = callBackInfo;
    }
}
