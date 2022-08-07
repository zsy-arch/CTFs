package com.yolanda.nohttp.download;

import com.hy.http.HttpEntity;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.RestRequest;
import java.io.File;

/* loaded from: classes2.dex */
public class RestDownloadRequest extends RestRequest<Void> implements DownloadRequest {
    private final boolean isDeleteOld;
    private final boolean isRange;
    private final String mFileDir;
    private final String mFileName;

    public RestDownloadRequest(String url, String fileFolder, String filename, boolean isRange, boolean isDeleteOld) {
        this(url, RequestMethod.GET, fileFolder, filename, isRange, isDeleteOld);
    }

    public RestDownloadRequest(String url, RequestMethod requestMethod, String fileFolder, String filename, boolean isRange, boolean isDeleteOld) {
        super(url, requestMethod);
        this.mFileDir = fileFolder;
        this.mFileName = filename;
        this.isRange = isRange;
        this.isDeleteOld = isDeleteOld;
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public String getAccept() {
        return HttpEntity.WILDCARD;
    }

    @Override // com.yolanda.nohttp.download.DownloadRequest
    public String getFileDir() {
        return this.mFileDir;
    }

    @Override // com.yolanda.nohttp.download.DownloadRequest
    public String getFileName() {
        return this.mFileName;
    }

    @Override // com.yolanda.nohttp.download.DownloadRequest
    public boolean isRange() {
        return this.isRange;
    }

    @Override // com.yolanda.nohttp.download.DownloadRequest
    public boolean isDeleteOld() {
        return this.isDeleteOld;
    }

    @Override // com.yolanda.nohttp.download.DownloadRequest
    public int checkBeforeStatus() {
        if (this.isRange) {
            try {
                if (new File(this.mFileDir, this.mFileName).exists() && !this.isDeleteOld) {
                    return 2;
                }
                if (new File(this.mFileDir, this.mFileName + ".nohttp").exists()) {
                    return 1;
                }
            } catch (Exception e) {
            }
        }
        return 0;
    }

    @Override // com.yolanda.nohttp.Request
    public Void parseResponse(String url, Headers responseHeaders, byte[] responseBody) {
        return null;
    }
}
