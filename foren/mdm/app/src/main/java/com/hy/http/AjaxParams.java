package com.hy.http;

import com.yolanda.nohttp.Binary;
import com.yolanda.nohttp.FileBinary;
import java.io.InputStream;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class AjaxParams implements Serializable {
    private static String ENCODING = "UTF-8";
    protected ConcurrentHashMap<String, Binary> fileParams;
    private PostData postData;
    protected ConcurrentHashMap<String, String> urlParams;

    public AjaxParams() {
        init();
    }

    public AjaxParams(String key, String value) {
        init();
        put(key, value);
    }

    private void init() {
        this.urlParams = new ConcurrentHashMap<>();
        this.fileParams = new ConcurrentHashMap<>();
    }

    public void put(String key, String value) {
        if (key != null && value != null) {
            this.urlParams.put(key, value);
        }
    }

    public void put(String key, int value) {
        put(key, value + "");
    }

    public void put(String key, long value) {
        put(key, value + "");
    }

    @Deprecated
    public void put(String key, FileBinary file) {
        this.fileParams.put(key, file);
    }

    public void put(PostData postData) {
        this.postData = postData;
    }

    public ConcurrentHashMap<String, String> getUrlParams() {
        return this.urlParams;
    }

    public ConcurrentHashMap<String, Binary> getFileParams() {
        return this.fileParams;
    }

    public PostData getPostData() {
        return this.postData;
    }

    public void setFileParams(ConcurrentHashMap<String, Binary> fileParams) {
        this.fileParams = fileParams;
    }

    @Deprecated
    /* loaded from: classes.dex */
    private static class FileWrapper {
        public String contentType;
        public String fileName;
        public InputStream inputStream;

        public FileWrapper(InputStream inputStream, String fileName, String contentType) {
            this.inputStream = inputStream;
            this.fileName = fileName;
            this.contentType = contentType;
        }

        public String getFileName() {
            return this.fileName != null ? this.fileName : "nofilename";
        }
    }
}
