package com.alibaba.sdk.android.oss.model;

import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.alibaba.sdk.android.oss.common.OSSHeaders;
import com.alibaba.sdk.android.oss.common.utils.DateUtil;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class ObjectMetadata {
    public static final String AES_256_SERVER_SIDE_ENCRYPTION = "AES256";
    private Map<String, String> userMetadata = new HashMap();
    private Map<String, Object> metadata = new HashMap();

    public Map<String, String> getUserMetadata() {
        return this.userMetadata;
    }

    public void setUserMetadata(Map<String, String> userMetadata) {
        this.userMetadata.clear();
        if (userMetadata != null && !userMetadata.isEmpty()) {
            this.userMetadata.putAll(userMetadata);
        }
    }

    public void setHeader(String key, Object value) {
        this.metadata.put(key, value);
    }

    public void addUserMetadata(String key, String value) {
        this.userMetadata.put(key, value);
    }

    public Date getLastModified() {
        return (Date) this.metadata.get("Last-Modified");
    }

    public void setLastModified(Date lastModified) {
        this.metadata.put("Last-Modified", lastModified);
    }

    public Date getExpirationTime() throws ParseException {
        return DateUtil.parseRfc822Date((String) this.metadata.get("Expires"));
    }

    public String getRawExpiresValue() {
        return (String) this.metadata.get("Expires");
    }

    public void setExpirationTime(Date expirationTime) {
        this.metadata.put("Expires", DateUtil.formatRfc822Date(expirationTime));
    }

    public long getContentLength() {
        Long contentLength = (Long) this.metadata.get("Content-Length");
        if (contentLength == null) {
            return 0L;
        }
        return contentLength.longValue();
    }

    public void setContentLength(long contentLength) {
        if (contentLength > OSSConstants.DEFAULT_FILE_SIZE_LIMIT) {
            throw new IllegalArgumentException("The content length could not be more than 5GB.");
        }
        this.metadata.put("Content-Length", Long.valueOf(contentLength));
    }

    public String getContentType() {
        return (String) this.metadata.get("Content-Type");
    }

    public void setContentType(String contentType) {
        this.metadata.put("Content-Type", contentType);
    }

    public String getContentMD5() {
        return (String) this.metadata.get(HttpHeaders.CONTENT_MD5);
    }

    public void setContentMD5(String contentMD5) {
        this.metadata.put(HttpHeaders.CONTENT_MD5, contentMD5);
    }

    public String getContentEncoding() {
        return (String) this.metadata.get("Content-Encoding");
    }

    public void setContentEncoding(String encoding) {
        this.metadata.put("Content-Encoding", encoding);
    }

    public String getCacheControl() {
        return (String) this.metadata.get("Cache-Control");
    }

    public void setCacheControl(String cacheControl) {
        this.metadata.put("Cache-Control", cacheControl);
    }

    public String getContentDisposition() {
        return (String) this.metadata.get("Content-Disposition");
    }

    public void setContentDisposition(String disposition) {
        this.metadata.put("Content-Disposition", disposition);
    }

    public String getETag() {
        return (String) this.metadata.get("ETag");
    }

    public String getServerSideEncryption() {
        return (String) this.metadata.get(OSSHeaders.OSS_SERVER_SIDE_ENCRYPTION);
    }

    public void setServerSideEncryption(String serverSideEncryption) {
        this.metadata.put(OSSHeaders.OSS_SERVER_SIDE_ENCRYPTION, serverSideEncryption);
    }

    public String getObjectType() {
        return (String) this.metadata.get(OSSHeaders.OSS_OBJECT_TYPE);
    }

    public Map<String, Object> getRawMetadata() {
        return Collections.unmodifiableMap(this.metadata);
    }

    public String toString() {
        String expirationTimeStr = "";
        try {
            expirationTimeStr = getExpirationTime().toString();
        } catch (Exception e) {
        }
        return "Last-Modified:" + getLastModified() + "\nExpires:" + expirationTimeStr + "\nrawExpires:" + getRawExpiresValue() + "\n" + HttpHeaders.CONTENT_MD5 + ":" + getContentMD5() + "\n" + OSSHeaders.OSS_OBJECT_TYPE + ":" + getObjectType() + "\n" + OSSHeaders.OSS_SERVER_SIDE_ENCRYPTION + ":" + getServerSideEncryption() + "\nContent-Disposition:" + getContentDisposition() + "\nContent-Encoding:" + getContentEncoding() + "\nCache-Control:" + getCacheControl() + "\nETag:" + getETag() + "\n";
    }
}
