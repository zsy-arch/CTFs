package com.parse;

import android.net.SSLCertificateSocketFactory;
import android.net.SSLSessionCache;
import bolts.Capture;
import com.parse.http.ParseHttpBody;
import com.parse.http.ParseHttpRequest;
import com.parse.http.ParseHttpResponse;
import com.parse.http.ParseNetworkInterceptor;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ParseOkHttpClient extends ParseHttpClient<Request, Response> {
    private static final String OKHTTP_DELETE = "DELETE";
    private static final String OKHTTP_GET = "GET";
    private static final String OKHTTP_POST = "POST";
    private static final String OKHTTP_PUT = "PUT";
    private OkHttpClient okHttpClient = new OkHttpClient();

    public ParseOkHttpClient(int socketOperationTimeout, SSLSessionCache sslSessionCache) {
        this.okHttpClient.setConnectTimeout(socketOperationTimeout, TimeUnit.MILLISECONDS);
        this.okHttpClient.setReadTimeout(socketOperationTimeout, TimeUnit.MILLISECONDS);
        this.okHttpClient.setFollowRedirects(false);
        this.okHttpClient.setSslSocketFactory(SSLCertificateSocketFactory.getDefault(socketOperationTimeout, sslSessionCache));
    }

    @Override // com.parse.ParseHttpClient
    ParseHttpResponse executeInternal(ParseHttpRequest parseRequest) throws IOException {
        return getResponse(this.okHttpClient.newCall(getRequest(parseRequest)).execute());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ParseHttpResponse getResponse(Response okHttpResponse) throws IOException {
        int statusCode = okHttpResponse.code();
        InputStream content = okHttpResponse.body().byteStream();
        int totalSize = (int) okHttpResponse.body().contentLength();
        String reasonPhrase = okHttpResponse.message();
        Map<String, String> headers = new HashMap<>();
        for (String name : okHttpResponse.headers().names()) {
            headers.put(name, okHttpResponse.header(name));
        }
        String contentType = null;
        ResponseBody body = okHttpResponse.body();
        if (!(body == null || body.contentType() == null)) {
            contentType = body.contentType().toString();
        }
        return new ParseHttpResponse.Builder().setStatusCode(statusCode).setContent(content).setTotalSize(totalSize).setReasonPhrase(reasonPhrase).setHeaders(headers).setContentType(contentType).build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.parse.ParseHttpClient
    public Request getRequest(ParseHttpRequest parseRequest) throws IOException {
        Request.Builder okHttpRequestBuilder = new Request.Builder();
        ParseHttpRequest.Method method = parseRequest.getMethod();
        switch (method) {
            case GET:
                okHttpRequestBuilder.get();
                break;
            case DELETE:
                okHttpRequestBuilder.delete();
                break;
            case POST:
            case PUT:
                break;
            default:
                throw new IllegalStateException("Unsupported http method " + method.toString());
        }
        okHttpRequestBuilder.url(parseRequest.getUrl());
        Headers.Builder okHttpHeadersBuilder = new Headers.Builder();
        for (Map.Entry<String, String> entry : parseRequest.getAllHeaders().entrySet()) {
            okHttpHeadersBuilder.add(entry.getKey(), entry.getValue());
        }
        okHttpRequestBuilder.headers(okHttpHeadersBuilder.build());
        ParseHttpBody parseBody = parseRequest.getBody();
        ParseOkHttpRequestBody okHttpRequestBody = null;
        if (parseBody instanceof ParseByteArrayHttpBody) {
            okHttpRequestBody = new ParseOkHttpRequestBody(parseBody);
        }
        switch (method) {
            case POST:
                okHttpRequestBuilder.post(okHttpRequestBody);
                break;
            case PUT:
                okHttpRequestBuilder.put(okHttpRequestBody);
                break;
        }
        return okHttpRequestBuilder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ParseHttpRequest getParseHttpRequest(Request okHttpRequest) {
        ParseHttpRequest.Builder parseRequestBuilder = new ParseHttpRequest.Builder();
        String method = okHttpRequest.method();
        char c = 65535;
        switch (method.hashCode()) {
            case 70454:
                if (method.equals("GET")) {
                    c = 0;
                    break;
                }
                break;
            case 79599:
                if (method.equals("PUT")) {
                    c = 3;
                    break;
                }
                break;
            case 2461856:
                if (method.equals("POST")) {
                    c = 2;
                    break;
                }
                break;
            case 2012838315:
                if (method.equals("DELETE")) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                parseRequestBuilder.setMethod(ParseHttpRequest.Method.GET);
                break;
            case 1:
                parseRequestBuilder.setMethod(ParseHttpRequest.Method.DELETE);
                break;
            case 2:
                parseRequestBuilder.setMethod(ParseHttpRequest.Method.POST);
                break;
            case 3:
                parseRequestBuilder.setMethod(ParseHttpRequest.Method.PUT);
                break;
            default:
                throw new IllegalArgumentException("Invalid http method " + okHttpRequest.method());
        }
        parseRequestBuilder.setUrl(okHttpRequest.urlString());
        for (Map.Entry<String, List<String>> entry : okHttpRequest.headers().toMultimap().entrySet()) {
            parseRequestBuilder.addHeader(entry.getKey(), entry.getValue().get(0));
        }
        ParseOkHttpRequestBody okHttpBody = okHttpRequest.body();
        if (okHttpBody != null) {
            parseRequestBuilder.setBody(okHttpBody.getParseHttpBody());
        }
        return parseRequestBuilder.build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.parse.ParseHttpClient
    public void addExternalInterceptor(final ParseNetworkInterceptor parseNetworkInterceptor) {
        this.okHttpClient.networkInterceptors().add(new Interceptor() { // from class: com.parse.ParseOkHttpClient.1
            public Response intercept(final Interceptor.Chain okHttpChain) throws IOException {
                final ParseHttpRequest parseRequest = ParseOkHttpClient.this.getParseHttpRequest(okHttpChain.request());
                final Capture<Response> okHttpResponseCapture = new Capture<>();
                final ParseHttpResponse parseResponse = parseNetworkInterceptor.intercept(new ParseNetworkInterceptor.Chain() { // from class: com.parse.ParseOkHttpClient.1.1
                    @Override // com.parse.http.ParseNetworkInterceptor.Chain
                    public ParseHttpRequest getRequest() {
                        return parseRequest;
                    }

                    @Override // com.parse.http.ParseNetworkInterceptor.Chain
                    public ParseHttpResponse proceed(ParseHttpRequest parseRequest2) throws IOException {
                        Response okHttpResponse = okHttpChain.proceed(ParseOkHttpClient.this.getRequest(parseRequest2));
                        okHttpResponseCapture.set(okHttpResponse);
                        return ParseOkHttpClient.this.getResponse(okHttpResponse);
                    }
                });
                Response.Builder newOkHttpResponseBuilder = okHttpResponseCapture.get().newBuilder();
                newOkHttpResponseBuilder.code(parseResponse.getStatusCode()).message(parseResponse.getReasonPhrase());
                if (parseResponse.getAllHeaders() != null) {
                    for (Map.Entry<String, String> entry : parseResponse.getAllHeaders().entrySet()) {
                        newOkHttpResponseBuilder.header(entry.getKey(), entry.getValue());
                    }
                }
                newOkHttpResponseBuilder.body(new ResponseBody() { // from class: com.parse.ParseOkHttpClient.1.2
                    public MediaType contentType() {
                        if (parseResponse.getContentType() == null) {
                            return null;
                        }
                        return MediaType.parse(parseResponse.getContentType());
                    }

                    public long contentLength() throws IOException {
                        return parseResponse.getTotalSize();
                    }

                    public BufferedSource source() throws IOException {
                        if (parseResponse.getContent() == null) {
                            return null;
                        }
                        return Okio.buffer(Okio.source(parseResponse.getContent()));
                    }
                });
                return newOkHttpResponseBuilder.build();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ParseOkHttpRequestBody extends RequestBody {
        private ParseHttpBody parseBody;

        public ParseOkHttpRequestBody(ParseHttpBody parseBody) {
            this.parseBody = parseBody;
        }

        public long contentLength() throws IOException {
            return this.parseBody.getContentLength();
        }

        public MediaType contentType() {
            if (this.parseBody.getContentType() == null) {
                return null;
            }
            return MediaType.parse(this.parseBody.getContentType());
        }

        public void writeTo(BufferedSink bufferedSink) throws IOException {
            this.parseBody.writeTo(bufferedSink.outputStream());
        }

        public ParseHttpBody getParseHttpBody() {
            return this.parseBody;
        }
    }
}
