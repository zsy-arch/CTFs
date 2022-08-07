package com.yolanda.nohttp;

import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.yolanda.nohttp.cache.CacheMode;
import com.yolanda.nohttp.tools.CounterOutputStream;
import com.yolanda.nohttp.tools.Writer;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Proxy;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Set;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes2.dex */
public abstract class BasicRequest<T> implements Request<T> {
    private static String acceptLanguage;
    private static String userAgent;
    private final String boundary;
    private String buildUrl;
    private Object cancelSign;
    private final String end_boundary;
    private boolean isCanceled;
    private boolean isFinished;
    private boolean isStart;
    private String mCacheKey;
    private CacheMode mCacheMode;
    private int mConnectTimeout;
    private Headers mHeaders;
    private HostnameVerifier mHostnameVerifier;
    private Proxy mProxy;
    private int mReadTimeout;
    private RedirectHandler mRedirectHandler;
    private byte[] mRequestBody;
    private RequestMethod mRequestMethod;
    private SSLSocketFactory mSSLSocketFactory;
    private Object mTag;
    private boolean queue;
    private final String start_boundary;
    private String url;

    protected abstract Set<String> keySet();

    protected abstract Object value(String str);

    public BasicRequest(String url) {
        this(url, RequestMethod.GET);
    }

