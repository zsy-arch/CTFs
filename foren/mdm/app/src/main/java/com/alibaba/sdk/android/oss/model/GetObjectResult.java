package com.alibaba.sdk.android.oss.model;

import java.io.InputStream;

/* loaded from: classes.dex */
public class GetObjectResult extends OSSResult {
    private long contentLength;
    private ObjectMetadata metadata = new ObjectMetadata();
    private InputStream objectContent;

    public ObjectMetadata getMetadata() {
        return this.metadata;
    }

    public void setMetadata(ObjectMetadata metadata) {
        this.metadata = metadata;
    }

    public InputStream getObjectContent() {
        return this.objectContent;
    }

    public void setObjectContent(InputStream objectContent) {
        this.objectContent = objectContent;
    }

    public long getContentLength() {
        return this.contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }
}
