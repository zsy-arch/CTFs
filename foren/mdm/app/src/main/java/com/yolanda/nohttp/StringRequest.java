package com.yolanda.nohttp;

import com.yolanda.nohttp.tools.HeaderParser;
import java.io.UnsupportedEncodingException;

/* loaded from: classes2.dex */
public class StringRequest extends RestRequest<String> {
    public StringRequest(String url) {
        this(url, RequestMethod.GET);
    }

    public StringRequest(String url, RequestMethod requestMethod) {
        super(url, requestMethod);
    }

    @Override // com.yolanda.nohttp.ImplServerRequest
    public String getAccept() {
        return "text/html,application/xhtml+xml,application/xml;*/*;q=0.9";
    }

    @Override // com.yolanda.nohttp.Request
    public String parseResponse(String url, Headers responseHeaders, byte[] responseBody) {
        return parseResponseString(url, responseHeaders, responseBody);
    }

    public static final String parseResponseString(String url, Headers responseHeaders, byte[] responseBody) {
        if (responseBody == null || responseBody.length <= 0) {
            return null;
        }
        try {
            return new String(responseBody, HeaderParser.parseHeadValue(responseHeaders.getContentType(), "charset", ""));
        } catch (UnsupportedEncodingException e) {
            Logger.w("Charset error in ContentType returned by the server：" + responseHeaders.getValue("Content-Type", 0));
            return new String(responseBody);
        }
    }
}