    public BasicRequest(String url, RequestMethod requestMethod) {
        this.boundary = createBoundary();
        this.start_boundary = "--" + this.boundary;
        this.end_boundary = this.start_boundary + "--";
        this.mCacheMode = CacheMode.DEFAULT;
        this.mSSLSocketFactory = null;
        this.mHostnameVerifier = null;
        this.mConnectTimeout = 10000;
        this.mReadTimeout = 20000;
        this.queue = false;
        this.isStart = false;
        this.isFinished = false;
        this.isCanceled = false;
        if (TextUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url is null");
        }
        this.url = url;
        this.mRequestMethod = requestMethod;
        this.mHeaders = new HttpHeaders();
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public String url() {
        if (TextUtils.isEmpty(this.buildUrl)) {
            this.buildUrl = buildUrl();
        }
        return this.buildUrl;
    }

    protected final String buildUrl() {
        StringBuffer urlBuffer = new StringBuffer(this.url);
        if (!doOutPut() && keySet().size() > 0) {
            StringBuffer paramBuffer = buildCommonParams();
            if (this.url.contains("?") && this.url.contains("=") && paramBuffer.length() > 0) {
                urlBuffer.append(a.b);
            } else if (paramBuffer.length() > 0) {
                urlBuffer.append("?");
            }
            urlBuffer.append(paramBuffer);
        }
        return urlBuffer.toString();
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public RequestMethod getRequestMethod() {
        return this.mRequestMethod;
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public boolean needCache() {
        return RequestMethod.GET == getRequestMethod() || CacheMode.REQUEST_FAILED_READ_CACHE == getCacheMode();
    }

    @Override // com.yolanda.nohttp.ImplClientRequest
    public void setCacheKey(String key) {
        this.mCacheKey = key;
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public String getCacheKey() {
        return TextUtils.isEmpty(this.mCacheKey) ? buildUrl() : this.mCacheKey;
    }

    @Override // com.yolanda.nohttp.ImplClientRequest
    public void setCacheMode(CacheMode cacheMode) {
        this.mCacheMode = cacheMode;
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public CacheMode getCacheMode() {
        return this.mCacheMode;
    }

    @Override // com.yolanda.nohttp.ImplClientRequest
    public void setProxy(Proxy proxy) {
        this.mProxy = proxy;
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public Proxy getProxy() {
        return this.mProxy;
    }

    @Override // com.yolanda.nohttp.ImplClientRequest
    public void setSSLSocketFactory(SSLSocketFactory socketFactory) {
        this.mSSLSocketFactory = socketFactory;
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public SSLSocketFactory getSSLSocketFactory() {
        return this.mSSLSocketFactory;
    }

    @Override // com.yolanda.nohttp.ImplClientRequest
    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.mHostnameVerifier = hostnameVerifier;
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public HostnameVerifier getHostnameVerifier() {
        return this.mHostnameVerifier;
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public boolean doOutPut() {
        switch (this.mRequestMethod) {
            case GET:
            case DELETE:
            case HEAD:
            case OPTIONS:
            case TRACE:
            default:
                return false;
            case POST:
            case PUT:
            case PATCH:
                return true;
        }
    }

    @Override // com.yolanda.nohttp.ImplClientRequest
    public void setConnectTimeout(int connectTimeout) {
        this.mConnectTimeout = connectTimeout;
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public int getConnectTimeout() {
        return this.mConnectTimeout;
    }

    @Override // com.yolanda.nohttp.ImplClientRequest
    public void setReadTimeout(int readTimeout) {
        this.mReadTimeout = readTimeout;
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public int getReadTimeout() {
        return this.mReadTimeout;
    }

    @Override // com.yolanda.nohttp.ImplClientRequest
    public void setRedirectHandler(RedirectHandler redirectHandler) {
        this.mRedirectHandler = redirectHandler;
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public RedirectHandler getRedirectHandler() {
        return this.mRedirectHandler;
    }

    @Override // com.yolanda.nohttp.ImplClientRequest
    public void setHeader(String key, String value) {
        this.mHeaders.set((Headers) key, value);
    }

    @Override // com.yolanda.nohttp.ImplClientRequest
    public void addHeader(String key, String value) {
        this.mHeaders.add((Headers) key, value);
    }

    @Override // com.yolanda.nohttp.ImplClientRequest
    public void removeHeader(String key) {
        this.mHeaders.remove(key);
    }

    @Override // com.yolanda.nohttp.ImplClientRequest
    public void removeAllHeader() {
        this.mHeaders.clear();
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public Headers headers() {
        return this.mHeaders;
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public String getAcceptLanguage() {
        if (TextUtils.isEmpty(acceptLanguage)) {
            acceptLanguage = createAcceptLanguage();
        }
        return acceptLanguage;
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public long getContentLength() {
        CounterOutputStream outputStream = new CounterOutputStream();
        onWriteRequestBody(new Writer(outputStream));
        return outputStream.get();
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public String getContentType() {
        StringBuilder contentTypeBuild = new StringBuilder();
        if (!doOutPut() || !hasBinary()) {
            contentTypeBuild.append("application/x-www-form-urlencoded; charset=").append(getParamsEncoding());
        } else {
            contentTypeBuild.append("multipart/form-data; boundary=").append(this.boundary);
        }
        return contentTypeBuild.toString();
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public String getUserAgent() {
        if (TextUtils.isEmpty(userAgent)) {
            userAgent = UserAgent.getUserAgent();
        }
        return userAgent;
    }

    @Override // com.yolanda.nohttp.ImplClientRequest
    public void setRequestBody(byte[] requestBody) {
        this.mRequestBody = requestBody;
    }

    @Override // com.yolanda.nohttp.ImplClientRequest
    public void setRequestBody(String requestBody) {
        if (!TextUtils.isEmpty(requestBody)) {
            try {
                this.mRequestBody = requestBody.getBytes(getParamsEncoding());
            } catch (UnsupportedEncodingException e) {
                Logger.e(e);
            }
        }
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public void onPreExecute() {
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public void onWriteRequestBody(Writer writer) {
        if (this.mRequestBody == null && hasBinary()) {
            writeFormStreamData(writer);
        } else if (this.mRequestBody == null) {
            writeCommonStreamData(writer);
        } else {
            writeRequestBody(writer);
        }
    }

    protected void writeFormStreamData(Writer writer) {
        try {
            for (String key : keySet()) {
                Object value = value(key);
                if (value != null && (value instanceof String)) {
                    writeFormString(writer, key, value.toString());
                } else if (value != null && (value instanceof Binary)) {
                    writeFormBinary(writer, key, (Binary) value);
                }
            }
            writer.write(("\r\n" + this.end_boundary + "\r\n").getBytes());
        } catch (IOException e) {
            Logger.e(e);
        }
    }

    private void writeFormString(Writer writer, String key, String value) throws IOException {
        print(writer.isPrint(), key + " = " + value);
        StringBuilder stringFieldBuilder = new StringBuilder(this.start_boundary).append("\r\n");
        stringFieldBuilder.append("Content-Disposition: form-data; name=\"").append(key).append("\"\r\n");
        stringFieldBuilder.append("Content-Type: text/plain; charset=").append(getParamsEncoding()).append("\r\n\r\n");
        writer.write(stringFieldBuilder.toString().getBytes());
        writer.write(value.getBytes(getParamsEncoding()));
        writer.write("\r\n".getBytes());
    }

    private void writeFormBinary(Writer writer, String key, Binary value) throws IOException {
        print(writer.isPrint(), key + " is Binary");
        StringBuilder binaryFieldBuilder = new StringBuilder(this.start_boundary).append("\r\n");
        binaryFieldBuilder.append("Content-Disposition: form-data; name=\"").append(key).append("\"");
        if (!TextUtils.isEmpty(value.getFileName())) {
            binaryFieldBuilder.append("; filename=\"").append(value.getFileName()).append("\"");
        }
        binaryFieldBuilder.append("\r\n");
        binaryFieldBuilder.append("Content-Type: ").append(value.getMimeType()).append("\r\n");
        binaryFieldBuilder.append("Content-Transfer-Encoding: binary\r\n\r\n");
        writer.write(binaryFieldBuilder.toString().getBytes());
        writer.write(value);
        writer.write("\r\n".getBytes());
    }

    protected void writeCommonStreamData(Writer writer) {
        String requestBody = buildCommonParams().toString();
        print(writer.isPrint(), "RequestBody: " + requestBody);
        try {
            if (requestBody.length() > 0) {
                writer.write(requestBody.getBytes());
            }
        } catch (IOException e) {
            Logger.e(e);
        }
    }

    protected void writeRequestBody(Writer writer) {
        try {
            print(writer.isPrint(), "Write RequestBody");
            if (this.mRequestBody.length > 0) {
                writer.write(this.mRequestBody);
            }
        } catch (IOException e) {
            Logger.e(e);
        }
    }

    protected StringBuffer buildCommonParams() {
        StringBuffer paramBuffer = new StringBuffer();
        for (String key : keySet()) {
            Object value = value(key);
            if (value != null && (value instanceof CharSequence)) {
                paramBuffer.append(a.b);
                String paramEncoding = getParamsEncoding();
                try {
                    paramBuffer.append(URLEncoder.encode(key, paramEncoding));
                    paramBuffer.append("=");
                    paramBuffer.append(URLEncoder.encode(value.toString(), paramEncoding));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("Encoding " + getParamsEncoding() + " format is not supported by the system");
                }
            }
        }
        if (paramBuffer.length() > 0) {
            paramBuffer.deleteCharAt(0);
        }
        return paramBuffer;
    }

    @Override // com.yolanda.nohttp.ImplClientRequest
    public void setTag(Object tag) {
        this.mTag = tag;
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public Object getTag() {
        return this.mTag;
    }

    @Override // com.yolanda.nohttp.able.QueueAble
    public void queue(boolean queue) {
        this.queue = queue;
    }

    @Override // com.yolanda.nohttp.able.QueueAble
    public boolean isQueue() {
        return this.queue;
    }

    @Override // com.yolanda.nohttp.able.QueueAble
    public void toggleQueue() {
        this.queue = !this.queue;
    }

    @Override // com.yolanda.nohttp.able.StartAble
    public void start(boolean start) {
        this.isStart = start;
        if (start) {
            this.isFinished = false;
        }
    }

    @Override // com.yolanda.nohttp.able.StartAble
    public boolean isStarted() {
        return this.isStart;
    }

    @Override // com.yolanda.nohttp.able.StartAble
    public void toggleStart() {
        this.isStart = !this.isStart;
    }

    @Override // com.yolanda.nohttp.able.FinishAble
    public void finish(boolean finish) {
        this.isFinished = finish;
        if (finish) {
            this.isStart = false;
        }
    }

    @Override // com.yolanda.nohttp.able.FinishAble
    public boolean isFinished() {
        return this.isFinished;
    }

    @Override // com.yolanda.nohttp.able.FinishAble
    public void toggleFinish() {
        this.isFinished = !this.isFinished;
    }

    @Override // com.yolanda.nohttp.able.CancelAble
    public void cancel(boolean cancel) {
        this.isCanceled = cancel;
        if (cancel) {
            this.isStart = false;
        }
    }

    @Override // com.yolanda.nohttp.able.CancelAble
    public void toggleCancel() {
        this.isCanceled = false;
    }

    @Override // com.yolanda.nohttp.able.CancelAble
    public boolean isCanceled() {
        return this.isCanceled;
    }

    @Override // com.yolanda.nohttp.able.SignCancelAble
    public void setCancelSign(Object sign) {
        this.cancelSign = sign;
    }

    @Override // com.yolanda.nohttp.able.SignCancelAble
    public void cancelBySign(Object sign) {
        if (this.cancelSign == sign) {
            cancel(true);
        }
    }

    public String getParamsEncoding() {
        return "utf-8";
    }

    protected boolean hasBinary() {
        for (String key : keySet()) {
            if (value(key) instanceof Binary) {
                return true;
            }
        }
        return false;
    }

    private void print(boolean isPrint, String msg) {
        if (isPrint) {
            Logger.d(msg);
        }
    }

    public static String createAcceptLanguage() {
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        String country = locale.getCountry();
        StringBuilder acceptLanguageBuilder = new StringBuilder(language);
        if (!TextUtils.isEmpty(country)) {
            acceptLanguageBuilder.append('-').append(country).append(',').append(language).append(";q=0.8");
        }
        return acceptLanguageBuilder.toString();
    }

    public static String createBoundary() {
        StringBuffer sb = new StringBuffer("------------------");
        for (int t = 1; t < 12; t++) {
            long time = System.currentTimeMillis() + t;
            if (time % 3 == 0) {
                sb.append(((char) time) % '\t');
            } else if (time % 3 == 1) {
                sb.append((char) (65 + (time % 26)));
            } else {
                sb.append((char) (97 + (time % 26)));
            }
        }
        return sb.toString();
    }
}
