package com.parse;

import android.net.SSLCertificateSocketFactory;
import android.net.SSLSessionCache;
import android.net.http.AndroidHttpClient;
import com.alipay.sdk.cons.b;
import com.parse.http.ParseHttpBody;
import com.parse.http.ParseHttpRequest;
import com.parse.http.ParseHttpResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ParseApacheHttpClient extends ParseHttpClient<HttpUriRequest, HttpResponse> {
    private static final String CONTENT_ENCODING_HEADER = "Content-Encoding";
    private DefaultHttpClient apacheClient;

    public ParseApacheHttpClient(int socketOperationTimeout, SSLSessionCache sslSessionCache) {
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setStaleCheckingEnabled(params, false);
        HttpConnectionParams.setConnectionTimeout(params, socketOperationTimeout);
        HttpConnectionParams.setSoTimeout(params, socketOperationTimeout);
        HttpConnectionParams.setSocketBufferSize(params, 8192);
        HttpClientParams.setRedirecting(params, false);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(HttpHost.DEFAULT_SCHEME_NAME, PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme(b.a, SSLCertificateSocketFactory.getHttpSocketFactory(socketOperationTimeout, sslSessionCache), 443));
        String maxConnectionsStr = System.getProperty("http.maxConnections");
        if (maxConnectionsStr != null) {
            int maxConnections = Integer.parseInt(maxConnectionsStr);
            ConnManagerParams.setMaxConnectionsPerRoute(params, new ConnPerRouteBean(maxConnections));
            ConnManagerParams.setMaxTotalConnections(params, maxConnections);
        }
        String host = System.getProperty("http.proxyHost");
        String portString = System.getProperty("http.proxyPort");
        if (!(host == null || host.length() == 0 || portString == null || portString.length() == 0)) {
            params.setParameter(ConnRoutePNames.DEFAULT_PROXY, new HttpHost(host, Integer.parseInt(portString), HttpHost.DEFAULT_SCHEME_NAME));
        }
        this.apacheClient = new DefaultHttpClient(new ThreadSafeClientConnManager(params, schemeRegistry), params);
        this.apacheClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
    }

    @Override // com.parse.ParseHttpClient
    ParseHttpResponse executeInternal(ParseHttpRequest parseRequest) throws IOException {
        return getResponse(this.apacheClient.execute(getRequest(parseRequest)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ParseHttpResponse getResponse(HttpResponse apacheResponse) throws IOException {
        if (apacheResponse == null) {
            throw new IllegalArgumentException("HttpResponse passed to getResponse should not be null.");
        }
        int statusCode = apacheResponse.getStatusLine().getStatusCode();
        InputStream content = disableHttpLibraryAutoDecompress() ? apacheResponse.getEntity().getContent() : AndroidHttpClient.getUngzippedContent(apacheResponse.getEntity());
        int totalSize = -1;
        Header[] contentLengthHeader = apacheResponse.getHeaders("Content-Length");
        if (contentLengthHeader.length > 0) {
            totalSize = Integer.parseInt(contentLengthHeader[0].getValue());
        }
        String reasonPhrase = apacheResponse.getStatusLine().getReasonPhrase();
        Map<String, String> headers = new HashMap<>();
        Header[] arr$ = apacheResponse.getAllHeaders();
        for (Header header : arr$) {
            headers.put(header.getName(), header.getValue());
        }
        if (!disableHttpLibraryAutoDecompress()) {
            headers.remove("Content-Encoding");
        }
        String contentType = null;
        HttpEntity entity = apacheResponse.getEntity();
        if (!(entity == null || entity.getContentType() == null)) {
            contentType = entity.getContentType().getValue();
        }
        return new ParseHttpResponse.Builder().setStatusCode(statusCode).setContent(content).setTotalSize(totalSize).setReasonPhrase(reasonPhrase).setHeaders(headers).setContentType(contentType).build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.parse.ParseHttpClient
    public HttpUriRequest getRequest(ParseHttpRequest parseRequest) throws IOException {
        HttpUriRequest apacheRequest;
        if (parseRequest == null) {
            throw new IllegalArgumentException("ParseHttpRequest passed to getApacheRequest should not be null.");
        }
        ParseHttpRequest.Method method = parseRequest.getMethod();
        String url = parseRequest.getUrl();
        switch (method) {
            case GET:
                apacheRequest = new HttpGet(url);
                break;
            case DELETE:
                apacheRequest = new HttpDelete(url);
                break;
            case POST:
                apacheRequest = new HttpPost(url);
                break;
            case PUT:
                apacheRequest = new HttpPut(url);
                break;
            default:
                throw new IllegalStateException("Unsupported http method " + method.toString());
        }
        for (Map.Entry<String, String> entry : parseRequest.getAllHeaders().entrySet()) {
            apacheRequest.setHeader(entry.getKey(), entry.getValue());
        }
        AndroidHttpClient.modifyRequestToAcceptGzipResponse(apacheRequest);
        ParseHttpBody body = parseRequest.getBody();
        switch (method) {
            case POST:
                ((HttpPost) apacheRequest).setEntity(new ParseApacheHttpEntity(body));
                break;
            case PUT:
                ((HttpPut) apacheRequest).setEntity(new ParseApacheHttpEntity(body));
                break;
        }
        return apacheRequest;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ParseApacheHttpEntity extends InputStreamEntity {
        private ParseHttpBody parseBody;

        public ParseApacheHttpEntity(ParseHttpBody parseBody) throws IOException {
            super(parseBody.getContent(), parseBody.getContentLength());
            super.setContentType(parseBody.getContentType());
            this.parseBody = parseBody;
        }

        @Override // org.apache.http.entity.InputStreamEntity, org.apache.http.HttpEntity
        public void writeTo(OutputStream out) throws IOException {
            this.parseBody.writeTo(out);
        }
    }
}
