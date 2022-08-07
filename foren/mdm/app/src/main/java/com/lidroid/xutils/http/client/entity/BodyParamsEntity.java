package com.lidroid.xutils.http.client.entity;

import com.lidroid.xutils.http.client.util.URLEncodedUtils;
import com.lidroid.xutils.util.LogUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.message.BasicNameValuePair;

/* loaded from: classes2.dex */
public class BodyParamsEntity extends AbstractHttpEntity implements Cloneable {
    private String charset;
    protected byte[] content;
    private boolean dirty;
    private List<NameValuePair> params;

    public BodyParamsEntity() {
        this((String) null);
    }

    public BodyParamsEntity(String charset) {
        this.dirty = true;
        this.charset = "UTF-8";
        if (charset != null) {
            this.charset = charset;
        }
        setContentType("application/x-www-form-urlencoded");
        this.params = new ArrayList();
    }

    public BodyParamsEntity(List<NameValuePair> params) {
        this(params, null);
    }

    public BodyParamsEntity(List<NameValuePair> params, String charset) {
        this.dirty = true;
        this.charset = "UTF-8";
        if (charset != null) {
            this.charset = charset;
        }
        setContentType("application/x-www-form-urlencoded");
        this.params = params;
        refreshContent();
    }

    public BodyParamsEntity addParameter(String name, String value) {
        this.params.add(new BasicNameValuePair(name, value));
        this.dirty = true;
        return this;
    }

    public BodyParamsEntity addParams(List<NameValuePair> params) {
        this.params.addAll(params);
        this.dirty = true;
        return this;
    }

    private void refreshContent() {
        if (this.dirty) {
            try {
                this.content = URLEncodedUtils.format(this.params, this.charset).getBytes(this.charset);
            } catch (UnsupportedEncodingException e) {
                LogUtils.e(e.getMessage(), e);
            }
            this.dirty = false;
        }
    }

    @Override // org.apache.http.HttpEntity
    public boolean isRepeatable() {
        return true;
    }

    @Override // org.apache.http.HttpEntity
    public long getContentLength() {
        refreshContent();
        return this.content.length;
    }

    @Override // org.apache.http.HttpEntity
    public InputStream getContent() throws IOException {
        refreshContent();
        return new ByteArrayInputStream(this.content);
    }

    @Override // org.apache.http.HttpEntity
    public void writeTo(OutputStream outStream) throws IOException {
        if (outStream == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        refreshContent();
        outStream.write(this.content);
        outStream.flush();
    }

    @Override // org.apache.http.HttpEntity
    public boolean isStreaming() {
        return false;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
