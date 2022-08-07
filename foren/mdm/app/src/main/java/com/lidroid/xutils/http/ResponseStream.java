package com.lidroid.xutils.http;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.util.IOUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import org.apache.http.HttpResponse;

/* loaded from: classes2.dex */
public class ResponseStream extends InputStream {
    private String _directResult;
    private HttpResponse baseResponse;
    private InputStream baseStream;
    private String charset;
    private long expiry;
    private String requestMethod;
    private String requestUrl;

    public ResponseStream(HttpResponse baseResponse, String requestUrl, long expiry) throws IOException {
        this(baseResponse, "UTF-8", requestUrl, expiry);
    }

    public ResponseStream(HttpResponse baseResponse, String charset, String requestUrl, long expiry) throws IOException {
        if (baseResponse == null) {
            throw new IllegalArgumentException("baseResponse may not be null");
        }
        this.baseResponse = baseResponse;
        this.baseStream = baseResponse.getEntity().getContent();
        this.charset = charset;
        this.requestUrl = requestUrl;
        this.expiry = expiry;
    }

    public ResponseStream(String result) throws IOException {
        if (result == null) {
            throw new IllegalArgumentException("result may not be null");
        }
        this._directResult = result;
    }

    public String getRequestUrl() {
        return this.requestUrl;
    }

    public String getRequestMethod() {
        return this.requestMethod;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public InputStream getBaseStream() {
        return this.baseStream;
    }

    public HttpResponse getBaseResponse() {
        return this.baseResponse;
    }

    public int getStatusCode() {
        if (this._directResult != null) {
            return 200;
        }
        return this.baseResponse.getStatusLine().getStatusCode();
    }

    public Locale getLocale() {
        return this._directResult != null ? Locale.getDefault() : this.baseResponse.getLocale();
    }

    public String getReasonPhrase() {
        return this._directResult != null ? "" : this.baseResponse.getStatusLine().getReasonPhrase();
    }

    public String readString() throws IOException {
        if (this._directResult != null) {
            return this._directResult;
        }
        if (this.baseStream == null) {
            return null;
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.baseStream, this.charset));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
            }
            this._directResult = sb.toString();
            if (this.requestUrl != null && HttpUtils.sHttpCache.isEnabled(this.requestMethod)) {
                HttpUtils.sHttpCache.put(this.requestUrl, this._directResult, this.expiry);
            }
            return this._directResult;
        } finally {
            IOUtils.closeQuietly(this.baseStream);
        }
    }

    public void readFile(String savePath) throws IOException {
        BufferedOutputStream out;
        if (this._directResult == null && this.baseStream != null) {
            BufferedOutputStream out2 = null;
            try {
                out = new BufferedOutputStream(new FileOutputStream(savePath));
            } catch (Throwable th) {
                th = th;
            }
            try {
                BufferedInputStream ins = new BufferedInputStream(this.baseStream);
                byte[] buffer = new byte[4096];
                while (true) {
                    int len = ins.read(buffer);
                    if (len != -1) {
                        out.write(buffer, 0, len);
                    } else {
                        out.flush();
                        IOUtils.closeQuietly(out);
                        IOUtils.closeQuietly(this.baseStream);
                        return;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                out2 = out;
                IOUtils.closeQuietly(out2);
                IOUtils.closeQuietly(this.baseStream);
                throw th;
            }
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.baseStream == null) {
            return -1;
        }
        return this.baseStream.read();
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (this.baseStream == null) {
            return 0;
        }
        return this.baseStream.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.baseStream != null) {
            this.baseStream.close();
        }
    }

    @Override // java.io.InputStream
    public void mark(int readLimit) {
        if (this.baseStream != null) {
            this.baseStream.mark(readLimit);
        }
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        if (this.baseStream == null) {
            return false;
        }
        return this.baseStream.markSupported();
    }

    @Override // java.io.InputStream
    public int read(byte[] buffer) throws IOException {
        if (this.baseStream == null) {
            return -1;
        }
        return this.baseStream.read(buffer);
    }

    @Override // java.io.InputStream
    public int read(byte[] buffer, int offset, int length) throws IOException {
        if (this.baseStream == null) {
            return -1;
        }
        return this.baseStream.read(buffer, offset, length);
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        if (this.baseStream != null) {
            this.baseStream.reset();
        }
    }

    @Override // java.io.InputStream
    public long skip(long byteCount) throws IOException {
        if (this.baseStream == null) {
            return 0L;
        }
        return this.baseStream.skip(byteCount);
    }

    public long getContentLength() {
        if (this.baseStream == null) {
            return 0L;
        }
        return this.baseResponse.getEntity().getContentLength();
    }
}
