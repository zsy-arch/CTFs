package com.alibaba.sdk.android.oss.internal;

import com.alibaba.sdk.android.oss.common.HttpMethod;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.utils.HttpUtil;
import com.alibaba.sdk.android.oss.common.utils.HttpdnsMini;
import com.alibaba.sdk.android.oss.common.utils.OSSUtils;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class RequestMessage {
    private String bucketName;
    private OSSCredentialProvider credentialProvider;
    private URI endpoint;
    private HttpMethod method;
    private String objectKey;
    private long readStreamLength;
    private byte[] uploadData;
    private String uploadFilePath;
    private InputStream uploadInputStream;
    private boolean isAuthorizationRequired = true;
    private Map<String, String> headers = new HashMap();
    private Map<String, String> parameters = new LinkedHashMap();
    private boolean isHttpdnsEnable = true;
    private boolean isInCustomCnameExcludeList = false;

    public HttpMethod getMethod() {
        return this.method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public URI getEndpoint() {
        return this.endpoint;
    }

    public OSSCredentialProvider getCredentialProvider() {
        return this.credentialProvider;
    }

    public void setCredentialProvider(OSSCredentialProvider credentialProvider) {
        this.credentialProvider = credentialProvider;
    }

    public void setEndpoint(URI endpoint) {
        this.endpoint = endpoint;
    }

    public boolean isHttpdnsEnable() {
        return this.isHttpdnsEnable;
    }

    public void setIsHttpdnsEnable(boolean isHttpdnsEnable) {
        this.isHttpdnsEnable = isHttpdnsEnable;
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

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public byte[] getUploadData() {
        return this.uploadData;
    }

    public void setUploadData(byte[] uploadData) {
        this.uploadData = uploadData;
    }

    public String getUploadFilePath() {
        return this.uploadFilePath;
    }

    public void setUploadFilePath(String uploadFilePath) {
        this.uploadFilePath = uploadFilePath;
    }

    public boolean isAuthorizationRequired() {
        return this.isAuthorizationRequired;
    }

    public void setIsAuthorizationRequired(boolean isAuthorizationRequired) {
        this.isAuthorizationRequired = isAuthorizationRequired;
    }

    public boolean isInCustomCnameExcludeList() {
        return this.isInCustomCnameExcludeList;
    }

    public void setIsInCustomCnameExcludeList(boolean isInExcludeCnameList) {
        this.isInCustomCnameExcludeList = isInExcludeCnameList;
    }

    public void setUploadInputStream(InputStream uploadInputStream, long inputLength) {
        if (uploadInputStream != null) {
            this.uploadInputStream = uploadInputStream;
            this.readStreamLength = inputLength;
        }
    }

    public InputStream getUploadInputStream() {
        return this.uploadInputStream;
    }

    public void createBucketRequestBodyMarshall(String locationConstraint) throws UnsupportedEncodingException {
        StringBuffer xmlBody = new StringBuffer();
        if (locationConstraint != null) {
            xmlBody.append("<CreateBucketConfiguration>");
            xmlBody.append("<LocationConstraint>" + locationConstraint + "</LocationConstraint>");
            xmlBody.append("</CreateBucketConfiguration>");
            byte[] binaryData = xmlBody.toString().getBytes("utf-8");
            setUploadInputStream(new ByteArrayInputStream(binaryData), binaryData.length);
        }
    }

    public long getReadStreamLength() {
        return this.readStreamLength;
    }

    public String buildCanonicalURL() {
        OSSUtils.assertTrue(this.endpoint != null, "Endpoint haven't been set!");
        String scheme = this.endpoint.getScheme();
        String originHost = this.endpoint.getHost();
        if (!OSSUtils.isCname(originHost) && this.bucketName != null) {
            originHost = this.bucketName + "." + originHost;
        }
        String urlHost = null;
        if (this.isHttpdnsEnable) {
            urlHost = HttpdnsMini.getInstance().getIpByHostAsync(originHost);
        } else {
            OSSLog.logDebug("[buildCannonicalURL] - proxy exist, disable httpdns");
        }
        if (urlHost == null) {
            urlHost = originHost;
        }
        String headerHost = originHost;
        if (OSSUtils.isCname(originHost) && isInCustomCnameExcludeList() && this.bucketName != null) {
            headerHost = this.bucketName + "." + originHost;
        }
        this.headers.put("Host", headerHost);
        String baseURL = scheme + "://" + urlHost;
        if (this.objectKey != null) {
            baseURL = baseURL + "/" + HttpUtil.urlEncode(this.objectKey, "utf-8");
        }
        String queryString = OSSUtils.paramToQueryString(this.parameters, "utf-8");
        StringBuilder printReq = new StringBuilder();
        printReq.append("request---------------------\n");
        printReq.append("request url=" + baseURL + "\n");
        printReq.append("request params=" + queryString + "\n");
        for (String key : this.headers.keySet()) {
            printReq.append("requestHeader [" + key + "]: ").append(this.headers.get(key) + "\n");
        }
        OSSLog.logDebug(printReq.toString());
        return OSSUtils.isEmptyString(queryString) ? baseURL : baseURL + "?" + queryString;
    }
}
