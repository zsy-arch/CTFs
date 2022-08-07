package com.alipay.android.phone.mrpc.core;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.alipay.sdk.util.h;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

/* loaded from: classes.dex */
public class HttpWorker implements Callable<Response> {
    private static final String TAG = "HttpWorker";
    private static final HttpRequestRetryHandler sHttpRequestRetryHandler = new ZHttpRequestRetryHandler();
    protected Context mContext;
    private CookieManager mCookieManager;
    private HttpHost mHttpHost;
    protected HttpManager mHttpManager;
    private HttpUriRequest mHttpRequest;
    private String mOperationType;
    private AbstractHttpEntity mPostDataEntity;
    protected HttpUrlRequest mRequest;
    private URL mTargetUrl;
    String mUrl;
    private HttpContext mLocalContext = new BasicHttpContext();
    private CookieStore mCookieStore = new BasicCookieStore();
    private int mRetryTimes = 0;
    private boolean hasIfNoneMatchInRequest = false;
    private boolean hasEtagInResponse = false;
    private String etagCacheKey = null;

    public HttpWorker(HttpManager httpManager, HttpUrlRequest httpUrlRequest) {
        this.mHttpManager = httpManager;
        this.mContext = this.mHttpManager.mContext;
        this.mRequest = httpUrlRequest;
    }

    private void abortRequest() {
        if (this.mHttpRequest != null) {
            this.mHttpRequest.abort();
        }
    }

    private void addRequestHeaders() {
        ArrayList<Header> headers = getHeaders();
        if (headers != null && !headers.isEmpty()) {
            Iterator<Header> it = headers.iterator();
            while (it.hasNext()) {
                getHttpUriRequest().addHeader(it.next());
            }
        }
        AndroidHttpClient.modifyRequestToAcceptGzipResponse(getHttpUriRequest());
        AndroidHttpClient.modifyRequestToKeepAlive(getHttpUriRequest());
        getHttpUriRequest().addHeader("cookie", getCookieManager().getCookie(this.mRequest.getUrl()));
    }

