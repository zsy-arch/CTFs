package com.parse;

import android.net.SSLCertificateSocketFactory;
import android.net.SSLSessionCache;
import com.parse.http.ParseHttpBody;
import com.parse.http.ParseHttpRequest;
import com.parse.http.ParseHttpResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ParseURLConnectionHttpClient extends ParseHttpClient<HttpURLConnection, HttpURLConnection> {
    private static final String ACCEPT_ENCODING_HEADER = "Accept-encoding";
    private static final String CONTENT_LENGTH_HEADER = "Content-Length";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String GZIP_ENCODING = "gzip";
    private int socketOperationTimeout;

    public ParseURLConnectionHttpClient(int socketOperationTimeout, SSLSessionCache sslSessionCache) {
        this.socketOperationTimeout = socketOperationTimeout;
        HttpsURLConnection.setDefaultSSLSocketFactory(SSLCertificateSocketFactory.getDefault(socketOperationTimeout, sslSessionCache));
    }

    @Override // com.parse.ParseHttpClient
    ParseHttpResponse executeInternal(ParseHttpRequest parseRequest) throws IOException {
        HttpURLConnection connection = getRequest(parseRequest);
        ParseHttpBody body = parseRequest.getBody();
        if (body != null) {
            OutputStream outputStream = connection.getOutputStream();
            body.writeTo(outputStream);
            outputStream.flush();
            outputStream.close();
        }
        return getResponse(connection);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.parse.ParseHttpClient
    public HttpURLConnection getRequest(ParseHttpRequest parseRequest) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(parseRequest.getUrl()).openConnection();
        connection.setRequestMethod(parseRequest.getMethod().toString());
        connection.setConnectTimeout(this.socketOperationTimeout);
        connection.setReadTimeout(this.socketOperationTimeout);
        connection.setDoInput(true);
        connection.setInstanceFollowRedirects(false);
        for (Map.Entry<String, String> entry : parseRequest.getAllHeaders().entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }
        if (disableHttpLibraryAutoDecompress()) {
            connection.setRequestProperty(ACCEPT_ENCODING_HEADER, GZIP_ENCODING);
        }
        ParseHttpBody body = parseRequest.getBody();
        if (body != null) {
            connection.setRequestProperty("Content-Length", String.valueOf(body.getContentLength()));
            connection.setRequestProperty("Content-Type", body.getContentType());
            connection.setFixedLengthStreamingMode(body.getContentLength());
            connection.setDoOutput(true);
        }
        return connection;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ParseHttpResponse getResponse(HttpURLConnection connection) throws IOException {
        InputStream content;
        int statusCode = connection.getResponseCode();
        if (statusCode < 400) {
            content = connection.getInputStream();
        } else {
            content = connection.getErrorStream();
        }
        int totalSize = connection.getContentLength();
        String reasonPhrase = connection.getResponseMessage();
        Map<String, String> headers = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : connection.getHeaderFields().entrySet()) {
            if (entry.getKey() != null && entry.getValue().size() > 0) {
                headers.put(entry.getKey(), entry.getValue() == null ? "" : entry.getValue().get(0));
            }
        }
        return new ParseHttpResponse.Builder().setStatusCode(statusCode).setContent(content).setTotalSize(totalSize).setReasonPhrase(reasonPhrase).setHeaders(headers).setContentType(connection.getContentType()).build();
    }
}
