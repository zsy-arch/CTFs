package com.parse;

import com.parse.http.ParseHttpResponse;
import com.parse.http.ParseNetworkInterceptor;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ParseDecompressInterceptor implements ParseNetworkInterceptor {
    private static final String CONTENT_ENCODING_HEADER = "Content-Encoding";
    private static final String CONTENT_LENGTH_HEADER = "Content-Length";
    private static final String GZIP_ENCODING = "gzip";

    @Override // com.parse.http.ParseNetworkInterceptor
    public ParseHttpResponse intercept(ParseNetworkInterceptor.Chain chain) throws IOException {
        ParseHttpResponse response = chain.proceed(chain.getRequest());
        if (!GZIP_ENCODING.equalsIgnoreCase(response.getHeader("Content-Encoding"))) {
            return response;
        }
        Map<String, String> newHeaders = new HashMap<>(response.getAllHeaders());
        newHeaders.remove("Content-Encoding");
        newHeaders.put("Content-Length", "-1");
        return new ParseHttpResponse.Builder(response).setTotalSize(-1L).setHeaders(newHeaders).setContent(new GZIPInputStream(response.getContent())).build();
    }
}
