package com.hy.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.protocol.HTTP;

/* loaded from: classes2.dex */
public class HttpEntity {
    public static final String APPLICATION_ATOM_XML = "application/atom+xml";
    public static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";
    public static final String APPLICATION_JSON = "application/json";
    public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";
    public static final String APPLICATION_SVG_XML = "application/svg+xml";
    public static final String APPLICATION_XHTML_XML = "application/xhtml+xml";
    public static final String APPLICATION_XML = "application/xml";
    protected static String DEFAULT_CHARSET = "UTF-8";
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    protected static final int OUTPUT_BUFFER_SIZE = 4096;
    public static final String TEXT_HTML = "text/html";
    public static final String TEXT_PLAIN = "text/plain";
    public static final String TEXT_XML = "text/xml";
    public static final String WILDCARD = "*/*";
    private String contentEncoding;
    private int contentLength;
    private String contentType;
    private InputStream inputStream;

    public static String createContentType(String typeValue, String charset) {
        return typeValue + HTTP.CHARSET_PARAM + charset;
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getContentEncoding() {
        return this.contentEncoding;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContent(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public void setContentEncoding(String contentEncoding) {
        this.contentEncoding = contentEncoding;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getContentLength() {
        return this.contentLength;
    }

    public InputStream getContent() throws IOException {
        return this.inputStream;
    }

    public void consumeContent() {
        if (this.inputStream != null) {
            try {
                this.inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeTo(ByteArrayOutputStream baos) throws IOException {
        InputStream instream = getContent();
        if (baos != null && instream != null) {
            try {
                byte[] tmp = new byte[4096];
                while (true) {
                    int l = instream.read(tmp);
                    if (l != -1) {
                        baos.write(tmp, 0, l);
                    } else {
                        return;
                    }
                }
            } finally {
                instream.close();
            }
        }
    }
}
