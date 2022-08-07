package com.yolanda.nohttp;

import com.yolanda.nohttp.error.ServerError;
import com.yolanda.nohttp.error.TimeoutError;
import com.yolanda.nohttp.error.URLError;
import com.yolanda.nohttp.error.UnKnownHostError;
import com.yolanda.nohttp.tools.HeaderParser;
import com.yolanda.nohttp.tools.NetUtil;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.zip.GZIPInputStream;

/* loaded from: classes2.dex */
public final class HttpRestConnection extends BasicConnection implements ImplRestConnection {
    private static HttpRestConnection instance;

    public static HttpRestConnection getInstance() {
        if (instance == null) {
            instance = new HttpRestConnection();
        }
        return instance;
    }

    private HttpRestConnection() {
    }

    @Override // com.yolanda.nohttp.ImplRestConnection
    public HttpResponse requestNetwork(ImplServerRequest request) {
        HttpURLConnection httpConnection;
        if (request == null) {
            throw new IllegalArgumentException("request == null.");
        }
        try {
            Logger.d("--------------Request start--------------");
            Headers responseHeaders = new HttpHeaders();
            byte[] responseBody = null;
            Exception exception = null;
            httpConnection = null;
            String url = request.url();
            try {
                try {
                    try {
                    } catch (MalformedURLException e) {
                        Exception exception2 = new URLError("The url is malformed: " + url + ".");
                        if (exception2 != null) {
                            Logger.e(exception2);
                        }
                        if (0 != 0) {
                            httpConnection.disconnect();
                        }
                        Logger.d("-------Response end-------");
                        exception = exception2;
                    }
                } catch (SocketTimeoutException e2) {
                    Exception exception3 = new TimeoutError("Request time out, the requested url is: " + url + ".");
                    if (exception3 != null) {
                        Logger.e(exception3);
                    }
                    if (0 != 0) {
                        httpConnection.disconnect();
                    }
                    Logger.d("-------Response end-------");
                    exception = exception3;
                }
            } catch (UnknownHostException e3) {
                Exception exception4 = new UnKnownHostError("Hostname can not be resolved: " + url + ".");
                if (exception4 != null) {
                    Logger.e(exception4);
                }
                if (0 != 0) {
                    httpConnection.disconnect();
                }
                Logger.d("-------Response end-------");
                exception = exception4;
            } catch (Exception e4) {
                exception = e4;
                if (exception != null) {
                    Logger.e(exception);
                }
                if (0 != 0) {
                    httpConnection.disconnect();
                }
                Logger.d("-------Response end-------");
            }
            if (!NetUtil.isNetworkAvailable(NoHttp.getContext())) {
                throw new Exception("The network is not available, please check the network. The requested url is: " + url);
            }
            HttpURLConnection httpConnection2 = getHttpConnection(request);
            int responseCode = httpConnection2.getResponseCode();
            Logger.d("-------Response start-------");
            responseHeaders = parseResponseHeaders(new URI(request.url()), responseCode, httpConnection2.getResponseMessage(), httpConnection2.getHeaderFields());
            if (hasResponseBody(request.getRequestMethod(), responseCode)) {
                InputStream inputStream = null;
                try {
                    try {
                        inputStream = httpConnection2.getInputStream();
                        if (HeaderParser.isGzipContent(responseHeaders.getContentEncoding())) {
                            inputStream = new GZIPInputStream(inputStream);
                        }
                        responseBody = readResponseBody(inputStream);
                    } catch (IOException e5) {
                        StringBuilder errorInfo = new StringBuilder("%1$s , the response code is ");
                        errorInfo.append(responseCode).append(", the requested url is: ").append(url);
                        if (responseCode >= 500) {
                            throw new ServerError(String.format(Locale.getDefault(), errorInfo.toString(), "Server internal error."));
                        } else if (responseCode >= 400) {
                            throw new ServerError(String.format(Locale.getDefault(), errorInfo.toString(), "The client request error."));
                        } else if (inputStream != null) {
                            inputStream.close();
                        }
                    }
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            }
            if (0 != 0) {
                Logger.e((Throwable) null);
            }
            if (httpConnection2 != null) {
                httpConnection2.disconnect();
            }
            Logger.d("-------Response end-------");
            Logger.d("--------------Request finish--------------");
            return new HttpResponse(false, responseHeaders, responseBody, exception);
        } catch (Throwable th) {
            if (0 != 0) {
                Logger.e((Throwable) null);
            }
            if (0 != 0) {
                httpConnection.disconnect();
            }
            Logger.d("-------Response end-------");
            throw th;
        }
    }
}
