package com.alibaba.sdk.android.oss.model;

import android.support.v4.media.session.PlaybackStateCompat;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import java.io.File;
import java.util.Map;

/* loaded from: classes.dex */
public class ResumableUploadRequest extends OSSRequest {
    private String bucketName;
    private Map<String, String> callbackParam;
    private Map<String, String> callbackVars;
    private Boolean deleteUploadOnCancelling;
    private ObjectMetadata metadata;
    private String objectKey;
    private long partSize;
    private OSSProgressCallback<ResumableUploadRequest> progressCallback;
    private String recordDirectory;
    private String uploadFilePath;

    public ResumableUploadRequest(String bucketName, String objectKey, String uploadFilePath) {
        this(bucketName, objectKey, uploadFilePath, null, null);
    }

    public ResumableUploadRequest(String bucketName, String objectKey, String uploadFilePath, ObjectMetadata metadata) {
        this(bucketName, objectKey, uploadFilePath, metadata, null);
    }

    public ResumableUploadRequest(String bucketName, String objectKey, String uploadFilePath, String recordDirectory) {
        this(bucketName, objectKey, uploadFilePath, null, recordDirectory);
    }

    public ResumableUploadRequest(String bucketName, String objectKey, String uploadFilePath, ObjectMetadata metadata, String recordDirectory) {
        this.deleteUploadOnCancelling = true;
        this.partSize = PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
        setBucketName(bucketName);
        setObjectKey(objectKey);
        setUploadFilePath(uploadFilePath);
        setMetadata(metadata);
        setRecordDirectory(recordDirectory);
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }

    public String getUploadFilePath() {
        return this.uploadFilePath;
    }

    public void setUploadFilePath(String uploadFilePath) {
        this.uploadFilePath = uploadFilePath;
    }

    public String getRecordDirectory() {
        return this.recordDirectory;
    }

    public void setRecordDirectory(String recordDirectory) {
        if (recordDirectory != null) {
            File file = new File(recordDirectory);
            if (!file.exists() || !file.isDirectory()) {
                throw new IllegalArgumentException("Record directory must exist, and it should be a directory!");
            }
        }
        this.recordDirectory = recordDirectory;
    }

    public ObjectMetadata getMetadata() {
        return this.metadata;
    }

    public void setMetadata(ObjectMetadata metadata) {
        this.metadata = metadata;
    }

    public OSSProgressCallback<ResumableUploadRequest> getProgressCallback() {
        return this.progressCallback;
    }

    public void setProgressCallback(OSSProgressCallback<ResumableUploadRequest> progressCallback) {
        this.progressCallback = progressCallback;
    }

    public long getPartSize() {
        return this.partSize;
    }

    public void setPartSize(long partSize) throws IllegalArgumentException {
        if (partSize < OSSConstants.MIN_PART_SIZE_LIMIT) {
            throw new IllegalArgumentException("Part size must be greater than or equal to 100KB!");
        }
        this.partSize = partSize;
    }

    public Map<String, String> getCallbackParam() {
        return this.callbackParam;
    }

    public void setCallbackParam(Map<String, String> callbackParam) {
        this.callbackParam = callbackParam;
    }

    public Map<String, String> getCallbackVars() {
        return this.callbackVars;
    }

    public void setCallbackVars(Map<String, String> callbackVars) {
        this.callbackVars = callbackVars;
    }

    public Boolean deleteUploadOnCancelling() {
        return this.deleteUploadOnCancelling;
    }

    public void setDeleteUploadOnCancelling(Boolean deleteUploadOnCancelling) {
        this.deleteUploadOnCancelling = deleteUploadOnCancelling;
    }
}
