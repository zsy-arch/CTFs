package com.lidroid.xutils.http;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.callback.DefaultHttpRedirectHandler;
import com.lidroid.xutils.http.callback.HttpRedirectHandler;
import java.io.IOException;
import java.net.UnknownHostException;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

/* loaded from: classes2.dex */
public class SyncHttpHandler {
    private String charset;
    private final AbstractHttpClient client;
    private final HttpContext context;
    private HttpRedirectHandler httpRedirectHandler;
    private String requestMethod;
    private String requestUrl;
    private int retriedTimes = 0;
    private long expiry = HttpCache.getDefaultExpiryTime();

    public void setHttpRedirectHandler(HttpRedirectHandler httpRedirectHandler) {
        this.httpRedirectHandler = httpRedirectHandler;
    }

    public SyncHttpHandler(AbstractHttpClient client, HttpContext context, String charset) {
        this.client = client;
        this.context = context;
        this.charset = charset;
    }

    public void setExpiry(long expiry) {
        this.expiry = expiry;
    }

    public ResponseStream sendRequest(HttpRequestBase request) throws HttpException {
        IOException exception;
        boolean retry;
        String result;
        HttpRequestRetryHandler retryHandler = this.client.getHttpRequestRetryHandler();
        do {
            try {
                this.requestUrl = request.getURI().toString();
                this.requestMethod = request.getMethod();
                if (!HttpUtils.sHttpCache.isEnabled(this.requestMethod) || (result = HttpUtils.sHttpCache.get(this.requestUrl)) == null) {
                    return handleResponse(this.client.execute(request, this.context));
                }
                return new ResponseStream(result);
            } catch (HttpException e) {
                throw e;
            } catch (IOException e2) {
                exception = e2;
                int i = this.retriedTimes + 1;
                this.retriedTimes = i;
                retry = retryHandler.retryRequest(exception, i, this.context);
                continue;
            } catch (NullPointerException e3) {
                exception = new IOException(e3.getMessage());
                exception.initCause(e3);
                int i2 = this.retriedTimes + 1;
                this.retriedTimes = i2;
                retry = retryHandler.retryRequest(exception, i2, this.context);
                continue;
            } catch (UnknownHostException e4) {
                exception = e4;
                int i3 = this.retriedTimes + 1;
                this.retriedTimes = i3;
                retry = retryHandler.retryRequest(exception, i3, this.context);
                continue;
            } catch (Throwable e5) {
                exception = new IOException(e5.getMessage());
                exception.initCause(e5);
                int i4 = this.retriedTimes + 1;
                this.retriedTimes = i4;
                retry = retryHandler.retryRequest(exception, i4, this.context);
                continue;
            }
        } while (retry);
        throw new HttpException(exception);
    }

    private ResponseStream handleResponse(HttpResponse response) throws HttpException, IOException {
        if (response == null) {
            throw new HttpException("response is null");
        }
        StatusLine status = response.getStatusLine();
        int statusCode = status.getStatusCode();
        if (statusCode < 300) {
            ResponseStream responseStream = new ResponseStream(response, this.charset, this.requestUrl, this.expiry);
            responseStream.setRequestMethod(this.requestMethod);
            return responseStream;
        } else if (statusCode == 301 || statusCode == 302) {
            if (this.httpRedirectHandler == null) {
                this.httpRedirectHandler = new DefaultHttpRedirectHandler();
            }
            HttpRequestBase request = this.httpRedirectHandler.getDirectRequest(response);
            if (request != null) {
                return sendRequest(request);
            }
            return null;
        } else if (statusCode == 416) {
            throw new HttpException(statusCode, "maybe the file has downloaded completely");
        } else {
            throw new HttpException(statusCode, status.getReasonPhrase());
        }
    }
}