    private HttpResponse executeHttpClientRequest() {
        new StringBuilder("By Http/Https to request. operationType=").append(getOperationType()).append(" url=").append(this.mHttpRequest.getURI().toString());
        getHttpClient().getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, getProxy());
        HttpHost httpHost = getHttpHost();
        if (getTargetPort() == 80) {
            httpHost = new HttpHost(getTargetURL().getHost());
        }
        return getHttpClient().execute(httpHost, this.mHttpRequest, this.mLocalContext);
    }

    private HttpResponse executeRequest() {
        return executeHttpClientRequest();
    }

    private CookieManager getCookieManager() {
        if (this.mCookieManager != null) {
            return this.mCookieManager;
        }
        this.mCookieManager = CookieManager.getInstance();
        return this.mCookieManager;
    }

    private AndroidHttpClient getHttpClient() {
        return this.mHttpManager.getHttpClient();
    }

    private HttpHost getHttpHost() {
        if (this.mHttpHost != null) {
            return this.mHttpHost;
        }
        URL targetURL = getTargetURL();
        this.mHttpHost = new HttpHost(targetURL.getHost(), getTargetPort(), targetURL.getProtocol());
        return this.mHttpHost;
    }

    private HttpUriRequest getHttpUriRequest() {
        if (this.mHttpRequest != null) {
            return this.mHttpRequest;
        }
        AbstractHttpEntity postData = getPostData();
        if (postData != null) {
            HttpPost httpPost = new HttpPost(getUri());
            httpPost.setEntity(postData);
            this.mHttpRequest = httpPost;
        } else {
            this.mHttpRequest = new HttpGet(getUri());
        }
        return this.mHttpRequest;
    }

    private String getOperationType() {
        if (!TextUtils.isEmpty(this.mOperationType)) {
            return this.mOperationType;
        }
        this.mOperationType = this.mRequest.getTag("operationType");
        return this.mOperationType;
    }

    private HttpHost getProxy() {
        HttpHost proxy = NetworkUtils.getProxy(this.mContext);
        if (proxy == null || !TextUtils.equals(proxy.getHostName(), "127.0.0.1") || proxy.getPort() != 8087) {
            return proxy;
        }
        return null;
    }

    private int getTargetPort() {
        URL targetURL = getTargetURL();
        return targetURL.getPort() == -1 ? targetURL.getDefaultPort() : targetURL.getPort();
    }

    private URL getTargetURL() {
        if (this.mTargetUrl != null) {
            return this.mTargetUrl;
        }
        this.mTargetUrl = new URL(this.mRequest.getUrl());
        return this.mTargetUrl;
    }

    private TransportCallback getTransportCallback() {
        return this.mRequest.getCallback();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Response call() {
        try {
            try {
                if (!NetworkUtils.isNetworkAvailable(this.mContext)) {
                    throw new HttpException(1, "The network is not available");
                }
                if (getTransportCallback() != null) {
                    getTransportCallback().onPreExecute(this.mRequest);
                }
                addRequestHeaders();
                this.mLocalContext.setAttribute(ClientContext.COOKIE_STORE, this.mCookieStore);
                getHttpClient().setHttpRequestRetryHandler(sHttpRequestRetryHandler);
                long currentTimeMillis = System.currentTimeMillis();
                HttpResponse executeRequest = executeRequest();
                this.mHttpManager.addConnectTime(System.currentTimeMillis() - currentTimeMillis);
                List<Cookie> cookies = this.mCookieStore.getCookies();
                if (this.mRequest.isResetCookie()) {
                    getCookieManager().removeAllCookie();
                }
                if (!cookies.isEmpty()) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getDomain() != null) {
                            getCookieManager().setCookie(this.mRequest.getUrl(), cookie.getName() + "=" + cookie.getValue() + "; domain=" + cookie.getDomain() + (cookie.isSecure() ? "; Secure" : ""));
                            CookieSyncManager.getInstance().sync();
                        }
                    }
                }
                Response processResponse = processResponse(executeRequest, this.mRequest);
                long j = -1;
                if (!(processResponse == null || processResponse.getResData() == null)) {
                    j = processResponse.getResData().length;
                }
                if (j == -1 && (processResponse instanceof HttpUrlResponse)) {
                    try {
                        Long.parseLong(((HttpUrlResponse) processResponse).getHeader().getHead("Content-Length"));
                    } catch (Exception e) {
                    }
                }
                String url = this.mRequest.getUrl();
                if (url == null || TextUtils.isEmpty(getOperationType())) {
                    return processResponse;
                }
                new StringBuilder().append(url).append("#").append(getOperationType());
                return processResponse;
            } catch (Exception e2) {
                abortRequest();
                if (getTransportCallback() != null) {
                    getTransportCallback().onFailed(this.mRequest, 0, String.valueOf(e2));
                }
                throw new HttpException(0, String.valueOf(e2));
            }
        } catch (HttpException e3) {
            abortRequest();
            if (getTransportCallback() != null) {
                getTransportCallback().onFailed(this.mRequest, e3.getCode(), e3.getMsg());
            }
            new StringBuilder().append(e3);
            throw e3;
        } catch (NullPointerException e4) {
            abortRequest();
            if (this.mRetryTimes <= 0) {
                this.mRetryTimes++;
                return call();
            }
            new StringBuilder().append(e4);
            throw new HttpException(0, String.valueOf(e4));
        } catch (SocketTimeoutException e5) {
            abortRequest();
            if (getTransportCallback() != null) {
                getTransportCallback().onFailed(this.mRequest, 4, String.valueOf(e5));
            }
            new StringBuilder().append(e5);
            throw new HttpException(4, String.valueOf(e5));
        } catch (IOException e6) {
            abortRequest();
            if (getTransportCallback() != null) {
                getTransportCallback().onFailed(this.mRequest, 6, String.valueOf(e6));
            }
            new StringBuilder().append(e6);
            throw new HttpException(6, String.valueOf(e6));
        } catch (URISyntaxException e7) {
            throw new RuntimeException("Url parser error!", e7.getCause());
        } catch (UnknownHostException e8) {
            abortRequest();
            if (getTransportCallback() != null) {
                getTransportCallback().onFailed(this.mRequest, 9, String.valueOf(e8));
            }
            new StringBuilder().append(e8);
            throw new HttpException(9, String.valueOf(e8));
        } catch (SSLHandshakeException e9) {
            abortRequest();
            if (getTransportCallback() != null) {
                getTransportCallback().onFailed(this.mRequest, 2, String.valueOf(e9));
            }
            new StringBuilder().append(e9);
            throw new HttpException(2, String.valueOf(e9));
        } catch (SSLPeerUnverifiedException e10) {
            abortRequest();
            if (getTransportCallback() != null) {
                getTransportCallback().onFailed(this.mRequest, 2, String.valueOf(e10));
            }
            new StringBuilder().append(e10);
            throw new HttpException(2, String.valueOf(e10));
        } catch (SSLException e11) {
            abortRequest();
            if (getTransportCallback() != null) {
                getTransportCallback().onFailed(this.mRequest, 6, String.valueOf(e11));
            }
            new StringBuilder().append(e11);
            throw new HttpException(6, String.valueOf(e11));
        } catch (NoHttpResponseException e12) {
            abortRequest();
            if (getTransportCallback() != null) {
                getTransportCallback().onFailed(this.mRequest, 5, String.valueOf(e12));
            }
            new StringBuilder().append(e12);
            throw new HttpException(5, String.valueOf(e12));
        } catch (ConnectionPoolTimeoutException e13) {
            abortRequest();
            if (getTransportCallback() != null) {
                getTransportCallback().onFailed(this.mRequest, 3, String.valueOf(e13));
            }
            new StringBuilder().append(e13);
            throw new HttpException(3, String.valueOf(e13));
        } catch (ConnectTimeoutException e14) {
            abortRequest();
            if (getTransportCallback() != null) {
                getTransportCallback().onFailed(this.mRequest, 3, String.valueOf(e14));
            }
            new StringBuilder().append(e14);
            throw new HttpException(3, String.valueOf(e14));
        } catch (HttpHostConnectException e15) {
            abortRequest();
            if (getTransportCallback() != null) {
                getTransportCallback().onFailed(this.mRequest, 8, String.valueOf(e15));
            }
            throw new HttpException(8, String.valueOf(e15));
        }
    }

    protected void fillResponse(HttpUrlResponse httpUrlResponse, HttpResponse httpResponse) {
        String str;
        String str2 = null;
        long period = getPeriod(httpResponse);
        Header contentType = httpResponse.getEntity().getContentType();
        if (contentType != null) {
            HashMap<String, String> contentType2 = getContentType(contentType.getValue());
            str = contentType2.get("charset");
            str2 = contentType2.get("Content-Type");
        } else {
            str = null;
        }
        httpUrlResponse.setContentType(str2);
        httpUrlResponse.setCharset(str);
        httpUrlResponse.setCreateTime(System.currentTimeMillis());
        httpUrlResponse.setPeriod(period);
    }

    protected HashMap<String, String> getContentType(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        String[] split = str.split(h.b);
        for (String str2 : split) {
            String[] split2 = str2.indexOf(61) == -1 ? new String[]{"Content-Type", str2} : str2.split("=");
            hashMap.put(split2[0], split2[1]);
        }
        return hashMap;
    }

    protected ArrayList<Header> getHeaders() {
        return this.mRequest.getHeaders();
    }

    protected long getPeriod(HttpResponse httpResponse) {
        Header firstHeader = httpResponse.getFirstHeader("Cache-Control");
        if (firstHeader != null) {
            String[] split = firstHeader.getValue().split("=");
            if (split.length >= 2) {
                try {
                    return parserMaxage(split);
                } catch (NumberFormatException e) {
                }
            }
        }
        Header firstHeader2 = httpResponse.getFirstHeader("Expires");
        if (firstHeader2 != null) {
            return AndroidHttpClient.parseDate(firstHeader2.getValue()) - System.currentTimeMillis();
        }
        return 0L;
    }

    protected AbstractHttpEntity getPostData() {
        if (this.mPostDataEntity != null) {
            return this.mPostDataEntity;
        }
        byte[] reqData = this.mRequest.getReqData();
        String tag = this.mRequest.getTag("gzip");
        if (reqData != null) {
            if (TextUtils.equals(tag, "true")) {
                this.mPostDataEntity = AndroidHttpClient.getCompressedEntity(reqData, null);
            } else {
                this.mPostDataEntity = new ByteArrayEntity(reqData);
            }
            this.mPostDataEntity.setContentType(this.mRequest.getContentType());
        }
        return this.mPostDataEntity;
    }

    public HttpUrlRequest getRequest() {
        return this.mRequest;
    }

    protected URI getUri() {
        String url = this.mRequest.getUrl();
        if (this.mUrl != null) {
            url = this.mUrl;
        }
        if (url != null) {
            return new URI(url);
        }
        throw new RuntimeException("url should not be null");
    }

    protected Response handleResponse(HttpResponse httpResponse, int i, String str) {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        HttpUrlResponse httpUrlResponse = null;
        new StringBuilder("开始handle，handleResponse-1,").append(Thread.currentThread().getId());
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null && httpResponse.getStatusLine().getStatusCode() == 200) {
            new StringBuilder("200，开始处理，handleResponse-2,threadid = ").append(Thread.currentThread().getId());
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream = null;
            }
            try {
                long currentTimeMillis = System.currentTimeMillis();
                writeData(entity, 0L, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                this.hasEtagInResponse = false;
                this.mHttpManager.addSocketTime(System.currentTimeMillis() - currentTimeMillis);
                this.mHttpManager.addDataSize(byteArray.length);
                new StringBuilder("res:").append(byteArray.length);
                httpUrlResponse = new HttpUrlResponse(handleResponseHeader(httpResponse), i, str, byteArray);
                fillResponse(httpUrlResponse, httpResponse);
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException("ArrayOutputStream close error!", e.getCause());
                }
            } catch (Throwable th3) {
                th = th3;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e2) {
                        throw new RuntimeException("ArrayOutputStream close error!", e2.getCause());
                    }
                }
                throw th;
            }
        } else if (entity == null) {
            httpResponse.getStatusLine().getStatusCode();
        }
        return httpUrlResponse;
    }

    protected HttpUrlHeader handleResponseHeader(HttpResponse httpResponse) {
        HttpUrlHeader httpUrlHeader = new HttpUrlHeader();
        Header[] allHeaders = httpResponse.getAllHeaders();
        for (Header header : allHeaders) {
            httpUrlHeader.setHead(header.getName(), header.getValue());
        }
        return httpUrlHeader;
    }

    protected long parserMaxage(String[] strArr) {
        for (int i = 0; i < strArr.length; i++) {
            if (ClientCookie.MAX_AGE_ATTR.equalsIgnoreCase(strArr[i]) && strArr[i + 1] != null) {
                try {
                    return Long.parseLong(strArr[i + 1]);
                } catch (Exception e) {
                }
            }
        }
        return 0L;
    }

    public Response processResponse(HttpResponse httpResponse, HttpUrlRequest httpUrlRequest) {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        String reasonPhrase = httpResponse.getStatusLine().getReasonPhrase();
        if (statusCode == 200 || willHandleOtherCode(statusCode, reasonPhrase)) {
            return handleResponse(httpResponse, statusCode, reasonPhrase);
        }
        throw new HttpException(Integer.valueOf(httpResponse.getStatusLine().getStatusCode()), httpResponse.getStatusLine().getReasonPhrase());
    }

    protected boolean willHandleOtherCode(int i, String str) {
        return i == 304;
    }

    protected void writeData(HttpEntity httpEntity, long j, OutputStream outputStream) {
        InputStream ungzippedContent;
        if (outputStream == null) {
            httpEntity.consumeContent();
            throw new IllegalArgumentException("Output stream may not be null");
        }
        try {
            ungzippedContent = AndroidHttpClient.getUngzippedContent(httpEntity);
            long contentLength = httpEntity.getContentLength();
            try {
                byte[] bArr = new byte[2048];
                while (true) {
                    int read = ungzippedContent.read(bArr);
                    if (read == -1 || this.mRequest.isCanceled()) {
                        break;
                    }
                    outputStream.write(bArr, 0, read);
                    j += read;
                    if (getTransportCallback() != null && contentLength > 0) {
                        getTransportCallback().onProgressUpdate(this.mRequest, j / contentLength);
                    }
                }
                outputStream.flush();
            } catch (Exception e) {
                e.getCause();
                throw new IOException("HttpWorker Request Error!" + e.getLocalizedMessage());
            }
        } finally {
            IOUtil.closeStream(ungzippedContent);
        }
    }
}
