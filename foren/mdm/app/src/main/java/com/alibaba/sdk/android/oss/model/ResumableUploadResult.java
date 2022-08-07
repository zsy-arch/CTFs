package com.alibaba.sdk.android.oss.model;

/* loaded from: classes.dex */
public class ResumableUploadResult extends CompleteMultipartUploadResult {
    public ResumableUploadResult(CompleteMultipartUploadResult completeResult) {
        setBucketName(completeResult.getBucketName());
        setObjectKey(completeResult.getObjectKey());
        setETag(completeResult.getETag());
        setLocation(completeResult.getLocation());
        setRequestId(completeResult.getRequestId());
        setResponseHeader(completeResult.getResponseHeader());
        setStatusCode(completeResult.getStatusCode());
        setServerCallbackReturnBody(completeResult.getServerCallbackReturnBody());
    }
}
