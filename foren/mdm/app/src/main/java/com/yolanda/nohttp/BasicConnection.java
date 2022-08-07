package com.yolanda.nohttp;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;
import com.yolanda.nohttp.tools.Writer;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes2.dex */
public class BasicConnection {
    protected HttpURLConnection getHttpConnection(ImplServerRequest request) throws IOException {
        HttpURLConnection connection;
        request.onPreExecute();
        String urlStr = request.url();
        Logger.i("Request address: " + urlStr);
        URL url = new URL(urlStr);
        Proxy proxy = request.getProxy();
        if (proxy == null) {
            connection = (HttpURLConnection) url.openConnection();
        } else {
            connection = (HttpURLConnection) url.openConnection(proxy);
        }
        if (connection instanceof HttpsURLConnection) {
            SSLSocketFactory sslSocketFactory = request.getSSLSocketFactory();
            if (sslSocketFactory != null) {
                ((HttpsURLConnection) connection).setSSLSocketFactory(sslSocketFactory);
            }
            HostnameVerifier hostnameVerifier = request.getHostnameVerifier();
            if (hostnameVerifier != null) {
                ((HttpsURLConnection) connection).setHostnameVerifier(hostnameVerifier);
            }
        }
        String requestMethod = request.getRequestMethod().toString();
        Logger.i("Request method: " + requestMethod + ".");
        connection.setRequestMethod(requestMethod);
        connection.setDoInput(true);
        connection.setDoOutput(request.doOutPut());
        connection.setConnectTimeout(request.getConnectTimeout());
        connection.setReadTimeout(request.getReadTimeout());
        connection.setInstanceFollowRedirects(true);
        URI uri = null;
        try {
            uri = url.toURI();
        } catch (URISyntaxException e) {
        }
        setHeaders(uri, connection, request);
        connection.connect();
        writeRequestBody(connection, request);
        return connection;
    }

    @TargetApi(19)
    private void setHeaders(URI uri, HttpURLConnection connection, ImplServerRequest request) {
        Headers headers = request.headers();
        String accept = request.getAccept();
        if (!TextUtils.isEmpty(accept)) {
            headers.set((Headers) Headers.HEAD_KEY_ACCEPT, accept);
        }
        headers.set((Headers) Headers.HEAD_KEY_ACCEPT_ENCODING, Headers.HEAD_VALUE_ACCEPT_ENCODING);
        String acceptLanguage = request.getAcceptLanguage();
        if (!TextUtils.isEmpty(acceptLanguage)) {
            headers.set((Headers) Headers.HEAD_KEY_ACCEPT_LANGUAGE, acceptLanguage);
        }
        if (Build.VERSION.SDK_INT > 19) {
            headers.set((Headers) "Connection", Headers.HEAD_VALUE_CONNECTION_KEEP_ALIVE);
        } else {
            headers.set((Headers) "Connection", Headers.HEAD_VALUE_CONNECTION_CLOSE);
        }
        if (request.doOutPut()) {
            long contentLength = request.getContentLength();
            if (contentLength < 2147483647L && contentLength > 0) {
                connection.setFixedLengthStreamingMode((int) contentLength);
            } else if (Build.VERSION.SDK_INT >= 19) {
                connection.setFixedLengthStreamingMode(contentLength);
            } else {
                connection.setChunkedStreamingMode(262144);
            }
            headers.set((Headers) "Content-Length", Long.toString(contentLength));
        }
        String contentType = request.getContentType();
        if (!TextUtils.isEmpty(contentType)) {
            headers.set((Headers) "Content-Type", contentType);
        }
        if (uri != null) {
            headers.addCookie(uri, NoHttp.getDefaultCookieHandler());
        }
        String userAgent = request.getUserAgent();
        if (!TextUtils.isEmpty(userAgent)) {
            headers.set((Headers) "User-Agent", userAgent);
        }
        for (Map.Entry<String, String> headerEntry : headers.toRequestHeaders().entrySet()) {
            String headKey = headerEntry.getKey();
            String headValue = headerEntry.getValue();
            Logger.i(headKey + ": " + headValue);
            connection.setRequestProperty(headKey, headValue);
        }
    }

    protected Headers parseResponseHeaders(URI uri, int responseCode, String responseMessage, Map<String, List<String>> responseHeaders) {
        try {
            NoHttp.getDefaultCookieHandler().put(uri, responseHeaders);
        } catch (IOException e) {
            Logger.e(e, "Save cookie filed: " + uri.toString() + ".");
        }
        Headers headers = new HttpHeaders();
        headers.set(responseHeaders);
        headers.set((Headers) Headers.HEAD_KEY_RESPONSE_MESSAGE, responseMessage);
        headers.set((Headers) Headers.HEAD_KEY_RESPONSE_CODE, Integer.toString(responseCode));
        for (String headKey : headers.keySet()) {
            for (String headValue : headers.getValues(headKey)) {
                StringBuilder builder = new StringBuilder();
                if (!TextUtils.isEmpty(headKey)) {
                    builder.append(headKey).append(": ");
                }
                if (!TextUtils.isEmpty(headValue)) {
                    builder.append(headValue);
                }
                Logger.i(builder.toString());
            }
        }
        return headers;
    }

    private void writeRequestBody(HttpURLConnection connection, ImplServerRequest request) throws IOException {
        if (request.doOutPut()) {
            Logger.i("-------Send request data start-------");
            BufferedOutputStream outputStream = new BufferedOutputStream(connection.getOutputStream());
            request.onWriteRequestBody(new Writer(outputStream, true));
            outputStream.flush();
            outputStream.close();
            Logger.i("-------Send request data end-------");
        }
    }

    protected byte[] readResponseBody(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        ByteArrayOutputStream content = new ByteArrayOutputStream();
        while (true) {
            int readBytes = bufferedInputStream.read(buffer);
            if (readBytes != -1) {
                content.write(buffer, 0, readBytes);
            } else {
                content.flush();
                content.close();
                return content.toByteArray();
            }
        }
    }

    public static boolean hasResponseBody(RequestMethod requestMethod, int responseCode) {
        return requestMethod != RequestMethod.HEAD && hasResponseBody(responseCode);
    }

    public static boolean hasResponseBody(int responseCode) {
        return ((100 <= responseCode && responseCode < 200) || responseCode == 204 || responseCode == 205 || responseCode == 304) ? false : true;
    }
}
